package com.OAuth2.o;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.codehaus.groovy.transform.EqualsAndHashCodeASTTransformation;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.Files.ReUsableClass;

import deserialization.MainJson;
import deserialization.WebAutomation;


public class OAuth2 {

	@Test
	public void oAuth2() {
		//THis authorization code is fetched from the browser
		String authorization_code ="4%2F4AHqn8hRRJNmg4PQI_4cboIj30d45LlxnLSuiLDzeNWthMlhtUY7eXicg15-trrQdpZP7e6wG6FXDGjUie1CwXQ";
		
		
		/*//To generate authorization code
		Map<String,String> mapobj = new HashMap<String,String>();
		mapobj.put("scope", "https://www.googleapis.com/auth/userinfo.email");
		mapobj.put("Auth_url", "https://accounts.google.com/o/oauth2/v2/auth");
		mapobj.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
		mapobj.put("redirect_uri", "https://rahulshettyacademy.com/getCourse.php");
		mapobj.put("State", "verify");
		
		String response=given().queryParams(mapobj).
		when().get("https://accounts.google.com/o/oauth2/v2/auth").asString();
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get(response);
		Thread.sleep(5000);
		String authori_url=driver.getCurrentUrl();*/
		
		
		
		//TO generate access token using authorization code
		Map<String,String> mapobj = new HashMap<String,String>();
		mapobj.put("code", authorization_code);
		mapobj.put("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com");
		mapobj.put("client_secret","erZOWM9g3UtwNRj340YYaK_W");
		mapobj.put("redirect_uri", "https://rahulshettyacademy.com/getCourse.php");
		mapobj.put("grant_type", "authorization_code");
		
		
		String accesscodeBody=given().log().all().urlEncodingEnabled(false)
		.queryParams(mapobj)
		.when().log().all().post("https://www.googleapis.com/oauth2/v4/token")
		.asString();
		
		JsonPath json=ReUsableClass.rawtoJSON(accesscodeBody);
		
		String Accesstoken=json.getString("access_token");
		System.out.println(Accesstoken);
		
		//To signin into rahulshetty url using accesstoken
		
		/*given().log().all().queryParam("access_token", Accesstoken)
		.when().log().all().get("https://rahulshettyacademy.com/getCourse.php")
		.then().assertThat().statusCode(200)
		.body("instructor",equalTo("RahulShetty"));*/
		
		//Using pojo class deserialization converting json response to pojo variables
		
		MainJson mj=given().queryParam("access_token", Accesstoken).expect().defaultParser(Parser.JSON)
		.when().get("https://rahulshettyacademy.com/getCourse.php").as(MainJson.class);
		
		System.out.println(mj.getInstructor());
		System.out.println(mj.getLinkedIn());
		
		//Assignment: To print all course titles of webautomation
		
		List<WebAutomation> webobj=mj.getCourses().getWebAutomation();
		List<String> actual_list = new ArrayList<String>();
		
		List<String> Expected_list = new ArrayList<String>();
		Expected_list.add("Selenium Webdriver Java");
		Expected_list.add("Cypress");
		Expected_list.add("Protractor");


		int a=1;
		for(int i =0;i<webobj.size();i++) {
			
			actual_list.add(webobj.get(i).getCourseTitle());
			System.out.println("Course title of "+a++ +" is "+webobj.get(i).getCourseTitle());
		}
		Assert.assertTrue(Expected_list.equals(actual_list),"Yes!Expected list and actual list are equal");
		
		
		
		
		
		
		
		
		
	}

}
