package com.rest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class PostmanGet {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass(){
        requestSpecification = with().
                baseUri("https://api.postman.com").
                header("X-Api-Key","PMAK-61e5a766f51da0121164e871-6802f627fd726b009cb903e65c03f888b4");
    }
    @Test
    public void validate_get() {

        String res = given(requestSpecification).
        when().get("/workspaces").
        then().
                log().all().statusCode(200).
                extract().
                response().
                path("workspaces[0].name");

        assertThat(res, equalTo("My Workspace"));
        Assert.assertEquals(res, "My Workspace");
    }

    @Test
    public void requestSpec() {
        given(requestSpecification).
        when().get("/workspaces").
        then().
                log().all().statusCode(200).
                extract().
                response().
                path("workspaces[0].name");

    }

    @Test
    public void bdd_non_bdd(){
        Response response = requestSpecification.get("/workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("workspaces[0].name"), is(equalTo("My Workspace")));
    }
}
