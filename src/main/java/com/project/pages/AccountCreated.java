package com.project.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountCreated extends BasePage {
    WebDriver driver;

    public AccountCreated(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "div.col-sm-9 col-sm-offset-1")
    private WebElement accountCreatedDiv;

    @FindBy (css = "h2.title > b")
    private WebElement accountCreatedHeader;

    @FindBy (css = "div.col-sm-9 > p")
    private List<WebElement> creationMessage;

    @FindBy (css = "div.google-anno-skip")
    private WebElement accountCreationServiceLink;

    @FindBy (css = ".google-anno")
    private WebElement shoppingLink;

    @FindBy (css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    //By accountCreated = By.cssSelector("h2[class='title text-center']/b");
    //By firstLine = By.cssSelector("p.font-size: 20px; font-family: garamond;[1]");
    //By secondLine = By.cssSelector("p.font-size: 20px; font-family: garamond;[2]");

    public String getAccountCreatedText() {
        waitForWebElementToBeVisible(accountCreatedHeader);
        return accountCreatedHeader.getText();
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
