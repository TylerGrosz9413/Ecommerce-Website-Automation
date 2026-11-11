package base;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.project.pages.HomePage;
import com.project.pages.LoginSignUp;

public class BaseTest {
    public WebDriver driver;
    public HomePage homePage;

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
            options.setExperimentalOption("prefs", Map.of(
                "autofill.profile_enabled", false, 
                "autofill.address_enabled", false
            ));
            //options.addArguments("--disable-extensions");
            //String crxPath = "C:\\Users\\tyler\\Downloads\\Unconfirmed 271068.crdownload";
            //options.addExtensions(new File(crxPath));
            options.addArguments("--user-data-dir=C:\\selenium\\chrome-adblock-profile");
            //options.addArguments("--disable-features=Translate,OptimizationHints");
            //options.addArguments("--user-data-dir=C:/temp/selenium-profile");
            //options.addArguments("--profile-directory=Default");
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
    public HomePage setUp() throws IOException {
        driver = initializeDriver();
        homePage = new HomePage(driver);
        return homePage;
    }

    public void launchApplication() {
        driver.get("https://automationexercise.com/");
    }

    @AfterMethod (alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            //driver.quit();
        }
    }
}
