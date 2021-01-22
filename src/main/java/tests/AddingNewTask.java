package tests;

import behavior.TestBaseBehavior;
import core.TestBase;
import data.ACUser;
import org.junit.After;
import org.junit.Test;

public class AddingNewTask extends TestBase {
    TestBaseBehavior behavior;
    TestBaseBehavior behaviorReceiver;
    ACUser userSender;
    ACUser userReceiver;


    public AddingNewTask() {
        userReceiver = new ACUser("rbcarballo@gmail.com", "4c5682af");
        userSender = new ACUser("qa+test@aircall.io", "f77398b7");
    }

    @Test
    public void addingNewTask() {
        createInstance("Adding a new task to To-Do list");
        behavior = new TestBaseBehavior(testCaseReport);
        behaviorReceiver = new TestBaseBehavior(testCaseReport);
        behavior.web().loginPage().login("https://phone.aircall.io/", userSender);
        behaviorReceiver.web().loginPage().login("https://phone.aircall.io/", userReceiver);
        behaviorReceiver.web().todo().archiveAllTask();
        String phoneNumberReceiver = behaviorReceiver.web().keypad().getPhone();
        //User 1 make a call to user 2
        behavior.web().keypad().standardCall(phoneNumberReceiver);
        behaviorReceiver.web().call().hangUp();
        //Adding a sleep in order to have a new task with voicemail.
        sleep(10000);
        behavior.web().call().hangUp();
        behaviorReceiver.web().navigation().navigateToToDo();
        behaviorReceiver.web().todo().checkTaskWithVoicemail();
    }

    @After
    public void tearDown() {
        behavior.web().closeNavigation();
        behaviorReceiver.web().closeNavigation();
    }
}

