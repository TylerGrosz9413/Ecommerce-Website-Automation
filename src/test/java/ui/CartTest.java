package ui;

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.WebElementToJsonConverter;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.project.pages.CartPage;
import com.project.pages.ProductCatalog;
import com.project.pages.ProductDetail;

import base.BaseTest;

public class CartTest extends BaseTest {
    
    @Test
    public void addProductsToCart() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        ProductCatalog productCatalog = homePage.clickProductsLink();
        //WebElement productCard1 = productCatalog.hoverOverProductCardByName("Men Tshirt");
        int menTshitCatalogPrice = productCatalog.getProductPriceHover("Men Tshirt");
        productCatalog.addProductToCartHover("Men Tshirt");
        productCatalog.clickContinueShoppingInAddedToCartPopUp();
        //productCatalog.hoverOverProductCardByName("Pure Cotton V-Neck T-Shirt");
        int pureCottonVneckTshirtCatalogPrice = productCatalog.getProductPriceHover("Pure Cotton V-Neck T-Shirt");
        productCatalog.addProductToCartHover("Pure Cotton V-Neck T-Shirt");
        CartPage cartPage = productCatalog.clickViewCartInAddedToCartPopUp();
        int productCountInCart = cartPage.getNumberOfProductsInCart();
        List<WebElement> productRowsInCart = cartPage.getProductRows();
        List<String> productNamesInCart = cartPage.getProductNames();
        //boolean menTshirtPresent = productNamesInCart.contains("Men Tshirt");
        boolean menTshirtPresent = cartPage.isProductInCart("Men Tshirt");
        softAssert.assertTrue(menTshirtPresent);
        //boolean pureCottonVneckTshirtPresent = cartPage.isProductInCart("Pure Cotton V-Neck T-Shirt");
        boolean pureCottonVneckTshirtPresent = productNamesInCart.contains("Pure Cotton V-Neck T-Shirt");
        softAssert.assertTrue(pureCottonVneckTshirtPresent);
        int menTshirtCartQuantity = cartPage.getProductQuantity("Men Tshirt");
        softAssert.assertEquals(menTshirtCartQuantity, 1, "Validating Men Tshirt quantity.");
        int pureCottonVneckTshirtCartQuantity = cartPage.getProductQuantity("Pure Cotton V-Neck T-Shirt");
        softAssert.assertEquals(pureCottonVneckTshirtCartQuantity, 1, "Validating Pure Cotton V-neck T-Shirt quantity.");
        int menTshirtCartUnitPrice = cartPage.getProductUnitPrice("Men Tshirt");
        softAssert.assertEquals(menTshitCatalogPrice, menTshirtCartUnitPrice, "Validating Men TShirt unit price.");
        int pureCottonVneckTshirtCartUnitPrice = cartPage.getProductUnitPrice("Pure Cotton V-Neck T-Shirt");
        softAssert.assertEquals(pureCottonVneckTshirtCatalogPrice, pureCottonVneckTshirtCartUnitPrice, "Validating Pure Cotton V-Nect T-Shirt unit price");
        int menTshirtTotalCalc = menTshitCatalogPrice * 1;
        int menTshirtCartTotalPrice = cartPage.getProductTotalPrice("Men Tshirt");
        softAssert.assertEquals(menTshirtTotalCalc, menTshirtCartTotalPrice, "Validating Men TShirt cart quantity.");
        int pureCottonVneckTshirtTotalCalc = pureCottonVneckTshirtCatalogPrice * 1;
        int pureCottonVneckTshirtCartTotalPrice = cartPage.getProductTotalPrice("Pure Cotton V-Neck T-Shirt");
        softAssert.assertEquals(pureCottonVneckTshirtTotalCalc, pureCottonVneckTshirtCartTotalPrice, "Validating Pure Cotton V-Neck T-Shirt");
        String menTshirtCartCategory = cartPage.getProductCategory("Men Tshirt");
        String pureCottonVneckTshirtCartCategory = cartPage.getProductCategory("Pure Cotton V-Neck T-Shirt");
        String menTshirtCartSubCategory = cartPage.getProductSubCategory("Men Tshirt");
        String pureCottonVneckTshirtCartSubCategory = cartPage.getProductSubCategory("Pure Cotton V-Neck T-Shirt");
        softAssert.assertAll();
    }

    @Test 
    public void verifyProductQuantityInCart() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        ProductCatalog productCatalog = homePage.clickProductsLink();
        ProductDetail productDetail = productCatalog.clickViewProductLink("Stylish Dress");
        String productNameOnProductDetailPage = productDetail.getProductName();
        softAssert.assertEquals(productNameOnProductDetailPage, "Stylish Dress", 
            "Validating product name is corerect on Product Detail page."
        );
        productDetail.setProductQuantity(4);
        productDetail.addProductToCart();
        CartPage cartPage = productDetail.clickViewCartInAddedToCartPopUp();
        boolean isProductInCart = cartPage.isProductInCart("Stylish Dress");
        softAssert.assertTrue(isProductInCart, "Validating product is in cart.");
        int productQuantityInCart = cartPage.getProductQuantity("Stylish Dress");
        softAssert.assertEquals(productQuantityInCart, 4, "Validating product quantity on cart page is correct.");
        softAssert.assertAll();
    }

    @Test 
    public void removeItemsFromCart() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        ProductCatalog productCatalog = homePage.clickProductsLink();
        productCatalog.addProductToCartHover("Blue Top");
        productCatalog.clickContinueShoppingInAddedToCartPopUp();
        productCatalog.addProductToCartHover("Winter Top");
        productCatalog.clickContinueShoppingInAddedToCartPopUp();
        productCatalog.addProductToCartHover("Green Side Placket Detail T-Shirt");
        productCatalog.clickContinueShoppingInAddedToCartPopUp();
        CartPage cartPage = productCatalog.clickCartLink();
        boolean cartPageDisplayed = cartPage.isCartPageDisplayed();
        softAssert.assertTrue(cartPageDisplayed, "Validating cart page is displayed.");
        int cartItems = cartPage.getNumberOfProductsInCart();
        softAssert.assertEquals(cartItems, 3, "Validating number of products in cart.");
        boolean blueTopInCart = cartPage.isProductInCart("Blue Top");
        softAssert.assertTrue(blueTopInCart, "Validating Blue Top is in cart.");
        cartPage.removeProductFromCart("Blue Top");
        cartItems = cartPage.getNumberOfProductsInCart();
        softAssert.assertEquals(cartItems, 2, "Validating number of items in cart after removing a product.");
        blueTopInCart = cartPage.isProductInCart("Blue Top");
        softAssert.assertFalse(blueTopInCart, "Validating Blue Top is no longer in cart.");
        softAssert.assertAll();
    }

    @Test 
    public void addToCartFromRecommendedItems() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        homePage.scrollToBottom();
        String recommendedItemsHeaderText = homePage.getRecommendedItemsHeaderText();
        softAssert.assertEquals(recommendedItemsHeaderText, "RECOMMENDED ITEMS", 
            "Validating 'recommended items' header text is present."
        );
        boolean recommendedItemsCarouselPresent = homePage.isRecommendedItemsCarouselDisplayed();
        softAssert.assertTrue(recommendedItemsCarouselPresent);
        homePage.addProductToCartFromRecommendedItems("Men Tshirt");
        CartPage cartPage = homePage.clickCartLink();
        boolean isRecommendedProductInCart = cartPage.isProductInCart("Men Tshirt");
        softAssert.assertTrue(isRecommendedProductInCart);
        softAssert.assertAll();
    }

    @Test 
    public void emptyCart() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        CartPage cartPage = homePage.clickCartLink();
        int countOfItemsInCart = cartPage.getNumberOfProductsInCart();
        softAssert.assertEquals(countOfItemsInCart, 0, "Validating there are no items in the cart.");
        boolean isCartEmpty = cartPage.isCartEmpty();
        softAssert.assertTrue(isCartEmpty, "Validating the cart is empty.");
        String emptyCartMessage = cartPage.getEmptyCartMessage();
        String expectedEmptyCartMessage = "Cart is empty!";
        softAssert.assertEquals(emptyCartMessage, expectedEmptyCartMessage, 
            "Validating empty cart message appears as expected."
        );
        ProductCatalog productCatalog = cartPage.clickEmptyCartProductsLink();
        boolean isProductCatalogPageDisplayed = productCatalog.isProductCatalogPageDisplayed();
        softAssert.assertTrue(isProductCatalogPageDisplayed, "Validating Product Catalog page is displayed.");
        String productsHeaderText = productCatalog.getProductsHeaderText();
        softAssert.assertEquals(productsHeaderText, "ALL PRODUCTS", "Validating Product Catalog header is displayed correctly.");
        softAssert.assertAll();
    }

}
