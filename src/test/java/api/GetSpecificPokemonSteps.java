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

public class GetSpecificPokemonSteps {
    private final ScenarioContext scenarioContext;
    private Response response;
    private String storedPokemonName;

    public GetSpecificPokemonSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Given("I look for a {string} type pokemon named {string}")
    public void i_request_details_for_the_pokemon(String ability, String pokemonName) throws IOException {
        response = given()
            .spec(RequestSpecFactory.getRequestSpecification())
            .when()
            .get("/pokemon/" + pokemonName);

        assertNotNull(response, "Response should not be null");
        storedPokemonName = pokemonName;

        scenarioContext.setResponse(response);
    }

    @Then("The search should return pokemon info")
    public void the_search_should_return_pokemon_info() {
        String returnedPokemonName = response.jsonPath().getString("forms[0].name");

        assertNotNull(response, "Response should not be null");
        assertNotNull(returnedPokemonName, "Pokemon name should not be null");
        assertEquals(storedPokemonName, returnedPokemonName,
        "Expected Pokemon name does not match response Pokemon name");

        List<String> abilities = response.jsonPath().getList("abilities.ability.name");

        assertNotNull(abilities, "Abilities list should not be null");
        assertFalse(abilities.isEmpty(), "Abilities list should not be empty");
    }

    @Then("The pokemon type is {string}")
    public void the_pokemon_type_is(String expectedType) {
        assertNotNull(response, "Response should not be null");

        // Extracting the types from the JSON response
        List<String> types = response.jsonPath().getList("types.type.name");

        assertNotNull(types, "Pokemon types should not be null or empty");
        assertFalse(types.isEmpty(), "Pokemon types should not be empty");

        // Assert that the expected type is present in the list
        assertTrue(types.contains(expectedType),
                "Expected type: " + expectedType + " not found in response types: " + types);
    }
}
