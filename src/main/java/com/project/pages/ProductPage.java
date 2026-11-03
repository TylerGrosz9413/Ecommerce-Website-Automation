package com.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage extends ProductCatalog {
    WebDriver driver;

    public ProductPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = ".product-information > span > span")
    private WebElement productPrice;

    @FindBy (css = "#quantity")
    private WebElement productQuantity;

    @FindBy (css = ".cart")
    private WebElement addToCartButton;

    @FindBy (xpath = "//p[.//b[contains(text(),'Availability:')]]\"")
    private WebElement productAvailability;

    @FindBy (xpath = "//p[.//b[contains(text(),'Condition:')]]\"")
    private WebElement productCondition;

    @FindBy (xpath = "//p[.//b[contains(text(),'Brand:')]]\"")
    private WebElement productBrand;

    @FindBy (css = "#name")
    private WebElement productReviewNameInput;

    @FindBy (css = "#email")
    private WebElement productReviewEmailInput;

    @FindBy (css = "#review")
    private WebElement productReviewInput;

    @FindBy (css = "#button-review")
    private WebElement productReviewSubmitButton;
    
}
