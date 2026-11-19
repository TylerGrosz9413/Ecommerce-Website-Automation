package com.project.pages;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chromium.HasCdp;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PaymentPage extends BasePage {
    WebDriver driver;

    PaymentPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy (css = "div.breadcrumbs a")
    private WebElement homeLink;

    @FindBy (css = "h2.heading")
    private WebElement paymentHeader;

    @FindBy (css = "input[name='name_on_card']")
    private WebElement nameOnCardInput;

    @FindBy (css = "input[name='card_number']")
    private WebElement cardNumberInput;

    @FindBy (css = "input[name='cvc']")
    private WebElement cvcInput;

    @FindBy (css = "input[name='expiry_month']")
    private WebElement expirationMonthInput;

    @FindBy (css = "input[name='expiry_year']")
    private WebElement expirationYearinput;

    @FindBy (css = "button.submit-button")
    private WebElement payAndConfirmOrderButton;

    private static final String SUCCESS_MESSAGE_STORAGE_KEY = "paymentSuccessMessage";
    private static final String SUCCESS_MESSAGE_SELECTOR = "#success_message .alert-success";
    private static final String SUCCESS_MESSAGE_OBSERVER_SCRIPT = """
        (() => {
            const storageKey = "%s";
            const selector = "%s";
            const capture = () => {
                const message = document.querySelector(selector);
                const text = message ? message.textContent.trim() : "";
                if (text) {
                    window.sessionStorage.setItem(storageKey, text);
                    observer.disconnect();
                }
            };
            const observer = new MutationObserver(capture);
            observer.observe(document, {
                childList: true,
                subtree: true,
                characterData: true
            });
            window.__paymentSuccessObserver = observer;
            capture();
            document.addEventListener("DOMContentLoaded", capture, { once: true });
        })();
        """.formatted(SUCCESS_MESSAGE_STORAGE_KEY, SUCCESS_MESSAGE_SELECTOR);

    private String capturedSuccessMessage;

    public String getPaymentHeader() {
        waitForWebElementToAppear(paymentHeader);
        return paymentHeader.getText();
    }

    public void enterPaymentDetails(String nameOnCard, String cardNumber, String cvc, String expirationMonth, String expirationYear) {
        waitForWebElementToAppear(nameOnCardInput);
        nameOnCardInput.sendKeys(nameOnCard);
        cardNumberInput.sendKeys(cardNumber);
        cvcInput.sendKeys(cvc);
        expirationMonthInput.sendKeys(expirationMonth);
        expirationYearinput.sendKeys(expirationYear);
    }

    public OrderConfirmationPage clickPayAndConfirmOrderButton() {
        waitForWebElementToAppear(payAndConfirmOrderButton);

        if (!(driver instanceof HasCdp cdp)) {
            throw new IllegalStateException("Transient payment success capture requires a Chromium WebDriver.");
        }

        JavascriptExecutor javascript = (JavascriptExecutor) driver;
        javascript.executeScript(
            "window.sessionStorage.removeItem(arguments[0]);",
            SUCCESS_MESSAGE_STORAGE_KEY
        );
        javascript.executeScript(SUCCESS_MESSAGE_OBSERVER_SCRIPT);
        String observerId = registerSuccessMessageObserver(cdp);

        try {
            payAndConfirmOrderButton.click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
            wait.pollingEvery(Duration.ofMillis(100));
            capturedSuccessMessage = wait.until(webDriver ->
                (String) ((JavascriptExecutor) webDriver).executeScript(
                    "return window.sessionStorage.getItem(arguments[0]);",
                    SUCCESS_MESSAGE_STORAGE_KEY
                )
            );
        } finally {
            cdp.executeCdpCommand(
                "Page.removeScriptToEvaluateOnNewDocument",
                Map.of("identifier", observerId)
            );
        }

        javascript.executeScript(
            "window.sessionStorage.removeItem(arguments[0]);",
            SUCCESS_MESSAGE_STORAGE_KEY
        );
        return new OrderConfirmationPage(driver);
    }

    private String registerSuccessMessageObserver(HasCdp cdp) {
        Object result = cdp.executeCdpCommand(
            "Page.addScriptToEvaluateOnNewDocument",
            Map.of("source", SUCCESS_MESSAGE_OBSERVER_SCRIPT)
        );
        if (result instanceof Map<?, ?> response
            && response.get("identifier") instanceof String identifier) {
            return identifier;
        }
        throw new IllegalStateException("Chromium did not return an observer script identifier.");
    }

    public String getNameOnCard() {
        waitForWebElementToAppear(nameOnCardInput);
        return nameOnCardInput.getAttribute("value");
    }

    public String getCardNumber() {
        waitForWebElementToAppear(cardNumberInput);
        return cardNumberInput.getAttribute("value");
    }

    public String getCVC() {
        waitForWebElementToAppear(cvcInput);
        return cvcInput.getAttribute("value");
    }

    public String getExpirationMonth() {
        waitForWebElementToAppear(expirationMonthInput);
        return expirationMonthInput.getAttribute("value");
    }

    public String getExpirationYear() {
        waitForWebElementToAppear(expirationYearinput);
        return expirationYearinput.getAttribute("value");
    }

    public HomePage clickHomeLink() {
        waitForWebElementToAppear(homeLink);
        homeLink.click();
        return new HomePage(driver);
    }

    public String getSuccessMessage() {
        if (capturedSuccessMessage == null) {
            throw new IllegalStateException("Success message has not been captured yet.");
        }
        return capturedSuccessMessage;
    }
}
