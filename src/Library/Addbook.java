package Library;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Files.Payloads;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

public class Addbook {
	//private static final String ReusableMethods = null;

	@Test(dataProvider="booksdata")
	public void addbook(String abc, int  b)
	{
		RestAssured.baseURI="http://216.10.245.166";
		String res=given().log().all().header("Content-Type","application/json").body(Payloads.addbook(abc,b)).
		when().post("Library/Addbook.php").then().assertThat().statusCode(200).extract().response().asString();

		
		//JsonPath js1= ReusableMethods.rawToJson(res);
		JsonPath js=new JsonPath(res);
		
	String msg=js.get("ID");
		System.out.println(msg);
		
		
	}
	@DataProvider(name="booksdata")
	public Object[][] getdata()
	{
		return new Object[][] {{"ab6cd",14234},{"pq6rt",34656},{"qw66r",4597},{"er6yty",8997}};
	}

}
