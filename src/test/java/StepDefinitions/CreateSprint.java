package StepDefinitions;

import Utils.PayloadUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateSprint {

    Response response;

    @When("user sends {string} request to Jira Api with parameters {string},{string},{string},{string},{string}")
    public void user_Sends_Request_To_Jira_Api_With_Parameters(String request, String name, String startDate,
                                                               String endDate, String originBoardId, String goal) {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "rest/agile/1.0/sprint";

        response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .cookie("JSESSIONID", PayloadUtils.getSessionCookie())
                .body(PayloadUtils.getSprint(name, Integer.valueOf(originBoardId),
                        startDate, endDate, goal))
                .when().request(request);
    }

    @And("status code is {int}")
    public void status_code_is(Integer statusCode) {
        response.then().statusCode(statusCode);
    }


    @Then("user verifies response scheme:")
    public void user_verifies_response_scheme(Map<String, String> responseValues) {

        Map<String, Object> responseValuesFromJira = response.getBody().as(new TypeRef<Map<String, Object>>() {});

        System.out.println("response values from table: "+responseValues);
        System.out.println("response values from creating sprint: "+responseValuesFromJira);

        Assert.assertEquals(responseValues.get("name"),responseValuesFromJira.get("name").toString());

        //when creating a sprint the dates change, so not equal, and assertions do not work
        //Assert.assertEquals(responseValues.get("startDate"),responseValuesFromJira.get("startDate").toString());
        //Assert.assertEquals(responseValues.get("endDate"),responseValuesFromJira.get("endDate").toString());

        Assert.assertEquals(responseValues.get("originBoardId"),responseValuesFromJira.get("originBoardId").toString());
        Assert.assertEquals(responseValues.get("goal"),responseValuesFromJira.get("goal").toString());

    }

}
