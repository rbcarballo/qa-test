package pages;

import core.Browser;
import core.Page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class KeypadPage extends Page {

    @FindBy(css = "div[data-test='line-number']")
    WebElement lineNumber;
    @FindBy(xpath = "//span[contains(@data-test,'keyboard-input-flag')]")
    WebElement flagButton;
    @FindBy(css = "input[placeholder='Search for a country']")
    WebElement searchCountry;
    @FindBy(css = "input[data-test='keyboard-input-text']")
    WebElement textBoxNumber;
    @FindBy(css = "button[data-test='keyboard-button-dial']")
    WebElement buttonMakeCall;


    public KeypadPage(Browser browser) {
        super(browser);
    }

    public String getPhoneNumber() {
        return lineNumber.getText().replace(" ", "");
    }

    public void standardCall(String destinationPhone) {
        String prefix = destinationPhone.substring(0, 3);
        String number = destinationPhone.substring(3, destinationPhone.length());
        click(flagButton);
        waitForElementVisibility(searchCountry, 5000);
        sendKeys(searchCountry, prefix);
        WebElement country = getElement(By.xpath("//small[.='" + prefix + "']"));
        click(country);
        waitForElementVisibility(textBoxNumber);
        sendKeys(textBoxNumber, number);
        click(buttonMakeCall);
    }

}
