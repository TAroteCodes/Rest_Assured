package rest_Assured;

import org.testng.annotations.Test;

import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Reqres_apis {
	public String url = "https://reqres.in/";
  

  @Test(priority=0)
  public void List_users() {
	  baseURI =url;
	  Response response = given().get("/api/users?page=2").then().statusCode(200).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is List users response: "+jsonresponse);
  }
  
  @Test(priority=1)
  public void Single_users() {
	  baseURI =url;
	  Response response = given().get("/api/users/2").then().statusCode(200).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is Single users response: "+jsonresponse);
  }
  
  @Test(priority=2)
  public void Single_user_notFound() {
	  baseURI =url;
	  given().get("/api/users/23").then().statusCode(404);
  }
  
  @Test(priority=3)
  public void List_resources() {
	  baseURI =url;
	  Response response = given().get("/api/unknown").then().statusCode(200).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is List Resources response: "+jsonresponse);
  }
  
  @Test(priority=4)
  public void single_resource() {
	  baseURI =url;
	  Response response = given().get("/api/unknown/2").then().statusCode(200).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is single Resource response: "+jsonresponse);
  }

  @Test(priority=5)
  public void Single_resource_notFound() {
	  baseURI =url;
	  given().get("/api/unknown/23").then().statusCode(404);
  }
  
  @Test(priority=6)
  public void create() {
	  baseURI =url;
	  Response response = given().queryParam("name","swap")
			  .queryParam("job", "SDET")
			  .header("Content-Type","application/json").when()
			  .post("/api/users").then().statusCode(201).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is create response: "+jsonresponse);
  }
  
  @Test(priority=7)
  public void Update() {
	  baseURI =url;
	  Response response = given().queryParam("name","Swarup")
			  .queryParam("job", "trainee")
			  .header("Content-Type","application/json").when()
			  .put("/api/users/2").then().statusCode(200).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is Update using put response: "+jsonresponse);
  }
  
  @Test(priority=8)
  public void Update_by_patch() {
	  baseURI =url;
	  Response response = given().queryParam("name","moolyaed")
			  .queryParam("job", "student")
			  .header("Content-Type","application/json").when()
			  .patch("/api/users/2").then().statusCode(200).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is Update by patch response: "+jsonresponse);
  }
  
  @Test(priority=9)
  public void delete() {
	  baseURI =url;
	  given().delete("/api/users/2").then().statusCode(204);
  }

  @Test(priority=10)
  public void register_sucess() {
	  baseURI =url;
	  Response response = given().queryParam("email","eve.holt@reqres.in")
			  .queryParam("password", "pistol")
			  .header("Content-Type","application/json").when()
			  .post("/api/register").then().statusCode(200).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is register_sucess response: "+jsonresponse);
  }
  
  @Test(priority=11)
  public void register_unsucess() {
	  baseURI =url;
	  Response response = given().queryParam("email","sydney@fife")
			  .header("Content-Type","application/json").when()
			  .post("/api/register").then().statusCode(400).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is register_unsucess response: "+jsonresponse);
  }
  
  @Test(priority=12)
  public void login() {
	  baseURI =url;
	  Response response = given().queryParam("email","eve.holt@reqres.in")
			  .queryParam("password", "cityslicka")
			  .header("Content-Type","application/json").when()
			  .post("/api/login").then().statusCode(200).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is login response: "+jsonresponse);
  }
  
  @Test(priority=13)
  public void login_unsuccess() {
	  baseURI =url;
	  Response response = given().queryParam("email","eve.holt@reqres.in")
			  .header("Content-Type","application/json").when()
			  .post("/api/login").then().statusCode(400).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is login_unsuccess response: "+jsonresponse);
  }
  
  @Test(priority=14)
  public void delayedResponse() {
	  baseURI =url;
	  Response response = given().get("/api/users?delay=3").then().statusCode(200).extract().response();
	  String jsonresponse = response.asString();
		
		System.out.println("\nThis is delayed response: "+jsonresponse);
  }
}
