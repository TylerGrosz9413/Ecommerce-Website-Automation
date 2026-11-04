package com.project.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends ProductCatalog {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "#recommended-item-carousel")
    private WebElement recommendedItemsCarouselDiv;

    @FindBy (css = ".left")
    private WebElement slideLeftRecommendedItemControl;

    @FindBy (css = ".right")
    private WebElement slideRightRecommendedItemControl;

    private final By productNameInCard = By.cssSelector("div.productinfo > p");
    private final By productPriceInCard = By.cssSelector("div.productinfo > h2");
    private final By productCard = By.cssSelector("div.productinfo");
    private final By addToCartLink = By.cssSelector("a.add-to-cart");

    public List<WebElement> getRecommendedItemProductCards() {
        return recommendedItemsCarouselDiv.findElements(productCard);
    }

    public List<WebElement> getRecommendedItemProductNames() {
        return recommendedItemsCarouselDiv.findElements(productNameInCard);
    }

    public List<WebElement> getRecommendedItemProductPrices() {
        return recommendedItemsCarouselDiv.findElements(productPriceInCard);
    }

    public List<WebElement> getvisibleRecommendedItemProductCards() {
        List<WebElement> productCards = getRecommendedItemProductCards();
        return productCards.stream().filter(WebElement::isDisplayed).toList();
    }

    public String getProductPrice(String productName) {
        List<WebElement> productPrices = getRecommendedItemProductPrices();
        List<WebElement> productNames = getRecommendedItemProductNames();

        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).getText().equals(productName)) {
                return productPrices.get(i).getText();
            }
        }

        throw new RuntimeException("Product not found: " + productName);
    }

    public void addProductToCart(String productName) {
        List<WebElement> productCards = getRecommendedItemProductCards();
        WebElement productCard = null;
        for (WebElement card : productCards) {
            if (card.findElement(productNameInCard).getText().equals(productName)) {
                productCard = card;
                break;
            }
        }
        if (productCard == null) {
            throw new RuntimeException("Product not found: " + productName);
        }
        WebElement addToCartLinkElement = productCard.findElement(addToCartLink);
        waitForWebElementToBeClickable(addToCartLinkElement);
        addToCartLinkElement.click();
    }

    public void clickSlideLeftRecommendedItemControl() {
        waitForWebElementToBeClickable(slideLeftRecommendedItemControl);
        slideLeftRecommendedItemControl.click();
    }

    public void clickSlideRightRecommendedItemControl() {
        waitForWebElementToBeClickable(slideRightRecommendedItemControl);
        slideRightRecommendedItemControl.click();
    }

    public boolean recommendedItemsCarouselIsDisplayed() {
        waitForWebElementToAppear(recommendedItemsCarouselDiv);
        return recommendedItemsCarouselDiv.isDisplayed();
    }

}
