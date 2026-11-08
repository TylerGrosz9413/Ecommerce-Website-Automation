package ui;

import org.testng.annotations.Test;

import com.project.pages.ProductCatalog;

import base.BaseTest;

public class CartTest extends BaseTest {
    
    @Test
    public void testCartFunctionality() {
        // Test steps for cart functionality
        
        launchApplication();
        ProductCatalog productCatalog = homePage.clickProductsLink();
        
    }

}
