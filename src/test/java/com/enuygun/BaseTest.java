package com.enuygun;

import com.enuygun.helper.BrowserFactory;
import com.enuygun.helper.ConfigHelper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;
import java.util.Locale;
import java.util.Properties;

public class BaseTest {
    WebDriver webDriver;
    public static Properties props = ConfigHelper.readProp("src/test/resources/properties/config.properties");
    public static String browserName = props.getProperty("browser");

    @BeforeClass
    public void setUp() {
        if (!browserName.toLowerCase(Locale.ENGLISH).equals("chrome")) {
            throw new IllegalStateException("chromedriver.failed.to.setup");
        }
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void startUp() {
        if (!browserName.toLowerCase(Locale.ENGLISH).equals("chrome")) {
            throw new IllegalStateException("chromedriver.failed.to.setup");
        }
        webDriver = new ChromeDriver(BrowserFactory.getChromeOptions());
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
        webDriver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }
}
