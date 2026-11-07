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
    private WebElement automationExerciseLink;

    @FindBy (css = "a[href='/']")
    private WebElement homeLink;

    @FindBy (css = "a[href='/products']")
    private WebElement productsLink;

    @FindBy (css = "a[href='/view cart']")
    private WebElement cartLink;

    /* @FindBy (css = "a[href='/logout']")
    WebElement logout; */

    /* @FindBy (css = "a[href='/delete account']")
    WebElement deleteAccount; */

    @FindBy (css = "a[href='/test cases']")
    private WebElement testcasesLink;

    @FindBy (css = "a[href='/api list']")
    private WebElement apiTestingLink;

    @FindBy (css = "a[href='/https://www.youtube.com/c/AutomationExercise']")
    private WebElement videoTutorialsLink;

    @FindBy (css = "a[href='/contact us']")
    private WebElement contactUsLink;

    /* @FindBy (css = "i[class='fa fa-user'] > b")
    WebElement loggedInAs; */

    @FindBy (css = "#susbscribe_email")
    private WebElement subscribeEmailInput;

    @FindBy (css = "#subscribe")
    private WebElement subscribeButton;

    private final By logoutLink = By.cssSelector("a[href='/logout']");
    private final By deleteAccountLink = By.cssSelector("a[href='/delete account']");
    private final By loggedInAsNonNavigableLink = By.cssSelector("i[class='fa fa-user'] > b");
    private final By signUpLoginLink = By.cssSelector("a[href='/login']");
    
    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public LoginSignUp clickLogoutLink() {
        waitForElementToAppear(logoutLink);
        driver.findElement(logoutLink).click();
        return new LoginSignUp(driver);
    }
    
    public AccountDeleted clickDeleteAccountLink() {
        waitForElementToAppear(deleteAccountLink);
        driver.findElement(deleteAccountLink).click();
        return new AccountDeleted(driver);
    }

    public LoginSignUp clickSignUpLoginLink() {
        waitForElementToAppear(signUpLoginLink);
        driver.findElement(signUpLoginLink).click();
        return new LoginSignUp(driver);
    }

    public HomePage clickHomeLink() {
        waitForWebElementToAppear(homeLink);
        homeLink.click();
        return new HomePage(driver);
    }

    public ProductCatalog clickProductsLink() {
        waitForWebElementToAppear(productsLink);
        productsLink.click();
        return new ProductCatalog(driver);
    }

    public CartPage clickCartLink() {
        waitForWebElementToAppear(cartLink);
        cartLink.click();
        return new CartPage(driver);
    }

    public void clickTestcasesLink() {
        waitForWebElementToAppear(testcasesLink);
        testcasesLink.click();
    }

    public void clickApiTestingLink() {
        waitForWebElementToAppear(apiTestingLink);
        apiTestingLink.click();
    }

    public void clickVideoTutorialsLink() {
        waitForWebElementToAppear(videoTutorialsLink);
        videoTutorialsLink.click();
    }

    public void clickContactUsLink() {
        waitForWebElementToAppear(contactUsLink);
        contactUsLink.click();
    }

    public void subscribeWithEmail(String email) {
        waitForWebElementToAppear(subscribeEmailInput);
        subscribeEmailInput.sendKeys(email);
        waitForWebElementToAppear(subscribeButton);
        subscribeButton.click();
    }

    public String getloggedInAsUserName() {
        waitForElementToAppear(loggedInAsNonNavigableLink);
        String loggedInAsText = driver.findElement(loggedInAsNonNavigableLink).getText();
        String username = loggedInAsText.split("Logged in as ")[1].trim();
        return username;
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

    public void waitForWebElementToAppear(WebElement ele) {
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
