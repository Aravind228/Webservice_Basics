package com.Basics;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;

import org.testng.annotations.Test;

import com.Files.Application_Payloads;
import com.Files.ReUsableClass;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//This is API_2_StaticPayloads_Notepad
//static payloads
//gfdgfd
//fdsfdsf
public class API_2_StaticPayloads_Notepad {
	
// This is performed to do any static payloads to be executed from external files
	@Test 
	public static void Add_books() throws IOException {
		 
			
			RestAssured.baseURI = "https://rahulshettyacademy.com";
			String AddPlace_response =given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
			.body(ReUsableClass.generate_string_from_Notepad("C:\\Users\\Admin\\Documents\\Rest Assured External Files\\ExternalFile_payload1.json"))
			.when().post("/maps/api/place/add/json")
			.then().assertThat().statusCode(200)
			.body("scope", equalTo("APP"))
			.header("Server", "Apache/2.4.18 (Ubuntu)")
			.header("Content-Length", equalTo("194")).extract().  response().asString();
			
			System.out.println(AddPlace_response);
			
			JsonPath json= ReUsableClass.rawtoJSON(AddPlace_response);
			
			String places = json.getString("place_id");
			
			System.out.println("Json Regerence value "+places);
	 }

}
