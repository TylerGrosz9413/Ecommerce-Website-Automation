package ui;

import org.testng.Assert;
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
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        Assert.assertTrue(isHomePagePresent, "Validating home page is present.");
        LoginSignUp loginSignUp = homePage.clickSignUpLoginLink();
        String newUserSignUpText = loginSignUp.getnewUserSignUpText();
        Assert.assertEquals(newUserSignUpText, "New User Signup!", "Validating New User SignUp text is present.");
        EnterAccountInfo enterAccountInfo = loginSignUp.signUp("name", "name777@email.com");
        String enterAccountInfoText = enterAccountInfo.getEnterAccountInfoText();
        Assert.assertEquals(enterAccountInfoText, "ENTER ACCOUNT INFORMATION", "validating Enter Account Information text is present " +
        "Found " + enterAccountInfoText);
        enterAccountInfo.enterAccountInfo("Mr", "pass44", "11", "January", "2000", true, true);
        enterAccountInfo.enterAddressInfo("firsty", "lasty", "company name", "444 main st", "apt 322", "United States", "MO", "some city", "54452", "2223336654");
        AccountCreated accountCreated = enterAccountInfo.clickCreateAccountButton();
        String accountCreatedHeader = accountCreated.getAccountCreatedHeader();
        Assert.assertEquals(accountCreatedHeader, "ACCOUNT CREATED!", "Validating Account Created Text is present.");
        accountCreated.clickContinue();
        String loggedInAsText = homePage.getLoggedInAsText();
        String loggedInAsUserName = homePage.getloggedInAsUserName();
        Assert.assertTrue(loggedInAsText.contains(loggedInAsUserName) && loggedInAsText.contains("Logged in as"), 
        "Validating logged in as text is present and has the right username.");
        AccountDeleted accountDeleted = homePage.clickDeleteAccountLink();
        String accountDeletedHeader = accountDeleted.getAccountDeletedHeader();
        Assert.assertEquals(accountDeletedHeader, "ACCOUNT DELETED!", "Validating account deleted header is present.");
        accountDeleted.clickContinue();
        isHomePagePresent = homePage.isHomePageDisplayed();
        Assert.assertTrue(isHomePagePresent, "Validating home page is present.");
        // loginSignUp.login("testuser", "testpassword");
    }

}
