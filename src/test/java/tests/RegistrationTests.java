package tests;

import dto.UserDto;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;
import utils.RetryAnalyzer;

import java.lang.reflect.Method;
import java.util.Random;

import static utils.RandomUtils.*;
import static utils.PropertiesReader.getProperty;

public class RegistrationTests extends ApplicationManager {

    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void registrationPositiveTest(Method method) {
        int i = new Random().nextInt(1000);
        logger.info("start --> " + method.getName());
        String email = "roma" + i + "@gmail.com";
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnRegistration()
                .typeRegistrationForm(getProperty("data.properties","name"),
                        getProperty("data.properties","lastName"),
                        email,
                        getProperty("data.properties","password"))
                .clickCheckBox()
                .clickBtnYalla()
                .isTextInElementPresent_regSuccess())
        ;
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void registrationNegativeTest_NameEmpty(Method method) {
        logger.info("start --> " + method.getName());
        UserDto user = new UserDto(generateString(0), generateString(5),
               getProperty("data.properties","email"), getProperty("data.properties", "password"));
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnRegistration()
                .typeRegistrationForm(user)
                .clickCheckBox()
                .clickBtnYalla()
                .checkAndClickBtnYalla());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void registrationNegativeTest_LastNameEmpty(Method method) {
        logger.info("start --> " + method.getName());
        UserDto user = new UserDto(generateString(5), generateString(0),
                getProperty("data.properties","email"), getProperty("data.properties", "password"));
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnRegistration()
                .typeRegistrationForm(user)
                .clickCheckBox()
                .clickBtnYalla()
                .checkAndClickBtnYalla());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void registrationNegativeTest_wrongEmail(Method method) {
        logger.info("start --> " + method.getName());
        UserDto user = new UserDto(getProperty("data.properties","name"),
                getProperty("data.properties","lastName"),
                generateNumber(10),
                getProperty("data.properties", "password"));
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnRegistration()
                .typeRegistrationForm(user)
                .clickCheckBox()
                .clickBtnYalla()
                .checkAndClickBtnYalla());
    }
    @Test(retryAnalyzer = RetryAnalyzer.class)
    public void registrationNegativeTest_wrongPassword(Method method) {
        logger.info("start --> " + method.getName());
        UserDto user = new UserDto(getProperty("data.properties","name"),
                getProperty("data.properties","lastName"),
                getProperty("data.properties","email"),
                generateEmail(10));
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnRegistration()
                .typeRegistrationForm(user)
                .clickCheckBox()
                .clickBtnYalla()
                .checkAndClickBtnYalla());
    }
}
