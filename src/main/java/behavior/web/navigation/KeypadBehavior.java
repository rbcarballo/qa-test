package behavior.web.navigation;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.TestCaseReport;
import pages.KeypadPage;

public class KeypadBehavior {
    public static final String CLASS_NAME = "PeopleBehavior";
    private final Browser browser;
    private final TestCaseReport testCaseReport;

    public KeypadBehavior(TestCaseReport testCaseReport, Browser browser) {
        this.testCaseReport = testCaseReport;
        this.browser = browser;
    }

    public String getPhone() {
        final String METHOD_NAME = "getPhone";
        NavigationBehavior navigationBehavior = new NavigationBehavior(testCaseReport, browser);
        navigationBehavior.navigateToKeypad();
        KeypadPage keypadPage = new KeypadPage(browser);
        String phone_number = keypadPage.getPhoneNumber();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
        return phone_number;
    }

    public void standardCall(String phone_number) {
        final String METHOD_NAME = "standardCall";
        NavigationBehavior navigationBehavior = new NavigationBehavior(testCaseReport, browser);
        navigationBehavior.navigateToKeypad();
        KeypadPage keypadPage = new KeypadPage(browser);
        keypadPage.standardCall(phone_number);
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

}
