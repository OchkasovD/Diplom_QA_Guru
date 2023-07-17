package web_api.specs;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static web_api.helpers.CustomApiListener.withCustomTemplates;


public class Specifications {

    public static RequestSpecification requestSpec = with()
            .log().uri()
            .log().all()
            .filter(withCustomTemplates())
            .contentType("application/x-www-form-urlencoded; charset=UTF-8");

    public static ResponseSpecification responseSpecFail200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();

    public static ResponseSpecification responseSpec302 = new ResponseSpecBuilder()
            .expectStatusCode(302)
            .build();
}
