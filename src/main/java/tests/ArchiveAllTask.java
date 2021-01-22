package tests;

import behavior.TestBaseBehavior;
import core.TestBase;
import data.ACUser;
import org.junit.After;
import org.junit.Test;

public class ArchiveAllTask extends TestBase {
    TestBaseBehavior behavior;
    TestBaseBehavior behaviorReceiver;
    ACUser userSender;
    ACUser userReceiver;


    public ArchiveAllTask() {
        userReceiver = new ACUser("rbcarballo@gmail.com", "4c5682af");
        userSender = new ACUser("qa+test@aircall.io", "f77398b7");
    }

    @Test
    public void archiveAllTask() {
        createInstance("Archive all task if present");
        behavior = new TestBaseBehavior(testCaseReport);
        behaviorReceiver = new TestBaseBehavior(testCaseReport);
        behavior.web().loginPage().login("https://phone.aircall.io/", userSender);
        behavior.web().todo().archiveAllTask();
        behavior.web().todo().checkEmptyListOfTask();
    }

    @After
    public void tearDown() {
        behavior.web().closeNavigation();
    }
}

