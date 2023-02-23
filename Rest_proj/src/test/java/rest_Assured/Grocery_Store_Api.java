package rest_Assured;

import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class Grocery_Store_Api {
	public String url = "https://simple-grocery-store-api.glitch.me";
	public String C_id,i_id,token;

@Test(priority=0)
public void API_STATUS() 
{
	baseURI =url;
	Response response = given().get("/status").then().statusCode(200).extract().response();
	String jsonresponse = response.asString();
	System.out.println("\nThis is Status Code response: "+jsonresponse);
}

@Test(priority=1)
public void GET_ALL_PRODUCTS()
{
	baseURI =url;
	Response response = given().get("/products").then().statusCode(200).extract().response();
	String jsonresponse = response.asString();
	System.out.println("\nThis is get all products response: "+jsonresponse);
}
@Test(priority=2)
public void get_single_product()
{
	baseURI=url;
	Response response = given().get("/products/4646").then().statusCode(200).extract().response();
	String jsonresponse = response.asString();
	System.out.println("\nThis is get single products response: "+jsonresponse);
}

@Test(priority=3)
public void CREATE_A_NEW_CART()
{
	baseURI=url;
	Response response = given().post("/carts").then().statusCode(201).extract().response();
	String jsonresponse = response.asString();
	System.out.println("\nThis is Create cart response: "+jsonresponse);
	JsonPath responseBody = new JsonPath(jsonresponse);
	C_id = responseBody.get("cartId");
}

@Test(priority=4)
public void GET_CART()
{
	baseURI=url;
	Response response = given().get("/carts/" + C_id).then().statusCode(200).extract().response();
	String jsonresponse = response.asString();
	System.out.println("\nThis is get cart response: "+jsonresponse );
}

@Test(priority=4)
public void ADD_ITEM_TO_CART()
{
	baseURI=url;
	Response response = given().queryParam("productId","1225").queryParam("productId","4641").header("Content-Type","application/json")
			.when().post("/carts/" + C_id +"/items").then().statusCode(201).extract().response();
	String jsonresponse = response.asString();
	JsonPath responseBody = new JsonPath(jsonresponse);
	i_id = responseBody.get("itemId");
	System.out.println("\nThis is add item to cart response: "+jsonresponse);
}

@Test(priority=5)
public void GET_CART_ITEMS()
{
	baseURI=url;
	Response response = given().get("/carts/" + C_id +"/items").then().statusCode(200).extract().response();
	String jsonresponse = response.asString();
	System.out.println("\nThis is get cart items response: "+jsonresponse );
}

@Test(priority=6)
public void UPDATE_QUANTITY()
{
	baseURI=url;
	Response response = given().queryParam( "quantity","5").header("Content-Type","application/json")
			.when().patch("/carts/" + C_id +"/items/"+i_id).then().statusCode(200).extract().response();
	String jsonresponse = response.asString();
	System.out.println("\nThis is update quantity response: "+jsonresponse);
}

@Test(priority=7)
public void REPLACE_PRODUCT_IN_CART()
{
	baseURI=url;
	Response response = given().queryParam( "productId", "4646")
			.queryParam( "quantity", "4")
			.header("Content-Type","application/json")
			.when().put("/carts/" + C_id +"/items/"+i_id).then().statusCode(200).extract().response();
	String jsonresponse = response.asString();
	System.out.println("\nThis is replace product in cart response: "+jsonresponse);
}

@Test(priority=8)
public void DELETE_ITEM_IN_CART()
{
	baseURI=url;
	Response response = given().delete("/carts/" + C_id +"/items/"+i_id).then().statusCode(200).extract().response();
	String jsonresponse = response.asString();
	System.out.println("\nThis is delete item response: "+jsonresponse);
}

@Test(priority=9)
public void REGISTER_API_CLIENT() 
{
	baseURI=url;
	Response response = given().queryParam("clientName","Restcomputer")
			.queryParam("clientEmail","sam87@example.com")
			.header("Content-Type","application/json")
			.when().post("/api-clients").then().statusCode(201).extract().response();
	String jsonresponse = response.asString();
	JsonPath responseBody = new JsonPath(jsonresponse);
	token = responseBody.get("accessToken");
	System.out.println("\nThis is register api client response: "+jsonresponse);
}
}