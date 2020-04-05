package StepDefinitions;

import Utils.PayloadUtils;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.sl.In;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import java.util.Map;
import static io.restassured.RestAssured.given;


public class CreateBoard {

    Response response;
    Integer boardId;


    @When("user sends {string} request to create a board:")
    public void user_sends_request_to_create_a_board(String request, Map<String, String> boardValues ) {
        RestAssured.baseURI ="http://localhost:8080";
        RestAssured.basePath ="rest/agile/1.0/board";

        String boardBody = PayloadUtils.getBoardBody(boardValues.get("name"),boardValues.get("type"),Integer.valueOf(boardValues.get("filterId")));

        response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                .cookie("JSESSIONID", PayloadUtils.getSessionCookie())
                .body(boardBody)
                .when().request(request).then().extract().response();
    }

    @Then("verify status code is {int}")
    public void verifyStatusCodeIs(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @And("user verifies the response scheme:")
    public void userVerifiesTheResponseScheme(Map<String, String> expectedResponseBody) {

        Map<String, Object> responseBody = response.getBody().as(new TypeRef<Map<String, Object>>(){});
        System.out.println("response body: "+responseBody);

        boardId = Integer.valueOf(responseBody.get("id").toString());
        System.out.println("BoardId: "+boardId);

        for(String key:expectedResponseBody.keySet()){
            Assert.assertEquals(expectedResponseBody.get(key), responseBody.get(key));
        }

    }

    @When("user sends {string} request to delete a board")
    public void user_sends_request_to_delete_a_board(String request) {

        RestAssured.baseURI ="http://localhost:8080";
        RestAssured.basePath ="rest/agile/1.0/board/"+boardId;

        //rest/agile/1.0/board/{boardId}

        response = given().cookie("JSESSIONID", PayloadUtils.getSessionCookie())
                .when().delete();
    }


}
