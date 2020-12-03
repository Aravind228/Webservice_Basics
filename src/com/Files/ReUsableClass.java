package com.Files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;

public class ReUsableClass {
	
	
	public static JsonPath rawtoJSON(String value) {
		JsonPath js = new JsonPath(value);
		return js;
		}
	
	public static String generate_string_from_Notepad(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
		}

}
