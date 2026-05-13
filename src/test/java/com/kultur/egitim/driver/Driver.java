package com.kultur.egitim.driver;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.List;

public class Driver {

    public static WebDriver driver;

    @BeforeAll
    public static void beforeAll() {

    }

    @BeforeEach
    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--incognito");
        options.addArguments("disable-translate");
        options.addArguments("disable-plugins");
        options.addArguments("--disable-notifications");
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-gpu");
        options.addArguments("disable-infobars");
        options.addArguments("--disable-popup-blocking");
        options.setExperimentalOption("excludeSwitches", List.of("enable-automation"));
        options.setPageLoadStrategy(PageLoadStrategy.NONE);
        //options.addArguments("--headless=new");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get("https://www.trendyol.com/");
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
    }

    @AfterAll
    public static void afterAll() {

    }
}
