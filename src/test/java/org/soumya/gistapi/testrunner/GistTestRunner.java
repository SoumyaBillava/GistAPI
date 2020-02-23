package org.soumya.gistapi.testrunner;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue = {"classpath:org/soumya/gistapi/steps"},features = {"src/test/features"})

public class GistTestRunner {
}
