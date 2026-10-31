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
    WebElement homeLink;

    @FindBy (css = "li.address_firstname")
    WebElement customerNameDelivery;

    @FindBy (css = "li.address_address1:nth-child(2)")
    WebElement customerAddressDelivery;

    @FindBy (css = "li.address_city")
    WebElement customerCityStateZipDelivery;

    @FindBy (css = "li.address_country_name")
    WebElement customercountryNameDelivery;

    @FindBy (css = "li.address_phone")
    WebElement customerPhonenumberDelivery;

    @FindBy (css = "li.address_firstname")
    WebElement customerNameBilling;

    @FindBy (css = "li.address_address1:nth-child(2)")
    WebElement customerAddressBilling;

    @FindBy (css = "li.address_city")
    WebElement customerCityStateZipBilling;

    @FindBy (css = "li.address_country_name")
    WebElement customercountryNameBilling;

    @FindBy (css = "li.address_phone")
    WebElement customerPhonenumberBilling;

    @FindBy (css = "tr[id*='product']")
    List<WebElement> productRows;

    @FindBy (css = "div.ordermsg textarea")
    WebElement orderCommentInput;

    @FindBy (css = "a[href='/payment']")
    WebElement placeOrderLink;

    By productName = By.cssSelector("td.cart_description a");
    By productCategoryAndSubCategory = By.cssSelector("td.cart_description p");
    By productUnitPrice = By.cssSelector("td.cart_price p");
    By productTotalPrice = By.cssSelector("td.cart_total p");
    By productQuantity = By.cssSelector("td.cart_quantity button");

}
