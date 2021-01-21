package core;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.fail;

public class Browser {

    private WebDriver driver;
    private final TestCaseReport testCaseReport;
    private long IMPLICIT_WAIT = 30;

    public Browser(TestCaseReport testCaseReport) {
        this.testCaseReport = testCaseReport;
        if (EnvironmentProperties.getEnvironmentProperties("browser").equals("chrome")) {
            String path = System.getProperty("user.dir");
            System.setProperty("webdriver.chrome.driver", path + "\\src\\main\\resources\\chromedriver.exe");
            ChromeOptions options = new ChromeOptions();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("profile.default_content_setting_values.notifications", 2);
            options.setExperimentalOption("prefs", prefs);
            options.addArguments("--lang=en");
            options.addArguments("use-fake-ui-for-media-stream");
            driver = new ChromeDriver(options);
            driver.manage().window().maximize();

        } else {
            this.testCaseReport.logMessage(LogStatus.FAIL, "Application run only in chrome, " +
                    "please, push on resources your driver needed and update browser factory");
            fail();
        }
    }

    public TestCaseReport getTestReport() {
        return this.testCaseReport;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void openURL(String url) {
        driver.get(url);
    }

    public void setImplicitWaitTimeoutMilliseconds(long implicitWaitTimeoutMilliseconds) {
        try {
            driver.manage().timeouts().implicitlyWait(implicitWaitTimeoutMilliseconds, TimeUnit.MILLISECONDS);
            IMPLICIT_WAIT = implicitWaitTimeoutMilliseconds;
        } catch (Exception e) {
            testCaseReport.logMessage(LogStatus.INFO, e + " setImplicitWaitTimeoutMilliseconds");
        }
    }

    public long getImplicitWaitTimeout() {
        return IMPLICIT_WAIT;
    }

    public void closeDriver() {
        try {
            getDriver().close();
        } catch (Exception e) {
            testCaseReport.logMessage(LogStatus.FAIL, e + " Exception closing driver");
        }
    }
}
