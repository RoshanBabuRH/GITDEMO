package Maps;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import Maps.Locations;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.List;

public class Specsbuilder {
	public static void main(String[] args) {
	
	//RestAssured.baseURI= "https://rahulshettyacademy.com";
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
	
	RequestSpecification req= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("Key", "qaclick123").setContentType(ContentType.JSON).build();
	ResponseSpecification Res= new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
	
	RequestSpecification res=given().spec(req).body(A);
	String response = res.when().post("/maps/api/place/add/json").then().spec(Res).extract().response().asString();

	System.out.println(response);
	
	

	
	

	
	}	

}
