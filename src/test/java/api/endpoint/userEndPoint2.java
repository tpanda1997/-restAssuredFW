package api.endpoint;

import static io.restassured.RestAssured.given;

import java.util.ResourceBundle;

import api.payload.user;
import io.restassured.response.Response;

public class userEndPoint2 {

	
	
	private static String  bToklen= "Bearer a86f068c1a0809ba7a2ec361e7477f15f44c45044cd7f2399965762377ab82aa";
	
	
	 static ResourceBundle  getUrl() {
		 
		 
		 ResourceBundle routes= ResourceBundle.getBundle("routes");
		 
		return routes;
	
	}
	
	public static Response createUser(user payload) {

          
			Response response=  given()
			
			.header("Authorization", bToklen)
			.contentType("application/Json")
			.body(payload)
			
			.when()
			.post(getUrl().getString("createUser"));
           
         return  response;		
			
	}
	
	public static Response updateUser(user payload,int userId) {

       Response response=  given()
		
		.header("Authorization", bToklen)
		.contentType("application/Json")
		.pathParam("id", userId)
		.body(payload)
		
		.when()
		.put(getUrl().getString("updateUser"));
         
     return  response;		
		
}
	
	public static Response getUser(int userId) {

       Response response=  given()
		.header("Authorization", bToklen)
		.pathParam("id", userId)
		.when()
		.get(getUrl().getString("getUser"));
       
     return  response;		
		
}
	
	public static Response dltUser(int userId) {

       Response response=  given()
		
		.header("Authorization", bToklen)
		.pathParam("id", userId)
		.when()
		.delete(getUrl().getString("deletUser"));
       
     return  response;		
		
}
	
	
	 
	
}
