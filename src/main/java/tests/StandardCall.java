package tests;

import behavior.TestBaseBehavior;
import core.TestBase;
import data.ACUser;
import org.junit.After;
import org.junit.Test;

public class StandardCall extends TestBase {
    public static final String URL_AIRCALL = "https://phone.aircall.io/";
    TestBaseBehavior behaviorSender;
    TestBaseBehavior behaviorReceiver;
    ACUser userSender;
    ACUser userReceiver;


    public StandardCall() {
        userReceiver = new ACUser("rbcarballo@gmail.com", "4c5682af");
        userSender = new ACUser("qa+test@aircall.io", "f77398b7");
    }

    @Test
    public void standardCall() {
        createInstance("Checking standard call with two browsers ");
        //login user 1
        behaviorSender = new TestBaseBehavior(testCaseReport);
        behaviorSender.web().loginPage().login(URL_AIRCALL, userSender);
        //login user 2
        behaviorReceiver = new TestBaseBehavior(testCaseReport);
        behaviorReceiver.web().loginPage().login(URL_AIRCALL, userReceiver);
        String phoneNumberReceiver = behaviorReceiver.web().keypad().getPhone();
        //User 1 make a call to user 2
        behaviorSender.web().keypad().standardCall(phoneNumberReceiver);
        behaviorReceiver.web().call().acceptCall();
        behaviorSender.web().call().checkStandardCallMenu();
        behaviorSender.web().call().hangUp();
    }

    @After
    public void tearDown() {
        behaviorReceiver.web().closeNavigation();
        behaviorSender.web().closeNavigation();
    }
}

