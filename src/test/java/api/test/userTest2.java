package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.userEndPoint2;

import api.payload.user;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;

public class userTest2 {
	
	user payloadUser;
	int userId;
	Faker fake=new Faker();
	ExtentReportManager reporter= new ExtentReportManager();
	
	public Logger logger = LogManager.getLogger(this.getClass());//log4j2
	
	@BeforeClass
	public void setData() {
		
	
		payloadUser= new user();
		
		payloadUser.setName(fake.name().firstName());
		payloadUser.setEmail(fake.internet().emailAddress());
		payloadUser.setGender("Male");
		payloadUser.setStatus("active");
		
	}
	

	
	@Test(priority = 1)
	public void createUserTest() {
		
		Response response =userEndPoint2.createUser(payloadUser);
	    response.then().log().all();
	    
	   reporter.sendInfo(response.asString());
		Assert.assertEquals(response.getStatusCode(), 201);
		
		userId=response.jsonPath().getInt("id");
		logger.info("--Test create user with p file---");
		
		
	}
	
	
	@Test(priority = 2)
	public void getUserTest() {
		
		Response response =userEndPoint2.getUser(userId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	
		logger.info("--Test get user with p file---");
		
	}

	@Test(priority = 3)
	public void updateUserTest() {
		
		payloadUser.setEmail(fake.internet().emailAddress());
		Response response =userEndPoint2.updateUser(payloadUser,userId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("--Test update user with p file---");
	
	}
	
	@Test(priority = 4)
	public void dltUserTest() {
		
		Response response =userEndPoint2.dltUser(userId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 204);
		
		logger.info("--Test delete user with p file---");
	}
}
