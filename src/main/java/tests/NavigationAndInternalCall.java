package tests;

import behavior.TestBaseBehavior;
import core.TestBase;
import data.ACUser;
import org.junit.After;
import org.junit.Test;

public class NavigationAndInternalCall extends TestBase {
    TestBaseBehavior behavior;
    TestBaseBehavior behaviorReceiver;
    ACUser userSender;
    ACUser userReceiver;


    public NavigationAndInternalCall() {
        userReceiver = new ACUser("rbcarballo@gmail.com", "4c5682af");
        userSender = new ACUser("qa+test@aircall.io", "f77398b7");
    }

    @Test
    public void testAirCall() {
        createInstance("Checking navigation and internal call");
        behavior = new TestBaseBehavior(testCaseReport);
        behaviorReceiver = new TestBaseBehavior(testCaseReport);
        behavior.web().loginPage().login("https://phone.aircall.io/", userSender);
        behaviorReceiver.web().loginPage().login("https://phone.aircall.io/", userReceiver);
        //check basic navigation
        behavior.web().navigation().checkBasicNavigation();
        behavior.web().call().internalCallToTeamMate(userReceiver);
        behaviorReceiver.web().call().acceptCall();
        behavior.web().call().checkInternalCallMenu();
        behaviorReceiver.web().call().checkInternalCallMenu();
        behavior.web().call().hangUp();
    }

    @After
    public void tearDown() {
        behavior.web().closeNavigation();
        behaviorReceiver.web().closeNavigation();
    }
}

