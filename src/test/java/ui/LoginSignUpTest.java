package ui;

import org.testng.annotations.Test;

import com.project.pages.LoginSignUp;

import base.BaseTest;

public class LoginSignUpTest extends BaseTest {
    
    @Test
    public void testLoginFunctionality() {
        // Test steps for login functionality
        launchApplication();
        boolean isHomePagePresent = homePage.isHomePageDisplayed();
        LoginSignUp loginSignUp = homePage.clickSignUpLoginLink();
        // loginSignUp.login("testuser", "testpassword");
    }

}
