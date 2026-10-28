package com.project.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    WebDriver driver;

    @FindBy (css = "img[alt='Website for automation practice']")
    WebElement automationExerciseLink;

    @FindBy (css = "a[href='/']")
    WebElement home;

    @FindBy (css = "a[href='/products']")
    WebElement products;

    @FindBy (css = "a[href='/view cart']")
    WebElement cart;

    @FindBy (css = "a[href='/logout']")
    WebElement logout;

    @FindBy (css = "a[href='/delete account")
    WebElement deleteAccount;

    @FindBy (css = "a[href='/test cases")
    WebElement testcases;

    @FindBy (css = "a[href='/api list")
    WebElement apiTesting;

    @FindBy (css = "a[href='/https://www.youtube.com/c/AutomationExercise")
    WebElement videoTutorials;

    @FindBy (css = "a[href='/contact us")
    WebElement contactUs;

    @FindBy (css = "i[class='fa fa-user']/b")
    WebElement loggedInAs;

    @FindBy (css = "#susbscribe_email")
    WebElement subscribeEmail;

    @FindBy (css = "#subscribe")
    WebElement subscribeButton;
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForElementToAppear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void waitForElementToDisappear(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void waitForElementToBeClickable(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    public void waitForWebElementToBeVisible(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(ele));
    }

    public void waitForWebElementToDisappear(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.invisibilityOf(ele));
    }

    public void waitForWebElementToBeClickable(WebElement ele) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(ele));
    }
}
