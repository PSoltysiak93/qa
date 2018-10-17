package pl.jsystems.qa.frontend.cucumber;


import org.junit.runner.RunWith;
import cucumber.api.junit.Cucumber;
import cucumber.api.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/",
    glue = "classpath:pl.jsystems.qa.frontend.cucumber",
    plugin = {"html:target/cucumber-html-report", "rerun:target/rerun.txt"},
    tags = {
            "@window", //odpala wszystkie test oznaczone takim tagiem
            "@windowtest"

    }
)
public class RunTest {


}
