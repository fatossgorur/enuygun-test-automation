package com.enuygun.helper;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class DriverHelper {
    protected WebDriver webDriver;
    protected WebDriverWait wait;

    public DriverHelper(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.wait = new WebDriverWait(webDriver, Duration.ofSeconds(20));
    }

    public void getUrl(String url) {
        webDriver.get(url);
    }

    public WebElement findElement(By element) {
        return webDriver.findElement(element);
    }

    public void clickElement(By element) {
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public void sendKeysElementWithEnter(By byElement, String text) {
        wait.until(ExpectedConditions.elementToBeClickable(byElement)).sendKeys(text + Keys.ENTER);
    }

    public void waitToElement(By element) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    public String getText(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
    }

    public String getTextAtIndex(By byElements, int index) {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byElements));
        if (index >= 0 && index < elements.size()) {
            return elements.get(index).getText();
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds: " + index);
        }
    }

    public void clickElementAtIndex(By byElements, int index) {
        List<WebElement> elements = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(byElements));
        if (index >= 0 && index < elements.size()) {
            elements.get(index).click();
        } else {
            throw new IndexOutOfBoundsException("Index is out of bounds: " + index);
        }
    }

    public void dragAndDropAction(By element, int xOffset, int yOffset) {
        Actions actions = new Actions(webDriver);
        actions.dragAndDropBy(findElement(element), xOffset, yOffset).perform();
    }

    public void scrollAndClickElement(By element) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView();", findElement(element));
        clickElement(element);
    }

    public List<WebElement> findElements(By element) {
        return webDriver.findElements(element);
    }

    public boolean isElementDisplayed(By element) {
        return findElement(element).isDisplayed();
    }

}
