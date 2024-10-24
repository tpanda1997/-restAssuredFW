package api.test;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.github.javafaker.Faker;

import api.endpoint.userEndpoint;
import api.payload.user;
import api.utilities.ExtentReportManager;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

public class userTest {
	
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
		
		Response response =userEndpoint.createUser(payloadUser);
	    response.then().log().all();
	    
	   reporter.sendInfo(response.asString());
		Assert.assertEquals(response.getStatusCode(), 201);
		
		userId=response.jsonPath().getInt("id");
		logger.info("--Test create user---");
		
		
	}
	
	
	@Test(priority = 2)
	public void getUserTest() {
		
		Response response =userEndpoint.getUser(userId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
	
		logger.info("--Test get user---");
		
	}

	@Test(priority = 3)
	public void updateUserTest() {
		
		payloadUser.setEmail(fake.internet().emailAddress());
		Response response =userEndpoint.updateUser(payloadUser,userId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 200);
		
		logger.info("--Test update user---");
	
	}
	
	@Test(priority = 4)
	public void dltUserTest() {
		
		Response response =userEndpoint.dltUser(userId);
		response.then().log().all();
		Assert.assertEquals(response.getStatusCode(), 204);
		
		logger.info("--Test delete user---");
	}
}
