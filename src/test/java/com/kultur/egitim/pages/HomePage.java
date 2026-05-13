package com.kultur.egitim.pages;

import com.kultur.egitim.method.Methods;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.List;

public class HomePage {

    Methods methods;

    By cookieInfoBy = By.id("onetrust-pc-btn-handler");
    By cookieRejectionBy = By.id("onetrust-reject-all-handler");
    By cookieAcceptBy = By.id("onetrust-accept-btn-handler");

    By closeGenderBy = By.cssSelector("div.modal-section.gender-modal-section .modal-section-close");
    By genderBy = By.cssSelector("div[data-testid=\"gender-card\"] .modal-action-button");
    By femaleGenderBy = By.xpath("//div[@data-testid=\"gender-card\"]//div[@class=\"modal-action-button\"][text()=\"Kadın\"]");

    By searchButton = By.cssSelector("button[data-testid=\"suggestion-placeholder\"]");
    By searchInput = By.cssSelector("input[data-testid=\"browsing-search-input\"]");

    public HomePage(){
        methods = new Methods();
    }

    public HomePage verifyHomePage(){
        Assertions.assertTrue(methods.isElementVisible(searchButton),"Search button is not visible");
        return this;
    }

    public HomePage verifyCookie(){
        Assertions.assertTrue(methods.isElementVisible(cookieInfoBy));
        Assertions.assertTrue(methods.isElementVisible(cookieRejectionBy));
        Assertions.assertTrue(methods.isElementVisible(cookieAcceptBy));
        return this;
    }

    public void clickAcceptCookieButton(){
        methods.click(cookieAcceptBy);
    }

    public void clickRejectCookieButton(){
        Assertions.assertTrue(methods.isElementVisible(cookieRejectionBy));
        methods.click(cookieRejectionBy);
    }

    public void verifyGender(){
        Assertions.assertTrue(methods.isElementVisible(closeGenderBy));
        Assertions.assertTrue(methods.isElementVisible(genderBy));
    }

    public void clickRandomGenderButton(){
        List<WebElement> genderList = methods.findElements(genderBy);

        methods.click(closeGenderBy);
    }

    public void clickFemaleGenderButton(){
        Assertions.assertTrue(methods.isElementVisible(femaleGenderBy));
       methods.click(femaleGenderBy);
    }

    public void clickSearchButton(){
        methods.click(searchButton);
    }

    public ProductPage doSearch(String text) {
        methods.sendKeys(searchInput, text);
        methods.sendKeys(searchInput, String.valueOf(Keys.ENTER)); // Enter'a basar
        return new ProductPage(); // Bizi yeni sayfaya götürür
    }

    public void inputSearch(String text){
        Assertions.assertTrue(methods.isElementVisible(searchInput), "Search input is not visible");
        methods.sendKeys(searchInput, text);
        Assertions.assertTrue(methods.isElementVisible(By.cssSelector(".personalized-suggestions-container")));
    }

    /**
     css

     tag[attribute='attributeValue'] > tag[attribute='attributeValue']

     a[data-testid='navigation-menu-user']

     #idValue

     * içeriyor mu
     a[data-testid*='navigation-menu']

     ^ başlıyor mu
     a[data-testid^='navigation-menu']

     $ sonlanıyor mu
     a[data-testid$='menu-user']

     tag[attribute='attributeValue'][attribute2='attributeValue2']

     a.navigation-menu-user

     .navigation.menu.user

     a[class='navigation menu user']

     xpath

     //tag[@attribute="attributeValue"]

     //tag[text()="textValue"]

     //tag[@attribute="attributeValue" and @attribute2="attributeValue2"]
     //tag[@attribute="attributeValue"][@attribute2="attributeValue2"]
     */
}
