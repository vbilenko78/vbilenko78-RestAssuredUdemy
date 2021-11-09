package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.util.HashMap;

import static com.sun.org.apache.xerces.internal.util.PropertyState.is;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.isEmptyOrNullString;

public class AutomatePost {

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
                post("/workspaces").
                then().
                log().all().
                statusCode(200).
                extract().response();
        assertThat(res.path("workspace.name"), equalTo("TeamWorkspace5"));
    }

    @Test
    public void post_request_file_payload() {

        File payload = new File("src/main/resources/PostPayload.json");

        Response res = with().
                body(payload).
                post("/workspaces");
                assertThat(res.statusCode(), equalTo(200));
                assertThat(res.path("workspace.name"), equalTo("PostFileWorkSpace"));
    }

    @Test
    public void post_request_hash_map_payload() {

        HashMap<String, Object> mainObject = new HashMap<String, Object>();
        HashMap<String, String> nestedObject = new HashMap<String, String>();
        nestedObject.put("name", "PostWorkSpace2");
        nestedObject.put("type", "team");
        nestedObject.put("description", "New Workspace");

        mainObject.put("workspace", nestedObject);

        Response res = with().
                body(mainObject).
                post("/workspaces");
        assertThat(res.statusCode(), equalTo(200));
        assertThat(res.path("workspace.name"), equalTo("PostWorkSpace2"));
    }

}
