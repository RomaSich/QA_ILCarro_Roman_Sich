package tests;

import dto.CarDto;
import dto.UserDto;
import enums.FuelForCar;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LetTheCarWorkPage;
import pages.LoginPage;
import utils.HeaderMenuItem;
import static utils.RandomUtils.*;
import static pages.BasePage.*;

public class AddNewCarTests extends ApplicationManager {
   UserDto user = new UserDto("Roman", "Sich", "roma@gmail.com", "7206Rom@");
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
                .serialNumber(generateNumber(10))
                .pricePerDay(1000)
                .about(generateString(20))
                .build();
        letTheCarWorkPage.typeAddNewCarForm(car);
        letTheCarWorkPage.ClickBtnSubmit();
        letTheCarWorkPage.ClickBtnAddCar();
        Assert.assertTrue(letTheCarWorkPage.samePageByUrl("https://ilcarro.web.app/let-car-work"));
    }
}
