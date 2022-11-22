package stepDefinations;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.*;

import POJO.Add_PlacePojoClass;
import POJO.Location;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import resourses.APIResources;
import resourses.TestDataBuild;
import resourses.Utils;

public class StepDefination extends Utils{
	ResponseSpecification res;
	RequestSpecification rs;
	Response response;
	TestDataBuild data=new TestDataBuild();
	static String placeid;
	
	
	@Given("AddPlace Payload {string} {string} {string}")
	public void add_place_payload(String name, String language, String address) throws IOException {
		
		rs=given().spec(requestSpecification()).body(data.addPlacePayload(name,language,address));
		 
	}
	
	@When("user calls {string} with http {string} request")
	public void user_calls_with_http_request(String resour, String httpRequest) {
		
		APIResources resorceAPI=APIResources.valueOf(resour);
		System.out.println(resorceAPI);
		 res=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build(); 
		
		if(httpRequest.equalsIgnoreCase("Post"))
		{
			response=rs.when().post(resorceAPI.getResource()).then().spec(res).extract().response();
		} else if(httpRequest.equalsIgnoreCase("Get")){
			
			response=rs.when().get(resorceAPI.getResource()).then().spec(res).extract().response();
		}
	}
	@Then("API call got success with status code {string}")
	public void api_call_got_success_with_status_code(String code) {
		 
		assertEquals(response.getStatusCode(),200);
	}
	@Then("{string} in response code is {string}")
	public void in_response_code_is(String key, String value) {
	  	   assertEquals(getJsonPath(response,key),value);
	}

	@Then("verify placeadded maps to {string} using {string}")
	public void verify_placeadded_maps_to_using(String expectedname, String resour) throws IOException {
	 placeid=getJsonPath(response,"place_id");
	  rs=given().spec(requestSpecification()).queryParam("place_id", placeid);
	  user_calls_with_http_request(resour, "Get");
	  assertEquals(getJsonPath(response,"name"),expectedname);
	  
	}
	

@Given("DeletePlace payload")
public void delete_place_payload() throws IOException {
    
	rs=given().spec(requestSpecification()).body(getDeletePlacePayload(placeid));
}
}
