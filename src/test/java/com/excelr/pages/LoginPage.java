package com.excelr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class LoginPage {
    private By usernameLocator = By.id("authUser");
    private By passwordLocator = By.cssSelector("#clearPass");
    private By languageLocator = By.cssSelector("select[name='languageChoice']");
    private By loginLocator = By.id("login-button");
    private By errorLocator = By.xpath("//*[contains(text(),'Invalid')]");
    private By appDescLocator = By.xpath("//p[contains(text(),'most')]");

    //will start at 11:10 AM IST
    private WebDriver driver;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterUsername(String username) {
        driver.findElement(usernameLocator).sendKeys(username);
    }

    public void enterPassword(String password) {
        driver.findElement(passwordLocator).sendKeys(password);
    }

    public void selectLanguageByText(String language) {
        Select selectLan = new Select(driver.findElement(languageLocator));
        selectLan.selectByVisibleText(language);
    }

    public void clickOnLogin() {
        driver.findElement(loginLocator).click();
    }

    public void loginToPortal(String username, String password, String language) {
        enterUsername(username);
        enterPassword(password);
        selectLanguageByText(language);
        clickOnLogin();
    }

    public String getInvalidErrorMessage() {
        return driver.findElement(errorLocator).getText().trim();
    }

    public String getApplicationDescription() {
        return driver.findElement(appDescLocator).getText().trim();
    }

}
