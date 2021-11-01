package com.rest;

import com.sun.org.apache.regexp.internal.RE;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;


public class ReqSpecBuilder {

    RequestSpecification requestSpecification;

    @BeforeClass
    public void beforeClass() {
/*        requestSpecification = given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-617ac9c8f1199a003b34391a-4b5bcb8cf997c25850f79d00fe5c0669be").
                log().all();*/

        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://api.postman.com");
        requestSpecBuilder.addHeader("x-api-key", "PMAK-617ac9c8f1199a003b34391a-4b5bcb8cf997c25850f79d00fe5c0669be");
        requestSpecBuilder.log(LogDetail.ALL);
    }

    @Test
    public void validateStatusCode() {
        Response response = given(requestSpecification).get("workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));

    }

    @Test
    public void validate_response_header() {
        Response response = given(requestSpecification).get("workspaces").then().log().all().extract().response();
        assertThat(response.statusCode(), is(equalTo(200)));
        assertThat(response.path("workspaces[1].name").toString(), equalTo("teamWorkspace"));
    }
}
