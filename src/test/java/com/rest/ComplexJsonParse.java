package com.rest;

import files.Payload;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.asserts.Assertion;

public class ComplexJsonParse {

    public static void main(String[] args) {

        JsonPath jasonPath = new JsonPath(Payload.CoursePrice());
        int size = jasonPath.getInt("courses.size()");
        Assert.assertEquals(3, size);
        System.out.println(size);

        for(int i=0; i < size; i++) {
            String titles = jasonPath.getString("courses["+i+"].title");
            System.out.println(titles);
        }

        String titlesAll = jasonPath.get("courses.price").toString();
        System.out.println(titlesAll);

    }
}