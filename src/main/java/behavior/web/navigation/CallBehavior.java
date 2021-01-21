package behavior.web.navigation;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.TestCaseReport;
import data.ACUser;
import pages.CallPage;
import pages.PeoplePage;

public class CallBehavior {
    public static final String CLASS_NAME = "PeopleBehavior";
    private final Browser browser;
    private final TestCaseReport testCaseReport;

    public CallBehavior(TestCaseReport testCaseReport, Browser browser) {
        this.testCaseReport = testCaseReport;
        this.browser = browser;
    }

    public void internalCallToTeamMate(ACUser userToSearch) {
        final String METHOD_NAME = "internalCallToTeamMate";
        NavigationBehavior navigationBehavior = new NavigationBehavior(testCaseReport, browser);
        navigationBehavior.navigateToPeople();
        PeoplePage peoplePage = new PeoplePage(browser);
        peoplePage.searchTeamMate(userToSearch);
        CallPage callPage = new CallPage(browser);
        callPage.callForSearchedTeamMate();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public void acceptCall() {
        final String METHOD_NAME = "acceptCall";
        CallPage callPage = new CallPage(browser);
        callPage.acceptCall();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public void checkInternalCallMenu() {
        final String METHOD_NAME = "checkInternalCallMenu";
        CallPage callPage = new CallPage(browser);
        callPage.checkInternalMenu();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public void checkStandardCallMenu() {
        final String METHOD_NAME = "checkStandardCallMenu";
        CallPage callPage = new CallPage(browser);
        callPage.checkStandardMenu();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public void hangUp() {
        final String METHOD_NAME = "hangUp";
        CallPage callPage = new CallPage(browser);
        callPage.hangUp();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }



}
