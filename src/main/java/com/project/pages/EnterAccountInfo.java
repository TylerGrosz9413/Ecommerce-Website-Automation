package com.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

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

    @FindBy (css = "#mobile_number")
    private WebElement mobilePhoneInput;

    @FindBy (css = "button[data-qa='create-account']")
    private WebElement createAccountButton;

    public void enterAccountInfo(String title, String password, String dobDay, String dobMonth, String dobYear, boolean newsletterSubscription, boolean specialOffers) {
        waitForWebElementToAppear(passwordInput);
        selectTitle(title);
        passwordInput.sendKeys(password);
        selectDateOfBirth(dobDay, dobMonth, dobYear);
        setNewsletterSubscription(newsletterSubscription);
        setReceiveOffers(specialOffers);
    }

    public void enterAddressInfo(String firstName, String lastName, String address1, String address2, String country, String state, String city, String zip, String mobilePhone) {
        waitForWebElementToAppear(firstNameInput);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
        address1Input.sendKeys(address1);
        address2Input.sendKeys(address2);
        selectDropdownOptionByVisibleText(countryDropdown, country);
        stateInput.sendKeys(state);
        cityInput.sendKeys(city);
        zipInput.sendKeys(zip);
        mobilePhoneInput.sendKeys(mobilePhone);
    }

    public void editName(String name) {
        waitForWebElementToAppear(nameInput);
        nameInput.clear();
        nameInput.sendKeys(name);
    }

    public void enterPassword(String password) {
        waitForWebElementToAppear(passwordInput);
        passwordInput.sendKeys(password);
    }

    public void enterMobilePhone(String mobilePhone) {
        waitForWebElementToAppear(mobilePhoneInput);
        mobilePhoneInput.sendKeys(mobilePhone);
    }

    public void enterFirstAndLastName(String firstName, String lastName) {
        waitForWebElementToAppear(firstNameInput);
        firstNameInput.sendKeys(firstName);
        lastNameInput.sendKeys(lastName);
    }

    public void enterCompany(String company) {
        waitForWebElementToAppear(companyInput);
        companyInput.sendKeys(company);
    }

    public void enterAddressLine1And2(String address1, String address2) {
        waitForWebElementToAppear(address1Input);
        address1Input.sendKeys(address1);
        address2Input.sendKeys(address2);
    }

    public void enterStateCityZip(String state, String city, String zip) {
        waitForWebElementToAppear(stateInput);
        stateInput.sendKeys(state);
        cityInput.sendKeys(city);
        zipInput.sendKeys(zip);
    }

    public void selectTitle(String title) {
        waitForWebElementToAppear(mrRadioButton);
        if (title.equalsIgnoreCase("Mr")) {
            mrRadioButton.click();
        } else if (title.equalsIgnoreCase("Mrs")) {
            mrsRadioButton.click();
        }
    }

    public void selectDateOfBirth(String day, String month, String year) {
        selectDropdownOptionByVisibleText(dobDaysDropdown, day);
        selectDropdownOptionByVisibleText(dobMonthsDropdown, month);
        selectDropdownOptionByVisibleText(dobYearsDropdown, year);
    }

    public void selectCountry(String country) {
        selectDropdownOptionByVisibleText(countryDropdown, country);
    }

    public void setNewsletterSubscription(boolean subscribe) {
        waitForWebElementToAppear(newsletterCheckbox);
        if (subscribe && !newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        } else if (!subscribe && newsletterCheckbox.isSelected()) {
            newsletterCheckbox.click();
        }
    }

    public void setReceiveOffers(boolean receiveOffers) {
        waitForWebElementToAppear(receiveOffersCheckbox);
        if (receiveOffers && !receiveOffersCheckbox.isSelected()) {
            receiveOffersCheckbox.click();
        } else if (!receiveOffers && receiveOffersCheckbox.isSelected()) {
            receiveOffersCheckbox.click();
        }
    }

    public void clickCreateAccountButton() {
        waitForWebElementToBeClickable(createAccountButton);
        createAccountButton.click();
    }

    public void selectDropdownOptionByVisibleText(WebElement dropdown, String visibleText) {
        waitForWebElementToAppear(dropdown);
        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

}
