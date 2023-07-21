package org.sExample.Pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    public static WebDriver driver;
    public static WebDriverWait wait;

    //webElements//
    @FindBy(how = How.CSS, using = "#onetrust-accept-btn-handler")
    public WebElement acceptCookiesButton;

    @FindBy(how = How.CSS, using = "#onetrust-pc-btn-handler")
    public WebElement rejectCookiesButton;

    @FindBy(how = How.ID, using = "genderManButton")
    private WebElement maleButton;

    @FindBy(how = How.CSS, using = "#genderWomanButton")
    private WebElement femaleButton;

    @FindBy(how = How.XPATH, using = "//*[@class='default-input o-header__search--input']")
    private WebElement searchBox;

    public void setDriver(WebDriver driver) {
        BasePage.driver = driver;
        BasePage.wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }


    public void acceptCookies() {
        acceptCookiesButton = wait.until(ExpectedConditions.elementToBeClickable(acceptCookiesButton));
        acceptCookiesButton.click();
    }

    //gender can be chosen by indicating an index instead we will use male gender
    public void chooseGender() {
        maleButton = wait.until(ExpectedConditions.elementToBeClickable(maleButton));
        maleButton.click();
    }


    public void enterSearch(String keyword) {
        searchBox = wait.until(ExpectedConditions.visibilityOf(searchBox));
        searchBox.click();
        searchBox.sendKeys(keyword);
    }

    public void clearSearch() {
        searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.click();
        searchBox.sendKeys(Keys.CONTROL + "A");
        searchBox.sendKeys(Keys.DELETE);
    }

    public void search() {
        searchBox = wait.until(ExpectedConditions.elementToBeClickable(searchBox));
        searchBox.click();
        searchBox.sendKeys(Keys.ENTER);
    }

}
