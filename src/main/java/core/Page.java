package core;

import com.relevantcodes.extentreports.LogStatus;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public abstract class Page {

    private static final long POLLING_TIME = 500;
    private static final int AFTER_CLICK_WAIT = 500;
    private static final String MIN_TIME_MESSAGE =
            "Minimum recommended timeout in milliseconds is: ";
    public static final String FINAL_PATH = System.getProperty("user.dir") + "/reports/screenshots/";
    protected Browser browser;
    protected TestCaseReport testCaseReport;

    public Page(Browser browser) {
        this.browser = browser;
        this.testCaseReport = browser.getTestReport();
        PageFactory.initElements(browser.getDriver(), this);
    }

    protected void openURL(String url) {
        browser.openURL(url);
    }

    protected void closeURL() {
        browser.closeDriver();
    }

    public WebDriver getDriver() {
        return browser.getDriver();
    }

    protected void sleep(int timeoutInMilliSeconds) {
        try {
            Thread.sleep(timeoutInMilliSeconds);
        } catch (InterruptedException e) {
            testCaseReport.logMessage(LogStatus.ERROR, "InterruptedException", e.getMessage());
            Thread.currentThread().interrupt();
        }
    }

    public void click(WebElement element) {
        waitForElementVisibility(element);
        moveToElement(element);
        element.click();
        sleep(AFTER_CLICK_WAIT);
        testCaseReport.logMessage(LogStatus.INFO, "clicked OK");
    }

    protected void sendKeys(WebElement element, String keys) {
        waitForElementVisibility(element);
        element.sendKeys(keys);
        testCaseReport.logMessage(LogStatus.INFO, " sent keys: " + keys + " OK");
    }

    protected boolean isDisplayed(WebElement element) {
        boolean displayed;
        waitForElementVisibility(element);
        displayed = element.isDisplayed();
        testCaseReport.logMessage(LogStatus.INFO, "Element displayed = " + displayed);
        return displayed;
    }

    public boolean isDisplayed(WebElement element, long waitTimeoutInSeconds) {
        long defaultWaitTimeout = browser.getImplicitWaitTimeout();
        browser.setImplicitWaitTimeoutMilliseconds(waitTimeoutInSeconds);
        boolean displayed = isDisplayed(element);
        browser.setImplicitWaitTimeoutMilliseconds(defaultWaitTimeout);
        return displayed;
    }

    private boolean isNotDisplayed(WebElement element) {
        boolean notDisplayed;
        try {
            element.isDisplayed();
            notDisplayed = false;
        } catch (NoSuchElementException e) {
            notDisplayed = true;
        }
        return notDisplayed;
    }

    protected boolean isNotDisplayed(WebElement element, long waitTimeoutInSeconds) {
        final long milliseconds = 100;
        final long second = 1000;
        long defaultWaitTimeout = browser.getImplicitWaitTimeout();
        browser.setImplicitWaitTimeoutMilliseconds(milliseconds);
        boolean notDisplayed = isNotDisplayed(element);
        long startTime = System.currentTimeMillis();
        while (!notDisplayed && (System.currentTimeMillis() - startTime
                < (waitTimeoutInSeconds * second))) {
            notDisplayed = isNotDisplayed(element);
        }
        browser.setImplicitWaitTimeoutMilliseconds(defaultWaitTimeout);
        return notDisplayed;
    }

    public boolean isSelected(WebElement element) {
        boolean selected;
        waitForElementVisibility(element);
        selected = element.isSelected();
        final String messageSelected = "Element selected = ";
        testCaseReport.logMessage(LogStatus.INFO, messageSelected + selected);
        return selected;
    }

    protected String getText(WebElement element) {
        String text;
        waitForElementVisibility(element);
        if (EnvironmentProperties.getEnvironmentProperties("browser").equalsIgnoreCase(
                "internet explorer")) {
            text = element.getAttribute("textContent");
        } else {
            text = element.getText();
        }
        final String messageText = "Text extracted = ";
        testCaseReport.logMessage(LogStatus.INFO, messageText + text);
        return text;
    }

    protected String getAttribute(WebElement element, String attribute) {
        String attributeValue;
        waitForElementVisibility(element);
        attributeValue = element.getAttribute(attribute);
        final String messageText = "Attribute value extracted = ";
        testCaseReport.logMessage(LogStatus.INFO, messageText + attributeValue);
        return attributeValue;
    }

    public void logWebDriverScreenShot(LogStatus logStatus, String message) {
        String path = null;
        try {
            path = takePageScreenshot();
        } catch (Exception e) {
            testCaseReport.logMessage(LogStatus.FAIL, "Was not possible to take screenshot");
        }
        testCaseReport.logMessage(logStatus, testCaseReport.getTestReport().addScreenCapture(path + ".png"), message);
    }

    protected boolean waitForElementVisibility(WebElement element, long timeoutMilliseconds) {
        final long startTime = System.currentTimeMillis();
        if (timeoutMilliseconds < 1000) {
            testCaseReport.logMessage(LogStatus.INFO, "waitForElementVisibility",
                    MIN_TIME_MESSAGE + 1000);
        }
        final long nullifyTime = 0;
        long defaultTimeout = browser.getImplicitWaitTimeout();
        browser.setImplicitWaitTimeoutMilliseconds(nullifyTime);
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(getDriver())
                    .withTimeout(Duration.ofMillis(timeoutMilliseconds))
                    .pollingEvery(Duration.ofMillis(POLLING_TIME))
                    .ignoring(StaleElementReferenceException.class)
                    .ignoring(NoSuchElementException.class)
                    .ignoring(Exception.class);
            wait.until(ExpectedConditions.visibilityOf(element));
            getByFromWebElement(element);
        } catch (NoSuchElementException | StaleElementReferenceException | TimeoutException
                | UnreachableBrowserException exception) {
            testCaseReport.logMessage(LogStatus.INFO, "waitForElementVisibility",
                    exception.getLocalizedMessage());
        }
        long endTime = System.currentTimeMillis();
        getDriver().manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
        testCaseReport.logMessage(LogStatus.INFO, "wait for element " + element + " during " + (endTime - startTime));
        return (endTime - startTime) < timeoutMilliseconds;
    }

    protected boolean waitForElementVisibility(WebElement element) {
        return waitForElementVisibility(element,
                browser.getImplicitWaitTimeout() * 1_000);
    }

    private By getByFromWebElement(WebElement element) {
        final String stepName = "getByFromWebElement";
        final String elementStringFormatOne = "] -> ";
        final String elementStringFormatTwo = " 'By.";
        if (element == null) {
            testCaseReport.logMessage(LogStatus.ERROR, stepName, "Null element");
        } else {
            String data = element.toString();
            if (data == null) {
                testCaseReport.logMessage(LogStatus.ERROR, stepName,
                        "Null element.toString()");
            } else {
                if (data.contains(elementStringFormatOne)) {
                    data = data.split(elementStringFormatOne)[1];
                } else if (data.contains(elementStringFormatTwo)) {
                    data = data.split(elementStringFormatTwo)[1];
                } else {
                    testCaseReport.logMessage(LogStatus.ERROR,
                            "Invalid By extracted: '" + data + "'");
                }
                final String delimiter = ": ";
                final int delimiterIndex = data.indexOf(delimiter);
                String byType = data.substring(0, delimiterIndex);
                String byValue = data.substring(delimiterIndex + delimiter.length(), data.length() - 1);
                switch (byType) {
                    case "xpath":
                        return By.xpath(byValue);
                    case "css selector":
                        return By.cssSelector(byValue);
                    case "id":
                        return By.id(byValue);
                    case "tag name":
                        return By.tagName(byValue);
                    case "name":
                        return By.name(byValue);
                    case "link text":
                        return By.linkText(byValue);
                    case "class name":
                        return By.className(byValue);
                    default:
                        testCaseReport.logMessage(LogStatus.ERROR, stepName,
                                "Undefined or not well transformed By from "
                                        + data + " to " + byType);
                }
            }
        }
        return (By) element;
    }

    private void logError(String errorMessage) {
        testCaseReport.logMessage(LogStatus.ERROR, errorMessage);
        logWebDriverScreenShot(LogStatus.ERROR, errorMessage);
    }

    protected String takePageScreenshot() throws IOException {
        TakesScreenshot ts = (TakesScreenshot) getDriver();
        File scrFile = ts.getScreenshotAs(OutputType.FILE);
        String path = FINAL_PATH + "screenshot_" + new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File DestFile = new File(path + ".png");
        FileUtils.copyFile(scrFile, DestFile);
        return path;
    }

    public void moveToElement(WebElement element) {
        Actions actions = new Actions(getDriver());
        actions.moveToElement(element);
        sleep(1000);
    }

    protected boolean waitForElementPresence(By by, long timeoutMilliseconds) {
        long startTime = System.currentTimeMillis();
        if (timeoutMilliseconds < 1000L) {
            this.testCaseReport.logMessage(LogStatus.INFO
                    , "waitForElementPresence", "Minimum recommended timeout in milliseconds is: 1000 searching " + by);
        }

        long defaultTimeout = this.browser.getImplicitWaitTimeout();
        this.browser.setImplicitWaitTimeoutMilliseconds(0L);
        WebElement element = null;

        try {
            FluentWait wait = (new FluentWait(this.getDriver())).withTimeout(Duration.ofMillis(timeoutMilliseconds)).pollingEvery(Duration.ofMillis(500L)).ignoring(StaleElementReferenceException.class).ignoring(NoSuchElementException.class).ignoring(Exception.class);
            element = (WebElement) wait.until(ExpectedConditions.presenceOfElementLocated(by));
        } catch (StaleElementReferenceException | TimeoutException | NoSuchElementException var16) {
            this.testCaseReport.logMessage(LogStatus.INFO, "waitForElementPresence", var16.getLocalizedMessage());
        }

        long stopTime = System.currentTimeMillis();
        long timeElapsed = stopTime - startTime;
        boolean result = timeElapsed < timeoutMilliseconds;
        if (result && element != null) {
            this.getByFromWebElement(element);
        }

        this.getDriver().manage().timeouts().implicitlyWait(defaultTimeout, TimeUnit.SECONDS);
        return result;
    }

    public List<WebElement> getElements(By by) {
        List elements = null;

        try {
            waitForElementPresence(by, browser.getImplicitWaitTimeout());
            elements = getDriver().findElements(by);
        } catch (Exception e) {
            logError("Not possible to get elements " + e);
        }
        return elements;
    }

    public WebElement getElement(By by) {
        WebElement element = null;
        try {
            waitForElementPresence(by, browser.getImplicitWaitTimeout() * 1_000);
            element = getDriver().findElement(by);
        } catch (Exception e) {
            logError("Was not possible to get element");
        }
        return element;
    }

    public boolean isClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(browser.getDriver(), 5);
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}

