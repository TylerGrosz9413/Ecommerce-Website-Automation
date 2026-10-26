package com.project.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountCreated extends BasePage {
    WebDriver driver;

    public AccountCreated(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy (css = "div.col-sm-9 col-sm-offset-1")
    WebElement accountCreatedDiv;

    @FindBy (css = "h2[class='title text-center']/b")
    WebElement accountCreated;

    @FindBy (css = "p.font-size: 20px; font-family: garamond;")
    List<WebElement> creationMessage;

    @FindBy (css = "div.google-anno-skip")
    WebElement accountCreationServiceLink;

    @FindBy (css = ".google-anno")
    WebElement shoppingLink;

    @FindBy (css = "a[data-qa='continue-button']")
    WebElement continueButton;

    //By accountCreated = By.cssSelector("h2[class='title text-center']/b");
    //By firstLine = By.cssSelector("p.font-size: 20px; font-family: garamond;[1]");
    //By secondLine = By.cssSelector("p.font-size: 20px; font-family: garamond;[2]");

    public String getAccountCreatedText() {
        waitForWebElementToBeVisible(accountCreated);
        return accountCreated.getText();
    }

    public String getCreationMessageTextFirstLine() {
        waitForWebElementToBeVisible(creationMessage.get(0));
        return creationMessage.get(0).getText();
    }

    public String getCreationMessageTextSecondLine() {
        waitForWebElementToBeVisible(creationMessage.get(1));
        return creationMessage.get(1).getText();
    }

    public void clickContinue() {
        waitForWebElementToBeClickable(continueButton);
        continueButton.click();
    }

    public void clickAccountCreationServiceLink() {
        waitForWebElementToBeClickable(accountCreationServiceLink);
        accountCreationServiceLink.click();
    }

    public void clickShoppingLink() {
        waitForWebElementToBeClickable(shoppingLink);
        shoppingLink.click();
    }
    
}
