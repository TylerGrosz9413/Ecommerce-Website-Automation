package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PaymentPage extends BasePage {
    WebDriver driver;

    PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "div.breadcrumbs a")
    private WebElement homeLink;

    @FindBy (css = "h2.heading")
    private WebElement paymentHeader;

    @FindBy (css = "input[name='name_on_card']")
    private WebElement nameOnCardInput;

    @FindBy (css = "input[name='card_number']")
    private WebElement cardNumberInput;

    @FindBy (css = "input[name='cvc']")
    private WebElement cvcInput;

    @FindBy (css = "input[name='expiry_month']")
    private WebElement expirationMonthInput;

    @FindBy (css = "input[name='expiry_year']")
    private WebElement expirationYearinput;

    @FindBy (css = "button.submit-button")
    private WebElement payAndConfirmOrderButton;

    private final By successMessage = By.cssSelector("div.alert-success");

    public String getPaymentHeader() {
        waitForWebElementToAppear(paymentHeader);
        return paymentHeader.getText();
    }

    public void enterPaymentDetails(String nameOnCard, String cardNumber, String cvc, String expirationMonth, String expirationYear) {
        waitForWebElementToAppear(nameOnCardInput);
        nameOnCardInput.sendKeys(nameOnCard);
        cardNumberInput.sendKeys(cardNumber);
        cvcInput.sendKeys(cvc);
        expirationMonthInput.sendKeys(expirationMonth);
        expirationYearinput.sendKeys(expirationYear);
    }

    public void clickPayAndConfirmOrderButton() {
        waitForWebElementToAppear(payAndConfirmOrderButton);
        payAndConfirmOrderButton.click();
    }

    public String getNameOnCard() {
        waitForWebElementToAppear(nameOnCardInput);
        return nameOnCardInput.getAttribute("value");
    }

    public String getCardNumber() {
        waitForWebElementToAppear(cardNumberInput);
        return cardNumberInput.getAttribute("value");
    }

    public String getCVC() {
        waitForWebElementToAppear(cvcInput);
        return cvcInput.getAttribute("value");
    }

    public String getExpirationMonth() {
        waitForWebElementToAppear(expirationMonthInput);
        return expirationMonthInput.getAttribute("value");
    }

    public String getExpirationYear() {
        waitForWebElementToAppear(expirationYearinput);
        return expirationYearinput.getAttribute("value");
    }

    public HomePage clickHomeLink() {
        waitForWebElementToAppear(homeLink);
        homeLink.click();
        return new HomePage(driver);
    }

    public String getSuccessMessage() {
        waitForElementToAppear(successMessage);
        return driver.findElement(successMessage).getText();
    }
}
