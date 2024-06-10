package com.saucedemo.tests.endToEndTest;

import com.saucedemo.pages.*;
import com.saucedemo.tests.TestBase;
import com.saucedemo.utilities.BrowserUtils;
import com.saucedemo.utilities.ConfigReader;
import com.saucedemo.utilities.Driver;
import org.apache.commons.collections4.list.TreeList;
import org.testng.Assert;
import org.testng.TestRunner;
import org.testng.annotations.Test;

public class EndToEndTest extends TestBase {
    LoginPage loginPage = new LoginPage();
    ProductPage productPage = new ProductPage();
    YourCartPage yourCartPage = new YourCartPage();
    YourInformationPage yourInformationPage = new YourInformationPage();
    CheckoutOverviewPage checkoutOverviewPage=new CheckoutOverviewPage();
    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage();

    @Test
    public void verifyShoppingTest() {

        extentLogger = report.createTest("Shopping Test");
        extentLogger.info("Navigate to "+ConfigReader.get("url"));
        Driver.get().get(ConfigReader.get("url"));

        extentLogger.info("Login with valid credentials");
        loginPage.login();

        extentLogger.info("Verify that products page is Displayed");
        String actualTitle = productPage.getPageTitle("Products");
        String expectedTitle = "Products";
        Assert.assertEquals(actualTitle,expectedTitle);

        extentLogger.info("Sort the products high to  low");
        productPage.productSorting();

        extentLogger.info("Add to cart at least two item");
        productPage.addtoCartWithName("Sauce Labs Backpack");
        productPage.addtoCartWithName("Sauce Labs Bolt T-Shirt");

        extentLogger.info("Assert that the basket shows the true number of product");
        productPage.verifyTrueNumberOfProduct(2);
        System.out.println("ProductPage.productCounter = " + ProductPage.productCounter);

        extentLogger.info("Navigate to Your cart page");
        yourCartPage.basketBox.click();

        extentLogger.info("Verify that Your Cart page is Displayed");
        String actualTitle2 = productPage.getPageTitle("Your Cart");
        String expectedTitle2 = "Your Cart";
        Assert.assertEquals(actualTitle2,expectedTitle2);

        extentLogger.info("Assert that the right items added to cart");
        productPage.verifyProductName("Sauce Labs Backpack");
        productPage.verifyProductName("Sauce Labs Bolt T-Shirt");

        extentLogger.info("Go to checkout page and fill the form");
        yourCartPage.checkoutBox.click();
        yourInformationPage.saveForm();

        extentLogger.info("Click continue button and go to the checkout overview page");
        checkoutOverviewPage.finishBox.click();

        extentLogger.info("Click finish and assert the success shopping message");
        checkoutCompletePage.verifyMessageText();

        extentLogger.pass("Pass!");


    }

}
