package behavior.web.sections;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.TestCaseReport;
import pages.SettingsPage;

public class SettingsBehavior {
    public static final String CLASS_NAME = "TodoBehavior";
    private final Browser browser;
    private final TestCaseReport testCaseReport;

    public SettingsBehavior(TestCaseReport testCaseReport, Browser browser) {
        this.testCaseReport = testCaseReport;
        this.browser = browser;
    }

    public void checkingDifferentStatus() {
        final String METHOD_NAME = "checkingDifferentStatus";
        NavigationBehavior navigationBehavior = new NavigationBehavior(testCaseReport, browser);
        SettingsPage settingsPage = new SettingsPage(browser);
        settingsPage.openSettings();
        settingsPage.selectStatusAuto();
        settingsPage.openSettings();
        testCaseReport.assertEquals("Auto status is selected ", true, settingsPage.isAutoChecked());
        settingsPage.selectStatusUnavailable();
        settingsPage.openSettings();
        testCaseReport.assertEquals("Unavailable status is selected ", true, settingsPage.isUnavailableChecked());
        settingsPage.selectStatusAvailable();
        settingsPage.openSettings();
        testCaseReport.assertEquals("Available is selected ", true, settingsPage.isAvailableChecked());
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

}
