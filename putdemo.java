package POSTMAN;

import java.util.HashMap;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
public class putdemo 
{
HashMap<String, String> map = new HashMap<>();
	
	@BeforeTest
	public void createpayload() {
		map.put("name", "Superrr");
		map.put("email","abc969986@gmail.com");
		map.put("gender", "female");
		map.put("status", "active");
		RestAssured.baseURI = "https://gorest.co.in/";
		RestAssured.basePath="/public/v2/users/1734454";
	}

	@Test
	public void updateResource() {
		RestAssured
		    .given()
		           .contentType("application/json")
		           .body(map)
		           .header("Authorization", "Bearer 9cc3524ee3c7053728f71ade567a76baacdc3eb8cb3aabd0eac2a6877fd2d51a")
		     .when()
		           .put()
		     .then()
		           .statusCode(200)
		           .log().all();
	}
}
