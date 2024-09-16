package tests;

import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class LoginTests extends ApplicationManager {

    @Test
    public void loginPositiveTest() {
        Assert.assertTrue(new HomePage(getDriver())
                .clickBtnLogin()
                .typeLoginForm("roma@gmail.com", "7206Rom@")
                .clickBtnYalla()
                .clickBtnOK().isElementPresent())
        ;
    }
    @Test
    public void loginNegativeTest_wrongEmail()
    {
        Assert.assertTrue(
                new HomePage(getDriver())
                        .clickBtnLogin()
                        .typeLoginForm("roma@gma.il.com", "7206Rom@")
                        .clickBtnYalla()
                        .isTextInElementPresent_errorMessageLogin());
    }
}
