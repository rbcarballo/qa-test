package pages;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.Page;
import data.ACUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CallPage extends Page {

    @FindBy(xpath = "//div[contains(@data-test,'people-teammates')]")
    WebElement tabTeamMates;
    @FindBy(css = "input[data-test='contacts-search-input']")
    WebElement searchTextBox;

    public CallPage(Browser browser) {
        super(browser);
    }

    public void callForSearchedTeamMate(ACUser user) {
        //Click on internal call, check if ringing status is shown, take screenshot and close call
        click(getElement(By.cssSelector("div[data-test='icon-call-internal']")));
        sleep(5000);
        logWebDriverScreenShot(LogStatus.INFO, "Calling to teammate");
        sleep(5000);
        click(getElement(By.cssSelector("button[data-test='hangup-button'")));
    }
}
