package com.Basics;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Files.Application_Payloads;
import com.Files.ReUsableClass;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
//This is API 2 class

public class API_2_Parameterization {
	
	
	//Add_book_Normal values passing through payload through random values generated
	@Test(enabled=false)
	public static void Add_book1() {
		RestAssured.baseURI="http://216.10.245.166";
		String AddBook=given().log().all().header("Content-Type","application/json")
		.body(Application_Payloads.payload4_Random_No_Generate())
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.body("Msg", equalTo("successfully added"))
		.header("Server", "Apache").extract().response().asString();
		
		JsonPath json=ReUsableClass.rawtoJSON(AddBook);
		String output =json.getString("Msg");
		
		System.out.println("output message"+output);
	}
	//Add_book_values passing to payload through dataprovider
	@Test(dataProvider="Books_data")
	public static void Add_book2(String isbn,String aisle) {
		RestAssured.baseURI="http://216.10.245.166";
		String AddBook=given().log().all().header("Content-Type","application/json")
		.body(Application_Payloads.payload4(isbn,aisle))
		.when().post("/Library/Addbook.php")
		.then().log().all().assertThat().statusCode(200)
		.body("Msg", equalTo("successfully added"))
		.header("Server", "Apache").extract().response().asString();
		
		JsonPath json=ReUsableClass.rawtoJSON(AddBook);
		String output =json.getString("Msg");
		
		System.out.println("output message"+output);
	}
	
	@DataProvider(name="Books_data")
	public static Object[][] dataprovider_method() {
		Object[][] obj=new Object[][] {{"Book1_ABC123","Book1_ABC1231"},{"Book2_ABC123","Book2_ABC1231"},{"Book3_ABC123","Book3_ABC1231"}};
		return obj;
	}
	
	
	
	//do tommorow
	//testng parameterization with dataprovider for add book and delete book
	
	

}
