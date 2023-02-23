package rest_Assured;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

public class Trello_Api {
	public String url = "https://trello.com/";
	public String id;
	
	@Test(priority=0)
	public void Create_Board() 
	{
		baseURI =url;
		Response response = given().queryParam("name", "Timepass1")
				  .queryParam("key","1b2cfd86362a7d8cb2f2e7e0bbfcd06a")
				  .queryParam("token", "ATTAebf4bae11426c789b2ffc2eb41b2ea39d17a8114fd8cc5030d1f2926586ed8cdF55C0328")
				  .header("Content-Type","application/json").when()
				  .post("/1/boards/").then().statusCode(200).extract().response();
		
		String jsonresponse = response.asString();
		System.out.println("\n"+jsonresponse);
		JsonPath responseBody = new JsonPath(jsonresponse);
		id = responseBody.get("id");
		System.out.println("this is id "+id);
	}
	
  @Test(priority=1)
  public void Get_Board() {
	  baseURI =url;
	  Response response = given().queryParam("key","1b2cfd86362a7d8cb2f2e7e0bbfcd06a")
			  .queryParam("token", "ATTAebf4bae11426c789b2ffc2eb41b2ea39d17a8114fd8cc5030d1f2926586ed8cdF55C0328")
			  .header("Content-Type","application/json").when()
			  .get("/1/boards/"+id).then().statusCode(200).extract().response();
	  
			String jsonresponse = response.asString();
			JsonPath responsebody = new JsonPath(jsonresponse);
			System.out.println("\nThis is get board response "+responsebody);
  }
  
  @Test(priority=2)
  public void Update_Board() 
  {
	  baseURI =url;
		Response response1 = given().queryParam("name", "Time")
				  .queryParam("key","1b2cfd86362a7d8cb2f2e7e0bbfcd06a")
				  .queryParam("token", "ATTAebf4bae11426c789b2ffc2eb41b2ea39d17a8114fd8cc5030d1f2926586ed8cdF55C0328")
				  .header("Content-Type","application/json").when()
				  .put("/1/boards/"+id).then().statusCode(200).extract().response();
		
		String jsonresponse = response1.asString();
		System.out.println("\n"+jsonresponse);
  }
  
  @Test(priority=3)
  public void Delete_Board() 
  {
	  baseURI =url;
		           given()
				  .queryParam("key","1b2cfd86362a7d8cb2f2e7e0bbfcd06a")
				  .queryParam("token", "ATTAebf4bae11426c789b2ffc2eb41b2ea39d17a8114fd8cc5030d1f2926586ed8cdF55C0328")
				  .header("Content-Type","application/json").when()
				  .delete("/1/boards/"+id).then().statusCode(200).extract().response();
		System.out.println("\nBoard was deleted successfully\n");
  }
}
