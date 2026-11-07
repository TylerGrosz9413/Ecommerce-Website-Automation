package com.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderConfirmationPage extends BasePage {
    WebDriver driver;

    OrderConfirmationPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = ".check_out")
    private WebElement downloadInvoiceButton;

    @FindBy (css = "a[data-qa='continue-button']")
    private WebElement continueButton;

    @FindBy (css = "h2.title b")
    private WebElement orderPlacedText;

    @FindBy (css = "h2.title p")
    private WebElement confirmationMessageText;

    public String getOrderPlacedText() {
        waitForWebElementToAppear(orderPlacedText);
        return orderPlacedText.getText();
    }

    public String getConfirmationMessageText() {
        waitForWebElementToAppear(confirmationMessageText);
        return confirmationMessageText.getText();
    }

    public void clickDownloadInvoiceButton() {
        waitForWebElementToAppear(downloadInvoiceButton);
        downloadInvoiceButton.click();
    }

    public HomePage clickContinueButton() {
        waitForWebElementToAppear(continueButton);
        continueButton.click();
        return new HomePage(driver);
    }

}
