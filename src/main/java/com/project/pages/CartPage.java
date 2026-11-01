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

}
