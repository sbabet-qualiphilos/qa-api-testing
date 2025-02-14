package api;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import utils.RequestSpecFactory;
import utils.ScenarioContext;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.util.List;

public class GetAbilitySteps {
    private final ScenarioContext scenarioContext;
    private Response response;

    public GetAbilitySteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I request details for the stored Pokemon ability")
    public void i_request_details_for_pokemon_ability() throws IOException {
        String abilityName = scenarioContext.get("abilityName", String.class);
        assertNotNull(abilityName, "Ability name should not be null");

        response = given()
            .spec(RequestSpecFactory.getRequestSpecification())
            .when()
            .get("/ability/" + abilityName);

        scenarioContext.setResponse(response);
        assertEquals(200, response.statusCode(), "Expected status code 200");
    }

    @Then("The response should contain the stored ability name")
    public void validate_ability_name() {
        String expectedAbility = scenarioContext.get("abilityName", String.class);
        String actualAbility = response.jsonPath().getString("name");

        assertNotNull(actualAbility, "Ability name should not be null");
        assertEquals(expectedAbility, actualAbility, "Expected ability name does not match response");
    }

    @Then("The ability should include the stored Pokemon name")
    public void validate_pokemon_in_ability() {
        String expectedPokemon = scenarioContext.get("pokemonName", String.class);
        List<String> pokemonList = response.jsonPath().getList("pokemon.pokemon.name");

        assertNotNull(pokemonList, "Pokemon list should not be null");
        assertTrue(pokemonList.contains(expectedPokemon), 
            "Expected Pokemon: " + expectedPokemon + " not found in ability's Pokemon list.");
    }
}
