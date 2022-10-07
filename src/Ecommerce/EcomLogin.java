package Ecommerce;
import static io.restassured.RestAssured.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.Order;
import pojo.Orderdetails;

public class EcomLogin {

	public static void main(String[] args) {
		
		LoginRequest request= new LoginRequest();
		request.setUserEmail("roshanfaru786@gmail.cpm");
		request.setUserPassword("Hanu@96bud");
		
		//Login
		
		
		RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
 RequestSpecification Request = given().log().all().spec(req).body(request);
 
 LoginResponse Response = Request.when().post("/api/ecom/auth/login").then().log().all().assertThat().statusCode(200).extract().response().as(LoginResponse.class);
 
	System.out.println(Response.getToken());
	String Token= Response.getToken();
	
	String userid= Response.getUserId();
	System.out.println(Response.getUserId());
	System.out.println(Response.getMessage());
	//Add product
	
		
	RequestSpecification reqproduct = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",Token).build();
	RequestSpecification Request1 = given().log().all().spec(reqproduct).param("productName", "Lap").param("productAddedBy", userid).param("productCategory", "fashion")
.param("productSubCategory", "shirts").param("productPrice", "12333").param("productDescription", "Addias Originals")
.param("productFor", "men").multiPart("productImage", new File("C:\\Users\\Admin\\Pictures\\Camera Roll\\WIN_20220108_17_18_49_Pro.jpg"));
	
	String response1 = Request1.when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();
	JsonPath js=new JsonPath(response1);
	String productId=js.getString("productId");
	System.out.println(productId);
	//cREATE order
	Orderdetails o= new Orderdetails();
	o.setCountry("INDIA");
	o.setProductOrderedId(productId);

	List<Orderdetails> orders = new ArrayList<Orderdetails>();
	orders.add(o);
	Order o2= new Order();
	o2.setOrders(orders);
	RequestSpecification reqORDER = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("Authorization",Token).build();

	RequestSpecification createOrderReq=given().relaxedHTTPSValidation().log().all().spec(reqORDER).body(o2);

	String responseAddOrder = createOrderReq.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
	System.out.println(responseAddOrder);
	//Delete product
	RequestSpecification deletebase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).addHeader("Authorization",Token).build();

	RequestSpecification delete=given().log().all().spec(deletebase).queryParam("productId", productId);

	String deleteprod = delete.when().post("/api/ecom/product/delete-product/{productId}").then().log().all().extract().response().asString();
	
	JsonPath js1=new JsonPath(deleteprod);
	String Msg=js1.getString("message");
	System.out.println(Msg);
	Assert.assertEquals("Product Deleted Successfully", Msg);
	//gET oRDER DETAILS
	
	
	
	
	
	
	
	
	
	
	}

	
}
