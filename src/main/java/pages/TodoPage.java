package pages;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class TodoPage extends Page {

    @FindBy(xpath = "//button[contains(@class,'styles_green')]")
    WebElement archiveAllEnabled;
    @FindBy(xpath = "//button[contains(@class,'styles_grey')]")
    WebElement archiveAllDisable;
    @FindBy(css = "button[data-test='dialog-confirm']")
    WebElement buttonConfirm;
    @FindBy(css = "div[data-test='counter']")
    WebElement counter;

    public TodoPage(Browser browser) {
        super(browser);
    }

    public void archiveAllTask() {
        try {
            if (isDisplayed(archiveAllEnabled)) {
                click(archiveAllEnabled);
                click(buttonConfirm);
            }
        } catch (Exception e) {
            testCaseReport.logMessage(LogStatus.INFO, "No task to archive");
        }
    }

    public void checkTaskWithVoicemail() {
        testCaseReport.assertEquals("Number of pending task is ", "1", getText(counter));
        waitForElementPresence(By.xpath("//div[contains(@data-test,'call-card-enriched')]"), 5000);
        List<WebElement> elements = getElements(By.xpath("//div[contains(@data-test,'call-card-enriched')]"));
        testCaseReport.assertEquals("Number of call receives is ", 1, elements.size());
        for (WebElement element : elements) {
            testCaseReport.assertEquals("Voice mail is shown ", true,
                    element.findElement(By.xpath("//div[@class='styles_audioPlayerWrapper__C6yAx']")).isDisplayed());
        }
        logWebDriverScreenShot(LogStatus.INFO, "Voicemail missed call");
    }

    public void checkEmptyTaskList() {
        String TASK_ORGANIZED = "You’ve organized all your calls, congrats!";
        waitForElementPresence(By.xpath("//div[@class='styles_subtitle__17WlU'][.='" + TASK_ORGANIZED + "']"), 5000);
        WebElement textOrganized = getElement(By.xpath("//div[@class='styles_subtitle__17WlU'][.='" + TASK_ORGANIZED + "']"));
        testCaseReport.assertEquals("All task are organized message is shown ", true, textOrganized.isDisplayed());
    }
}
