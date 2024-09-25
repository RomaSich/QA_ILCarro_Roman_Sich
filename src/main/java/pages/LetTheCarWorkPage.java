package pages;

import dto.CarDto;
import enums.FuelForCar;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

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

    public void typeAddNewCarForm(CarDto car) {
        inputLocation.sendKeys(car.getCity());
        pause(3);
        driver.findElement(By.xpath("//div[@class='pac-item']")).click();
        inputManufacture.sendKeys(car.getManufacture());
        inputModel.sendKeys(car.getModel());
        inputYear.sendKeys(car.getYear());
       clickFuelField(car.getFuel());
        inputNumberSeats(car.getSeats());
        inputCarClass.sendKeys(car.getCarClass());
        inputSerialNumber.sendKeys(car.getSerialNumber());
       Price(car.getPricePerDay());
        inputAbout.sendKeys(car.getAbout());
    }
    private void clickFuelField(FuelForCar fuel)
    {
        WebElement fuelElement = driver.findElement(By.xpath(fuel.getLocator()));
        fuelElement.click();
    }
    private void inputNumberSeats(int seats)
    {
        inputSeats.sendKeys(String.valueOf(seats));
    }
    private void Price(double price)
    {
        inputPrice.sendKeys(String.valueOf(price));
    }
}
