import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static org.hamcrest.Matchers.*;

import Files.Payloads;

public class Update {

	public static void main(String[] args) {
		
		JsonPath js= new JsonPath(Payloads.mockjson());
		//Print No of courses returned by API
		 int coursenum=js.getInt("courses.size()");
		 System.out.println(coursenum);
		for(int i=0;i<coursenum;i++)
		{
			  String cname=js.get("courses["+i+"].title");
			  if(cname.equalsIgnoreCase("Cypress"))
			  {
				int n=(js.get("courses["+i+"].copies"));
				System.out.println(n);
			  }
			  
		}

		// TODO Auto-generated method stub

	}

}
