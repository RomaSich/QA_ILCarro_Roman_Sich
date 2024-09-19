package tests;

import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

import java.util.Random;

import static utils.RandomUtils.*;

public class RegistrationTests extends ApplicationManager {

    @Test
    public void registrationPositiveTest() {
        int i = new Random().nextInt(1000);
        String email = "roma" + i + "@gmail.com";
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnRegistration()
                .typeRegistrationForm("Ro", "Ma", email, "Rom@7206")
                .clickCheckBox()
                .clickBtnYalla()
                .isTextInElementPresent_regSuccess())
        ;
    }
    @Test
    public void registrationNegativeTest() {
        UserDto user = new UserDto(generateString(5), generateString(5),
                generateString(10), "Rom@7206");
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnRegistration()
                .typeRegistrationForm(user)
                .clickCheckBox()
                .clickBtnYalla()
                .isTextInElementPresent_regSuccess("Wrong email format"))
        ;
    }
}
