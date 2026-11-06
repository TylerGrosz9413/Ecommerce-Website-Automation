package com.project.pages;

import java.time.Duration;
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

    public List<WebElement> getRecommendedItemsProductCards() {
        waitForWebElementToAppear(recommendedItemsCarouselDiv);
        return recommendedItemsCarouselDiv.findElements(productCard);
    }

    public List<String> getRecommendedItemsProductNames() {
        waitForWebElementToAppear(recommendedItemsCarouselDiv);
        List<WebElement> productCards = getRecommendedItemsProductCards();
        return productCards.stream().map(card -> card.findElement(productNameInCard).getText()).toList();
    }

    public List<String> getRecommendedItemsProductPrices() {
        waitForWebElementToAppear(recommendedItemsCarouselDiv);
        List<WebElement> productCards = getRecommendedItemsProductCards();
        return productCards.stream().map(card -> card.findElement(productPriceInCard).getText()).toList();
    }

    public List<WebElement> getVisibleRecommendedItemsProductCards() {
        List<WebElement> productCards = getRecommendedItemsProductCards();
        return productCards.stream().filter(WebElement::isDisplayed).toList();
    }

    public boolean isRecommendedItemsProductVisible(String productName) {
        List<WebElement> productCards = getVisibleRecommendedItemsProductCards();
        for (WebElement card : productCards) {
            if (card.findElement(productNameInCard).getText().equals(productName)) {
                return true;
            }
        }
        return false;
    }

    public String getProductPrice(String productName) {
        waitForWebElementToAppear(recommendedItemsCarouselDiv);
        List<String> productPrices = getRecommendedItemsProductPrices();
        List<String> productNames = getRecommendedItemsProductNames();

        for (int i = 0; i < productNames.size(); i++) {
            if (productNames.get(i).equals(productName)) {
                return productPrices.get(i);
            }
        }

        throw new RuntimeException("Product not found: " + productName);
    }

    public void addProductToCart(String productName) {
        waitForWebElementToAppear(recommendedItemsCarouselDiv);
        List<WebElement> productCards = getRecommendedItemsProductCards();
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
        while (!productCard.isDisplayed()) {
            clickSlideRightRecommendedItemControl();
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
        return recommendedItemsCarouselDiv.isDisplayed();
    }

}
