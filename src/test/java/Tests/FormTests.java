package Tests;

import Api.Form;
import Utility.ENV;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class FormTests extends Form {

    public Map<String,String> headers;

    @BeforeClass
    public void initialize(){
        SetUp(ENV.DEV);
        headers = initializeHeader();
    }


    @Test(description = "Verify GET /form status code 405")
    public void verify_GET_Form(){
        System.out.println(baseURI+getAPI());
        System.out.println(headers);
        given()
                .headers(headers)
                .when()
                .get(baseURI+getAPI())
                .then()
                .statusCode(200)
                .statusLine("HTTP/1.1 200 OK");
    }

    @Test(description = "Verify POST /form status code 405")
    public void verify_POST_Form(){
        given()
                .headers(headers)
                .when()
                .post(baseURI+getAPI())
                .then()
                .statusCode(405)
                .statusLine("HTTP/1.1 405 Method Not Allowed");
    }

    @Test(description = "Verify PUT /form status code 405")
    public void verify_PUT_Form(){
        System.out.println(baseURI+getAPI());
        System.out.println(headers);
        given()
                .headers(headers)
                .when()
                .put(baseURI+getAPI())
                .then()
                .statusCode(405)
                .statusLine("HTTP/1.1 405 Method Not Allowed");
    }

    @Test(description = "Verify PATCH /form status code 405")
    public void verify_PATCH_Form(){
        System.out.println(baseURI+getAPI());
        System.out.println(headers);
        given()
                .headers(headers)
                .when()
                .patch(baseURI+getAPI())
                .then()
                .statusCode(405)
                .statusLine("HTTP/1.1 405 Method Not Allowed");
    }
}
