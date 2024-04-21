import io.restassured.RestAssured;
import io.restassured.http.ContentType;

import static io.restassured.RestAssured.*;

import io.restassured.response.Response;
import jdk.jfr.Description;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.json.simple.JSONObject;

public class POReqresAPITest {

    private static final Logger logger = LogManager.getLogger(POReqresAPITest.class);

    @BeforeClass
    public void setup() {
        baseURI = "https://reqres.in/api";
    }

    @Description("Testing the GET API and validating the response fields")
    @Test
    public void testGetMethod() {
        logger.info("*******Start GET API Testing********");
        Response response =
                given().
                when().
                        get("/users/2").
                then().
                        statusCode(200).
                        extract().
                        response();

        logger.info("Get Response body: " + response.getBody().asString());
        Assert.assertEquals(response.jsonPath().getString("data.email"), "janet.weaver@reqres.in");
        Assert.assertEquals(response.jsonPath().getString("data.first_name"), "Janet");
        Assert.assertEquals(response.jsonPath().getString("data.last_name"), "Weaver");
        logger.info("GET API validated successfully");
        logger.info("*******End of GET API Testing********");
    }

    @Description("Testing the PUT API method and validating the response fields")
    @Test
    public void testPutMethod() {

        logger.info("*******Start PUT API Testing********");

        JSONObject request = new JSONObject();
        request.put("name", "Lakshmi");
        request.put("job", "IT");

        Response putResponse =
                given().
                        header("Conteny-Type", "application/json").
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        body(request.toJSONString()).
                when().
                        put("/users/2").
                then().
                        statusCode(200).
                        extract().
                        response();

        logger.info("Put Response body: " + putResponse.getBody().asString());
        //Negative Testing
        Assert.assertNotEquals(putResponse.jsonPath().getString("name"), "Janet");

        Assert.assertEquals(putResponse.jsonPath().getString("name"), "Lakshmi");
        Assert.assertEquals(putResponse.jsonPath().getString("job"), "IT");
        logger.info("PUT API validated successfully");
        logger.info("*******End PUT API Testing********");
    }

    @Description("Testing the POST API method and validating the response fields")
    @Test
    public void testPostMethod() {
        logger.info("*******Start POST API Testing********");
        JSONObject request = new JSONObject();
        request.put("name", "Lakshmi");
        request.put("job", "Lead QA");

        Response postResponse =
                given().
                        header("Content-Type", "application/json").
                        contentType(ContentType.JSON).
                        accept(ContentType.JSON).
                        body(request.toJSONString()).
                when().
                        post("/users").
                then().
                        statusCode(201).
                        extract().
                        response();

        logger.info("Post Response body: " + postResponse.getBody().asString());
        Assert.assertEquals(postResponse.jsonPath().getString("name"), "Lakshmi");
        Assert.assertEquals(postResponse.jsonPath().getString("job"), "Lead QA");
        logger.info("POST API validated successfully");
        logger.info("*******End POST API Testing********");
    }

    @Description("Testing the DELETE API method and confirming for empty response")
    @Test
    public void testDeleteMethod() {
        logger.info("*******Start DELETE API Testing********");

        Response response =
                 given().
                 when().
                         delete("/users/2").
                 then().
                         statusCode(204).
                         extract().
                         response();

        Assert.assertTrue(response.getBody().asString().isEmpty());
        logger.info("DELETE API validated successfully");
        logger.info("*******End DELETE API Testing********");
    }
}

