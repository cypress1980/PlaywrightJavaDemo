package com.saucedemo.tests;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.saucedemo.pages.HomePage;
import com.saucedemo.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTest {

    LoginPage login;
    HomePage home;
    Playwright playwright = Playwright.create();
    BrowserType firefox = playwright.firefox();
    Browser browser = firefox.launch(new BrowserType.LaunchOptions().setHeadless(false));
    Page page = browser.newPage();

    @BeforeTest
    public void setUp() {
        page.navigate("https://www.saucedemo.com/");
        home = new HomePage(page);
        login = new LoginPage(page);
    }

    // Verify title before login
    @Test(priority = 1)
    public void verifyPageTitle() {
        String title = login.verifyTitle();
        Assert.assertEquals(title, "Swag Labs");

    }

    // Login into the application
    @Test(priority = 2)
    public void loginIntoTheApplication() {
        login.loginIntoApplication("standard_user", "secret_sauce");
    }

    // Verify product name after login
    @Test(priority = 3)
    public void verifyProductsName() {
        String productName = home.productName();
        Assert.assertEquals(productName, "Sauce Labs Backpack");

    }

    // Logout from application
    @Test(priority = 4)
    public void logoutFromApplication() {
        login.logoutApplication();

    }

    // Close the browser
    @AfterTest
    public void closeBrowser() {
        browser.close();
    }
}

