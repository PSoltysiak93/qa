package pl.jsystems.qa.frontend.cucumber.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DiaryTestPage extends BasePage {
    public DiaryTestPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver,this);

    }
    @FindBy (id= "mywindowframe")
    public WebElement windowFrame;

}
