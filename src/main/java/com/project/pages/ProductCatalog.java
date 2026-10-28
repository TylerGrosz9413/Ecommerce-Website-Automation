package com.project.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductCatalog extends BasePage {
    WebDriver driver;

    ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy (css = "#search_product")
    WebElement searchProduct;

    @FindBy (css = "a[href='#Women']")
    WebElement women;

    @FindBy (css = "a[href='#Men']")
    WebElement men;

    @FindBy (css = "a[href='#Kids']")
    WebElement kids;


}
