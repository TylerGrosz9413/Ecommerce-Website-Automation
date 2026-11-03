package com.project.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductCatalog extends BasePage {
    WebDriver driver;

    ProductCatalog(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "#search_product")
    private WebElement searchProductInput;

    @FindBy (css = "#submit_search")
    private WebElement searchProductButton;

    @FindBy (css = "a[href='#Women']")
    private WebElement womenCategoryHeaderToggle;

    @FindBy (css = "a[href='#Women'] i.fa-plus")
    private WebElement womenCategoryPlusSignToggle;

    @FindBy (css = "a[href='#Men']")
    private WebElement menCategoryHeaderToggle;

    @FindBy (css = "a[href='#Men'] i.fa-plus")
    private WebElement menCategoryPlusSignToggle;

    @FindBy (css = "a[href='#Kids']")
    private WebElement kidsCategoryHeaderToggle;

    @FindBy (css = "a[href='#Kids'] i.fa-plus")
    private WebElement kidsCategoryPlusSignToggle;

    @FindBy (css = "a[href*='/brand products/Polo']")
    private WebElement poloBrandLink;

    @FindBy (css = "a[href*='/brand products/H&M']")
    private WebElement hmBrandlink;

    @FindBy (css = "a[href*='/brand products/Madame']")
    private WebElement madameBrandLink;

    @FindBy (css = "a[href*='/brand products/Mast & Harbour']")
    private WebElement mastHarbourBrandLink;

    @FindBy (css = "a[href*='/brand products/Babyhug']")
    private WebElement babyHugBrandLink;

    @FindBy (css = "a[href*='/brand products/Allen Solly Junior']")
    private WebElement allenSollyJrBrandLink;

    @FindBy (css = "a[href*='/brand products/Kookie Kids']")
    private WebElement kookieKidsBrandLink;

    @FindBy (css = "a[href*='/brand products/Biba']")
    private WebElement bibaBrandLink;

    @FindBy (css = "div.category-products a[data-toggle='collapse']")
    private List<WebElement> categoryToggles;

    @FindBy (css = "div.features_items > div.col-sm-4")
    private List<WebElement> productCards;

    @FindBy (css = "ul.nav > li")
    private List<WebElement> brandLinks;

    private final By subCategoties = By.cssSelector("a[data-toggle='collapse'] li a");
    private final By addToCartButton = By.cssSelector("a.add-to-cart");
    private final By productBox = By.cssSelector("div.productinfo");
    private final By viewProductLink = By.cssSelector("a[href*='/product details/']");
    private final By productPriceBeforeHover = By.cssSelector("div.productinfo > h2");
    private final By productNameBeforeHover = By.cssSelector("div.productinfo > p");
    private final By productPriceHover = By.cssSelector("div.productinfo div.overlay-content > h2");
    private final By productNameHover = By.cssSelector("div.productinfo div.overlay-content > p");
    private final By addedToCartPopUp = By.cssSelector("div.modal-content");
    private final By viewCart = By.cssSelector("div.modal-content a[href='/view cart']");
    private final By continueShopping = By.cssSelector("div.modal-content button.btn-success");
    private final By addedHeader = By.cssSelector("div.modal-content h4.modal-title");
    private final By addedMessage = By.cssSelector("div.modal-content p");
    private final By dressSubCategoryLink = By.linkText("Dress ");
    private final By topsSubCategoryLink = By.linkText("Tops ");
    private final By sareeSubCategoryLink = By.linkText("Saree ");
    private final By tShirtsSubCategoryLink = By.linkText("Tshirts ");
    private final By jeansSubCategoryLink = By.linkText("Jeans ");
    private final By kidsdressSubCategoryLink = By.linkText("Dress ");
    private final By topsTShirtsSubCategoryLink = By.linkText("Tops & Shirts ");

    //WebElement dress = driver.findElement(By.xpath("//div[contains(.,'Dress')]"));

    public void clickWomenCategoryHeaderToggle() {
        waitForWebElementToBeClickable(womenCategoryHeaderToggle);
        womenCategoryHeaderToggle.click();
    }

    public void clickMenCategoryHeaderToggle() {
        waitForWebElementToBeClickable(menCategoryHeaderToggle);
        menCategoryHeaderToggle.click();
    }

    public void clickKidsCategoryHeaderToggle() {
        waitForWebElementToBeClickable(kidsCategoryHeaderToggle);
        kidsCategoryHeaderToggle.click();
    }

    public void clickPoloBrandLink() {
        waitForWebElementToBeClickable(poloBrandLink);
        poloBrandLink.click();
    }

    public void clickHMBandLink() {
        waitForWebElementToBeClickable(hmBrandlink);
        hmBrandlink.click();
    }

    public void clickMadameBrandLink() {
        waitForWebElementToBeClickable(madameBrandLink);
        madameBrandLink.click();
    }

    public void clickMastHarbourBrandLink() {
        waitForWebElementToBeClickable(mastHarbourBrandLink);
        mastHarbourBrandLink.click();
    }

    public void clickBabyHugBrandLink() {
        waitForWebElementToBeClickable(babyHugBrandLink);
        babyHugBrandLink.click();
    }

    public void clickAllenSollyJrBrandLink() {
        waitForWebElementToBeClickable(allenSollyJrBrandLink);
        allenSollyJrBrandLink.click();
    }

    public void clickKookieKidsBrandLink() {
        waitForWebElementToBeClickable(kookieKidsBrandLink);
        kookieKidsBrandLink.click();
    }

    public void clickBibaBrandLink() {
        waitForWebElementToBeClickable(bibaBrandLink);
        bibaBrandLink.click();
    }

    public void clickDressSubCategoryLink() {
        waitForElementToBeClickable(dressSubCategoryLink);
        driver.findElement(dressSubCategoryLink).click();
    }

    public void clickTopsSubCategoryLink() {
        waitForElementToBeClickable(topsSubCategoryLink);
        driver.findElement(topsSubCategoryLink).click();
    }

    public void clickSareeSubCategoryLink() {
        waitForElementToBeClickable(sareeSubCategoryLink);
        driver.findElement(sareeSubCategoryLink).click();
    }

    public void clickTShirtsSubCategoryLink() {
        waitForElementToBeClickable(tShirtsSubCategoryLink);
        driver.findElement(tShirtsSubCategoryLink).click();
    }

    public void clickJeansSubCategoryLink() {
        waitForElementToBeClickable(jeansSubCategoryLink);
        driver.findElement(jeansSubCategoryLink).click();
    }

    public void clickKidsDressSubCategoryLink() {
        waitForElementToBeClickable(kidsdressSubCategoryLink);
        driver.findElement(kidsdressSubCategoryLink).click();
    }

    public void clickTopsTShirtsSubCategoryLink() {
        waitForElementToBeClickable(topsTShirtsSubCategoryLink);
        driver.findElement(topsTShirtsSubCategoryLink).click();
    }

    public List<WebElement> getProductNamesBeforeHover() {
        return driver.findElements(productNameBeforeHover);
    }

    public List<WebElement> getProductPricesBeforeHover() {
        return driver.findElements(productPriceBeforeHover);
    }

    public List<WebElement> getProductNamesHover() {
        return driver.findElements(productNameHover);
    }
    

    public void hoverOverProductCardByName(String productName) {
        //List<WebElement> productCards = driver.findElements(By.cssSelector("div.features_items > div.col-sm-4"));
        Actions actions = new Actions(driver);

        for (WebElement productCard : productCards) {
            actions.moveToElement(productCard).perform();
            waitForWebElementToAppear(productCard.findElement(productNameHover));

            List<WebElement> hoveredProductNames = driver.findElements(productNameHover);
            for (WebElement hoveredProductName : hoveredProductNames) {
                if (hoveredProductName.getText().trim().equalsIgnoreCase(productName.trim())) {
                    return;
                }
            }
        }

        throw new NoSuchElementException("Product not found on screen after hover: " + productName);
    }

}
