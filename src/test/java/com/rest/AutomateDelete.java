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
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

public class AutomateDelete {

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
    public void delete_request() {

        String id = "a636ef33-8609-46f8-a3f5-1a8f39c9553c";

        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"My Workspace\",\n" +
                "        \"type\": \"team\",\n" +
                "        \"descripition\": \"New Post Workspace\"\n" +
                "    }\n" +
                "}";

        Response res = given().
                body(payload).
                when().
                delete("/workspaces/" + id).
                then().
                log().all().
                statusCode(200).
                extract().response();
        assertThat(res.path("workspace.id"), equalTo(id));
    }


}
