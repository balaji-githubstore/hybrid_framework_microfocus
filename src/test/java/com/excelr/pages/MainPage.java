package com.excelr.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MainPage {

    private By messageLocator=By.xpath("//div[text()='Messages']");
    private By calendarLocator=By.xpath("//div[text()='Calendar']");

    private WebDriver driver;

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForPresenceMessagesMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfElementLocated(messageLocator));
    }
    public void waitForPresenceCalendarMenu() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.presenceOfElementLocated(calendarLocator));
    }
    public String getMainPageTitle() {
        return driver.getTitle();
    }

    public void clickOnMessages() {
        driver.findElement(messageLocator).click();
    }

    public void clickOnCalendar() {
        driver.findElement(calendarLocator).click();
    }


}
