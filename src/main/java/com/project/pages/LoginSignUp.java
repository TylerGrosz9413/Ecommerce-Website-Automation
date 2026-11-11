package com.project.pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginSignUp extends BasePage{
    WebDriver driver;

    public LoginSignUp(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = ".signup-form > h2")
    private WebElement newUserSignUpText;

    @FindBy (css = "login-form > h2")
    private WebElement loginToYourAccountText;

    @FindBy (css = "input[data-qa='login-email']")
    private WebElement loginEmailInput;

    @FindBy (css = "input[data-qa='login-password']")
    private WebElement loginPasswordInput;

    @FindBy (css = "button[data-qa='login-button']")
    private WebElement loginButton;

    @FindBy (css = "input[data-qa='signup-name']")
    private WebElement signUpNameInput;

    @FindBy (css = "input[data-qa='signup-email']")
    private WebElement signUpEmailInput;

    @FindBy (css = "button[data-qa='signup-button']")
    private WebElement signUpButton;

    @FindBy (xpath = "//form[@action='/signup']//p[contains(normalize-space(.), 'Email Address already exist!')]")
    private WebElement signupEmailAlreadyExistsErrorText;

    private final By closeAdButton = By.id("dismiss-button");

    public String getloginToYourAccountText() {
        waitForWebElementToAppear(loginToYourAccountText);
        return loginToYourAccountText.getText();
    }

    public String getnewUserSignUpText() {
        waitForWebElementToAppear(newUserSignUpText);
        return newUserSignUpText.getText();
    }

    public String getSignUpEmailAlreadyExistsErrorMessage() {
        waitForWebElementToAppear(signupEmailAlreadyExistsErrorText);
        return signupEmailAlreadyExistsErrorText.getText();
    }

    public ProductCatalog login(String email, String password) {
        waitForWebElementToAppear(loginEmailInput);
        loginEmailInput.sendKeys(email);
        loginPasswordInput.sendKeys(password);
        loginButton.click();
        return new ProductCatalog(driver);
    }

    public EnterAccountInfo signUp(String name, String email) {
        waitForWebElementToAppear(signUpNameInput);
        signUpNameInput.sendKeys(name);
        signUpEmailInput.sendKeys(email);
        //closeAdIfPresent();
        waitForWebElementToBeClickable(signUpButton);
        signUpButton.click();
        //handleAdPopUp();
        return new EnterAccountInfo(driver);
    }

    public void goToLoginSignUpPage() {
        driver.get("https://automationexercise.com/login");
    }

    public void handleAdPopUp() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.switchTo().frame("aswift_1");
        try {
            WebElement closeAd = wait.until(ExpectedConditions.visibilityOfElementLocated(closeAdButton));
            closeAd.click();
        } catch (Exception e) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("document.getElementById('dismiss-button').click();");
        }
    }

}
