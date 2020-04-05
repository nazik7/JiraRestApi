package StepDefinitions;

import Utils.PayloadUtils;
import io.cucumber.java.en.And;
import io.restassured.RestAssured;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class CreateIssue {

    @And("user creates {int} {string} issueType")
    public void user_creates_issueType(Integer totalIssueNumber, String issueType) {

        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "rest/api/2/issue";

        //http://localhost:8080/rest/api/2/issue/

        String assignee = "nazira.teshebaeva7",summary,description, priority;
        Response response;

        for(int i=0; i< totalIssueNumber;i++){

            summary = issueType + " number "+ (i+1)+ " creation";
            description = summary + " by Automation Test";

            String postBody = PayloadUtils.getJiraPayload("TEC",assignee,summary,
                    description,issueType,"Medium");

            response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                    .cookie("JSESSIONID", PayloadUtils.getSessionCookie())
                    .body(postBody).when().post()
                    .then().statusCode(201).extract().response();

            Map<String, Object> responseBody = response.getBody().as(new TypeRef<Map<String, Object>>(){});
            System.out.println(responseBody);
        }

    }


}
