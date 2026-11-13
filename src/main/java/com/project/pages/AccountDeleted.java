package com.project.pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class AccountDeleted extends BasePage {

    WebDriver driver;

    public AccountDeleted(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "h2.title > b")
    private WebElement accountDeletedHeader;

    @FindBy (css = "div.col-sm-9 > p")
    private List<WebElement> accountDeletedMessage;

    @FindBy (css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    @FindBy (css = "div.google-anno-skip")
    private WebElement shoppingLink;

    public String getAccountDeletedHeaderText() {
        waitForWebElementToAppear(accountDeletedHeader);
        return accountDeletedHeader.getText();
    }

    public String getAccountDeletedMessageTextFirstLine() {
        waitForWebElementToAppear(accountDeletedMessage.get(0));
        return accountDeletedMessage.get(0).getText();
    }

    public String getAccountDeletedMessageTextSecondLine() {
        waitForWebElementToAppear(accountDeletedMessage.get(1));
        return accountDeletedMessage.get(1).getText();
    }

    public HomePage clickContinue() {
        waitForWebElementToBeClickable(continueButton);
        continueButton.click();
        return new HomePage(driver);
    }

    
}
