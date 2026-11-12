package com.project.pages;

import org.openqa.selenium.By;
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

    @FindBy (css = ".product-information > h2")
    private WebElement productName;

    @FindBy (css = "div.product-information > p:nth-of-type(1)")
    private WebElement productCategorySubcategory;

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

    private final By productReviewSuccessMessage = By.cssSelector(".alert-success > span");

    public String getProductName() {
        waitForWebElementToAppear(productName);
        return productName.getText();
    }

    public String getProductCategory() {
        waitForWebElementToAppear(productCategorySubcategory);
        String text = productCategorySubcategory.getText();
        return text.replaceFirst("^Category:\\s*", "")
                   .split("\\s*>\\s*")[0]
                   .trim();
    }

    public String getProductSubcategory() {
        waitForWebElementToAppear(productCategorySubcategory);
        String text = productCategorySubcategory.getText();
        return text.replaceFirst("^Category:\\s*", "")
                   .split("\\s*>\\s*")[1]
                   .trim();
    }

    public String getProductPrice() {
        waitForWebElementToAppear(productPrice);
        return productPrice.getText();
    }

    public String getProductAvailability() {
        waitForWebElementToAppear(productAvailability);
        return productAvailability.getText();
    }

    public String getProductCondition() {
        waitForWebElementToAppear(productCondition);
        return productCondition.getText();
    }

    public String getProductBrand() {
        waitForWebElementToAppear(productBrand);
        return productBrand.getText();
    }

    public void addProductToCart() {
        waitForWebElementToBeClickable(addToCartButton);
        addToCartButton.click();
    }

    public void submitProductReview(String name, String email, String review) {
        waitForWebElementToAppear(productReviewNameInput);
        productReviewNameInput.sendKeys(name);
        productReviewEmailInput.sendKeys(email);
        productReviewInput.sendKeys(review);
        waitForWebElementToBeClickable(productReviewSubmitButton);
        productReviewSubmitButton.click();
    }

    public String getProductReviewSuccessMessage() {
        waitForElementToAppear(productReviewSuccessMessage);
        return driver.findElement(productReviewSuccessMessage).getText();
    }

    public void setProductQuantity(int quantity) {
        waitForWebElementToAppear(productQuantity);
        productQuantity.clear();
        productQuantity.sendKeys(String.valueOf(quantity));
    }
    
}
