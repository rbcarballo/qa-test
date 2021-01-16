package behavior.api.contacts;

import com.relevantcodes.extentreports.LogStatus;
import core.TestCaseReport;
import io.restassured.response.ValidatableResponse;
import request.contacts.RequestContact;


public class ContactsBehavior {
    public static final String CLASS_NAME = "NavigationBehavior";
    private final TestCaseReport testCaseReport;

    public ContactsBehavior(TestCaseReport testCaseReport) {
        this.testCaseReport = testCaseReport;
    }

    public void getContactList() {
        final String METHOD_NAME = "getContactList";
        ValidatableResponse response = RequestContact.getContacts();
        testCaseReport.assertEquals("Expected code ", 200, response.extract().statusCode());
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
    }

    public ValidatableResponse getContactByPhone(String phoneNumber) {
        final String METHOD_NAME = "getContactByPhone";
        ValidatableResponse response = RequestContact.searchContactByPhone(phoneNumber);
        testCaseReport.assertEquals("Expected code ", 200, response.extract().statusCode());
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
        return response;
    }

    public ValidatableResponse getContactById(String id) {
        final String METHOD_NAME = "getContactById";
        ValidatableResponse response = RequestContact.searchContactById(id);
        testCaseReport.assertEquals("Expected code ", 200, response.extract().statusCode());
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
        return response;
    }

    public ValidatableResponse createContact(String body) {
        final String METHOD_NAME = "createContact";
        ValidatableResponse response = RequestContact.createContact(body);
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
        return response;
    }

    public ValidatableResponse deleteContact(int id) {
        final String METHOD_NAME = "deleteContact";
        ValidatableResponse response = RequestContact.deleteContact(String.valueOf(id));
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
        return response;
    }

    public ValidatableResponse updateContact(int id, String body) {
        final String METHOD_NAME = "updateContact";
        ValidatableResponse response = RequestContact.updateContact(String.valueOf(id), body);
        testCaseReport.logMessage(LogStatus.PASS, CLASS_NAME, METHOD_NAME);
        return response;
    }
}
