package demo;
import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import pojo.Api;
import pojo.GetCourse;
import pojo.WebAutomation;
public class oAuthTest {

	public static void main(String[] args) 
	{
		String[] webactualCourseTitles = {"Selenium Webdriver Java","Cypress","Protractor"};
		
		// TODO Auto-generated method stub
		String response = given().formParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com").
		formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W").
		formParam("grant_type", "client_credentials").
		formParam("scope", "trust").when().log().all().post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
		.asString();
		
		System.out.println(response);
		
		//architect1
		System.out.println(response);
		System.out.println(response);
		System.out.println(response);
		System.out.println("it just a sample for git branch");
		
		JsonPath js = new JsonPath(response);
		String accessToken = js.getString("access_token");
		
		//To get the Json response of course Details
		
	/*    	String response2  = given().queryParam("access_token",accessToken).
			when().log().all().
			get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").asString();
			System.out.println(response2);     */
	
		// Using Pojo class how to deserialization process and how to retrive the data
		
		GetCourse gc  = given().queryParam("access_token",accessToken).
				when().log().all().
				get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);
				System.out.println(gc.getLinkedIn());
				System.out.println(gc.getInstructor());
				//System.out.println(gc.getUrl());
				//System.out.println(gc.getServices());
				//System.out.println(gc.getExpertise());
				
				//Print the price from the Api -->course title --->SoapUI Webservices testing
				
				//System.out.println(gc.getCourses().getApi().get(1).getCourseTitle());
				String actualCourseTitle = gc.getCourses().getApi().get(1).getCourseTitle();
				System.out.println(actualCourseTitle);
				List<Api> apiCourses = gc.getCourses().getApi();
				for(int i=0; i<apiCourses.size() ;i++)
				{
					if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase(actualCourseTitle))
					{
						System.out.println(apiCourses.get(i).getPrice());
					}
				}

				//Print all course title of the web automation
			/*	List<WebAutomation> webAutCourses = gc.getCourses().getWebAutomation();
				for (int i=0; i<webAutCourses.size(); i++)
				{
					System.out.println(webAutCourses.get(i).getCourseTitle());
				}    */
				
				// print course titles ot webautomation using the actual array comparison
				ArrayList<String> a = new ArrayList<String>();
				List<WebAutomation> webAutCourses = gc.getCourses().getWebAutomation();
				for (int i=0; i<webAutCourses.size(); i++)
				{
					a.add(webAutCourses.get(i).getCourseTitle());
				} 
				List<String> expListCourses = Arrays.asList(webactualCourseTitles);
				Assert.assertTrue(a.equals(expListCourses));
	}

}
