package com.lambdatest.tests;

import com.lambdatest.pages.LoginLambdaTestPage;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginTestLambdaTest {
    LoginLambdaTestPage login;
    LoginLambdaTestPage home;
    Playwright playwright = Playwright.create();
    BrowserType firefox = playwright.firefox();
    Browser browser = firefox.launch(new BrowserType.LaunchOptions().setHeadless(false));
    Page page = browser.newPage();

    @BeforeTest
    public void setUp() {
        page.navigate("https://ecommerce-playground.lambdatest.io/index.php?route=account/login");
        //home = new HomePage(page);
        login = new LoginLambdaTestPage(page);
    }
    // Verify title before login
    @Test(priority = 1)
    public void verifyPageTitle() {
        String title = login.verifyTitle();
        Assert.assertEquals(title, "Account Login");
    }
    // Login into the application
    @Test(priority = 2)
    public void loginIntoTheApplication() {
        login.loginIntoApplication("lambdatestnew@yopmail.", "Lambda123");
        login.searchProduct();
        login.verifySearchProduct();
    }

    // Verify product name after login
    /*@Test(priority = 3)
    public void verifyProductsName() {
        String productName = home.productName();
        Assert.assertEquals(productName, "Sauce Labs Backpack");

    }

    // Logout from application
    @Test(priority = 4)
    public void logoutFromApplication() {
        login.logoutApplication();

    }*/

    // Close the browser
    @AfterTest
    public void closeBrowser() {
        browser.close();
    }
}

