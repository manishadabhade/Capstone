package POSTMAN;

import org.testng.annotations.Test;
import io.restassured.RestAssured;

public class getdemo 
{
	@Test
	public void getresourceinfo()
	{
		RestAssured
		    .given() 
		         .contentType("application/json")
	         .header("Authorization", "Bearer 9cc3524ee3c7053728f71ade567a76baacdc3eb8cb3aabd0eac2a6877fd2d51a")
	    .when()
	         .get("https://gorest.co.in/public/v2/users/1734454")
	    .then()
	          .assertThat()
	          .statusCode(200)
	          .log().all();
}
}

