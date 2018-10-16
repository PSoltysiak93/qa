package pl.jsystems.qa.frontend.factory.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PrivatPage extends BasePage {
    public PrivatPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(css = "span.form-toggle__switch")
    public WebElement checkbox;

    @FindBy(css ="button.form-button.is-primary")
    public WebElement saveButton;


}
