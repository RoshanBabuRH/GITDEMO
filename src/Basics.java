import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import Files.Payloads;
public class Basics {

	public static void main(String[] args) throws IOException {
		
		// TODO Auto-generated method stub
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		String Response=given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json")
		.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\Admin\\Desktop\\Practice\\new 1.txt")))).when().post("maps/api/place/add/json").then().statusCode(200).body("scope",equalTo("APP"))
		.header("Server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
	System.out.println(Response);
		
		
		JsonPath js=new JsonPath(Response);
		String PlaceID = js.getString("place_id");
		
		System.out.println(PlaceID);
		//Update place
		String Nplace="DVG";
		String Upplace = given().log().all().queryParam("key", "qaclick123").header("Content-Type","application/json").body("{\r\n"
				+ "\"place_id\":\""+PlaceID+"\",\r\n"
				+ "\"address\":\""+Nplace+"\",\r\n"
				+ "\"key\":\"qaclick123\"\r\n"
				+ "}")
		.when().put("maps/api/place/update/json").
		then().log().all().assertThat().statusCode(200).extract().response().asString();
		//.body("msg", equalTo("Address successfully updated")).extract().response().asString();
		System.out.println(Upplace);
	}
}
		
/*JsonPath js3= new JsonPath(Upplace);
		String Msg=js3.get("msg");
		
		System.out.println(Msg);
		
		//Get place
		String aplace=given().queryParam("key", "qaclick123").queryParam("place_id",PlaceID )
		.when().get("maps/api/place/get/json").then().log().all().statusCode(200).extract().asString();
		JsonPath js1=new JsonPath(aplace);
		String aplace1 = js1.get("address");
		System.out.println(aplace1); 
		
		
			
			
			
			
			
		

		
		

	}

}*/
