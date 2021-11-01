package com.rest;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class Test {

    @org.testng.annotations.Test

    public void validateGetStatusCode() {

        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-617ac9c8f1199a003b34391a-4b5bcb8cf997c25850f79d00fe5c0669be").
                when().
                get("workspaces").
                then().
                log().all().
                assertThat().statusCode(200).
                body("workspaces.name", hasItems("My Workspace", "teamWorkspace"),
                        "workspaces.type", hasItems("team", "personal"));
    }

    @org.testng.annotations.Test
    public void extractResponse() {

        Response res = given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-617ac9c8f1199a003b34391a-4b5bcb8cf997c25850f79d00fe5c0669be").
                when().
                get("workspaces").
                then().
                log().all().
                assertThat().statusCode(200).
                body("workspaces.name", hasItems("My Workspace", "teamWorkspace"),
                        "workspaces.type", hasItems("team", "personal")).
                extract().response();
        System.out.println("response" + res.asString());
    }

    @org.testng.annotations.Test
    public void extractSingleValueFromResponse() {

        String name = given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-617ac9c8f1199a003b34391a-4b5bcb8cf997c25850f79d00fe5c0669be").
                when().
                get("workspaces").
                then().
                log().all().
                assertThat().statusCode(200).
                extract().response().path(("workspaces[1].type"));

        Assert.assertEquals(name, "team");
        assertThat(name, equalTo("team"));
        System.out.println("Response = " + name);
    }
}