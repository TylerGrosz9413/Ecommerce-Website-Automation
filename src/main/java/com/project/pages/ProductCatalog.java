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

    private final By productsHeader = By.cssSelector(".features_items > h2.title");
    private final By subCategoties = By.cssSelector("a[data-toggle='collapse'] li a");
    private final By addToCartLinkHover = By.cssSelector("a.add-to-cart");
    private final By productBox = By.cssSelector("div.productinfo");
    private final By viewProductLink = By.cssSelector("a[href*='/product details/']");
    private final By productPriceBeforeHover = By.cssSelector("div.features_items div.productinfo > h2");
    private final By productNameBeforeHover = By.cssSelector("div.features_items div.productinfo > p");
    private final By productPriceHover = By.cssSelector("div.productinfo div.overlay-content > h2");
    private final By productNameHover = By.cssSelector("div.productinfo div.overlay-content > p");
    private final By addedToCartPopUp = By.cssSelector("div.modal-content");
    private final By viewCartAddedToCartPopUp = By.cssSelector("div.modal-content a[href='/view cart']");
    private final By continueShoppingAddedToCartPopUp = By.cssSelector("div.modal-content button.btn-success");
    private final By addedToCartHeader = By.cssSelector("div.modal-content h4.modal-title");
    private final By addedToCartMessage = By.cssSelector("div.modal-content p");
    private final By dressSubCategoryLink = By.linkText("Dress ");
    private final By topsSubCategoryLink = By.linkText("Tops ");
    private final By sareeSubCategoryLink = By.linkText("Saree ");
    private final By tShirtsSubCategoryLink = By.linkText("Tshirts ");
    private final By jeansSubCategoryLink = By.linkText("Jeans ");
    private final By kidsdressSubCategoryLink = By.linkText("Dress ");
    private final By topsTShirtsSubCategoryLink = By.linkText("Tops & Shirts ");

    //WebElement dress = driver.findElement(By.xpath("//div[contains(.,'Dress')]"));

    public String getProductsHeaderText() {
        waitForElementToAppear(productsHeader);
        return driver .findElement(productsHeader).getText();
    }

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

    public BrandsPage clickPoloBrandLink() {
        waitForWebElementToBeClickable(poloBrandLink);
        poloBrandLink.click();
        return new BrandsPage(driver);
    }

    public BrandsPage clickHMBandLink() {
        waitForWebElementToBeClickable(hmBrandlink);
        hmBrandlink.click();
        return new BrandsPage(driver);
    }

    public BrandsPage clickMadameBrandLink() {
        waitForWebElementToBeClickable(madameBrandLink);
        madameBrandLink.click();
        return new BrandsPage(driver);
    }

    public BrandsPage clickMastHarbourBrandLink() {
        waitForWebElementToBeClickable(mastHarbourBrandLink);
        mastHarbourBrandLink.click();
        return new BrandsPage(driver);
    }

    public BrandsPage clickBabyHugBrandLink() {
        waitForWebElementToBeClickable(babyHugBrandLink);
        babyHugBrandLink.click();
        return new BrandsPage(driver);
    }

    public BrandsPage clickAllenSollyJrBrandLink() {
        waitForWebElementToBeClickable(allenSollyJrBrandLink);
        allenSollyJrBrandLink.click();
        return new BrandsPage(driver);
    }

    public BrandsPage clickKookieKidsBrandLink() {
        waitForWebElementToBeClickable(kookieKidsBrandLink);
        kookieKidsBrandLink.click();
        return new BrandsPage(driver);
    }

    public BrandsPage clickBibaBrandLink() {
        waitForWebElementToBeClickable(bibaBrandLink);
        bibaBrandLink.click();
        return new BrandsPage(driver);
    }

    public CategorySubcategoryPage clickDressSubCategoryLink() {
        waitForElementToBeClickable(dressSubCategoryLink);
        driver.findElement(dressSubCategoryLink).click();
        return new CategorySubcategoryPage(driver);
    }

    public CategorySubcategoryPage clickTopsSubCategoryLink() {
        waitForElementToBeClickable(topsSubCategoryLink);
        driver.findElement(topsSubCategoryLink).click();
        return new CategorySubcategoryPage(driver);
    }

    public CategorySubcategoryPage clickSareeSubCategoryLink() {
        waitForElementToBeClickable(sareeSubCategoryLink);
        driver.findElement(sareeSubCategoryLink).click();
        return new CategorySubcategoryPage(driver);
    }

    public CategorySubcategoryPage clickTShirtsSubCategoryLink() {
        waitForElementToBeClickable(tShirtsSubCategoryLink);
        driver.findElement(tShirtsSubCategoryLink).click();
        return new CategorySubcategoryPage(driver);
    }

    public CategorySubcategoryPage clickJeansSubCategoryLink() {
        waitForElementToBeClickable(jeansSubCategoryLink);
        driver.findElement(jeansSubCategoryLink).click();
        return new CategorySubcategoryPage(driver);
    }

    public CategorySubcategoryPage clickKidsDressSubCategoryLink() {
        waitForElementToBeClickable(kidsdressSubCategoryLink);
        driver.findElement(kidsdressSubCategoryLink).click();
        return new CategorySubcategoryPage(driver);
    }

    public CategorySubcategoryPage clickTopsTShirtsSubCategoryLink() {
        waitForElementToBeClickable(topsTShirtsSubCategoryLink);
        driver.findElement(topsTShirtsSubCategoryLink).click();
        return new CategorySubcategoryPage(driver);
    }

    public List<WebElement> getProductNamesBeforeHover() {
        waitForWebElementToAppear(productCards.get(0)); // Wait for at least one product card to appear
        return driver.findElements(productNameBeforeHover);
    }

    public List<WebElement> getProductPricesBeforeHover() {
        waitForWebElementToAppear(productCards.get(0)); // Wait for at least one product card to appear
        return driver.findElements(productPriceBeforeHover);
    }

    public void searchProduct(String productName) {
        waitForWebElementToAppear(searchProductInput);
        searchProductInput.clear();
        searchProductInput.sendKeys(productName);
        waitForWebElementToBeClickable(searchProductButton);
        searchProductButton.click();
    }

    public String getAddedToCartHeaderText() {
        waitForElementToAppear(addedToCartPopUp);
        WebElement addedToCartHeaderElement = driver.findElement(addedToCartHeader);
        return addedToCartHeaderElement.getText();
    }

    public String getAddedToCartMessageText() {
        waitForElementToAppear(addedToCartPopUp);
        WebElement addedToCartMessageElement = driver.findElement(addedToCartMessage);
        return addedToCartMessageElement.getText();
    }

    public CartPage clickViewCartInAddedToCartPopUp() {
        waitForElementToAppear(addedToCartPopUp);
        WebElement viewCartButton = driver.findElement(viewCartAddedToCartPopUp);
        waitForWebElementToBeClickable(viewCartButton);
        viewCartButton.click();
        return new CartPage(driver);
    }

    public void clickContinueShoppingInAddedToCartPopUp() {
        waitForElementToAppear(addedToCartPopUp);
        WebElement continueShoppingButton = driver.findElement(continueShoppingAddedToCartPopUp);
        continueShoppingButton.click();
    }

    public boolean addedToCartPopUpIsDisplayed() {
        waitForElementToAppear(addedToCartPopUp);
        WebElement addedToCartPopUpElement = driver.findElement(addedToCartPopUp);
        return addedToCartPopUpElement.isDisplayed();
    }

    public String getProductNameHover() {
        waitForElementToAppear(productNameHover);
        WebElement productNameHoverElement = driver.findElement(productNameHover);
        return productNameHoverElement.getText();
    }

    public String getProductPriceHover() {
        waitForElementToAppear(productPriceHover);
        WebElement productPriceHoverElement = driver.findElement(productPriceHover);
        return productPriceHoverElement.getText();
    }

    public ProductPage clickViewProductLink(String productName) {
        waitForElementToAppear(viewProductLink);
        WebElement viewProductLinkElement = driver.findElement(viewProductLink);
        viewProductLinkElement.click();
        return new ProductPage(driver);
    }
    
    public void addProductToCartHover(String productName) {
        hoverOverProductCardByName(productName);
        waitForElementToAppear(addToCartLinkHover);
        WebElement addToCartLink = driver.findElement(addToCartLinkHover);
        addToCartLink.click();
    }

    public void hoverOverProductCardByName(String productName) {
        //List<WebElement> productCards = driver.findElements(By.cssSelector("div.features_items > div.col-sm-4"));
        Actions actions = new Actions(driver);
        waitForWebElementToAppear(productCards.get(0)); // Wait for at least one product card to appear
        for (WebElement productCard : productCards) {
            actions.moveToElement(productCard).perform();
            waitForWebElementToAppear(productCard.findElement(productNameHover));

            WebElement hoveredProductName = driver.findElement(productNameHover);
            if (hoveredProductName.getText().trim().equalsIgnoreCase(productName.trim())) {
                return;
            }
        }

        throw new NoSuchElementException("Product not found on screen after hover: " + productName);
    }

}
