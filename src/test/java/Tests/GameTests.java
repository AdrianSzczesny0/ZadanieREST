package Tests;

import Api.Game;
import Utility.ENV;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;

public class GameTests extends Game {

    public Map<String,String> headers;

    @BeforeClass
    public void initialize(){
        SetUp(ENV.DEV);
        headers = initializeHeader();
    }


    @Test(description = "Verify POST /game without body - should get status code 422")
    public void verify_POST_game_WithoutBody(){
        System.out.println(baseURI+getGamesAPI());
        System.out.println(headers);
        given()
                .headers(headers)
                .when()
                .post(baseURI+getGamesAPI())
                .then()
                .statusCode(405)
                .statusLine("HTTP/1.1 405 Method Not Allowed");
    }

    // verify all get all games.
    @Test(description = "Verify GET /games  get all games")
    public void verify_GET_games_successfull(){
        int expectedListLenght = 7;
        System.out.println(baseURI+getGamesAPI());
        System.out.println(headers);
        Response response = RestAssured.given()
                .headers(headers)
                .when()
                .get(baseURI+getGamesAPI())
                .then()
                .statusCode(200)
                .assertThat().
                        body("code",equalTo("success")).
                        body("message",equalTo("list created")).
                        body("game[0].name",equalTo("string")).
                        body("game[0].vendorId",equalTo(2)).
                        body("game[0].mobile",equalTo(true)).
                        body("game[0].active",equalTo(true)).
                        body("game[0].id",equalTo(1)).
                        body("game[0].visible",equalTo(true)).
                        body("game[0].vendor",equalTo("string"))

                .extract().response();

        // get list size by adding it to the arrray by game.id
        String game0Data = response.path("game.id").toString();
        game0Data = game0Data.replace("["," ");
        game0Data = game0Data.replace("]"," ");
        System.out.println(game0Data);
        String[] list = game0Data.split(",");
        for(int i=0;i<list.length;i++){
            System.out.println(list[i]);
        }
        // verify list size
        Assert.assertEquals(list.length,expectedListLenght);
    }


    // test case PUTS game with specific parameters. then  gets the game by ID and verifies that it was updated.
    // to make it more specific( needs update in future)
    // it should first check the data by getting the game data with given vendorID
    // then it should change the data and verify agian that it was updated accordingly.
    @Test(description = "Verify POST /games  update game and verify it is updated properly.")
    public void verify_PUT_games_update_successfull(){

        int vendorID = 19;
        putGame(vendorID,19,"newVendorName1","newGameName1",false,false,true);
        Response response = RestAssured.given()
                .headers(headers)
                .when()
                .get(baseURI+getGamesAPI(19))
                .then()
                .statusCode(200)
                .assertThat()
                        .body("game.name",equalTo("newGameName1")).
                        body("game.vendorId",equalTo(19)).
                        body("game.mobile",equalTo(true)).
                        body("game.active",equalTo(false)).
                        body("game.id",equalTo(3)).
                        body("game.visible",equalTo(true)).
                        body("game.vendor",equalTo("newVendorName1"))
                .extract().response();
    }


    // post game method for quicker game update for multistep test case. There could be another one for validation.
    // additionally this could be moved in a separate TestClass for re usability.
    public void putGame(int vendorID,int newVendorID,String vendorName,String gameName,boolean active, boolean mobile,boolean visible){
        JSONObject request = new JSONObject();
        request = setPUTGamePayload(newVendorID,vendorName,gameName,active,mobile,visible);
        Response response = RestAssured.given()
                .headers(headers)
                .body(request.toJSONString())
                .when()
                .put(baseURI+putGamesAPI(vendorID))
                .then()
                .statusCode(200)
                .extract().response();
    }
}
