package pages;

import dto.CarDto;
import utils.FuelForCar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

import java.io.File;

public class LetTheCarWorkPage extends BasePage{

    public LetTheCarWorkPage(WebDriver driver) {
        setDriver(driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 10), this);
    }

    @FindBy(id = "pickUpPlace")
    WebElement inputLocation;
    @FindBy(id = "make")
    WebElement inputManufacture;
    @FindBy(id = "model")
    WebElement inputModel;
    @FindBy(id = "year")
    WebElement inputYear;
    @FindBy(id = "fuel")
    WebElement inputFuel;
    @FindBy(id = "seats")
    WebElement inputSeats;
    @FindBy(id = "class")
    WebElement inputCarClass;
    @FindBy(id = "serialNumber")
    WebElement inputSerialNumber;
    @FindBy(id = "price")
    WebElement inputPrice;
    @FindBy(id = "about")
    WebElement inputAbout;
    @FindBy(id = "photos")
    WebElement inputPhoto;
    @FindBy(xpath = "//button[text()='Submit']")
    WebElement clickBtnSubmit;
    @FindBy(xpath = "//button[text()='Add another car']")
    WebElement clickBtnAddCar;
    @FindBy(xpath = "//div[@class='dialog-container']/h2")
    WebElement messageSuccessAddCar;
    @FindBy(xpath = "//input[@id='make']/..//div[@class='error']/div")
    WebElement errorMessageManufacture;
    @FindBy(xpath = "//button[contains(text(),'Submit')and @disabled]")
    WebElement btnSubmitNotClickable;
    @FindBy(xpath = "//input[@id='model']/..//div[@class='error']/div")
    WebElement errorMessageModel;
    @FindBy(xpath = "//input[@id='year']/..//div[@class='error']/div")
    WebElement errorMessageYear;
    @FindBy(xpath = "//input[@id='seats']/..//div[@class='error']")
    WebElement errorMessageSeats;
    @FindBy(xpath = "//input[@id='class']/..//div[@class='error']/div")
    WebElement errorMessageClassCar;
    @FindBy(xpath = "//input[@id='serialNumber']/..//div[@class='error']/div")
    WebElement errorMessageSerialNumber;
    @FindBy(xpath = "//input[@id='price']/..//div[@class='error']")
    WebElement errorMessagePrice;

    public void typeAddNewCarForm(CarDto car) {
        inputLocation.sendKeys(car.getCity());
//
//        driver.findElement(By.xpath("//div[@class='pac-item']")).click();
        clickWait(By.xpath("//div[@class='pac-item']"),3);
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
       inputFuel.click();
        clickWait(By.xpath(car.getFuel()),3);
       // ================================================
        inputNumberSeats(car.getSeats());
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
       Price(car.getPricePerDay());
        inputAbout.sendKeys(car.getAbout());
        File file = new File("src/test/resources/"+car.getImage());
        inputPhoto.sendKeys(file.getAbsolutePath());
    }
    private void inputNumberSeats(int seats)
    {
        inputSeats.sendKeys(String.valueOf(seats));
    }
    private void Price(double price)
    {
        inputPrice.sendKeys(String.valueOf(price));
    }
    public void ClickBtnSubmit()
    {
        if(clickBtnSubmit.getAttribute("disabled")==null) {
            clickWait(By.xpath("//button[text()='Submit']"), 10);
        }else
        {
            System.out.println("btnSubmit no clickable");
        }
    }
    public boolean validatePopUpMessage(String text){
        clickWait(By.xpath("//div[@class='dialog-container']/h2"),10);
        return isTextInElementPresent(messageSuccessAddCar, text);
    }
    public void ClickBtnAddCar()
    {

        clickBtnAddCar.click();
    }
    public boolean samePageByUrl(String url)
    {
        return driver.getCurrentUrl().equals(url);
    }

    public boolean checkAndClickBtnSubmit() {
        if (btnSubmitNotClickable.getAttribute("disabled") == null) {
            clickWait(clickBtnSubmit, 10);
            return false;
        } else {
            System.out.println("btnSubmit not clickable");
            return true;
        }
    }
    public boolean isTextInElementErrorMessageManufacture(String text)
    {
        clickWait(By.xpath("//input[@id='make']/..//div[@class='error']/div"),10);
        return isTextInElementPresent(errorMessageManufacture, text);
    }
    public boolean isTextInElementErrorMessageModel(String text)
    {
        clickWait(By.xpath("//input[@id='model']/..//div[@class='error']/div"),10);
        return isTextInElementPresent(errorMessageModel, text);
    }
    public boolean isTextInElementErrorMessageYear(String text)
    {
        clickWait(By.xpath("//input[@id='year']/..//div[@class='error']/div"),10);
        return isTextInElementPresent(errorMessageYear, text);
    }
    public boolean isTextInElementErrorMessageSeats(String text)
    {
        clickWait(By.xpath("//input[@id='seats']/..//div[@class='error']"),10);
        return isTextInElementPresent(errorMessageSeats, text);
    }
    public boolean isTextInElementErrorMessageClassCar(String text)
    {
        clickWait(By.xpath("//input[@id='class']/..//div[@class='error']/div"),10);
        return isTextInElementPresent(errorMessageClassCar, text);
    }
    public boolean isTextInElementErrorMessageSerialNumber(String text)
    {
        clickWait(By.xpath("//input[@id='serialNumber']/..//div[@class='error']/div"),10);
        return isTextInElementPresent(errorMessageSerialNumber, text);
    }
    public boolean isTextInElementErrorMessagePrice(String text)
    {
        clickWait(By.xpath("//input[@id='price']/..//div[@class='error']"),10);
        return isTextInElementPresent(errorMessagePrice, text);
    }

}
