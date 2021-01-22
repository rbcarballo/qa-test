package behavior.web.navigation;

import com.relevantcodes.extentreports.LogStatus;
import core.Browser;
import core.TestCaseReport;
import pages.TodoPage;

public class TodoBehavior {
    public static final String CLASS_NAME = "TodoBehavior";
    private final Browser browser;
    private final TestCaseReport testCaseReport;

    public TodoBehavior(TestCaseReport testCaseReport, Browser browser) {
        this.testCaseReport = testCaseReport;
        this.browser = browser;
    }

    public void archiveAllTask() {
        final String METHOD_NAME = "archiveAllTask";
        NavigationBehavior navigationBehavior = new NavigationBehavior(testCaseReport, browser);
        navigationBehavior.navigateToToDo();
        TodoPage todoPage = new TodoPage(browser);
        todoPage.archiveAllTask();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public void checkTaskWithVoicemail() {
        final String METHOD_NAME = "checkTaskWithVoicemail";
        NavigationBehavior navigationBehavior = new NavigationBehavior(testCaseReport, browser);
        navigationBehavior.navigateToToDo();
        TodoPage todoPage = new TodoPage(browser);
        todoPage.checkTaskWithVoicemail();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public void checkEmptyListOfTask() {
        final String METHOD_NAME = "checkEmptyTaskList";
        NavigationBehavior navigationBehavior = new NavigationBehavior(testCaseReport, browser);
        navigationBehavior.navigateToToDo();
        TodoPage todoPage = new TodoPage(browser);
        todoPage.checkEmptyTaskList();
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }


}
