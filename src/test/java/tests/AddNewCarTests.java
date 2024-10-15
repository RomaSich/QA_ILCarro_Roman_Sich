package tests;

import dto.CarDto;
import dto.UserDto;
import org.testng.annotations.Listeners;
import utils.FuelForCar;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.HeaderMenuItem;
import utils.RetryAnalyzer;
import utils.TestNGListener;

import java.lang.reflect.Method;

import static utils.RandomUtils.*;
import static pages.BasePage.*;
import static utils.PropertiesReader.getProperty;
@Listeners(TestNGListener.class)

public class AddNewCarTests extends ApplicationManager {
    UserDto user = new UserDto(getProperty("data.properties", "name"),
            getProperty("data.properties", "lastName"),
            getProperty("data.properties", "email"),
            getProperty("data.properties", "password"));
    LoginPage loginPage;
    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void startAddCar() {
        new HomePage(getDriver());
        loginPage = clickButtonsOnHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(user).clickBtnYalla();
        letTheCarWorkPage = clickButtonsOnHeader(HeaderMenuItem.LET_THE_CAR_WORK);
    }

    @Test
    public void loginPositiveTest() {
        CarDto carDto = CarDto.builder().build();
        letTheCarWorkPage.typeAddNewCarForm(carDto);
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarPositiveTest(Method method) {
        CarDto car = CarDto.builder()
                .city("Haifa")
                .manufacture("Toyota")
                .model("Prius")
                .year("2019")
                .fuel(FuelForCar.GAS.getLocator())
                .seats(5)
                .carClass("C-class")
                .serialNumber(generateNumber(10))
                .pricePerDay(1000)
                .about(generateString(300))
                .image("maxresdefault.jpg")
                .build();
        logger.info("start --> " + method.getName() + car.toString());
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.ClickBtnSubmit();
        Assert.assertTrue(letTheCarWorkPage.validatePopUpMessage
                (car.getManufacture() + " " + car.getModel() + " " + "added successful"));
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarNegativeTestWrongManufacture(Method method) {
        CarDto car = CarDto.builder()
                .city("Haifa")
                .manufacture("")
                .model("Prius")
                .year("2019")
                .fuel(FuelForCar.GAS.getLocator())
                .seats(5)
                .carClass("C-class")
                .serialNumber(generateNumber(10))
                .pricePerDay(1000)
                .about(generateString(100))
                .image("maxresdefault.jpg")
                .build();
        logger.info("start --> " + method.getName() + car.toString());
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.ClickBtnSubmit();
        Assert.assertTrue( letTheCarWorkPage.checkAndClickBtnSubmit());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarNegativeTestWrongModel(Method method) {
        CarDto car = CarDto.builder()
                .city("Haifa")
                .manufacture("Toyota")
                .model("")
                .year("2019")
                .fuel(FuelForCar.GAS.getLocator())
                .seats(5)
                .carClass("C-class")
                .serialNumber(generateNumber(10))
                .pricePerDay(1000)
                .about(generateString(100))
                .image("maxresdefault.jpg")
                .build();
        logger.info("start --> " + method.getName() + car.toString());
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.ClickBtnSubmit();
        Assert.assertTrue(letTheCarWorkPage.checkAndClickBtnSubmit());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarNegativeTestWrongYear(Method method) {
        CarDto car = CarDto.builder()
                .city("Haifa")
                .manufacture("Toyota")
                .model("Prius")
                .year("2026")
                .fuel(FuelForCar.GAS.getLocator())
                .seats(5)
                .carClass("C-class")
                .serialNumber(generateNumber(10))
                .pricePerDay(1000)
                .about(generateString(100))
                .image("maxresdefault.jpg")
                .build();
        logger.info("start --> " + method.getName() + car.toString());
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.ClickBtnSubmit();
        Assert.assertTrue(letTheCarWorkPage.checkAndClickBtnSubmit());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarNegativeTestWrongSeats(Method method) {
        CarDto car = CarDto.builder()
                .city("Haifa")
                .manufacture("Toyota")
                .model("Prius")
                .year("2019")
                .fuel(FuelForCar.GAS.getLocator())
                .seats(0)
                .carClass("C-class")
                .serialNumber(generateNumber(10))
                .pricePerDay(1000)
                .about(generateString(100))
                .image("maxresdefault.jpg")
                .build();
        logger.info("start --> " + method.getName() + car.toString());
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.ClickBtnSubmit();
        Assert.assertTrue(letTheCarWorkPage.checkAndClickBtnSubmit());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarNegativeTestWrongClassCar(Method method) {
        CarDto car = CarDto.builder()
                .city("Haifa")
                .manufacture("Toyota")
                .model("Prius")
                .year("2019")
                .fuel(FuelForCar.GAS.getLocator())
                .seats(5)
                .carClass("")
                .serialNumber(generateNumber(10))
                .pricePerDay(1000)
                .about(generateString(100))
                .image("maxresdefault.jpg")
                .build();
        logger.info("start --> " + method.getName() + car.toString());
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.ClickBtnSubmit();
        Assert.assertTrue(letTheCarWorkPage.checkAndClickBtnSubmit());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarNegativeTestWrongSerialNumber(Method method) {
        CarDto car = CarDto.builder()
                .city("Haifa")
                .manufacture("Toyota")
                .model("Prius")
                .year("2019")
                .fuel(FuelForCar.GAS.getLocator())
                .seats(5)
                .carClass("C-class")
                .serialNumber(generateNumber(30))
                .pricePerDay(1000)
                .about(generateString(100))
                .image("maxresdefault.jpg")
                .build();
        logger.info("start --> " + method.getName() + car.toString());
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.ClickBtnSubmit();
        Assert.assertTrue(letTheCarWorkPage.checkAndClickBtnSubmit());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void addNewCarNegativeTestWrongPrice(Method method) {
        CarDto car = CarDto.builder()
                .city("Haifa")
                .manufacture("Toyota")
                .model("Prius")
                .year("2019")
                .fuel(FuelForCar.GAS.getLocator())
                .seats(5)
                .carClass("C-class")
                .serialNumber(generateNumber(10))
                .pricePerDay(-1)
                .about(generateString(100))
                .image("maxresdefault.jpg")
                .build();
        logger.info("start --> " + method.getName() + car.toString());
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.ClickBtnSubmit();
        Assert.assertTrue(letTheCarWorkPage.checkAndClickBtnSubmit());
    }
}
