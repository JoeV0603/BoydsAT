package org.stepdefinition;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "C:\\Users\\joevi\\eclipse-workspace\\BoydsCoffee\\src\\test\\resources\\Feature\\UserReg.feature", 
                 glue = { "org.stepdefinition" }, 
                 monochrome = true, 
                 plugin = { "pretty", "html:C:\\Users\\joevi\\eclipse-workspace\\BoydsCoffee\\target//API_TestReport" })

public class RunnerClass {

}
