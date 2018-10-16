package pl.jsystems.qa.frontend.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import pl.jsystems.qa.frontend.Configuration;

import java.rmi.ConnectIOException;
import java.util.concurrent.TimeUnit;

import static pl.jsystems.qa.frontend.Configuration.WORDPRESS_URL;

public class FrontConfig {

    public WebDriver driver;


    @BeforeAll
    public static void setupAll(){
        WebDriverManager.chromedriver().setup();

    }

    @BeforeEach
    public void setUp(){
//        ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.addArguments("--start-maximized");



//        WebDriver driver = new ChromeDriver();
        if (driver == null) {
            driver = new ChromeDriver();

        }

        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

        driver.get(Configuration.WORDPRESS_URL);



    }

//    @AfterEach
//    public void teareDown() {
//        driver.quit();
//        driver = null;
//        }
}
