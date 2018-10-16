package pl.jsystems.qa.frontend.factory.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PasswordLoginPage extends BasePage {

//    WebDriver driver;
    public PasswordLoginPage(WebDriver driver) {
        super(driver);

//        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

        @FindBy(id = "password")
        public WebElement passwordPage;

        @FindBy(css="button.form-button.is-primary")
        public WebElement passwordLoginButton;


    }

