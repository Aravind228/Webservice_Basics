package serialization;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class GoogleAPI_Serialization {
	
	
	@Test
	public static void Serialization() {
		
		
		List<String> mc2 = new ArrayList<String>();
		mc2.add("shoe park");
		mc2.add("shop");
		
		Location loc = new Location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		
		Mainjson mc = new Mainjson();
		mc.setAccuracy(50);
		mc.setName("Frontline house");
		mc.setPhone_number("(+91) 983 893 3937");
		mc.setAddress("29, side layout, cohen 09");
		mc.setWebsite("http://google.com");
		mc.setLanguage("French-IN");
		mc.setTypes(mc2);
		mc.setLocation(loc);
		
		RestAssured.baseURI="https://rahulshettyacademy.com";

		String res=given().queryParam("key", "qaclick123").
		body(mc).
		when().post("/maps/api/place/add/json").
		then().assertThat().statusCode(200).extract().asString();
		
		System.out.println(res);
	}

}
