package pl.jsystems.qa.frontend.factory.test;

import org.junit.jupiter.api.Test;
import pl.jsystems.qa.frontend.Configuration;
import pl.jsystems.qa.frontend.factory.FrontConfig;
import pl.jsystems.qa.frontend.factory.page.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class FrontEndTest extends FrontConfig {

    @Test
    public void firsFrontTest() {

        MainPage mainPage = new MainPage(driver);


        assertTrue(driver.getTitle().contains("WordPress.com"));
        assertEquals(mainPage.logIn.getText(), "Log In");
        assertTrue(mainPage.logIn.isDisplayed());
        mainPage.logIn.click();


        LoginEmailPage loginEmailPage = new LoginEmailPage(driver);

        loginEmailPage.waitForVisibilityOfElement(loginEmailPage.loginEmail,30);
        loginEmailPage.waitForVisibilityOfElement(loginEmailPage.emailContinueButton,30);
        assertTrue(loginEmailPage.loginEmail.isDisplayed());
        assertTrue(loginEmailPage.emailContinueButton.isDisplayed());

        loginEmailPage.loginEmail.sendKeys(Configuration.WORDPRESS_LOGIN);
        loginEmailPage.emailContinueButton.click();



        PasswordLoginPage passwordLoginPage= new PasswordLoginPage(driver);

        passwordLoginPage.waitForVisibilityOfElement(passwordLoginPage.passwordPage,30);
        passwordLoginPage.waitForVisibilityOfElement(passwordLoginPage.passwordLoginButton,30);

        assertTrue(passwordLoginPage.passwordPage.isDisplayed());
        assertTrue(passwordLoginPage.passwordLoginButton.isDisplayed());

        passwordLoginPage.passwordPage.sendKeys(Configuration.WORDPRESS_PASSWORD);
        passwordLoginPage.passwordLoginButton.click();


        UserMainPage userMainPage = new UserMainPage(driver);
        userMainPage.waitForVisibilityOfElement(userMainPage.userAvatar,30);
        userMainPage.userAvatar.click();

        UserPersonalPage userPersonalPage = new UserPersonalPage(driver);
        userPersonalPage.waitForVisibilityOfElement(userPersonalPage.privacyButton,30);
        userPersonalPage.privacyButton.click();

        PrivatPage privatPage = new PrivatPage(driver);
//        privatPage.waitForVisibilityOfElement(privatPage.saveButton,30);

        assertFalse(privatPage.saveButton.isEnabled());

        privatPage.checkbox.click();
        assertTrue(privatPage.saveButton.isEnabled());

    }





    @Test
    public void secondFrontTest() {

        MainPage mainPage = new MainPage(driver);


        assertTrue(driver.getTitle().contains("WordPress.com"));
        assertEquals(mainPage.logIn.getText(), "Log In");
        assertTrue(mainPage.logIn.isDisplayed());
        mainPage.logIn.click();


        LoginEmailPage loginEmailPage = new LoginEmailPage(driver);

        loginEmailPage.waitForVisibilityOfElement(loginEmailPage.loginEmail,30);
        loginEmailPage.waitForVisibilityOfElement(loginEmailPage.emailContinueButton,30);
        assertTrue(loginEmailPage.loginEmail.isDisplayed());
        assertTrue(loginEmailPage.emailContinueButton.isDisplayed());

        loginEmailPage.loginEmail.sendKeys(Configuration.WORDPRESS_LOGIN);
        loginEmailPage.emailContinueButton.click();



        PasswordLoginPage passwordLoginPage= new PasswordLoginPage(driver);

        passwordLoginPage.waitForVisibilityOfElement(passwordLoginPage.passwordPage,30);
        passwordLoginPage.waitForVisibilityOfElement(passwordLoginPage.passwordLoginButton,30);

        assertTrue(passwordLoginPage.passwordPage.isDisplayed());
        assertTrue(passwordLoginPage.passwordLoginButton.isDisplayed());

        passwordLoginPage.passwordPage.sendKeys(Configuration.WORDPRESS_PASSWORD);
        passwordLoginPage.passwordLoginButton.click();


        UserMainPage userMainPage = new UserMainPage(driver);
        userMainPage.waitForVisibilityOfElement(userMainPage.userAvatar,30);
        userMainPage.userAvatar.click();

        UserPersonalPage userPersonalPage = new UserPersonalPage(driver);
        userPersonalPage.waitForVisibilityOfElement(userPersonalPage.privacyButton,30);
        userPersonalPage.notifications.click();

        NotificationPage notificationPage = new NotificationPage(driver);
        assertTrue(notificationPage.secondSelector.isSelected());
        notificationPage.secondSelector.click();
        assertFalse(notificationPage.secondSelector.isSelected());
        notificationPage.secondSelector.click();
        assertTrue(notificationPage.secondSelector.isSelected());



    }

}
