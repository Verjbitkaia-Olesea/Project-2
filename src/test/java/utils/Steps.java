package utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsEqual.equalTo;

/**
import static org.hamcrest.Matchers.*;
import io.restassured.matcher.ResponseAwareMatcher;
import static io.restassured.RestAssured.baseURI;
**/

public class Steps {

    @Step
    public static Response GET(String endpoint){
        Allure.addAttachment("URL",baseURI+endpoint);
        Response response=get(endpoint);
        Allure.addAttachment("Response body",response.body().prettyPrint());
        Allure.addAttachment("Status Code",String.valueOf(response.statusCode()));
        return response;
    }

    @Step
    public static void isStatusCodeValid(Response response, int expectedStatusCode){
        response.then().assertThat().statusCode(expectedStatusCode);
    }

    @Step
    public static Response POST(String body, String endpoint){
        Allure.addAttachment("URL",baseURI+endpoint);
        Allure.addAttachment("Request body",body);
        Response response=given().body(body).post(endpoint);
        Allure.addAttachment("Status Code",String.valueOf(response.statusCode()));
        Allure.addAttachment("Response body", response.body().prettyPrint());
        return response;

    }

    @Step
    public static void isBodyContainsValue(Response response, String key, String expectedResult){
        response.then().assertThat().body(key, equalTo(expectedResult));
    }

    @Step
    public static void isBodyContains(Response response, String expectedResult){
        response.then().assertThat().body(containsString(expectedResult));
    }

    @Step
    public static Response PUT(String body, String endpoint){
        Allure.addAttachment("URL",baseURI+endpoint);
        Allure.addAttachment("Request body",body);
        Response response=given().body(body).put(endpoint);
        Allure.addAttachment("Status Code",String.valueOf(response.statusCode()));
        Allure.addAttachment("Response body", response.body().prettyPrint());
        return response;

    }

    @Step
    public static Response PATCH(String body, String endpoint){
        Allure.addAttachment("URL",baseURI+endpoint);
        Allure.addAttachment("Request body",body);
        Response response=given().body(body).patch(endpoint);
        Allure.addAttachment("Status Code",String.valueOf(response.statusCode()));
        Allure.addAttachment("Response body", response.body().prettyPrint());
        return response;

    }

    @Step
    public static Response DELETE(String endpoint) {
        Allure.addAttachment("URL", baseURI + endpoint);
        Response response = delete(endpoint);
        Allure.addAttachment("Status Code", String.valueOf(response.statusCode()));
        return response;
    }
}

