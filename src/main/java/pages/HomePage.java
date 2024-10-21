package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;

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
    @FindBy(id = "city")
    WebElement inputCity;
   @FindBy( id="dates")
   WebElement inputDates;
    @FindBy(xpath = "//button[@aria-label='Choose month and year']")
    WebElement btnChooseMonthYear;
    @FindBy(xpath = "//button[@type='submit']")
    WebElement btnYalla;
//    @FindBy(xpath = "//input[formcontrolname='dates']")
//    WebElement inputDate;


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

    public void fillSearchCarFormCalendar(String city, String startDate, String endDate) {
        fillInputCity(city);
        fillInputDateCalendar(startDate,endDate);
        btnYalla.click();
    }
    private void fillInputDateCalendar(String startDate, String endDate){
        inputDates.click();
        String[] startDateArray = startDate.split(" ");
        String[] endDateArray = endDate.split(" ");
        typeYearMonthDay(startDateArray[2],startDateArray[1], startDateArray[0]);
        typeYearMonthDay(endDateArray[2],endDateArray[1], endDateArray[0]);

    }

    private void typeYearMonthDay(String year, String month, String day) {
        btnChooseMonthYear.click();
        driver.findElement(By.xpath("//div[contains(text(),'" + year + "')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'" + month.toUpperCase() + "')]")).click();
        driver.findElement(By.xpath("//div[contains(text(),'" + day + "')]")).click();
    }


    private void fillInputCity(String city)
    {
        inputCity.click();
        inputCity.sendKeys(city);
        Actions actions = new Actions(driver);
        actions.moveToElement(inputCity,0,40).pause(3000).click().perform();
    }

    public boolean validDateUrlContainsResult()
    {
        new WebDriverWait(driver, Duration.ofSeconds(5)).
                until(ExpectedConditions.urlContains("search/result"));
        return true;
    }

    public void fillSearchCarForm(String city, String date) {
        fillInputCity(city);
        inputDates.sendKeys(date);
        btnYalla.click();
    }
}
