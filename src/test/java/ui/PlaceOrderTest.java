package ui;

import java.util.List;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.project.pages.AccountCreated;
import com.project.pages.AccountDeleted;
import com.project.pages.CartPage;
import com.project.pages.CheckoutPage;
import com.project.pages.EnterAccountInfo;
import com.project.pages.LoginSignUp;
import com.project.pages.OrderConfirmationPage;
import com.project.pages.PaymentPage;

import base.BaseTest;

public class PlaceOrderTest extends BaseTest {

    @Test 
    public void placeOrderRegisterWhileCheckout() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is displayed.");
        homePage.addProductToCartHover("Fancy Green Top");
        homePage.clickContinueShoppingInAddedToCartPopUp();
        homePage.addProductToCartHover("Frozen Tops For Kids");
        homePage.clickContinueShoppingInAddedToCartPopUp();
        homePage.addProductToCartHover("Premium Polo T-Shirts");
        homePage.clickContinueShoppingInAddedToCartPopUp();
        CartPage cartPage = homePage.clickCartLink();
        boolean isCartPageDisplayed = cartPage.isCartPageDisplayed();
        softAssert.assertTrue(isCartPageDisplayed, "Validating cart page is displayed.");
        List<String> expectedProductNames = List.of(
            "Fancy Green Top",
            "Frozen Tops For Kids",
            "Premium Polo T-Shirts"
        );
        List<Integer> expectedProductQuantities = List.of(1, 1, 1);
        List<Integer> expectedProductTotalPrices = cartPage.getProductTotalPrices();
        int expectedOrderTotal = expectedProductTotalPrices.stream().mapToInt(Integer::intValue).sum();
        softAssert.assertEquals(cartPage.getProductNames(), expectedProductNames,
            "Validating all expected products are in the cart.");
        softAssert.assertEquals(cartPage.getProductQuantities(), expectedProductQuantities,
            "Validating cart product quantities.");
        cartPage.clickCheckoutLink();
        LoginSignUp loginSignUp = cartPage.clickRegisterLoginLinkPopUp();
        String userName = "name444";
        EnterAccountInfo enterAccountInfo = loginSignUp.signUp(userName, "name688@email.com");
        enterAccountInfo.enterAccountInfo("Mr", "pass44", "11", "January", "2000", true, true);
        enterAccountInfo.enterAddressInfo("James", "Smith", "company", "433 main st", "apt 311", "United States", "MO", "some city", "54452", "2223336654");
        AccountCreated accountCreated = enterAccountInfo.clickCreateAccountButton();
        String accountCreatedHeaderText = accountCreated.getAccountCreatedHeaderText();
        softAssert.assertEquals(accountCreatedHeaderText, "ACCOUNT CREATED!", "Validating Account Created Text is present.");
        accountCreated.clickContinue();
        String loggedInAsText = homePage.getLoggedInAsText();
        String loggedInAsUserName = homePage.getloggedInAsUserName();
        softAssert.assertEquals(userName, loggedInAsUserName, "Validating username is correct in logged in as text.");
        softAssert.assertTrue(loggedInAsText.contains(userName) && loggedInAsText.contains("Logged in as"), 
        "Validating logged in as text is present and has the right username.");
        homePage.clickCartLink();
        cartPage.clickCheckoutLink();
        CheckoutPage checkoutPage = cartPage.waitForCheckoutPage();
        softAssert.assertEquals(checkoutPage.getCustomerNameDelivery(), "Mr. James Smith",
            "Validating delivery name.");
        String deliveryAddress = checkoutPage.getCustomerAddressDelivery();
        softAssert.assertTrue(deliveryAddress.contains("company") && deliveryAddress.contains("433 main st")
            && deliveryAddress.contains("apt 311"),
            "Validating delivery company and street address.");
        String deliveryCityStateZip = checkoutPage.getCustomerCityStateZipDelivery();
        softAssert.assertTrue(deliveryCityStateZip.contains("some city")
            && deliveryCityStateZip.contains("MO") && deliveryCityStateZip.contains("54452"),
            "Validating delivery city, state, and ZIP code.");
        softAssert.assertEquals(checkoutPage.getCustomerCountryNameDelivery(), "United States",
            "Validating delivery country.");
        softAssert.assertEquals(checkoutPage.getCustomerPhoneNumberDelivery(), "2223336654",
            "Validating delivery phone number.");
        softAssert.assertEquals(checkoutPage.getCustomerNameBilling(), "Mr. James Smith",
            "Validating billing name.");
        String billingAddress = checkoutPage.getCustomerAddressBilling();
        softAssert.assertTrue(billingAddress.contains("company") && billingAddress.contains("433 main st")
            && billingAddress.contains("apt 311"),
            "Validating billing company and street address.");
        String billingCityStateZip = checkoutPage.getCustomerCityStateZipBilling();
        softAssert.assertTrue(billingCityStateZip.contains("some city")
            && billingCityStateZip.contains("MO") && billingCityStateZip.contains("54452"),
            "Validating billing city, state, and ZIP code.");
        softAssert.assertEquals(checkoutPage.getCustomerCountryNameBilling(), "United States",
            "Validating billing country.");
        softAssert.assertEquals(checkoutPage.getCustomerPhoneNumberBilling(), "2223336654",
            "Validating billing phone number.");
        softAssert.assertEquals(checkoutPage.getProductNames(), expectedProductNames,
            "Validating all expected products are in checkout.");
        softAssert.assertEquals(checkoutPage.getProductQuantities(), expectedProductQuantities,
            "Validating checkout product quantities.");
        softAssert.assertEquals(checkoutPage.getProductTotalPrices(), expectedProductTotalPrices,
            "Validating product totals are preserved from the cart.");
        softAssert.assertEquals(checkoutPage.getCartTotalPrice(), expectedOrderTotal,
            "Validating checkout order total matches the sum of product totals.");
        checkoutPage.enterOrderComment("These products are great.");
        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
        paymentPage.enterPaymentDetails("James Smith", "1234567896321475", "455", "07", "2028");
        OrderConfirmationPage orderConfirmationPage = paymentPage.clickPayAndConfirmOrderButton();
        String successMessage = paymentPage.getSuccessMessage();
        softAssert.assertEquals(successMessage, "Your order has been placed successfully!", 
            "Validating order success message is populating correctly.");
        String orderPlacedText = orderConfirmationPage.getOrderPlacedText();
        softAssert.assertEquals(orderPlacedText, "ORDER PLACED!", 
            "Validating 'ORDER PLACED!' text is present.");
        String orderConfirmationText = orderConfirmationPage.getConfirmationMessageText();
        softAssert.assertEquals(orderConfirmationText, "Congratulations! Your order has been confirmed!", 
            "Validating order confirmation message is populating correctly.");
        AccountDeleted accountDeleted = orderConfirmationPage.clickDeleteAccountLink();
        String accountDeletedHeaderText = accountDeleted.getAccountDeletedHeaderText();
        softAssert.assertEquals(accountDeletedHeaderText, "ACCOUNT DELETED!", 
            "Validating 'ACCOUNT DELETED!' text is populating.");
        accountDeleted.clickContinue();
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePageDisplayed, "Validating home page is displayed.");
        softAssert.assertAll();
    }

    @Test 
    public void placeOrderRegisterBeforeCheckout() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        LoginSignUp loginSignUp = homePage.clickSignUpLoginLink();
        String newUserSignUpText = loginSignUp.getnewUserSignUpText();
        softAssert.assertEquals(newUserSignUpText, "New User Signup!", "Validating New User SignUp text is present.");
        String userName = "name7";
        EnterAccountInfo enterAccountInfo = loginSignUp.signUp(userName, "name6887@email.com");
        String enterAccountInfoText = enterAccountInfo.getEnterAccountInfoText();
        softAssert.assertEquals(enterAccountInfoText, "ENTER ACCOUNT INFORMATION", "validating Enter Account Information text is present " +
        "Found " + enterAccountInfoText);
        enterAccountInfo.enterAccountInfo("Mr", "pass99", "11", "January", "1997", true, true);
        enterAccountInfo.enterAddressInfo("John", "Black", "company name2", "477 main st", "apt 211", "United States", "MN", "city", "51152", "2223116654");
        AccountCreated accountCreated = enterAccountInfo.clickCreateAccountButton();
        String accountCreatedHeader = accountCreated.getAccountCreatedHeaderText();
        softAssert.assertEquals(accountCreatedHeader, "ACCOUNT CREATED!", "Validating Account Created Text is present.");
        accountCreated.clickContinue();
        String loggedInAsText = homePage.getLoggedInAsText();
        String loggedInAsUserName = homePage.getloggedInAsUserName();
        softAssert.assertEquals(userName, loggedInAsUserName, "Validating username is correct in logged in as text.");
        softAssert.assertTrue(loggedInAsText.contains(userName) && loggedInAsText.contains("Logged in as"), 
        "Validating logged in as text is present and has the right username.");
        homePage.addProductToCartHover("Sleeves Printed Top - White");
        homePage.clickContinueShoppingInAddedToCartPopUp();
        homePage.addProductToCartHover("Half Sleeves Top Schiffli Detailing - Pink");
        homePage.clickContinueShoppingInAddedToCartPopUp();
        homePage.addProductToCartHover("Regular Fit Straight Jeans");
        homePage.clickContinueShoppingInAddedToCartPopUp();
        CartPage cartPage = homePage.clickCartLink();
        boolean isCartPageDisplayed = cartPage.isCartPageDisplayed();
        softAssert.assertTrue(isCartPageDisplayed, "Validating cart page is displayed.");
        List<String> expectedProductNames = List.of(
            "Sleeves Printed Top - White",
            "Half Sleeves Top Schiffli Detailing - Pink",
            "Regular Fit Straight Jeans"
        );
        List<Integer> expectedProductQuantities = List.of(1, 1, 1);
        List<Integer> expectedProductTotalPrices = cartPage.getProductTotalPrices();
        int expectedOrderTotal = expectedProductTotalPrices.stream().mapToInt(Integer::intValue).sum();
        softAssert.assertEquals(cartPage.getProductNames(), expectedProductNames,
            "Validating all expected products are in the cart.");
        softAssert.assertEquals(cartPage.getProductQuantities(), expectedProductQuantities,
            "Validating cart product quantities.");
        cartPage.clickCheckoutLink();
        CheckoutPage checkoutPage = cartPage.waitForCheckoutPage();
        softAssert.assertEquals(checkoutPage.getCustomerNameDelivery(), "Mr. John Black",
            "Validating delivery name.");
        String deliveryAddress = checkoutPage.getCustomerAddressDelivery();
        softAssert.assertTrue(deliveryAddress.contains("company name2") && deliveryAddress.contains("477 main st")
            && deliveryAddress.contains("apt 211"),
            "Validating delivery company and street address.");
        String deliveryCityStateZip = checkoutPage.getCustomerCityStateZipDelivery();
        softAssert.assertTrue(deliveryCityStateZip.contains("city")
            && deliveryCityStateZip.contains("MN") && deliveryCityStateZip.contains("51152"),
            "Validating delivery city, state, and ZIP code.");
        softAssert.assertEquals(checkoutPage.getCustomerCountryNameDelivery(), "United States",
            "Validating delivery country.");
        softAssert.assertEquals(checkoutPage.getCustomerPhoneNumberDelivery(), "2223116654",
            "Validating delivery phone number.");
        softAssert.assertEquals(checkoutPage.getCustomerNameBilling(), "Mr. John Black",
            "Validating billing name.");
        String billingAddress = checkoutPage.getCustomerAddressBilling();
        softAssert.assertTrue(billingAddress.contains("company name2") && billingAddress.contains("477 main st")
            && billingAddress.contains("apt 211"),
            "Validating billing company and street address.");
        String billingCityStateZip = checkoutPage.getCustomerCityStateZipBilling();
        softAssert.assertTrue(billingCityStateZip.contains("city")
            && billingCityStateZip.contains("MN") && billingCityStateZip.contains("51152"),
            "Validating billing city, state, and ZIP code.");
        softAssert.assertEquals(checkoutPage.getCustomerCountryNameBilling(), "United States",
            "Validating billing country.");
        softAssert.assertEquals(checkoutPage.getCustomerPhoneNumberBilling(), "2223116654",
            "Validating billing phone number.");
        softAssert.assertEquals(checkoutPage.getProductNames(), expectedProductNames,
            "Validating all expected products are in checkout.");
        softAssert.assertEquals(checkoutPage.getProductQuantities(), expectedProductQuantities,
            "Validating checkout product quantities.");
        softAssert.assertEquals(checkoutPage.getProductTotalPrices(), expectedProductTotalPrices,
            "Validating product totals are preserved from the cart.");
        softAssert.assertEquals(checkoutPage.getCartTotalPrice(), expectedOrderTotal,
            "Validating checkout order total matches the sum of product totals.");
        checkoutPage.enterOrderComment("These products are awesome.");
        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
        paymentPage.enterPaymentDetails("John Black", "1277567896321475", "488", "11", "2028");
        OrderConfirmationPage orderConfirmationPage = paymentPage.clickPayAndConfirmOrderButton();
        String successMessage = paymentPage.getSuccessMessage();
        softAssert.assertEquals(successMessage, "Your order has been placed successfully!", 
            "Validating order success message is populating correctly.");
        String orderPlacedText = orderConfirmationPage.getOrderPlacedText();
        softAssert.assertEquals(orderPlacedText, "ORDER PLACED!", 
            "Validating 'ORDER PLACED!' text is present.");
        String orderConfirmationText = orderConfirmationPage.getConfirmationMessageText();
        softAssert.assertEquals(orderConfirmationText, "Congratulations! Your order has been confirmed!", 
            "Validating order confirmation message is populating correctly.");
        AccountDeleted accountDeleted = orderConfirmationPage.clickDeleteAccountLink();
        String accountDeletedHeaderText = accountDeleted.getAccountDeletedHeaderText();
        softAssert.assertEquals(accountDeletedHeaderText, "ACCOUNT DELETED!", 
            "Validating 'ACCOUNT DELETED!' text is populating.");
        accountDeleted.clickContinue();
        boolean isHomePageDisplayed = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePageDisplayed, "Validating home page is displayed.");
        softAssert.assertAll();
    }

    @Test 
    public void placeOrderLoginBeforeCheckout() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        LoginSignUp loginSignUp = homePage.clickSignUpLoginLink();
        String loginToAccountText = loginSignUp.getloginToYourAccountText();
        softAssert.assertEquals(loginToAccountText, "Login to your account", "Validating login to account text.");
        String userName = "name2";
        loginSignUp.login("name2777@email.com", "pass44");
        String loggedInAsText = homePage.getLoggedInAsText();
        String loggedInAsUserName = homePage.getloggedInAsUserName();
        softAssert.assertEquals(userName, loggedInAsUserName, "Validating username is correct in logged in as text.");
        softAssert.assertTrue(loggedInAsText.contains(userName) && loggedInAsText.contains("Logged in as"), 
        "Validating logged in as text is present and has the right username.");
        homePage.addProductToCartHover("GRAPHIC DESIGN MEN T SHIRT - BLUE");
        homePage.clickContinueShoppingInAddedToCartPopUp();
        homePage.addProductToCartHover("Beautiful Peacock Blue Cotton Linen Saree");
        homePage.clickContinueShoppingInAddedToCartPopUp();
        homePage.addProductToCartHover("Soft Stretch Jeans");
        homePage.clickContinueShoppingInAddedToCartPopUp();
        CartPage cartPage = homePage.clickCartLink();
        boolean isCartPageDisplayed = cartPage.isCartPageDisplayed();
        softAssert.assertTrue(isCartPageDisplayed, "Validating cart page is displayed.");
        List<String> expectedProductNames = List.of(
            "GRAPHIC DESIGN MEN T SHIRT - BLUE",
            "Beautiful Peacock Blue Cotton Linen Saree",
            "Soft Stretch Jeans"
        );
        List<Integer> expectedProductQuantities = List.of(1, 1, 1);
        List<Integer> expectedProductTotalPrices = cartPage.getProductTotalPrices();
        int expectedOrderTotal = expectedProductTotalPrices.stream().mapToInt(Integer::intValue).sum();
        softAssert.assertEquals(cartPage.getProductNames(), expectedProductNames,
            "Validating all expected products are in the cart.");
        softAssert.assertEquals(cartPage.getProductQuantities(), expectedProductQuantities,
            "Validating cart product quantities.");
        cartPage.clickCheckoutLink();
        CheckoutPage checkoutPage = cartPage.waitForCheckoutPage();
        softAssert.assertEquals(checkoutPage.getCustomerNameDelivery(), "Mr. firsty lasty",
            "Validating delivery name.");
        String deliveryAddress = checkoutPage.getCustomerAddressDelivery();
        softAssert.assertTrue(deliveryAddress.contains("444 main st")
            && deliveryAddress.contains("apt 322"),
            "Validating delivery company and street address.");
        String deliveryCityStateZip = checkoutPage.getCustomerCityStateZipDelivery();
        softAssert.assertTrue(deliveryCityStateZip.contains("some city")
            && deliveryCityStateZip.contains("MO") && deliveryCityStateZip.contains("54452"),
            "Validating delivery city, state, and ZIP code.");
        softAssert.assertEquals(checkoutPage.getCustomerCountryNameDelivery(), "United States",
            "Validating delivery country.");
        softAssert.assertEquals(checkoutPage.getCustomerPhoneNumberDelivery(), "2223336654",
            "Validating delivery phone number.");
        softAssert.assertEquals(checkoutPage.getCustomerNameBilling(), "Mr. firsty lasty",
            "Validating billing name.");
        String billingAddress = checkoutPage.getCustomerAddressBilling();
        softAssert.assertTrue(billingAddress.contains("444 main st")
            && billingAddress.contains("apt 322"),
            "Validating billing company and street address.");
        String billingCityStateZip = checkoutPage.getCustomerCityStateZipBilling();
        softAssert.assertTrue(billingCityStateZip.contains("some city")
            && billingCityStateZip.contains("MO") && billingCityStateZip.contains("54452"),
            "Validating billing city, state, and ZIP code.");
        softAssert.assertEquals(checkoutPage.getCustomerCountryNameBilling(), "United States",
            "Validating billing country.");
        softAssert.assertEquals(checkoutPage.getCustomerPhoneNumberBilling(), "2223336654",
            "Validating billing phone number.");
        softAssert.assertEquals(checkoutPage.getProductNames(), expectedProductNames,
            "Validating all expected products are in checkout.");
        softAssert.assertEquals(checkoutPage.getProductQuantities(), expectedProductQuantities,
            "Validating checkout product quantities.");
        softAssert.assertEquals(checkoutPage.getProductTotalPrices(), expectedProductTotalPrices,
            "Validating product totals are preserved from the cart.");
        softAssert.assertEquals(checkoutPage.getCartTotalPrice(), expectedOrderTotal,
            "Validating checkout order total matches the sum of product totals.");
        checkoutPage.enterOrderComment("These products are awesome.");
        PaymentPage paymentPage = checkoutPage.clickPlaceOrder();
        paymentPage.enterPaymentDetails("John Black", "1277567896321475", "488", "11", "2028");
        OrderConfirmationPage orderConfirmationPage = paymentPage.clickPayAndConfirmOrderButton();
        String successMessage = paymentPage.getSuccessMessage();
        softAssert.assertEquals(successMessage, "Your order has been placed successfully!", 
            "Validating order success message is populating correctly.");
        String orderPlacedText = orderConfirmationPage.getOrderPlacedText();
        softAssert.assertEquals(orderPlacedText, "ORDER PLACED!", 
            "Validating 'ORDER PLACED!' text is present.");
        String orderConfirmationText = orderConfirmationPage.getConfirmationMessageText();
        softAssert.assertEquals(orderConfirmationText, "Congratulations! Your order has been confirmed!", 
            "Validating order confirmation message is populating correctly.");
            orderConfirmationPage.clickLogoutLink();
        /* AccountDeleted accountDeleted = orderConfirmationPage.clickDeleteAccountLink();
        String accountDeletedHeaderText = accountDeleted.getAccountDeletedHeaderText();
        softAssert.assertEquals(accountDeletedHeaderText, "ACCOUNT DELETED!", 
            "Validating 'ACCOUNT DELETED!' text is populating.");
        accountDeleted.clickContinue(); */
        boolean isLoginSignUpPageDisplayed = loginSignUp.isLoginSignUpPageDisplayed();
        softAssert.assertTrue(isLoginSignUpPageDisplayed, "Validating login/sign up page is displayed.");
        softAssert.assertAll();
    }

}
