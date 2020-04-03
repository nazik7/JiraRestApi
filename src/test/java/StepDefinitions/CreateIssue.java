package StepDefinitions;

import Utils.PayloadUtils;
import io.cucumber.java.en.And;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class CreateIssue {

    @And("user creates {int} {string} issueType")
    public void user_creates_issueType(Integer totalIssueNumber, String issueType) {

        String assignee = null,summary,description, priority;

        for(int i=0; i< totalIssueNumber;i++){

            summary = issueType + " number "+ (i+1)+ " creation";
            description = summary + " by Automation Test";

            String postBody = PayloadUtils.getJiraPayload("TEC",assignee,summary, description,"Medium",issueType);

            Response response = given().accept(ContentType.JSON).contentType(ContentType.JSON)
                    .cookie("JSESSIONID", PayloadUtils.getSessionCookie())
                    .body(postBody).when().post()
                    .then().statusCode(201).extract().response();
        }

    }



}
