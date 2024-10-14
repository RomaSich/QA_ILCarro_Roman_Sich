package pages;

import dto.UserDto;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Listeners;
import utils.TestNGListener;

import java.time.Duration;

@Listeners(TestNGListener.class)


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
    @FindBy(xpath = "//button[contains(text(),'Y’alla!')and @disabled]")
    WebElement btnYallaNotClickable;
    @FindBy(xpath = "//button[@type='button']")
    WebElement btnOK;
    @FindBy(xpath = "//h2[@class='message']")
    WebElement textPopUp_LoginSuccess;
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
  public LoginPage clickBtnYalla()
  {
      if(btnYalla.getAttribute("disabled")==null)
      {
          clickWait(btnYalla,10);
      }else
      {
          System.out.println("btnYalla not clickable");
      }return this;
  }

    public boolean checkAndClickBtnYalla() {
        if(btnYallaNotClickable.getAttribute("disabled")==null)
        {
            clickWait(btnYalla,10);
            return false;
        }else
        {  System.out.println("btnYalla not clickable");
            return true;
        }

    }
    public HomePage clickBtnOK()
    {
        clickWait(btnOK,3);
        return new HomePage(driver);
    }
    public boolean isTextInElementPresent_LoginSuccess(){
        return isTextInElementPresent(textPopUp_LoginSuccess, "Logged in success");
    }
    public boolean isTextInElementPresent_errorEmail()
    {
        return isTextInElementPresent(ErrorMessageLogin, "Login or Password incorrect");
    }
    public boolean isTextInElementPresent_EmailEmpty(String text)
    {
        clickWait(By.xpath("//input[@id='email']/..//div[@class='error']"),10);
        return isTextInElementPresent(errorMessageInputEmailEmpty, text);
    }
    public boolean isTextInElementPresent_PasswordEmpty(String text)
    {
        clickWait(By.xpath("//input[@id = 'password']/..//div[@class='error']"),10);
        return isTextInElementPresent(errorMessageInputPassword, text);
    }


}
