package api.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;


import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import api.test.userTest;
import io.restassured.response.Response;

public class ExtentReportManager implements ITestListener {

	
	public ExtentSparkReporter sparkReporter;
	public ExtentReports extent ;
	public ExtentTest test;
	
	String reportName;
	String info;
	
	
	public void sendInfo(String info) {
		
		this.info=info;
		
		
	}
	public void onStart(ITestContext context) {
		
		String timeStamp= new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date() );
		reportName="Test-report-"+timeStamp+".html";
		
		sparkReporter= new ExtentSparkReporter(".\\reports\\"+reportName);
		sparkReporter.config().setDocumentTitle("RestAssured FW Test");
		sparkReporter.config().setReportName("Rest assured FW");
		sparkReporter.config().setTheme(Theme.DARK);
		
		extent = new ExtentReports();
		
		extent.attachReporter(sparkReporter);
		extent.setSystemInfo("Application", "goRestApis");
		extent.setSystemInfo("Operating system",System.getProperty("os.name"));
		extent.setSystemInfo("User name", System.getProperty("user.name"));
		extent.setSystemInfo("Enviremnt","QA");
		extent.setSystemInfo("user","Tathagata");
	}
	
	public void onTestSuccess(ITestResult result) {
		
		test = extent.createTest(result.getName());
		
		test.assignCategory(result.getMethod().getGroups());
		
		test.createNode(result.getName());
		test.log(Status.PASS,info);
		test.info(info);
		
		test.pass("Test is pass");
		
	}
	
public void onTestFailure(ITestResult result) {
		
		test = extent.createTest(result.getName());
		test.assignCategory(result.getMethod().getGroups());
		test.createNode(result.getName());
		test.log(Status.FAIL,"Test is fail");
		test.log(Status.FAIL,result.getThrowable().getMessage());
		test.fail("Test is fail");
		
	}

public void onTestSkipped(ITestResult result) {
	
	test = extent.createTest(result.getName());
	test.assignCategory(result.getMethod().getGroups());
	test.createNode(result.getName());
	test.log(Status.SKIP,"Test is SKIPPED");
	
	test.log(Status.SKIP,result.getThrowable().getMessage());

	
}

public void onFinish(ITestContext context) {

	extent.flush();

}





	
}
