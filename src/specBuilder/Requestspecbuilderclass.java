package specBuilder;

import static org.hamcrest.Matchers.equalTo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

import org.testng.annotations.Test;

import com.Files.Application_Payloads;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;



public class Requestspecbuilderclass {
	
	@Test
	public static void method() throws FileNotFoundException {
		PrintStream stream = new PrintStream(new FileOutputStream("log.txt"));
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
		addHeader("Content-Type", "application/json").addQueryParam("key","qaclick123").
		addFilter(RequestLoggingFilter.logRequestTo(stream)).
		addFilter(RequestLoggingFilter.logRequestTo(stream)).
		build();
		
		ResponseSpecification res=new ResponseSpecBuilder().expectStatusCode(200).expectBody("scope", equalTo("APP")).
		build();
		
		RequestSpecification reqs=given().spec(req).body(Application_Payloads.payload1());
		
		reqs.when().post("/maps/api/place/add/json")
		.then().spec(res);

		
		
	}
 
}
