package api;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import utils.ScenarioContext;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CommonSteps {
    private final ScenarioContext scenarioContext;

    public CommonSteps(ScenarioContext scenarioContext) {
        this.scenarioContext = scenarioContext;
    }

    @Then("Status Code should return {int}")
    public void status_code_should_return(int expectedStatus) {
        Response response = scenarioContext.getResponse();
        assertEquals(expectedStatus, response.statusCode(), "Expected status code did not match");
    }
}
