package tests;

import dto.CarDto;
import enums.FuelForCar;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import utils.HeaderMenuItem;

import static pages.BasePage.*;

public class AddNewCarTests extends ApplicationManager {

    LetTheCarWorkPage letTheCarWorkPage;

    @BeforeMethod
    public void startAddCar() {
        new HomePage(getDriver());
        letTheCarWorkPage = clickButtonsOnHeader(HeaderMenuItem.LET_THE_CAR_WORK);
    }

    @Test
    public void loginPositiveTest() {
        CarDto carDto = CarDto.builder().build();
        letTheCarWorkPage.typeAddNewCarForm(carDto);
    }

    @Test
    public void addNewCarPositiveTest() {

        CarDto car = CarDto.builder()
                .city("Haifa")
                .manufacture("Toyota")
                .model("Prius")
                .year("2019")
                .fuel(FuelForCar.GAS)
                .seats(5)
                .carClass("C-class")
                .serialNumber("1222")
                .pricePerDay(1000)
                .about("text")
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
}
}
