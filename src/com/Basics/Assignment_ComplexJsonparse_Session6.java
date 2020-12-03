package com.Basics;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Files.Application_Payloads;
import com.Files.ReUsableClass;

import io.restassured.path.json.JsonPath;

public class Assignment_ComplexJsonparse_Session6 {
	
	static JsonPath json;
	static String purchase;
	
	@Test
	public static void Question1() {
		
		//courses count 
		json=ReUsableClass.rawtoJSON(Application_Payloads.payload3());
		int count=json.getInt("courses.size()");
		System.out.println("Total size of course "+count);
	}
	@Test
		public static void Question2() {

		//Return the purchase amount from payload
		String purchase=json.getString("dashboard.purchaseAmount");
		System.out.println("Purchase amount value "+purchase);
		}
	@Test
		public static void Question3() {

		String title=json.getString("courses[0].title");
		System.out.println("Title of first course "+title);
		}
	@Test
		public static void Question4() {
		int size =json.getInt("courses.size");
		
		for(int i=0;i<size;i++) {
			String name=json.getString("courses["+i+"].title");
			if(name.equalsIgnoreCase("RPA")) {
			int copies=json.getInt("courses["+i+"].copies");
			System.out.println("No. of copies sold by "+name+" is "+copies);

			}
		}

		
		}
		@Test
		public static void Question5() {
		int Total_price=0;
		int size =json.getInt("courses.size");
		for(int i=0;i<size;i++) {
			int price=json.getInt("courses["+i+"].price");
			int copies=json.getInt("courses["+i+"].copies");
			int prices = price*copies;

			Total_price = Total_price+prices;
		}
		System.out.println("Sum of total copy price "+Total_price);
		
		int total= json.getInt("dashboard.purchaseAmount");
		
		Assert.assertEquals(total, Total_price);
		
	}
	
		@Test
		public static void Question6() {
			
			int size =json.getInt("courses.size");
			
			for(int i=0;i<size;i++) {
				String title= json.getString("courses["+i+"].title");
				int price=json.getInt("courses["+i+"].price");
				System.out.println("Title is "+title+" and price is "+price);
			}
			
		}
		}

	
	
		
		
		
		
		



