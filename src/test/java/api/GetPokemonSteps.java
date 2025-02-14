package api;

import io.cucumber.java.en.Given;
import io.restassured.response.Response;
import utils.RequestSpecFactory;
import utils.ScenarioContext;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;
import java.util.Random;

public class GetPokemonSteps {
    private final ScenarioContext scenarioContext;
    private Response response;

    public GetPokemonSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I request a list of all Pokemon")
    public void i_request_all_pokemon() throws IOException {
        response = given()
            .spec(RequestSpecFactory.getRequestSpecification())
            .when()
            .get("/pokemon");

        scenarioContext.setResponse(response);
        assertEquals(200, response.statusCode(), "Expected status code 200");

        List<String> pokemonList = response.jsonPath().getList("results.name");
        assertNotNull(pokemonList, "Pokemon list should not be null");
        assertFalse(pokemonList.isEmpty(), "Pokemon list should not be empty");

        String randomPokemon = pokemonList.get(new Random().nextInt(pokemonList.size()));
        scenarioContext.set("pokemonName", randomPokemon);
    }

    @Given("I request details for the stored Pokemon")
    public void i_request_details_for_stored_pokemon() throws IOException {
        String pokemonName = scenarioContext.get("pokemonName", String.class);
        assertNotNull(pokemonName, "Pokemon name should not be null");

        response = given()
            .spec(RequestSpecFactory.getRequestSpecification())
            .when()
            .get("/pokemon/" + pokemonName);

        scenarioContext.setResponse(response);
        assertEquals(200, response.statusCode(), "Expected status code 200");

        List<String> abilities = response.jsonPath().getList("abilities.ability.name");
        assertNotNull(abilities, "Abilities list should not be null");
        assertFalse(abilities.isEmpty(), "Abilities list should not be empty");

        String abilityName = abilities.get(0);
        scenarioContext.set("abilityName", abilityName);
    }
}
