package behavior.api;

import behavior.api.contacts.ContactsBehavior;
import core.TestCaseReport;

public class ApiBehavior {
    private final TestCaseReport testCaseReport;

    public ApiBehavior(TestCaseReport testCaseReport) {
        this.testCaseReport = testCaseReport;
    }

    public ContactsBehavior contacts() {
        return new ContactsBehavior(testCaseReport);
    }
}
