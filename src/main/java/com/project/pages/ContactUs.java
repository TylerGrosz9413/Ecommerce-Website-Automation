package com.project.pages;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ContactUs extends BasePage {
    WebDriver driver;

    public ContactUs(WebDriver driver) {
        super(driver);
        this.driver = driver;
    }

    @FindBy (css = "input[name='name']")
    private WebElement nameInput;

    @FindBy (css = "input[name='email']")
    private WebElement emailInput;

    @FindBy (css = "input[name='subject']")
    private WebElement subjectInput;

    @FindBy (css = "textarea[name='message']")
    private WebElement messageInput;

    @FindBy (css = "input[name='upload_file']")
    private WebElement uploadFileInput;

    @FindBy (css = "input[name='submit']")
    private WebElement submitButton;

    private @FindBy (css = "a[href='mailto:feedback@automationexercise.com']")
    WebElement feedbackEmailLink;

    private final By successMessage = By.cssSelector(".alert-success");
    private final By homeButton = By.cssSelector(".btn-success");

    public void fillForm(String name, String email, String subject, String message) {
        waitForWebElementToAppear(nameInput);
        nameInput.sendKeys(name);
        emailInput.sendKeys(email);
        subjectInput.sendKeys(subject);
        messageInput.sendKeys(message);
    }

    public void getSuccessMessageText() {
        waitForElementToAppear(successMessage);
        String successMessageText = driver.findElement(successMessage).getText();
        System.out.println("Success message text: " + successMessageText);
    }

    public void clickSubmitButton() {
        waitForWebElementToBeClickable(submitButton);
        submitButton.click();
    }

    public void uploadFile(String filePath) throws IOException {
        waitForWebElementToBeClickable(uploadFileInput);
        uploadFileInput.click();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new IllegalStateException("Interrupted while waiting for the upload dialog to appear.", e);
        }

        String autoItExecutablePath = "src\\main\\resources\\AutoIT\\UploadFile.exe";
        String[] autoItExecutablePathArray = autoItExecutablePath.split(" ");
        Runtime.getRuntime().exec(autoItExecutablePathArray);

        submitFormAndAcceptAlert();
    }

    public void submitFormAndAcceptAlert() {
        waitForWebElementToBeClickable(submitButton);
        submitButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        Alert alert = wait.until(ExpectedConditions.alertIsPresent());

        String alertText = alert.getText();
        System.out.println("Alert text: " + alertText);

        // Verify expected message
        if (!alertText.equals("Press OK to proceed!")) {
            throw new AssertionError("Unexpected alert text: " + alertText);
        }

        alert.accept(); // Click OK
    }

    public boolean isHomeButtonDisplayed() {
        waitForElementToAppear(homeButton);
        return driver.findElement(homeButton).isDisplayed();
    }

    public void clickFeedbackEmailLink() {
        waitForWebElementToBeClickable(feedbackEmailLink);
        feedbackEmailLink.click();
    }

    public HomePage clickHomeButton() {
        waitForElementToBeClickable(homeButton);
        driver.findElement(homeButton).click();
        return new HomePage(driver);
    }

}
