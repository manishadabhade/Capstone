package POSTMAN;

import java.util.HashMap;
import java.util.UUID;
import static org.hamcrest.Matchers.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


public class verify_post_get_put_delete 
{
	HashMap<String, String> map = new HashMap<>();
	int id;
	Logger logger;
	UUID uuid = UUID.randomUUID();
	
	@BeforeTest
	public void createpayload() {
		logger = Logger.getLogger("LogDemo");
		PropertyConfigurator.configure("log4j.properties");
		map.put("name", "superrr");
		map.put("email", uuid + "@gmail.com");
		map.put("gender", "female");
		map.put("status", "active");
		logger.info("payload created");
		
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.basePath = "public/v2/users";
	}
	@Test(priority = 0)
	public void createresource() {
		//Extract the response
      Response response = RestAssured
		.given() 
			.contentType("application/json")
			 .body(map)
			 .header("Authorization", "Bearer 9cc3524ee3c7053728f71ade567a76baacdc3eb8cb3aabd0eac2a6877fd2d51a")
	    .when() 
	         .post()
	     .then()
	         .log().all()
	         .contentType(ContentType.JSON).extract().response();
      
      JsonPath jsonPath = response.jsonPath();
      id = jsonPath.getInt("id");
      logger.info("resource created");
	  System.out.println("Id of the resource = " + id);  
	}
	//verify resource has been created
	@Test(priority = 1)
	public void getresourceinfo() {
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.basePath = "/public/v2/users/"+id;
		
		RestAssured
		    .given()
		         .contentType("application/json")
		         .header("Authorization", "Bearer 9cc3524ee3c7053728f71ade567a76baacdc3eb8cb3aabd0eac2a6877fd2d51a")
		    .when()
		         .get()
		    .then()
		         .statusCode(200)
		         .body("name", is("superrr"));
		logger.info("resource created verified");
	}
	
	@Test(priority  =2)
	public void updateresource() {
		map.put("name", "Superrrr");
		map.put("email", uuid + "@gmail.com");
		map.put("gender", "female");
		map.put("status", "active");
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.basePath = "public/v2/users/" + id;
		
		RestAssured
	    .given()
	         .contentType("application/json")
	         .body(map)
	         .header("Authorization", "Bearer 9cc3524ee3c7053728f71ade567a76baacdc3eb8cb3aabd0eac2a6877fd2d51a" )
	    .when()
	         .put()
	    .then()
	         .statusCode(200)
	         .log().all();
		logger.info("resource updated");
	}
	
	@Test(priority = 4)
	public void deleteresource() {
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.basePath = "/public/v2/users/" + id;
		
		RestAssured
	    .given()
	         .contentType("application/json")
	         .header("Authorization", "Bearer 9cc3524ee3c7053728f71ade567a76baacdc3eb8cb3aabd0eac2a6877fd2d51a" )
	    .when()
	         .delete()
	    .then()
	         .statusCode(204);
		logger.info("resource deleted");
		
	}
}

