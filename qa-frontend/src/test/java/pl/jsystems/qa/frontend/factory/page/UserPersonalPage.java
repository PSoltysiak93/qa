package pl.jsystems.qa.frontend.factory.page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPersonalPage extends BasePage {
        public UserPersonalPage(WebDriver driver) {
            super(driver);
            PageFactory.initElements(driver,this);
        }

        @FindBy(css =".gridicon.gridicons-visible")
        public WebElement privacyButton;

        @FindBy(css= "a[href=\"/me/notifications\"] span.menu-link-text")
        public WebElement notifications;
}
