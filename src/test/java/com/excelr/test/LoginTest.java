package com.excelr.test;

import com.excelr.base.AutomationWrapper;
import com.excelr.pages.LoginPage;
import com.excelr.pages.MainPage;
import com.excelr.utilities.DataUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.devtools.v85.log.Log;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

public class LoginTest extends AutomationWrapper {

    @Test(dataProvider = "commonDataProvider",dataProviderClass = DataUtils.class)
    public void validCredentialTest(String username, String password, String language, String expectedTitle) {

        LoginPage loginPage=new LoginPage(driver);

        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.selectLanguageByText(language);
        loginPage.clickOnLogin();

        MainPage mainPage=new MainPage(driver);
        mainPage.waitForPresenceCalendarMenu();

        String actualTitle = mainPage.getMainPageTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test(dataProvider = "commonDataProvider",dataProviderClass = DataUtils.class)
    public void invalidCredentialTest(String username,String password,String language,String expectedError) {

        LoginPage loginPage=new LoginPage(driver);
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.selectLanguageByText(language);
        loginPage.clickOnLogin();

        String actualError = loginPage.getInvalidErrorMessage();
        Assert.assertTrue(actualError.contains(expectedError)); //should be true
    }

}
