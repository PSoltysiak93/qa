package pl.jsystems.qa.frontend.cucumber.steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pl.jsystems.qa.frontend.cucumber.CucumberStepConfig;
import pl.jsystems.qa.frontend.cucumber.page.DiaryTestPage;

import java.util.Set;

import static junit.framework.TestCase.assertTrue;

public class WindowSteps {



        WebDriver driver;
    String firstPageWindowHandle;
    String secondTestWindowHandle;

        public WindowSteps(CucumberStepConfig config) {
            try {
                driver = config.setUp();
            }catch (Exception e){
                    System.out.println(e.toString());
            }
        }
    DiaryTestPage diaryTestPage;

    @Given("^User is one main page$")
    public void userIsOneMainPage() {
        driver.get("http://www.testdiary.com/training/selenium/selenium-test-page/");

        firstPageWindowHandle = driver.getWindowHandle();

        diaryTestPage = new DiaryTestPage(driver);


        diaryTestPage.windowFrame.isDisplayed();

        int hyperLinkYCord = diaryTestPage.windowFrame.getLocation().getY();
        int hyperLinkXCord = diaryTestPage.windowFrame.getLocation().getX();

        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(" + hyperLinkXCord + "," + hyperLinkYCord + ")");

        new WebDriverWait(driver,30).until(ExpectedConditions.elementToBeClickable(By.linkText("Open page in a new window")));

        assertTrue(true);

    }

    @When("^User press link$")
    public void userPressLink(){
        diaryTestPage.windowFrame.click();
        assertTrue(true);
    }




    @Then("^User is switch to other window$")
    public void userIsSwitchToOtherWindow() {

        Set<String> testPageWindows = driver.getWindowHandles();
        for(String windowHandle: testPageWindows){
            if(!firstPageWindowHandle.equals(windowHandle)){
                secondTestWindowHandle = windowHandle;
            }
        }

        driver.switchTo().window(secondTestWindowHandle);

        new WebDriverWait(driver, 10)
                .until(ExpectedConditions.visibilityOfElementLocated(By.id("testpagelink")));

        driver.switchTo().window(secondTestWindowHandle).close();

        driver.switchTo().window(firstPageWindowHandle);

        (new WebDriverWait(driver, 10))
                .until(ExpectedConditions.visibilityOfElementLocated(By.linkText("Open page in a new window")));


        assertTrue(true);
    }
}

