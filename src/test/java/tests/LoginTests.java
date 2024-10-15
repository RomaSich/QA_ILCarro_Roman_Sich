package tests;

import dto.UserDto;
import manager.ApplicationManager;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.LoginPage;
import utils.HeaderMenuItem;
import utils.RetryAnalyzer;
import utils.TestNGListener;
import static utils.TakeScreenShot.takeScreenShot;
import java.lang.reflect.Method;
import java.util.Locale;

import static utils.RandomUtils.*;
import static pages.BasePage.clickButtonsOnHeader;
import static utils.PropertiesReader.getProperty;
@Listeners(TestNGListener.class)

public class LoginTests extends ApplicationManager {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginPositiveTest(Method method)
    {

        logger.info("start --> " + method.getName());
        takeScreenShot((TakesScreenshot) getDriver());
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnLogin()
                .typeLoginForm(getProperty("data.properties", "email"),
                        getProperty("data.properties", "password"))
                .clickBtnYalla()
                .isTextInElementPresent_LoginSuccess())
        ;
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginNegativeTest_wrongEmailWOAt(Method method) {
        logger.info("start --> " + method.getName());
        UserDto user = new UserDto(getProperty("data.properties", "name"),
                getProperty("data.properties", "lastName"),
                generateEmail(10),
                getProperty("data.properties", "password"));
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnLogin()
                .typeLoginForm(user)
                .clickBtnYalla()
                .isTextInElementPresent_errorEmail())
        ;
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginNegativeTest_wrongEmailWOAt_Enum(Method method) {
        logger.info("start --> " + method.getName());
        UserDto user = new UserDto(getProperty("data.properties", "name"),
                getProperty("data.properties", "lastName"),
                generateString(10),
                getProperty("data.properties", "password"));
        new HomePage(getDriver());
        LoginPage loginPage = clickButtonsOnHeader(HeaderMenuItem.LOGIN);
      Assert.assertTrue(loginPage.typeLoginForm(user)
                .checkAndClickBtnYalla());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginNegativeTest_emailEmpty(Method method) {
        logger.info("start --> " + method.getName());
        UserDto user = new UserDto(getProperty("data.properties", "name"),
                getProperty("data.properties", "lastName"),
                generateString(0),
                getProperty("data.properties", "password"));
        new HomePage(getDriver());
        LoginPage loginPage = clickButtonsOnHeader(HeaderMenuItem.LOGIN);
       Assert.assertTrue( loginPage.typeLoginForm(user)
                .checkAndClickBtnYalla());
    }

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void loginNegativeTest_wrongPassword(Method method) {
        logger.info("start --> " + method.getName());
        UserDto user = new UserDto(getProperty("data.properties", "name"),
                getProperty("data.properties", "lastName"),
                getProperty("data.properties", "email"),
                generateString(10));
        new HomePage(getDriver());
        LoginPage loginPage = clickButtonsOnHeader(HeaderMenuItem.LOGIN);
        Assert.assertTrue(loginPage.typeLoginForm(user).clickBtnYalla()
                .isTextInElementPresent_errorEmail());
    }

}
