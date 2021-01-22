package behavior.web.sections;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.TestCaseReport;
import data.ACUser;
import pages.PeoplePage;

public class PeopleBehavior {
    public static final String CLASS_NAME = "PeopleBehavior";
    private final Browser browser;
    private final TestCaseReport testCaseReport;

    public PeopleBehavior(TestCaseReport testCaseReport, Browser browser) {
        this.testCaseReport = testCaseReport;
        this.browser = browser;
    }

    public void searchTeamMate(ACUser userToSearch) {
        final String METHOD_NAME = "searchTeamMate";
        PeoplePage peoplePage = new PeoplePage(browser);
        peoplePage.searchTeamMate(userToSearch);
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }
}
