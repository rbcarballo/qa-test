package pages;

import core.Browser;
import core.Page;
import data.ACUser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PeoplePage extends Page {

    @FindBy(xpath = "//div[contains(@data-test,'people-teammates')]")
    WebElement tabTeamMates;
    @FindBy(css = "input[data-test='contacts-search-input']")
    WebElement searchTextBox;

    public PeoplePage(Browser browser) {
        super(browser);
    }

    public void searchTeamMate(ACUser user) {
        //go to team mates and search for user
        click(tabTeamMates);
        sendKeys(searchTextBox, user.getUsername().split("@")[0]);
        waitForElementPresence(By.cssSelector("div[data-test='people-search-teammates-section']"), 5000);
        List<WebElement> elements;
        elements = getElements(By.xpath("//div[@data-test='people-search-teammates-section']//div[contains(@data-test,'teammate')]"));

        // check every single results in order to check if searched user is present
        for (WebElement element : elements) {
            click(element);
            if (user.getUsername().equalsIgnoreCase(
                    getElement(By.cssSelector("p[data-test='email-value']")).getText())) {
                break;
            } else {
                click(getElement(By.xpath("//div[@data-test='teammate-details']//div[@data-test='modal-close']")));
            }
        }

        //Compare user.mail with results shown in screen
        testCaseReport.assertEquals("User searched ",
                user.getUsername().toLowerCase(),
                getElement(By.cssSelector("p[data-test='email-value']")).getText().toLowerCase());
    }

}
