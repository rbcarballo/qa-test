package behavior.web.sections;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.TestCaseReport;
import pages.NavigationPage;

public class NavigationBehavior {
    public static final String CLASS_NAME = "NavigationBehavior";
    private final Browser browser;
    private final TestCaseReport testCaseReport;

    public NavigationBehavior(TestCaseReport testCaseReport, Browser browser) {
        this.testCaseReport = testCaseReport;
        this.browser = browser;
    }

    public void checkBasicNavigation() {
        navigateToToDo();
        navigateToHistory();
        navigateToKeypad();
        navigateToPeople();
        navigateToSettings();
    }

    public void navigateToPeople() {
        final String METHOD_NAME = "navigateToPeople";
        NavigationPage navigationPage = new NavigationPage(browser);
        navigationPage.navigateToPeople();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public void navigateToToDo() {
        final String METHOD_NAME = "navigateToToDo";
        NavigationPage navigationPage = new NavigationPage(browser);
        navigationPage.navigateToToDo();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public void navigateToHistory() {
        final String METHOD_NAME = "navigateToHistory";
        NavigationPage navigationPage = new NavigationPage(browser);
        navigationPage.navigateToHistory();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public void navigateToKeypad() {
        final String METHOD_NAME = "navigateToKeypad";
        NavigationPage navigationPage = new NavigationPage(browser);
        navigationPage.navigateToKey();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public void navigateToSettings() {
        final String METHOD_NAME = "navigateToSettings";
        NavigationPage navigationPage = new NavigationPage(browser);
        navigationPage.navigateToSettings();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }
}
