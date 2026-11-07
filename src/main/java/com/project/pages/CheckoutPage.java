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

    @FindBy (css = "p.cart_total_price")
    private WebElement cartTotalPrice;

    @FindBy (css = "div.ordermsg textarea")
    private WebElement orderCommentInput;

    @FindBy (css = "a[href='/payment']")
    private WebElement placeOrderLink;

    private final By productName = By.cssSelector("td.cart_description a");
    private final By productCategoryAndSubCategory = By.cssSelector("td.cart_description p");
    private final By productUnitPrice = By.cssSelector("td.cart_price p");
    private final By productTotalPrice = By.cssSelector("td.cart_total p");
    private final By productQuantity = By.cssSelector("td.cart_quantity button");

    public List<WebElement> getProductRows() {
        waitForWebElementToAppear(productRows.get(0));
        return productRows;
    }

    public List<String> getProductNames() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.stream().map(row -> row.findElement(productName).getText()).toList();
    }

    public List<String> getProductUnitPrices() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.stream().map(row -> row.findElement(productUnitPrice).getText()).toList();
    }

    public List<Integer> getProductQuantities() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.stream().map(row -> Integer.parseInt(row.findElement(productQuantity).getText())).toList();
    }

    public List<String> getProductTotalPrices() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.stream().map(row -> row.findElement(productTotalPrice).getText()).toList();
    }

    public void enterOrderComment(String comment) {
        waitForWebElementToAppear(orderCommentInput);
        orderCommentInput.clear();
        orderCommentInput.sendKeys(comment);
    }

    public void clickPlaceOrder() {
        waitForWebElementToAppear(placeOrderLink);
        placeOrderLink.click();
    }

    public String getCustomerNameDelivery() {
        waitForWebElementToAppear(customerNameDelivery);
        return customerNameDelivery.getText();
    }

    public String getCustomerAddressDelivery() {
        waitForWebElementToAppear(customerAddressDelivery);
        return customerAddressDelivery.getText();
    }

    public String getCustomerCityStateZipDelivery() {
        waitForWebElementToAppear(customerCityStateZipDelivery);
        return customerCityStateZipDelivery.getText();
    }

    public String getCustomerCountryNameDelivery() {
        waitForWebElementToAppear(customercountryNameDelivery);
        return customercountryNameDelivery.getText();
    }

    public String getCustomerPhoneNumberDelivery() {
        waitForWebElementToAppear(customerPhonenumberDelivery);
        return customerPhonenumberDelivery.getText();
    }

    public String getCustomerNameBilling() {
        waitForWebElementToAppear(customerNameBilling);
        return customerNameBilling.getText();
    }

    public String getCustomerAddressBilling() {
        waitForWebElementToAppear(customerAddressBilling);
        return customerAddressBilling.getText();
    }

    public String getCustomerCityStateZipBilling() {
        waitForWebElementToAppear(customerCityStateZipBilling);
        return customerCityStateZipBilling.getText();
    }

    public String getCustomerCountryNameBilling() {
        waitForWebElementToAppear(customercountryNameBilling);
        return customercountryNameBilling.getText();
    }

    public String getCustomerPhoneNumberBilling() {
        waitForWebElementToAppear(customerPhonenumberBilling);
        return customerPhonenumberBilling.getText();
    }

    public HomePage clickHomeLink() {
        waitForWebElementToBeClickable(homeLink);
        homeLink.click();
        return new HomePage(driver);
    }

    public boolean isProductInCheckout(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public Integer getProductQuantity(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return Integer.parseInt(row.findElement(productQuantity).getText());
            }
        }
        throw new RuntimeException("Product not found in checkout: " + productName);
    }

    public String getProductUnitPrice(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return row.findElement(productUnitPrice).getText();
            }
        }
        throw new RuntimeException("Product not found in checkout: " + productName);
    }

    public String getProductTotalPrice(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return row.findElement(productTotalPrice).getText();
            }
        }
        throw new RuntimeException("Product not found in checkout: " + productName);
    }

    public boolean isCheckoutEmpty() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.isEmpty();
    }

    public int getNumberOfProductsInCheckout() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.size();
    }

    public String getProductCategory(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return row.findElement(productCategoryAndSubCategory).getText();
            }
        }
        throw new RuntimeException("Product not found in checkout: " + productName);
    }

    public String getProductSubCategory(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return row.findElement(productCategoryAndSubCategory).getText().split(" > ")[1].trim();
            }
        }
        throw new RuntimeException("Product not found in checkout: " + productName);
    }

    public String getOrderComment() {
        waitForWebElementToAppear(orderCommentInput);
        return orderCommentInput.getAttribute("value");
    }

    public String getCartTotalPrice() {
        waitForWebElementToAppear(cartTotalPrice);
        return cartTotalPrice.getText();
    }

}
