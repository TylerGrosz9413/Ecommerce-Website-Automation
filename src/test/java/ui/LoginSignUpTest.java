package ui;

import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import org.testng.annotations.Test;

import com.project.pages.AccountCreated;
import com.project.pages.AccountDeleted;
import com.project.pages.EnterAccountInfo;
import com.project.pages.LoginSignUp;

import base.BaseTest;

public class LoginSignUpTest extends BaseTest {
    
    @Test
    public void createAndDeleteUserAccount() {
        // Test steps for login functionality
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        LoginSignUp loginSignUp = homePage.clickSignUpLoginLink();
        String newUserSignUpText = loginSignUp.getnewUserSignUpText();
        softAssert.assertEquals(newUserSignUpText, "New User Signup!", "Validating New User SignUp text is present.");
        String userName = "name2";
        EnterAccountInfo enterAccountInfo = loginSignUp.signUp(userName, "name2777@email.com");
        String enterAccountInfoText = enterAccountInfo.getEnterAccountInfoText();
        softAssert.assertEquals(enterAccountInfoText, "ENTER ACCOUNT INFORMATION", "validating Enter Account Information text is present " +
        "Found " + enterAccountInfoText);
        enterAccountInfo.enterAccountInfo("Mr", "pass44", "11", "January", "2000", true, true);
        enterAccountInfo.enterAddressInfo("firsty", "lasty", "company name", "444 main st", "apt 322", "United States", "MO", "some city", "54452", "2223336654");
        AccountCreated accountCreated = enterAccountInfo.clickCreateAccountButton();
        String accountCreatedHeader = accountCreated.getAccountCreatedHeaderText();
        softAssert.assertEquals(accountCreatedHeader, "ACCOUNT CREATED!", "Validating Account Created Text is present.");
        accountCreated.clickContinue();
        String loggedInAsText = homePage.getLoggedInAsText();
        String loggedInAsUserName = homePage.getloggedInAsUserName();
        softAssert.assertEquals(userName, loggedInAsUserName, "Validating username is correct in logged in as text.");
        softAssert.assertTrue(loggedInAsText.contains(userName) && loggedInAsText.contains("Logged in as"), 
        "Validating logged in as text is present and has the right username.");
        /* AccountDeleted accountDeleted = homePage.clickDeleteAccountLink();
        String accountDeletedHeader = accountDeleted.getAccountDeletedHeaderText();
        softAssert.assertEquals(accountDeletedHeader, "ACCOUNT DELETED!", "Validating account deleted header is present.");
        accountDeleted.clickContinue();
        isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present."); */
        softAssert.assertAll();
    }

    @Test 
    public void loginWithInocrrectCredentials() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        LoginSignUp loginSignUp = homePage.clickSignUpLoginLink();
        String loginToAccountText = loginSignUp.getloginToYourAccountText();
        softAssert.assertEquals(loginToAccountText, "Login to your account", "Validating 'Login to your account' text is visible.");
        loginSignUp.login("incorrect@email.com", "credentials");
        String incorrectEmailOrPasswordError = loginSignUp.getIncorrectEmailOrPasswordErrorText();
        softAssert.assertEquals(incorrectEmailOrPasswordError, "Your email or password is incorrect!", "Validating incorrect email or password error populating.");
        softAssert.assertAll();

    }

    @Test 
    public void loginSignUpWithNoAtSymbolInEmail() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        LoginSignUp loginSignUp = homePage.clickSignUpLoginLink();
        String loginToAccountText = loginSignUp.getloginToYourAccountText();
        softAssert.assertEquals(loginToAccountText, "Login to your account", "Validating login to account text.");
        loginSignUp.login("email", "password");
        String loginEmailWithNoAtSymbolErrorText = loginSignUp.getEmailwithNoAtSymbolText();
        boolean correctLoginErrorText = loginEmailWithNoAtSymbolErrorText.contains("Please include an '@' in the email address.");
        softAssert.assertTrue(correctLoginErrorText, "Validating the '@' symbol error message is correctly populating for login.");
        loginSignUp.signUp("name", "email");
        String signUpEmailWithNoAtSymbolErrorText = loginSignUp.getEmailwithNoAtSymbolText();
        boolean correctSignUpErrorText = signUpEmailWithNoAtSymbolErrorText.contains("Please include an '@' in the email address.");
        softAssert.assertTrue(correctSignUpErrorText, "Validating the '@' symbol error message is correctly populating for sign up.");
        softAssert.assertAll();
    }

    @Test 
    public void logout() {
        SoftAssert softAssert = new SoftAssert();
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        softAssert.assertTrue(isHomePagePresent, "Validating home page is present.");
        LoginSignUp loginSignUp = homePage.clickSignUpLoginLink();
        String loginToAccountText = loginSignUp.getloginToYourAccountText();
        softAssert.assertEquals(loginToAccountText, "Login to your account", "Validating login to account text.");
        String userName = "name2";
        loginSignUp.login("name3777@email.com", "pass44");
        String loggedInAsText = homePage.getLoggedInAsText();
        String loggedInAsUserName = homePage.getloggedInAsUserName();
        softAssert.assertEquals(userName, loggedInAsUserName, "Validating username is correct in logged in as text.");
        softAssert.assertTrue(loggedInAsText.contains(userName) && loggedInAsText.contains("Logged in as"), 
        "Validating logged in as text is present and has the right username.");
        homePage.clickLogoutLink();
        String loginToAccountText2 = loginSignUp.getloginToYourAccountText();
        softAssert.assertEquals(loginToAccountText2, "Login to your account", "Validating login to account text.");
        String newUserSignUpText = loginSignUp.getnewUserSignUpText();
        softAssert.assertEquals(newUserSignUpText, "New User Signup!", "Validating New User SignUp text is present.");
        softAssert.assertAll();
    }

}
