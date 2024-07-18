package demo;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;

import pojo.LoginRequest;
import pojo.LoginResponse;
import pojo.OrderDetails;

import pojo.Orders;
import pojo.ViewOrder;
import pojo.ViewOrderDetails;

import static io.restassured.RestAssured.given;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

public class EcommerceAPITest {

	public static void main(String[] args) 
	{
		// TODO Auto-generated method stub
		
	RequestSpecification req = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
			setContentType(ContentType.JSON).build();
	
	LoginRequest loginRequest = new LoginRequest();
	loginRequest.setUserEmail("vineethavijayan234@gmail.com");
	loginRequest.setUserPassword("Vineetha@123");
	RequestSpecification reqLogin = given().log().all().spec(req).body(loginRequest);
	
	LoginResponse loginResponse = reqLogin.when().post("/api/ecom/auth/login").then().log().all().extract().response().
			as(LoginResponse.class);
	String token = loginResponse.getToken();
	System.out.println(token);
	System.out.println(loginResponse.getUserId());
	String userId = loginResponse.getUserId();
	
	//architect1
	System.out.println(token);
	System.out.println(token);
	System.out.println(token);
	System.out.println(token);
	
	//Add Product
	
	RequestSpecification addProductReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
			addHeader("Authorization", token).build();
	RequestSpecification reqAddProduct = given().log().all().spec(addProductReq).param("productName", "Laptop").param("productAddedBy", userId).
	param("productCategory", "Equipment")
	.param("productSubCategory", "Screen").param("productPrice", "50000").
	param("productDescription", "Good quality").param("productFor", "All")
	.multiPart("productImage",new File("C://Vineetha//Laptopimg.jpg"));
	
	String addProductResponse = reqAddProduct.when().post("/api/ecom/product/add-product")
	.then().log().all().extract().response().asString();
	
	JsonPath js = new JsonPath(addProductResponse);
	String productId = js.get("productId");
	System.out.println(productId);
	
	//Create order for the particular product
	
	RequestSpecification createOrderReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
			addHeader("Authorization", token).setContentType(ContentType.JSON).build();
	
	OrderDetails orderDetails = new OrderDetails();
	orderDetails.setCountry("India");
	orderDetails.setProductOrderedId(productId);
	
	List<OrderDetails> orderDetailList =new ArrayList<OrderDetails>();
	orderDetailList.add(orderDetails);
	Orders orders = new Orders();
	orders.setOrders(orderDetailList);
	
	RequestSpecification createOrderRequirment = given().log().all().spec(createOrderReq).body(orders);
	String responseCreateOrder = createOrderRequirment.when().post("/api/ecom/order/create-order").then().log().all().extract().response().asString();
	//System.out.println(responseCreateOrder);
	JsonPath js2 = new JsonPath(responseCreateOrder);
	String msg = js2.get("message");
	System.out.println(msg);
	List<String> orderIds = js2.getList("orders");
	String orderId = orderIds.get(0);
	System.out.println(orderId);
	
	//view order details
	
	RequestSpecification viewOrderReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
			addHeader("Authorization", token).setContentType(ContentType.JSON).build();
	RequestSpecification viewOrderRequirment = given().log().all().spec(viewOrderReq).queryParam("id", orderId);
	
	//String viewOrderResponse = viewOrderRequirment.when().get("/api/ecom/order/get-orders-details").then().log().all().
	//statusCode(200).extract().response().asString();
	//System.out.println(viewOrderResponse);
	ViewOrder viewOrder = viewOrderRequirment.when().get("/api/ecom/order/get-orders-details").then().log().all().
			statusCode(200).extract().response().as(ViewOrder.class);
	System.out.println(viewOrder.getMessage());
	System.out.println(viewOrder.getData().getProductName());
	System.out.println(viewOrder.getData().getOrderPrice());
	System.out.println(viewOrder.getData().getOrderBy());
	System.out.println(viewOrder.getData().getProductImage());
	System.out.println(viewOrder.getData().get__v());
	
	//Delete product
	RequestSpecification deleteProductReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").
			addHeader("Authorization", token).setContentType(ContentType.JSON).build();
	
	RequestSpecification deleteProductRequirement = given().log().all().spec(deleteProductReq).pathParam("productId", productId);
	String deleteProductResponse = deleteProductRequirement.when().delete("/api/ecom/product/delete-product/{productId}").
	then().log().all().extract().response().asString();
	
	JsonPath js1 = new JsonPath(deleteProductResponse);
	String message = js1.get("message");
	Assert.assertEquals("Product Deleted Successfully", message);
	}
	

}
