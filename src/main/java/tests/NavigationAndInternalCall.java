package tests;

import behavior.TestBaseBehavior;
import core.TestBase;
import data.ACUser;
import org.junit.Test;

public class NavigationAndInternalCall extends TestBase {
    TestBaseBehavior behavior;
    ACUser userSender;
    ACUser userReceiver;


    public NavigationAndInternalCall() {
        userSender = new ACUser("rbcarballo@gmail.com", "4c5682af");
        userReceiver = new ACUser("qa+test@aircall.io", "f77398b7");
    }

    @Test
    public void testAirCall() {
        createInstance("Checking navigation and internal call");
        behavior = new TestBaseBehavior(testCaseReport);
        behavior.web().loginPage().login("https://phone.aircall.io/", userSender);
        //check basic navigation
        behavior.web().navigation().navigateToToDo();
        behavior.web().navigation().navigateToHistory();
        behavior.web().navigation().navigateToKeypad();
        behavior.web().navigation().navigateToPeople();
        behavior.web().navigation().navigateToSettings();
        behavior.web().call().internalCallToTeamMate(userReceiver);
        behavior.web().closeNavigation();
    }
}

