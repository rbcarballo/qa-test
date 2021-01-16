package pages;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.Page;
import data.ACUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationPage extends Page {

    @FindBy(css = "input[placeholder='Email address']")
    WebElement loginUser;
    @FindBy(css = "input[placeholder='Password']")
    WebElement loginPassword;
    @FindBy(css = "button[type='Submit']")
    WebElement buttonSubmit;
    @FindBy(css = "button[data-test='continue-button']")
    WebElement continueButton;
    @FindBy(css = "button[data-test='next-button']")
    WebElement nextButton;
    @FindBy(css = "li[data-test='tab-bar-people']")
    WebElement tabPeople;
    @FindBy(css = "li[data-test='tab-bar-todo']")
    WebElement tabTodo;
    @FindBy(css = "li[data-test='tab-bar-history']")
    WebElement tabHistory;
    @FindBy(css = "li[data-test='tab-bar-keypad']")
    WebElement tabKeypad;
    @FindBy(css = "li[data-test='tab-bar-settings']")
    WebElement tabSetting;
    @FindBy(xpath = "//div[@data-test='modal-close']")
    WebElement modelClose;


    public NavigationPage(Browser browser) {
        super(browser);
    }

    public void loginAction(String url, ACUser user) {
        openURL(url);
        sendKeys(loginUser, user.getUsername());
        sendKeys(loginPassword, user.getPassword());
        click(buttonSubmit);
        WebDriverWait wait = new WebDriverWait(getDriver(), 5000);
        wait.until(ExpectedConditions.visibilityOf(continueButton));
        click(continueButton);
        for (int i = 0; i < 4; i++) {
            wait.until(ExpectedConditions.visibilityOf(nextButton));
            click(nextButton);
        }
    }

    public void closeNavigation() {

        logWebDriverScreenShot(LogStatus.PASS, "Page opened");
        closeURL();
    }

    public void navigateToPeople() {
        click(tabPeople);
        waitForElementVisibility(getElement(By.cssSelector("div[data-test='people-contacts'")));
    }

    public void navigateToToDo() {
        click(tabTodo);
        waitForElementVisibility(getElement(By.cssSelector("div[data-test='todo-view'")));
    }

    public void navigateToHistory() {
        click(tabHistory);
        waitForElementVisibility(getElement(By.cssSelector("div[data-test='call-history-view'")));
    }

    public void navigateToKey() {
        click(tabKeypad);
        waitForElementVisibility(getElement(By.cssSelector("input[data-test='keyboard-input-text'")));
    }

    public void navigateToSettings() {
        click(tabSetting);
        waitForElementVisibility(getElement(By.xpath("//h2[@data-test='title-text'][.='Settings']")));
        click(modelClose);
    }
}
