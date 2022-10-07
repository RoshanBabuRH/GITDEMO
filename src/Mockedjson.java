import Files.Payloads;
import io.restassured.path.json.JsonPath;

public class Mockedjson {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		
	JsonPath js= new JsonPath(Payloads.mockjson());
	//Print No of courses returned by API
	 int coursenum=js.getInt("courses.size()");
	 System.out.println(coursenum);
	 // Print Purchase Amount

int Pamount=js.getInt("dashboard.purchaseAmount");
System.out.println(Pamount);


//Print Title of the first course
String title=js.get("courses[0].title");
System.out.println(title);

//Print All course titles and their respective Prices
  for(int i=0;i<coursenum;i++)
	  
  {
	   //String a =js.get("courses["+i+"].title");
	   //System.out.println(a);
	   
	System.out.println(js.get("courses["+i+"].title").toString());
	System.out.println( js.get("courses["+i+"].price").toString()); 
	
  }
  
  //Print no of copies sold by RPA Course
  System.out.println("copies of courses");
  
  
for(int i=0;i<coursenum;i++)
{
	  String cname=js.get("courses["+i+"].title");
	  if(cname.equalsIgnoreCase("RPA"))
	  {
		int n=(js.get("courses["+i+"].copies"));
		System.out.println(n);
		break;
	  }
	  
}
//Verify if Sum of all Course prices matches with Purchase Amount



  int temp = 0;
  int total=0;
 
for(int i=0;i<coursenum;i++)
{
	  int cprice=js.getInt("courses["+i+"].price");
	  int cps=js.getInt("courses["+i+"].copies");
	  int sum1=(cprice*cps);
	  
	   total=total+sum1;
	    temp=total;	   
}
System.out.println(temp);
if(Pamount==temp)
{
	System.out.println("matching");
	
}
else
	System.out.println("Not matching");








	}
	

}

