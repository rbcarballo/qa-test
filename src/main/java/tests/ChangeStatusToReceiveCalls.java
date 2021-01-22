package tests;

import behavior.TestBaseBehavior;
import core.TestBase;
import data.ACUser;
import org.junit.After;
import org.junit.Test;

public class ChangeStatusToReceiveCalls extends TestBase {
    TestBaseBehavior behavior;
    ACUser userSender;

    public ChangeStatusToReceiveCalls() {
        userSender = new ACUser("qa+test@aircall.io", "f77398b7");
    }

    @Test
    public void changeStatusToReceiveCalls() {
        createInstance("Change status to receive calls ");
        behavior = new TestBaseBehavior(testCaseReport);
        behavior.web().loginPage().login("https://phone.aircall.io/", userSender);
        behavior.web().settings().checkingDifferentStatus();
    }

    @After
    public void tearDown() {
        behavior.web().closeNavigation();
    }
}

