package com.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EnterAccountInfo extends BasePage {
    WebDriver driver;

    EnterAccountInfo(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "#id_gender1")
    private WebElement mrRadioButton;

    @FindBy (css = "#id_gender2")
    private WebElement mrsRadioButton;

    @FindBy (css = "#name")
    private WebElement nameInput;

    @FindBy (css = "#email")
    private WebElement emailInput;

    @FindBy (css = "#password")
    private WebElement passwordInput;

    @FindBy (css = "#days")
    private WebElement dobDaysDropdown;

    @FindBy (css = "#months")
    private WebElement dobMonthsDropdown;

    @FindBy (css = "#years")
    private WebElement dobYearsDropdown;

    @FindBy (css = "#newsletter")
    private WebElement newsletterCheckbox;

    @FindBy (css = "#optin")
    private WebElement receiveOffersCheckbox;

    @FindBy (css = "#first_name")
    private WebElement firstNameInput;

    @FindBy (css = "#last_name")
    private WebElement lastNameInput;

    @FindBy (css = "#company")
    private WebElement companyInput;

    @FindBy (css = "#address1")
    private WebElement address1Input;

    @FindBy (css = "#address2")
    private WebElement address2Input;

    @FindBy (css = "#country")
    private WebElement countryDropdown;

    @FindBy (css = "#state")
    private WebElement stateInput;

    @FindBy (css = "#city")
    private WebElement cityInput;

    @FindBy (css = "#zipcode")
    private WebElement zipInput;

    @FindBy (css = "button[data-qa='create-account']")
    private WebElement createAccountButton;

}
