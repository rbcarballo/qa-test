package behavior.web.navigation;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.TestCaseReport;
import data.ACUser;
import pages.NavigationPage;

public class LoginBehavior {
    public static final String CLASS_NAME = "LoginBehavior";
    private final Browser browser;
    private final TestCaseReport testCaseReport;

    public LoginBehavior(TestCaseReport testCaseReport, Browser browser) {
        this.testCaseReport = testCaseReport;
        this.browser = browser;
    }

    public void login(String url, ACUser user) {
        final String METHOD_NAME = "login";
        NavigationPage navigationPage = new NavigationPage(browser);
        try {
            navigationPage.loginAction(url, user);
            testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
        } catch (Exception e) {
            testCaseReport.logMessage(LogStatus.FAIL, CLASS_NAME, METHOD_NAME);
        }
    }

}
