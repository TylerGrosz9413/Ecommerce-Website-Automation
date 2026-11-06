package com.project.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {
    WebDriver driver;

    CartPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "div.breadcrumbs a")
    private WebElement homeLink;

    @FindBy (css = ".check out")
    private WebElement checkoutLink;

    @FindBy (css = "tr[id*='product']")
    private List<WebElement> productRows;

    private final By productName = By.cssSelector("td.cart_description a");
    private final By productCategoryAndSubCategory = By.cssSelector("td.cart_description p");
    private final By productUnitPrice = By.cssSelector("td.cart_price p");
    private final By productTotalPrice = By.cssSelector("td.cart_total p");
    private final By productQuantity = By.cssSelector("td.cart_quantity button");
    private final By removeProductLink = By.cssSelector("i.fa");
    private final By registerLoginLinkPopUp = By.cssSelector(".modal-content a[href='/login']");
    private final By continueOnCartBtnPopUp = By.cssSelector(".modal-content button.close-checkout-modal");
    private final By emptyCartMessage = By.cssSelector("#empty_cart b");
    private final By emptyCartProductsLink = By.cssSelector("#empty_cart a[href='/products']");

    public List<WebElement> getProductRows() {
        waitForWebElementToAppear(productRows.get(0));
        return productRows;
    }

    public List<String> getProductNames() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.stream().map(row -> row.findElement(productName).getText()).toList();
    }

    public List<String> getProductPrices() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.stream().map(row -> row.findElement(productUnitPrice).getText()).toList();
    }

    public List<String> getProductQuantities() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.stream().map(row -> row.findElement(productQuantity).getText()).toList();
    }

    public List<String> getProductTotalPrices() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.stream().map(row -> row.findElement(productTotalPrice).getText()).toList();
    }

    public void removeProductFromCart(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                row.findElement(removeProductLink).click();
                break;
            }
        }
    }

    public void clickCheckoutLink() {
        waitForWebElementToBeClickable(checkoutLink);
        checkoutLink.click();
    }

    public HomePage clickHomeLink() {
        waitForWebElementToBeClickable(homeLink);
        homeLink.click();
        return new HomePage(driver);
    }

    public boolean isProductInCart(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public boolean isCartEmpty() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.isEmpty();
    }

    public int getNumberOfProductsInCart() {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        return rows.size();
    }

    public String getProductUnitPrice(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return row.findElement(productUnitPrice).getText();
            }
        }
        throw new RuntimeException("Product not found in cart: " + productName);
    }

    public String getProductTotalPrice(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return row.findElement(productTotalPrice).getText();
            }
        }
        throw new RuntimeException("Product not found in cart: " + productName);
    }

    public String getProductQuantity(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return row.findElement(productQuantity).getText();
            }
        }
        throw new RuntimeException("Product not found in cart: " + productName);
    }

    public String getProductCategory(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return row.findElement(productCategoryAndSubCategory).getText().split(" > ")[0].trim();
            }
        }
        throw new RuntimeException("Product not found in cart: " + productName);
    }

    public String getProductSubCategory(String productName) {
        waitForWebElementToAppear(productRows.get(0));
        List<WebElement> rows = getProductRows();
        for (WebElement row : rows) {
            if (row.findElement(this.productName).getText().equals(productName)) {
                return row.findElement(productCategoryAndSubCategory).getText().split(" > ")[1].trim();
            }
        }
        throw new RuntimeException("Product not found in cart: " + productName);
    }

    public boolean isRegisterLoginLinkPopUpPresent() {
        return driver.findElement(registerLoginLinkPopUp).isDisplayed();
    }

    public LoginSignUp clickRegisterLoginLinkPopUp() {
        waitForElementToBeClickable(registerLoginLinkPopUp);
        driver.findElement(registerLoginLinkPopUp).click();
        return new LoginSignUp(driver);
    }

    public boolean isContinueOnCartBtnPopUpPresent() {
        return driver.findElement(continueOnCartBtnPopUp).isDisplayed();
    }

    public void clickContinueOnCartBtnPopUp() {
        waitForElementToBeClickable(continueOnCartBtnPopUp);
        driver.findElement(continueOnCartBtnPopUp).click();
    }

    public String getEmptyCartMessage() {
        waitForElementToAppear(emptyCartMessage);
        return driver.findElement(emptyCartMessage).getText();
    }

    public boolean isEmptyCartProductsLinkPresent() {
        return driver.findElement(emptyCartProductsLink).isDisplayed();
    }

    public ProductCatalog clickEmptyCartProductsLink() {
        waitForElementToBeClickable(emptyCartProductsLink);
        driver.findElement(emptyCartProductsLink).click();
        return new ProductCatalog(driver);
    }

}
