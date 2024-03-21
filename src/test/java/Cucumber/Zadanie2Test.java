package Cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/Cucumber/Features/Zadanie2.feature"},
        plugin = {"pretty", "html:report.html"}
)
public class Zadanie2Test {
    public Zadanie2Test() {
    }
}