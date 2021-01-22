package behavior.web;

import behavior.web.sections.*;
import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.TestCaseReport;
import pages.NavigationPage;

public class WebBehavior {
    private Browser browser;
    private final TestCaseReport testCaseReport;

    public WebBehavior(TestCaseReport testCaseReport) {
        this.testCaseReport = testCaseReport;
    }

    public WebBehavior(TestCaseReport testCaseReport, Browser navigator) {
        this.testCaseReport = testCaseReport;
        this.browser = navigator;
    }

    public LoginBehavior loginPage() {
        return new LoginBehavior(testCaseReport, browser);
    }

    public void closeNavigation() {
        final String METHOD_NAME = "closeNavigation";
        NavigationPage navigationPage = new NavigationPage(browser);
        navigationPage.closeNavigation();
        testCaseReport.logMessage(LogStatus.PASS, "WebBehavior", METHOD_NAME);
    }

    public NavigationBehavior navigation() {
        return new NavigationBehavior(testCaseReport, browser);
    }

    public PeopleBehavior people() {
        return new PeopleBehavior(testCaseReport, browser);
    }

    public TodoBehavior todo() {
        return new TodoBehavior(testCaseReport, browser);
    }

    public CallBehavior call() {
        return new CallBehavior(testCaseReport, browser);
    }

    public KeypadBehavior keypad() {
        return new KeypadBehavior(testCaseReport, browser);
    }

    public SettingsBehavior settings() {
        return new SettingsBehavior(testCaseReport, browser);
    }
}
