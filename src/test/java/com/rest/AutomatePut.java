package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AutomatePut {

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.postman.com").
                addHeader("x-api-key", "PMAK-617ac9c8f1199a003b34391a-4b5bcb8cf997c25850f79d00fe5c0669be").
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void post_request_bdd_style() {

        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"TeamWorkspace5\",\n" +
                "        \"type\": \"team\",\n" +
                "        \"descripition\": \"New Post Workspace\"\n" +
                "    }\n" +
                "}";
        Response res = given().
                body(payload).
                when().
                put("/workspaces").
                then().
                log().all().
                statusCode(200).
                extract().response();
        assertThat(res.path("workspace.name"), equalTo("TeamWorkspace5"));
    }

    @Test
    public void post_request_non_bdd_style() {

        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"TeamWorkspace6\",\n" +
                "        \"type\": \"team\",\n" +
                "        \"descripition\": \"New Post Workspace\"\n" +
                "    }\n" +
                "}";

        Response res = with().
                body(payload).
                post("/workspaces");
                assertThat(res.statusCode(), equalTo(200));
                assertThat(res.path("workspace.name"), equalTo("TeamWorkspace6"));
    }
}
