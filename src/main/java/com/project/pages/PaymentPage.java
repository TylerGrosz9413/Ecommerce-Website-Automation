package com.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PaymentPage extends BasePage {
    WebDriver driver;

    PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy (css = "div.breadcrumbs a")
    WebElement homeLink;

    @FindBy (css = "input[name='name_on_card']")
    WebElement nameOnCardInput;

    @FindBy (css = "input[name='card_number']")
    WebElement cardNumberInput;

    @FindBy (css = "input[name='cvc']")
    WebElement cvcInput;

    @FindBy (css = "input[name='expiry_month']")
    WebElement expirationMonthInput;

    @FindBy (css = "input[name='expiry_year']")
    WebElement expirationYearinput;

    @FindBy (css = "button.submit-button")
    WebElement payAndConfirmOrderButton;
}
