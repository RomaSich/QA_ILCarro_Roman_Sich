package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.internal.PackageUtils;

public class BasePage {
    static WebDriver driver;
    public static void setDriver(WebDriver wd){
        driver = wd;
    }
    @FindBy(xpath = "//a[text()='Delete account']")
    WebElement isTextInPagePresent;
    @FindBy(xpath = "//h2[@class='message']")
    WebElement ErrorMessageLogin;
    public void pause(int time){
        try {
            Thread.sleep(time*1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean  isTextInElementPresent(WebElement element, String text)
    {
        return element.getText().contains(text);
    }

    public boolean isElementPresent()
    {
        return isTextInPagePresent.isDisplayed();
    }
    public boolean isTextInElementPresent_errorMessageLogin()
    {
        return isTextInElementPresent(ErrorMessageLogin, "Login or Password incorrect");
    }
}
