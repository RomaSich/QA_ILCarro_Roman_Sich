package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class RegistrationPage extends BasePage{
    public RegistrationPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }
    @FindBy(id = "name")
    WebElement inputName;
    @FindBy(id = "lastName")
    WebElement inputLastName;
    @FindBy(id = "email")
    WebElement inputEmail;
    @FindBy(id = "password")
    WebElement inputPassword;
    @FindBy(xpath = "//label[@for='terms-of-use']")
    WebElement checkBox;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
    @FindBy(xpath = "//h2[@class='message']")
    WebElement textPopUp_regSuccess;


    public RegistrationPage typeRegistrationForm(String name, String lastName, String email, String password) {
        inputName.sendKeys(name);
        inputLastName.sendKeys(lastName);
        inputEmail.sendKeys(email);
        inputPassword.sendKeys(password);
        return this;
    }

    public RegistrationPage clickCheckBox() {
        //checkBox.click();
        System.out.println(checkBox.getRect().getWidth() +" X "+checkBox.getRect().getHeight());
        int width= checkBox.getRect().getWidth();
        int height = checkBox.getRect().getHeight();
        Actions actions = new Actions(driver);
        actions.moveToElement(checkBox, -width/4, -height/4).click().perform();
        return this;
    }
    public RegistrationPage clickBtnYalla(){
        btnYalla.click();
        return this;
    }

    public boolean isTextInElementPresent_regSuccess(){
        return isTextInElementPresent(textPopUp_regSuccess, "You are logged in success");
    }

}
