package Maps;

import io.restassured.RestAssured;
import Maps.Locations;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Searilaze {
	public static void main(String[] args) {
	
	RestAssured.baseURI= "https://rahulshettyacademy.com";
	Adress A =new Adress();
	A.setAccuracy(50);
	A.setAddress("hiriyur");
	A.setLanguage("Kannada");
	A.setName("Ro");
	A.setPhone("(90)1234");
	A.setWebsite("ancv.com");
	
	List<String> list=new ArrayList<String>();
	list.add("Ros");
	list.add("Nbab");
	A.setTypes(list);
	
	Locations l =new Locations();
	l.setLat(-1234);
	l.setLng(-5647.908);
	A.setLocation(l);
	
	String res=given().log().all().queryParam("Key", "qaclick123").body(A).
	when().post("/maps/api/place/add/json").then().log().all().assertThat().statusCode(200).extract().response().asString();
	System.out.println(res);
	

	
	

	
	}	

}
