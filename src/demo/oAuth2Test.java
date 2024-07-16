package demo;
import static io.restassured.RestAssured.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.restassured.path.json.JsonPath;
public class oAuth2Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		
		/*System.setProperty("webdriver.chrome.driver", "C:\\Users\\Vineetha\\Downloads\\chromedriver_win32\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.get("https://accounts.google.com/o/oauth2/v2/auth/oauthchooseaccount?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email&Auth_url=https%3A%2F%2Faccounts.google.com%2Fo%2Foauth2%2Fv2%2Fauth&client_id=692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com&response_type=code&redirect_uri=https%3A%2F%2Frahulshettyacademy.com%2FgetCourse.php&state=verifyfjdss&service=lso&o2v=2&ddm=0&flowName=GeneralOAuthFlow");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys("vineethavijayan234");
		driver.findElement(By.cssSelector("input[type='email']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys("");
		driver.findElement(By.cssSelector("input[type='password']")).sendKeys(Keys.ENTER);
		Thread.sleep(4000);
		String url = driver.getCurrentUrl();*/
		
		String url ="https://rahulshettyacademy.com/getCourse.php?state=verifyfjdss&code=4%2F0ATx3LY4OIiGeeCfb0JOKSztwkDMvmaYRtiOGIN6UW5FCtFt_8m8mZ29vZymQEhE5l_ieVA&scope=email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+openid&authuser=0&prompt=none";
		url.split("code=");
		String partialCode = url.split("code=")[1];
		String code =partialCode.split("&scope")[0];
		System.out.println(code);
		System.out.println(code);
		System.out.println(code);
		System.out.println(code);
		System.out.println(code);
		System.out.println(code);
		
		String accessTokenResponse = given().urlEncodingEnabled(false)
		.queryParams("code",code)
		.queryParams("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
		.queryParams("client_secret","erZOWM9g3UtwNRj340YYaK_W")
		.queryParams("redirect_uri", "https://rahulshettyacademy.com/getCourse.php")
		.queryParams("grant_type","authorization_code")
		.when().log().all()
		.post("https://www.googleapis.com/oauth2/v4/token").asString();
		JsonPath js = new JsonPath(accessTokenResponse);
		String accessToken = js.getString("access_token");
		
		
		
		String response = given().queryParam("access_token", accessToken)
		.when().log().all()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		System.out.println(response);
		
	}

}
