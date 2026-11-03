package base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.BeforeMethod;

import com.project.pages.LoginSignUp;

public class BaseTest {
    public WebDriver driver;
    public LoginSignUp loginSignUp;

    public WebDriver initializeDriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
            System.getProperty("user.dir") + "\\src\\main\\java\\com\\project\\config\\config.properties");
        prop.load(fis);

        String browser = System.getProperty("browser") != null ? System.getProperty("browser") : prop.getProperty("browser");
        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            if (browser.contains("headless")) {
                options.addArguments("headless");
            }
            options.addArguments("--disable-extensions");
            options.addArguments("--disable-plugins");
            options.addArguments("--disable-features=Translate,OptimizationHints");
            options.addArguments("--user-data-dir=C:/temp/selenium-profile");
            options.addArguments("--profile-directory=Default");
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            // Initialize Firefox driver
            FirefoxOptions options = new FirefoxOptions();
            if (browser.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            // Initialize Edge driver
            EdgeOptions options = new EdgeOptions();
            if (browser.contains("headless")) {
                options.addArguments("headless");
            }
            driver = new EdgeDriver(options);
        } else {
            throw new IllegalArgumentException("Browser not supported: " + browser);
        }
        driver.manage().timeouts().implicitlyWait(java.time.Duration.ofSeconds(10));
        driver.manage().window().maximize();
        return driver;
    }

    @BeforeMethod (alwaysRun = true)
    public LoginSignUp setUp() throws IOException {
        driver = initializeDriver();
        loginSignUp = new LoginSignUp(driver);
        launchApplication();
        loginSignUp.goToLoginSignUpPage();
        //loginSignUp.waitForWebElementToAppear(loginSignUp.);
        return loginSignUp;
    }

    public void launchApplication() {
        driver.get("https://automationexercise.com/");
    }

    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
