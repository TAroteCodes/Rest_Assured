package rest_Assured;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import junit.framework.Assert;
import static org.hamcrest.Matchers.*;

import org.json.simple.JSONObject;
public class Class_1 {
	public String url="https://reqres.in/api/users?page=2";  //setting url
	
	@Test(enabled=false)
	public void User_List() 
	{
	  Response rep= get(url);         //going to url using rest assured
	  int status_code=rep.statusCode();           //storing status code
	  System.out.println(status_code);            //printing status coad
	  Assert.assertEquals(status_code, 201);      // hard assertion 
	  System.out.println(rep.asString());         //print response in the string format
	  System.out.println(rep.getBody());          //print body of request
	}
	  @Test(enabled = false)
	  public void responce() 
	  {
		given().get(url).then().statusCode(200).body("data.first_name",hasItems("Rachel")).body("data.id[0]",equalTo(7)).log().all();   //assertion for status coad and first name,id in response
	  }
	
	 @Test(enabled = false)
	 public void post_method() 
	 {
		 JSONObject js=new JSONObject();
		 js.put("name", "Swapnil");
		 js.put("job", "SDET");
		 given().body(js.toJSONString()).when().post("https://reqres.in/api/users").then().statusCode(201).log().all();
		 System.out.println(js.toJSONString());
	 }
	 
	 @Test(enabled = false)
	 public void Put() 
	 {
		 JSONObject js=new JSONObject();
		 js.put("name", "Swapnil");
		 js.put("job", "senior SDET");
		 given().body(js.toJSONString()).when().put("https://reqres.in/api/users/2").then().statusCode(200).log().all();
		 System.out.println(js.toJSONString());
	 }
	 
	 @Test
	 public void delete() 
	 {
		 given().delete("https://reqres.in/api/users?page=2").then().statusCode(204).log().all();
	 }
	  
}
