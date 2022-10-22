package com.rest;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;
import static io.restassured.config.EncoderConfig.encoderConfig;


public class AddPlace {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        given().
                config(RestAssured.config().encoderConfig(encoderConfig().
                        encodeContentTypeAs("application-json", ContentType.JSON))).
                queryParam("key", "qaclick123").header("Content-Type", "application-json").
                body("{\n" +
                        "  \"location\": {\n" +
                        "    \"lat\": -38.383494,\n" +
                        "    \"lng\": 33.427362\n" +
                        "  },\n" +
                        "  \"accuracy\": 50,\n" +
                        "  \"name\": \"Rahul Shetty Academy\",\n" +
                        "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                        "  \"address\": \"29, side layout, cohen 09\",\n" +
                        "  \"types\": [\n" +
                        "    \"shoe park\",\n" +
                        "    \"shop\"\n" +
                        "  ],\n" +
                        "  \"website\": \"http://rahulshettyacademy.com\",\n" +
                        "  \"language\": \"French-FR\"\n" +
                        "}").
        when().put("maps/api/place/add/json").
        then().assertThat().statusCode(200);

    }
}

