package Tests;
import static com.jayway.restassured.RestAssured.*;

import org.testng.annotations.Test;


public class First {

 @Test
 public void makeSureThatGoogleIsUp() {
     given().when().get("http://www.google.com").then().statusCode(200);
 }

 @Test
 public void invalidParkingSpace() {
     given().when().get("/garage/slots/999")
         .then().statusCode(404);
 }
}