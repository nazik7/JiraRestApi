package StepDefinitions;

import Utils.PayloadUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateSprint {

    @When("user sends {string} request to Jira Api with parameters {string},{string},{string},{string},{string}")
    public void user_Sends_Request_To_Jira_Api_With_Parameters(String request, String name, String startDate,
                                                               String endDate, String originBoardId, String goal) {
        RestAssured.baseURI = "https://localhost:8080";
        RestAssured.basePath = "/rest/agile/1.0/board";

        Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .cookie("JSESSIONID", PayloadUtils.getSessionCookie())
                .body(PayloadUtils.getSprint(name,Integer.valueOf(originBoardId),
                        startDate,endDate,goal))
                .when().post().then().statusCode(201).extract().response();
    }

    @And("status code is {int}")
    public void status_code_is(Integer int1) {

    }


    @Then("user verifies response scheme:")
    public void user_verifies_response_scheme(Map<String,String> responseValues) {

    }


}
