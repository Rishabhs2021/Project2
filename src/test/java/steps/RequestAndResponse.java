package steps;

import java.util.HashMap;
import java.util.Map;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RequestAndResponse {
	
	private RequestSpecification request;
	private Response response;
	private String jsonString;
	private String expectedName = "Luke Skywalker";

	@Given("a People API Base URI")
	public void givenPeopleBaseUri() {
		
		RestAssured.baseURI = "https://swapi.dev/api";
		request = RestAssured.given();
	}

	@When("make a GET call")
	public void getCall() {
		
		response = request.get("/people/1/").then().statusCode(200).log().body().extract().response();
	}


	@Then("validate the person name")
	public void validatePersonName() {
		
		jsonString = response.asString();
		Map<String, String> peopleMap = JsonPath.from(jsonString).get();
		String actualName = peopleMap.get("name");
		Assert.assertEquals(actualName, expectedName, "Actual name in the response '"+actualName+"' is not matching with expected name '"+expectedName+"'");
	}
	
	@Given("a Users API Base URI")
	public void givenUsersBaseUri() {
		
		RestAssured.baseURI = "https://reqres.in/api";
		request = RestAssured.given();
	}
	
	@Then("make a POST call")
	public void postCall() {
		
		Map<String, String> data = new HashMap<String, String>();
		data.put("name", "Rishabh Saharan");
		data.put("title", "QA Lead");
		
		request.header("Content-Type", "application/json");
		response = request.body(data)
							.post("/users")
							.then().statusCode(201).log().body().extract().response();
	}


}
