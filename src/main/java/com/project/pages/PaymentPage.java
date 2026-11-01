package com.project.pages;

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
}
