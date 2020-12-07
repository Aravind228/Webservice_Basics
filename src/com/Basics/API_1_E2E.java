package com.Basics;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Files.Application_Payloads;
import com.Files.ReUsableClass;
//This is API 1 class

public class API_1_E2E {
	
	public static String places;

	@Test
	public static void End_2_end() {
		
		//given //when //then
		
		//given - All input details
		//when - what type of request going to be submitted eg. get or post,Resources,HTTP method
		//Then - validation purpose
		
		//Add place Rest Request
		
		System.out.println("Add place inititated");
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String AddPlace_response =given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Application_Payloads.payload1())
		.when().post("/maps/api/place/add/json")
		.then().assertThat().statusCode(200)
		.body("scope", equalTo("APP"))
		.header("Server", "Apache/2.4.18 (Ubuntu)")
		.header("Content-Length", equalTo("194")).extract().response().asString();
		
		System.out.println(AddPlace_response);
		
		JsonPath json= ReUsableClass.rawtoJSON(AddPlace_response);
		
		places = json.getString("place_id");
		
		System.out.println("Json Regerence value "+places);
		
		//update place Rest Request
		System.out.println("*************************");
		System.out.println("Update place inititated");

		
		given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(Application_Payloads.payload2())
		.when().put("/maps/api/place/update/json")
		.then().log().all().assertThat().statusCode(200)
		.body("msg", equalTo("Address successfully updated"))
		.header("Access-Control-Allow-Methods", equalTo("POST")).extract().response().asString();
		
		                             
		//Check given place is updated with new address
		System.out.println("*************************");
		System.out.println("Get place inititated");

		String Get_response =given().log().all().queryParam("key", "qaclick123").queryParam("place_id", places)
		.when().get("/maps/api/place/get/json")
		.then().log().all().assertThat().statusCode(200)
		.extract().response().asString();
		
		JsonPath json2= ReUsableClass.rawtoJSON(Get_response);
		String Updated_Address=json2.getString("address");

		
		Assert.assertEquals(Updated_Address, "761 Winter walk, India");
		
		
	}
	
	
		

}
