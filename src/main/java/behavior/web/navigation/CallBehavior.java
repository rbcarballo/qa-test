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
        PeoplePage peoplePage = new PeoplePage(browser);
        peoplePage.searchTeamMate(userToSearch);
        CallPage callPage = new CallPage(browser);
        callPage.callForSearchedTeamMate(userToSearch);
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

}
