package pages;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CallPage extends Page {

    @FindBy(css = "button[data-test='keyboard-button-dial']")
    WebElement buttonAccept;
    @FindBy(css = "div[data-test='action-mute']")
    WebElement buttonMute;
    @FindBy(css = "div[data-test='action-hold']")
    WebElement buttonHold;
    @FindBy(css = "div[data-test='action-keypad']")
    WebElement buttonKeypad;
    @FindBy(css = "div[data-test='add-or-call-button']")
    WebElement buttonAddToCall;
    @FindBy(css = "button[data-test='hangup-button']")
    WebElement buttonHangUp;
    @FindBy(css = "button[data-test='notes-button']")
    WebElement buttonNotes;
    @FindBy(css = "button[data-test='tags-button']")
    WebElement buttonTag;
    @FindBy(css = "button[data-test='assign-button']")
    WebElement buttonAssign;
    @FindBy(css = "div[data-test='transfer-button']")
    WebElement buttonTransfer;


    public CallPage(Browser browser) {
        super(browser);
    }

    public void callForSearchedTeamMate() {
        //Click on internal call, check if ringing status is shown, take screenshot and close call
        click(getElement(By.cssSelector("div[data-test='icon-call-internal']")));
        waitForElementPresence(By.cssSelector("button[data-test='hangup-button'"), 10000);
        logWebDriverScreenShot(LogStatus.INFO, "Calling to teammate");
    }

    public void acceptCall() {
        waitForElementPresence(By.cssSelector("button[data-test='keyboard-button-dial']"), 30000);
        sleep(5000);
        click(buttonAccept);
        logWebDriverScreenShot(LogStatus.INFO, "Accepting Call");
    }

    public void checkInternalMenu() {
        checkCommomMenu();
        testCaseReport.assertEquals("Start recording is not enable ",
                true, getElement(By.xpath("//div[contains(@class,'styles_grey')]/ancestor::div[@data-test='action-start-recording']")).isEnabled());
        logWebDriverScreenShot(LogStatus.INFO, "Internal call menu");
    }

    private void checkCommomMenu() {
        waitForElementVisibility(buttonMute);
        testCaseReport.assertEquals("Mute button is enabled", true, buttonMute.isEnabled());
        testCaseReport.assertEquals("Hold button is enabled ", true, buttonHold.isEnabled());
        testCaseReport.assertEquals("Add to call button is enabled ", true, buttonAddToCall.isEnabled());
        testCaseReport.assertEquals("Keypad button is enabled ", true, buttonKeypad.isEnabled());
    }

    public void checkStandardMenu() {
        checkCommomMenu();
        testCaseReport.assertEquals("Start recording enabled", true,
                getElement(By.xpath("//div[@data-test='action-start-recording']//div[contains(@class,'styles_white')]")).isEnabled());
        testCaseReport.assertEquals("Notes button is enabled", true, buttonNotes.isEnabled());
        testCaseReport.assertEquals("Tag button is enabled", true, buttonTag.isEnabled());
        testCaseReport.assertEquals("Assign button is enabled", true, buttonAssign.isEnabled());
        testCaseReport.assertEquals("Transfer button is enabled", true, buttonTransfer.isEnabled());
        logWebDriverScreenShot(LogStatus.INFO, "Standard call menu");
    }

    public void hangUp() {
        waitForElementPresence(By.cssSelector("button[data-test='hangup-button']"), 30000);
        click(buttonHangUp);
    }

    public void rejectCall() {
        click(buttonHangUp);
    }
}
