package com.project.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage extends BasePage {
    WebDriver driver;

    CheckoutPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "div.breadcrumbs a")
    private WebElement homeLink;

    @FindBy (css = "li.address_firstname")
    private WebElement customerNameDelivery;

    @FindBy (css = "li.address_address1:nth-child(2)")
    private WebElement customerAddressDelivery;

    @FindBy (css = "li.address_city")
    private WebElement customerCityStateZipDelivery;

    @FindBy (css = "li.address_country_name")
    private WebElement customercountryNameDelivery;

    @FindBy (css = "li.address_phone")
    private WebElement customerPhonenumberDelivery;

    @FindBy (css = "li.address_firstname")
    private WebElement customerNameBilling;

    @FindBy (css = "li.address_address1:nth-child(2)")
    private WebElement customerAddressBilling;

    @FindBy (css = "li.address_city")
    private WebElement customerCityStateZipBilling;

    @FindBy (css = "li.address_country_name")
    private WebElement customercountryNameBilling;

    @FindBy (css = "li.address_phone")
    private WebElement customerPhonenumberBilling;

    @FindBy (css = "tr[id*='product']")
    private List<WebElement> productRows;

    @FindBy (css = "div.ordermsg textarea")
    private WebElement orderCommentInput;

    @FindBy (css = "a[href='/payment']")
    private WebElement placeOrderLink;

    private final By productName = By.cssSelector("td.cart_description a");
    private final By productCategoryAndSubCategory = By.cssSelector("td.cart_description p");
    private final By productUnitPrice = By.cssSelector("td.cart_price p");
    private final By productTotalPrice = By.cssSelector("td.cart_total p");
    private final By productQuantity = By.cssSelector("td.cart_quantity button");

}
