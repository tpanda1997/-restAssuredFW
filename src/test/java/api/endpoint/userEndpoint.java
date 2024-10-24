package api.endpoint;


import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ResourceBundle;

import api.payload.user;
import io.restassured.response.Response;


public class userEndpoint {

	private static String  bToklen= "Bearer a86f068c1a0809ba7a2ec361e7477f15f44c45044cd7f2399965762377ab82aa";
	
	
	
	
	public static Response createUser(user payload) {

           
			Response response=  given()
			
			.header("Authorization", bToklen)
			.contentType("application/Json")
			.body(payload)
			
			.when()
			.post(routes.createUser);
            
          return  response;		
			
	}
	
	public static Response updateUser(user payload,int userId) {

        Response response=  given()
		
		.header("Authorization", bToklen)
		.contentType("application/Json")
		.pathParam("id", userId)
		.body(payload)
		
		.when()
		.put(routes.updateUser);
        
      return  response;		
		
}
	
	public static Response getUser(int userId) {

        Response response=  given()
		.header("Authorization", bToklen)
		.pathParam("id", userId)
		.when()
		.get(routes.getUser);
        
      return  response;		
		
}
	
	public static Response dltUser(int userId) {

        Response response=  given()
		
		.header("Authorization", bToklen)
		.pathParam("id", userId)
		.when()
		.delete(routes.deletUser);
        
      return  response;		
		
}
	
	
	
}
