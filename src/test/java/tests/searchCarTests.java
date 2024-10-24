package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import manager.ApplicationManager;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.HomePage;

public class searchCarTests extends ApplicationManager {
    HomePage homePage;
    @Description("positive methode search car")
    @Owner("QA Roman")
    @Test
    public void searchCarPositiveTest()
    {
       homePage = new HomePage(getDriver());
       homePage.fillSearchCarFormCalendar("Haifa","22 jan 2025", "10 dec 2024");
        Assert.assertTrue(homePage.validDateUrlContainsResult());
    }
    @Description("negative methode search car")
    @Owner("QA not Roman")
    @Test(expectedExceptions = {org.openqa.selenium.NoSuchElementException.class, IndexOutOfBoundsException.class})
    public void searchCarNegativeTest_wrongDate()
    {
        Allure.step("fill search car form");
        homePage = new HomePage(getDriver());
        Allure.step("fill home car form");
        homePage.fillSearchCarFormCalendar("Haifa","22 jan 2023", "10 dec 2024");
    }


    @Description("positive methode search car without calendar")
    @Owner("QA not Roman")
    @Test
    public void searchCarPositiveTest_withoutCalendar()
    {
        homePage = new HomePage(getDriver());
        homePage.fillSearchCarFormWithoutCalendar("Haifa","12/10/2024 - 1/22/2025");
    }

}
