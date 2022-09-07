package com.excelr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SearchOrAddPatientPage {

    private WebDriver driver;

    public SearchOrAddPatientPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToPatFrame()
    {
        driver.switchTo().frame(driver.findElement(By.xpath("//iframe[@name='pat']")));
    }

    public void enterFirstName(String firstName)
    {
        switchToPatFrame();
        driver.findElement(By.id("form_fname")).sendKeys(firstName);
    }

}
