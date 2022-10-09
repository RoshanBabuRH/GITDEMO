package demo;
import static io.restassured.RestAssured.given;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.hc.core5.util.Asserts;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;


public class Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver;
	WebDriverManager.chromedriver().setup();
		
		 driver = new ChromeDriver();
		 driver.close();
		

		/* System.setProperty("webdriver.chrome.driver", "C://chromedriver.exe");
		
		//driver.get("https://rahulshettyacademy.com/AutomationPractice/");
		driver.get("https://accounts.google.com/o/oauth2/v2/auth?scope=https://www.googleapis.com/auth/userinfo.email&auth_url=https://accounts.google.com/o/oauth2/v2/auth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https://rahulshettyacademy.com/getCourse.php&state=verifyfjdss");
		
		driver.findElement(By.cssSelector("[type='email']")).sendKeys("dfsf");
		driver.findElement(By.cssSelector("[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("[type='password']")).sendKeys("vxcvd");
		driver.findElement(By.cssSelector("[type='password']")).sendKeys(Keys.ENTER);
		
		Thread.sleep(5000);*/
		
		String[] a= {"Selenium Webdriver Java","Cypress","Protractor"};
		
		//driver.get("https://rahulshettyacademy.com/getCourse.php?state=roshan&code=4%2F0AdQt8qgLdTCWHI6vk4vAjT-rogcpPUHA3O3uTR-H0mnnGwz2Gl9Oxy9akfHTbHzVtPBAyQ&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=consent");
		String url ="https://rahulshettyacademy.com/getCourse.php?state=roshan&code=4%2F0AdQt8qgsxGQylA8e_zwww49nebKgcSCxTWUgfVro8ivrRoNU2sYzsYwWRU0t6E52bWkGMg&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		//String url=driver.getCurrentUrl();
		System.out.println(url);
		String partialcode=url.split("code=")[1];
		String code=partialcode.split("&scope")[0];
		
		System.out.println("code is+++ ");
		System.out.println(code );
		
		
	
		
		String response =
                given()  
                .urlEncodingEnabled(false)
                       .queryParams("code",code)
                
                   .queryParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                        .queryParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                        .queryParams("grant_type", "authorization_code")
                        .queryParams("state", "roshan")
                        .queryParams("session_state", "ff4a89d1f7011eb34eef8cf02ce4353316d9744b..7eb8")
                     .queryParam("scope", "email+openid+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email")
                        
                        .queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
                        .when().log().all()
                        .post("https://www.googleapis.com/oauth2/v4/token").asString();
	System.out.println(response);
		 JsonPath jsonPath = new JsonPath(response);
		    String accessToken = jsonPath.getString("access_token");
		    System.out.println(accessToken);
		GetCourse r2=    given().contentType("application/json").
				 queryParams("access_token", accessToken).expect().defaultParser(Parser.JSON)
				 .when()
           .get("https://rahulshettyacademy.com/getCourse.php")
		.as(GetCourse.class);
		System.out.println(r2.getInstructor());
		System.out.println(r2.getLinkedIn());
		int count = r2.getCourses().getWebAutomation().size();
		for (int i=0;i<count;i++)
		{
		String Cname = r2.getCourses().getWebAutomation().get(i).getCourseTitle();
		System.out.println(Cname);
		}
		int count2 = r2.getCourses().getWebAutomation().size();
		/*for(int i=0;i<count2;i++)
		{
			if(r2.getCourses().getApi().get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing"))
			{
				System.out.println(r2.getCourses().getApi().get(i).getPrice());
			}
		}*/
		ArrayList<String> b = new ArrayList<String>();
		
		List<WebAutomation> w = r2.getCourses().getWebAutomation();
		for(int i=0;i<count2;i++)
		{
			b.add(w.get(i).getCourseTitle());
			
		}
		
		
		List<String> d = Arrays.asList(a);
		  
		Asserts.assertTrue(b.equals(d));
		
		
		
		
		
		
		
		
	}
	
	

}
