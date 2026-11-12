package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class BrandsPage extends ProductCatalog {
    WebDriver driver;

    public BrandsPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    private final By brandsHeader = By.cssSelector(".features_items > h2.title");

    public String getBrandHeaderText() {
        waitForElementToAppear(brandsHeader);
        return driver.findElement(brandsHeader).getText();
    }

}
