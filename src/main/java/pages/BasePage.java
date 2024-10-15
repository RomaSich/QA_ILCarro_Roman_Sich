package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.internal.PackageUtils;
import utils.HeaderMenuItem;

import java.time.Duration;

public class BasePage {
    static WebDriver driver;
    static Logger logger = LoggerFactory.getLogger(BasePage.class);
    public static void setDriver(WebDriver wd){
        driver = wd;
    }

    public void pause(int time){
        try {
            Thread.sleep(time*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
    @FindBy(xpath = "//a[text()='Delete account']")
    WebElement isTextInPagePresent;

    public boolean isElementPresent()
    {
        return isTextInPagePresent.isDisplayed();
    }
    public boolean  isTextInElementPresent(WebElement element, String text)
    {
        return element.getText().contains(text);
    }
    public static <T extends BasePage> T clickButtonsOnHeader(HeaderMenuItem headerMenuItem)
    {
        WebElement element = driver.findElement(By.xpath(headerMenuItem.getLocator()));
        element.click();
        switch (headerMenuItem)
        {
            case LOGIN:
            return (T) new LoginPage(driver);
            case SIGN_UP:
                return (T) new RegistrationPage(driver);
            case TERMS_OF_USE:
                return (T) new Terms(driver);
            case LET_THE_CAR_WORK:
                return (T) new LetTheCarWorkPage(driver);

            default:
                throw new IllegalArgumentException("invalid parametr headerMenuItem");

        }
    }
    public void clickWait(WebElement element, int time)
    {
        new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public void clickWait(By locator, int time)
    {
        new WebDriverWait(driver, Duration.ofSeconds(time))
                .until(ExpectedConditions.elementToBeClickable(locator)).click();
    }

}
