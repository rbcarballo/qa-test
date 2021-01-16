package behavior;

import behavior.api.ApiBehavior;
import behavior.web.WebBehavior;
import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.TestCaseReport;

public class TestBaseBehavior {

    private Browser browser;
    private final TestCaseReport testCaseReport;

    public TestBaseBehavior(TestCaseReport testCaseReport) {
        this.testCaseReport = testCaseReport;
    }

    public TestBaseBehavior(TestCaseReport testCaseReport, Browser browser) {
        this.testCaseReport = testCaseReport;
        this.browser = browser;
    }

    public Browser getBrowser() {
        return this.browser;
    }

    public WebBehavior web() {
        try {
            if (browser == null) {
                browser = new Browser(testCaseReport);
            }
        } catch (Exception e) {
            testCaseReport.logMessage(LogStatus.ERROR, "Was not possible to open browser");
        }
        return new WebBehavior(testCaseReport, browser);
    }


    public ApiBehavior api() {
        return new ApiBehavior(testCaseReport);
    }
}
