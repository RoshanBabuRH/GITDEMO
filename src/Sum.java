import static org.testng.Assert.assertEquals;

import org.testng.Assert;
import org.testng.annotations.Test;

import Files.Payloads;
import io.restassured.path.json.JsonPath;

public class Sum {
	@Test
	public void sum1()
	{
		
		JsonPath js= new JsonPath(Payloads.mockjson());
		//Print No of courses returned by API
		 int coursenum=js.getInt("courses.size()");
		 System.out.println(coursenum);
		 // Print Purchase Amount

	int Pamount=js.getInt("dashboard.purchaseAmount");
	System.out.println(Pamount);
	

	  int sum = 0;
	  int total=0;
	 
	for(int i=0;i<coursenum;i++)
	{
		  int cprice=js.getInt("courses["+i+"].price");
		  int cps=js.getInt("courses["+i+"].copies");
		  int sum1=(cprice*cps);
		  
		   sum=sum+sum1;
		   
	}
Assert.assertEquals(sum, Pamount);
	
	/*System.out.println(sum);
	if(Pamount==sum)
	{
		System.out.println("matching");
		
	}
	else
		System.out.println("Not matching");*/



		
	}

}
