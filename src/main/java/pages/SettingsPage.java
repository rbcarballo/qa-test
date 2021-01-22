package pages;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SettingsPage extends Page {

    public static final String ANCESTOR_LABEL = "/ancestor::label";
    @FindBy(xpath = "//input[@class='styles_input__38FzS'][@value='always_opened']")
    WebElement availableButton;
    @FindBy(xpath = "//input[@class='styles_input__38FzS'][@value='always_opened']" + ANCESTOR_LABEL)
    WebElement availableButtonClickable;
    @FindBy(xpath = "//input[@class='styles_input__38FzS'][@value='auto']")
    WebElement autoButton;
    @FindBy(xpath = "//input[@class='styles_input__38FzS'][@value='auto']" + ANCESTOR_LABEL)
    WebElement autoButtonClickable;
    @FindBy(xpath = "//input[@class='styles_input__38FzS'][@value='always_closed']")
    WebElement unavailableButton;
    @FindBy(xpath = "//input[@class='styles_input__38FzS'][@value='always_closed']" + ANCESTOR_LABEL)
    WebElement unavailableButtonClickable;

    @FindBy(css = "li[data-test='tab-bar-settings']")
    WebElement tabSetting;

    public SettingsPage(Browser browser) {
        super(browser);
    }


    public void openSettings() {
        click(tabSetting);
        waitForElementVisibility(getElement(By.xpath("//h2[@data-test='title-text'][.='Settings']")));
    }

    public void selectStatusAvailable() {
        waitForElementVisibility(availableButton, 5000);
        if (!isSelected(availableButton)) {
            click(availableButtonClickable);
            waitForElementVisibility(tabSetting, 2000);
        }
    }

    public void selectStatusAuto() {
        waitForElementVisibility(autoButton, 5000);
        if (!isSelected(autoButton)) {
            click(autoButtonClickable);
            waitForElementVisibility(tabSetting, 2000);
        }
    }

    public void selectStatusUnavailable() {
        waitForElementVisibility(unavailableButton, 5000);
        if (!isSelected(unavailableButton)) {
            click(unavailableButtonClickable);
            waitForElementVisibility(tabSetting, 2000);
        }
    }

    public boolean isAvailableChecked() {
        waitForElementVisibility(availableButton, 5000);
        logWebDriverScreenShot(LogStatus.INFO, "Available status selected");
        return availableButton.isSelected();
    }

    public boolean isAutoChecked() {
        waitForElementVisibility(autoButton, 5000);
        logWebDriverScreenShot(LogStatus.INFO, "Auto status selected");
        return autoButton.isSelected();
    }

    public boolean isUnavailableChecked() {
        waitForElementVisibility(unavailableButton, 5000);
        logWebDriverScreenShot(LogStatus.INFO, "Unavailable status selected");
        return unavailableButton.isSelected();
    }


}
