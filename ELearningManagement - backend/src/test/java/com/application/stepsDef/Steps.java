package com.application.stepsDef;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;

public class Steps {
  private final String BASE_URL = "http://localhost:8081";
  private static Response response;

  @When("I query the API for a list of users")
  public void queryListOfUsers() {
    RestAssured.baseURI = BASE_URL;
    RequestSpecification request = RestAssured.given();
    response = request.get("/userlist");
  }

  @Then("Then a list of users is returned")
  public void listOfUsersReturned() {
    Assert.assertEquals(200, response.getStatusCode());
  }
}
