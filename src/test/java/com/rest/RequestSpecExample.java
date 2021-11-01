package com.rest;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class RequestSpecExample {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
        requestSpecification = given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-617ac9c8f1199a003b34391a-4b5bcb8cf997c25850f79d00fe5c0669be");
    }

    @Test
    public void validateStatusCode() {

        given().spec(requestSpecification).
                when().
                get("workspaces").
                then().
                log().all().
                assertThat().statusCode(200);
    }

    @Test
    public void validate_response_header() {
        given(requestSpecification).
                when().
                get("workspaces").
                then().
                log().body().
                assertThat().statusCode(200).
                body("workspaces[1].name", equalTo("teamWorkspace"));
    }


}
