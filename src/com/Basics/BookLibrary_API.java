package com.Basics;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.HashMap;


public class BookLibrary_API {
	
	
	
	@Test
	public void method1() {
		
		int random=(int)(10000*Math.random());
		
		System.out.println("Random "+random);
		
		RestAssured.baseURI="http://216.10.245.166";
		
		HashMap<String,Object> payload = new HashMap<>();
		payload.put("name", "Automation");
		payload.put("isbn", "\""+random+"\"");
		payload.put("aisle", "22755");
		payload.put("author", "John foe");

		//ADD BOOK API
		
		
		String response=given().log().all().body(payload).
		when().post("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		System.out.println("response value" +response);
		
		JsonPath json = new JsonPath(response);
		
		String id=json.getString("ID");
		
		System.out.println("The id is"+id);
		
		
		//GET BOOK API
		
		given().log().all().queryParam("ID", id).
		when().get("/Library/Addbook.php").
		then().log().all().assertThat().statusCode(200);
		
		//DELETE BOOK API
		
		given().log().all().body("{\r\n" + 
				" \r\n" + 
				"\"ID\" : \""+id+"\"\r\n" + 
				" \r\n" + 
				"} ").
		when().post("/Library/DeleteBook.php").
		then().log().all().assertThat().statusCode(200);
		
		
		
		
		
		
		
		
		
	}

}
