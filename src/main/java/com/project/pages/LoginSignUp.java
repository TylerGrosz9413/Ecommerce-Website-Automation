package com.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginSignUp extends BasePage{
    WebDriver driver;

    public LoginSignUp(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

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

    @FindBy (css = "input[data-qa='signup-button']")
    private WebElement signUpButton;

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
        signUpButton.click();
        return new EnterAccountInfo(driver);
    }

    public void goToLoginSignUpPage() {
        driver.get("https://automationexercise.com/login");
    }

}
