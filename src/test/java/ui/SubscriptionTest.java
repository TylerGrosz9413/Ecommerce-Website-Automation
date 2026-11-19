package ui;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.project.pages.CartPage;

import base.BaseTest;

public class SubscriptionTest extends BaseTest {
    
    @Test 
    public void verifySubscriptionInHomePage() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        homePage.scrollToBottom();
        String subscriptionHeader = homePage.getSubscriptionHeader();
        softAssert.assertEquals(subscriptionHeader, "SUBSCRIPTION", "Validating subscription header text.");
        String email = "myname@email.com";
        homePage.subscribeWithEmail(email);
        String subscriptionSuccessMessage = homePage.getSubscriptionSuccessMessage();
        softAssert.assertEquals(subscriptionSuccessMessage, "You have been successfully subscribed!", 
            "Validating subscription success message."
        );
        softAssert.assertAll();
    }
    
    @Test 
    public void verifySubscriptionInCartPage() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        CartPage cartPage = homePage.clickCartLink();
        cartPage.scrollToBottom();
        String subscriptionHeader = cartPage.getSubscriptionHeader();
        softAssert.assertEquals(subscriptionHeader, "SUBSCRIPTION", "Validating subscription header text.");
        String email = "myname@email.com";
        cartPage.subscribeWithEmail(email);
        String subscriptionSuccessMessage = cartPage.getSubscriptionSuccessMessage();
        softAssert.assertEquals(subscriptionSuccessMessage, "You have been successfully subscribed!", 
            "Validating subscription success message."
        );
        softAssert.assertAll();
    }

    @Test 
    public void subscribeWithNoAtSymbol() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        homePage.scrollToBottom();
        String subscriptionHeader = homePage.getSubscriptionHeader();
        softAssert.assertEquals(subscriptionHeader, "SUBSCRIPTION", "Validating subscription header text.");
        String email = "email";
        homePage.subscribeWithEmail(email);
        String expectedNoAtSymbolErrorText = "Please include an '@' in the email address. '"
         + email + "' is missing an '@'.";
        String subscriptionEmailWithNoAtSymbolText = homePage.getSubscriptionEmailWithNoAtSymbolText();
        softAssert.assertEquals(subscriptionEmailWithNoAtSymbolText, expectedNoAtSymbolErrorText, 
            "Validating correct error message for no @ symbol in subscription email.");
        
        softAssert.assertAll();
    }

}
