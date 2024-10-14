package manager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringDecorator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utils.WDListener;

import java.lang.reflect.Method;

public class ApplicationManager {

    private WebDriver driver;

    public Logger logger = LoggerFactory.getLogger(ApplicationManager.class);

    public WebDriver getDriver()
    {
        return driver;
    }

    @BeforeMethod
    public void setUp()
    {
        logger.info("Start method --> setUp");
        driver = new ChromeDriver();

        WebDriverListener webDriverListener = new WDListener();
        driver = new EventFiringDecorator<>(webDriverListener).decorate(driver);
        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown()
    {
        logger.info("Stop method --> tearDown");
//        if(driver != null)
//        {
//            pause(2);
//            driver.quit();
//        }
    }
    private void pause(int time) {
        try {
            Thread.sleep(time*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
