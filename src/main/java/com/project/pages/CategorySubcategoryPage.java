package com.project.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class CategorySubcategoryPage extends ProductCatalog {
    WebDriver driver;

    public CategorySubcategoryPage(WebDriver driver) {
        super(driver);
        this.driver = driver; 
    }

    private final By categorySubcategoryHeader = By.cssSelector(".features_items > h2.title");

    public String getCategorySubcategoryHeaderText() {
        waitForElementToAppear(categorySubcategoryHeader);
        return driver.findElement(categorySubcategoryHeader).getText();
    }
    
}
