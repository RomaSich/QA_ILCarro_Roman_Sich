package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import static utils.PropertiesReader.getProperty;

public class HomePage extends BasePage{
    public HomePage(WebDriver driver){
        setDriver(driver);
        driver.get(getProperty("data.properties","url"));
        logger.info("URL ------------------> "+driver.getCurrentUrl());
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(xpath = "//a[text()=' Log in ']")
    WebElement btnLogin;
    @FindBy(xpath = "//a[text()=' Sign up ']")
    WebElement btnSignUp ;
    @FindBy(xpath = "//a[text()=' Let the car work ']")
    WebElement btnLetTheCarWork ;

    public LoginPage clickBtnLogin() {
        btnLogin.click();
        return new LoginPage(driver);
    }

    public RegistrationPage clickBtnRegistration() {
        btnSignUp.click();
        return new RegistrationPage(driver);
    }


    public void clickBtnLetTheCarWork() {
        btnLetTheCarWork.click();
    }
}
