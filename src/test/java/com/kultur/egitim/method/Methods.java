package com.kultur.egitim.method;

import com.kultur.egitim.driver.Driver;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;

public class Methods {

    WebDriver driver;
    FluentWait<WebDriver> fluentWait;
    long pollingEvery = 300;
    long timeout = 20;
    Actions actions;
    JavascriptExecutor js;

    public Methods(){
        driver = Driver.driver;
        fluentWait = getWait(pollingEvery,timeout);
        actions = new Actions(driver);
        js = (JavascriptExecutor) driver;
    }

    public FluentWait<WebDriver> getWait(long pollingEvery,long timeout){
        return new FluentWait<>(driver)
                .pollingEvery(Duration.ofMillis(pollingEvery))
                .withTimeout(Duration.ofSeconds(timeout))
                .ignoring(NoSuchElementException.class);
    }

    public WebElement findElement(By by){
        return fluentWait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public List<WebElement> findElements(By by){
        return driver.findElements(by);
    }

    public void click(By by){
        findElement(by).click();
    }

    public void clickWithStaleElement(By by){
        try {
            findElement(by).click();
        }catch (StaleElementReferenceException e){
            System.out.println(e.getMessage());
            isElementClickable(by);
            findElement(by).click();
        }
    }

    public void sendKeys(By by, String value){
        findElement(by).sendKeys(value);
    }

    public void clear(By by){
        findElement(by).clear();
    }

    public String getAttribute(By by, String attributeName){
        return findElement(by).getAttribute(attributeName);
    }

    public String getText(By by){
        return findElement(by).getText();
    }

    public boolean isElementVisible(By by){
        try {
            fluentWait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isElementClickable(By by){
        try {
            fluentWait.until(ExpectedConditions.elementToBeClickable(by));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isElementVisible(By by, long timeout){
        try {
            isElementVisible(by,pollingEvery,timeout);
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean isElementVisible(By by, long pollingEvery, long timeout){
        try {
            getWait(pollingEvery, timeout).until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void hoverElement(WebElement webElement) {
        actions.moveToElement(webElement).build().perform();
    }

    public void scrollElement(WebElement webElement){

        js.executeScript("arguments[0].scrollIntoView();", webElement);
    }

    public void scrollElementCenter(WebElement webElement){

        js.executeScript(
                "arguments[0].scrollIntoView({behavior: 'smooth', block: 'center', inline: 'center'});",
                webElement);
    }

    // Methods.java içine ekle
    public void switchToNewTab() {
        for (String winHandle : driver.getWindowHandles()) {
            driver.switchTo().window(winHandle);
        }
    }

    public void clickWithJs(By by) {
        js.executeScript("arguments[0].click();", findElement(by));
    }


    public static void wait(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
