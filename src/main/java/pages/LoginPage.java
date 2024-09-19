package pages;

import dto.UserDto;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage extends BasePage {
    public LoginPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//button[@type='button']")
    WebElement btnOK;
    @FindBy(xpath = "//h2[@class='message']")
    WebElement ErrorMessageLogin;
    @FindBy(xpath = "//input[@id='email']/..//div[@class='error']/div")
    WebElement errorMessageInputEmail;
    @FindBy(xpath = "//input[@id='email']/..//div[@class='error']")
    WebElement errorMessageInputEmailEmpty;
    @FindBy(xpath = "//input[@id = 'password']/..//div[@class='error']")
    WebElement errorMessageInputPassword;


    public LoginPage typeLoginForm(String email, String password) {
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        return this;
    }
    public LoginPage typeLoginForm(UserDto user) {
        inputEmail.sendKeys(user.getEmail());
        inputPassword.sendKeys(user.getPassword());
        return this;
    }

    public LoginPage clickBtnYalla() {
        pause(3);
        btnYalla.click();
        return this;
    }
    public HomePage clickBtnOK()
    {
        pause(3);
        btnOK.click();
        return new HomePage(driver);
    }
    public boolean isTextInElementPresent_errorEmail(String text)
    {
        return isTextInElementPresent(errorMessageInputEmail, text);
    }
    public boolean isTextInElementPresent_EmailEmpty(String text)
    {
        pause(2);
        return isTextInElementPresent(errorMessageInputEmailEmpty, text);
    }
    public boolean isTextInElementPresent_PasswordEmpty(String text)
    {
        pause(2);
        return isTextInElementPresent(errorMessageInputPassword, text);
    }


}
