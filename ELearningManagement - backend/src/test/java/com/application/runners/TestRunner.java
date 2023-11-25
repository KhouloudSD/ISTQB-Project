package com.application.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith (Cucumber.class)
@CucumberOptions(
  features = "src/test/resources/acceptanceTests",
  glue = {"com.application.stepsDef"},
  monochrome = true
)
public class TestRunner {

}
