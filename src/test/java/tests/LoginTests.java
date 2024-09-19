package tests;

import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.HeaderMenuItem;

import static pages.BasePage.clickButtonsOnHeader;
import static utils.RandomUtils.*;

public class LoginTests extends ApplicationManager {

    @Test
    public void loginPositiveTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnLogin()
                .typeLoginForm("roma@gmail.com", "7206Rom@")
                .clickBtnYalla()
                .clickBtnOK()
                .isElementPresent())
        ;
    }

    @Test
    public void loginNegativeTest_wrongEmailWOAt() {
        UserDto user = new UserDto(generateString(5), generateString(7),
                generateString(10), "7206Rom@");
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnLogin()
                .typeLoginForm(user)
                .clickBtnYalla()
                .isTextInElementPresent_errorEmail("It'snot look like email"))
        ;
    }

    @Test
    public void loginNegativeTest_wrongEmailWOAt_Enum() {
        UserDto user = new UserDto(generateString(5), generateString(7),
                generateString(10), "7206Rom@");
        new HomePage(getDriver());
        LoginPage loginPage = clickButtonsOnHeader(HeaderMenuItem.LOGIN);
        loginPage.typeLoginForm(user)
                .clickBtnYalla()
                .isTextInElementPresent_errorEmail("It'snot look like email")
        ;
    }
    @Test
    public void loginNegativeTest_wrongEmailEmptyWOAt() {
        UserDto user = new UserDto(generateString(5), generateString(7),
                generateString(0), "7206Rom@");
        Assert.assertTrue(
        new HomePage(getDriver())
                .clickBtnLogin()
                .typeLoginForm(user)
                .clickBtnYalla()
                .isTextInElementPresent_EmailEmpty("Email is required"))
        ;

    }
    @Test
    public void loginNegativeTest_wrongPasswordEmptyWOAt() {
        UserDto user = new UserDto(generateString(5), generateString(7),
                generateEmail(10), "");
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnLogin()
                .typeLoginForm(user)
                .clickBtnYalla()
                .isTextInElementPresent_PasswordEmpty("Password is required"));

    }
}
