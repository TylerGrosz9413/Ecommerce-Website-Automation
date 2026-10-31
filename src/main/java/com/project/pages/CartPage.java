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
    WebElement homeLink;

    @FindBy (css = ".check out")
    WebElement checkoutLink;

    @FindBy (css = "tr[id*='product']")
    List<WebElement> productRows;

    By productName = By.cssSelector("td.cart_description a");
    By productCategoryAndSubCategory = By.cssSelector("td.cart_description p");
    By productUnitPrice = By.cssSelector("td.cart_price p");
    By productTotalPrice = By.cssSelector("td.cart_total p");
    By productQuantity = By.cssSelector("td.cart_quantity button");
    By removeProductLink = By.cssSelector("i.fa");

}
