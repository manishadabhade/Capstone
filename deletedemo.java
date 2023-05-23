package POSTMAN;

import org.testng.annotations.Test;
import io.restassured.RestAssured;
public class deletedemo 
{
	@Test
	public void deleteResource() {
		RestAssured.baseURI = "https://gorest.co.in";
		RestAssured.basePath = "public/v2/users/1734454";
		RestAssured
		    .given()
		        .contentType("application/json")
		        .header("Authorization","Bearer 9cc3524ee3c7053728f71ade567a76baacdc3eb8cb3aabd0eac2a6877fd2d51a")
		    .when()   
		         .delete()
		     .then()
		          .statusCode(204);
	}
}

