package JIRA;

import io.restassured.RestAssured;
import io.restassured.filter.session.SessionFilter;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.testng.Assert.assertEquals;

import java.io.File;

import org.testng.Assert;

public class Addcomment {
	//LOGIN AUTHENTICATION

	public static void main(String[] args) {
		RestAssured.baseURI="http://localhost:8080";
		SessionFilter session=new SessionFilter();
		String Res=given().relaxedHTTPSValidation().log().all().header("Content-Type","application/json").body("{ \"username\": \"Roshan\", \"password\": \"abc123\" }").filter(session).
		when().post("/rest/auth/1/session").then().assertThat().statusCode(200).log().all().extract().response().asString();
		
		//CREATING AN ISSUE AND ADDING COMMNT
		String Emsg="SBK love u";
		
		String Res1 =given().pathParam("ID", "10007").log().all().header("Content-Type","application/json").body("{\r\n"
				+ "    \"body\": \""+Emsg+"\",\r\n"
				+ "    \"visibility\": {\r\n"
				+ "        \"type\": \"role\",\r\n"
				+ "        \"value\": \"Administrators\"\r\n"
				+ "    }\r\n"
				+ "}").filter(session).
		when().post("/rest/api/2/issue/{ID}/comment").then().assertThat().statusCode(201).log().all().extract().response().asString();
		JsonPath js1=new JsonPath(Res1);
				String Eid=js1.get("id");
				System.out.println(Eid);
		
		//ADD attachment
		
		given().log().all().header("X-Atlassian-Token","no-check")
		.pathParam("ID", "10007").header("Content-Type","multipart/form-data").
		filter(session).multiPart("file", new File("C:\\Users\\Admin\\eclipse-workspace\\DEMO2\\src\\Library\\test"))
		.when().post("/rest/api/2/issue/{ID}/attachments").then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		// get issue deatils
		String isuuedetails=given().log().all().pathParam("ID", "10007").filter(session).queryParam("fields", "comment")
				.when().get("/rest/api/2/issue/{ID}").then().log().all().assertThat().statusCode(200).extract().response().asString();
		System.out.println(isuuedetails);
		
		JsonPath js =new JsonPath(isuuedetails);
		int count=js.get("fields.comment.comments.size()");
		System.out.println(count);
		String Amsg;
		for(int i=0;i<count;i++)
		{
			String Aid=js.get("fields.comment.comments["+i+"].id").toString();
			if(Aid.equalsIgnoreCase(Eid))
			{
				 Amsg=js.get("fields.comment.comments["+i+"].body").toString();
				 System.out.println(Amsg);
				Assert.assertEquals(Amsg, Emsg);
		}
		
		

	}

	}}
