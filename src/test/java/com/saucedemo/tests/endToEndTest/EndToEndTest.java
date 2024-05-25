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

import java.util.ArrayList;
import java.util.List;

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
        Driver.get().get(ConfigReader.get("url")); // 2

        extentLogger.info("Login with valid credentials");
        loginPage.login(); // 3

        extentLogger.info("Verif that products page is Displayed");
        String actualTitle = productPage.getPageTitle("Products");
        String expectedTitle = "Products";
        Assert.assertEquals(actualTitle,expectedTitle);

        extentLogger.info("Sort the products high to  low");
        productPage.productSorting();// 4

        extentLogger.info("Add to cart at least two item");
        productPage.addtoCartWithName("Sauce Labs Backpack");
        productPage.addtoCartWithName("Sauce Labs Bolt T-Shirt");

        extentLogger.info("Assert that the basket shows the true number of product"); //5
        productPage.verifyTrueNumberOfProduct(2);
        System.out.println("ProductPage.productCounter = " + ProductPage.productCounter);

        extentLogger.info("Navigate to Your cart page");
        yourCartPage.basketBox.click();  // 7

        extentLogger.info("Verify that Your Cart page is Displayed");

        String actualTitle2 = productPage.getPageTitle("Your Cart");
        String expectedTitle2 = "Your Cart";
        Assert.assertEquals(actualTitle2,expectedTitle2);

        extentLogger.info("Assert that the right items added to cart"); // 8
        productPage.verifyProductName("Sauce Labs Backpack");
        productPage.verifyProductName("Sauce Labs Bolt T-Shirt");
        /*List<String> expectedProducts =new ArrayList<>();
        expectedProducts.add("Sauce Labs Backpack");
        expectedProducts.add("Sauce Labs Bolt T-Shirt");
        List<String> actualProducts = BrowserUtils.getElementsText(yourCartPage.cartProducts);
        Assert.assertEquals(actualProducts,expectedProducts);*/


        extentLogger.info("Go to checkout page and fill the form");
        yourCartPage.checkoutBox.click(); // 9
        yourInformationPage.saveForm(); // 9

        extentLogger.info("Click continue button and go to the checkout overview page");
        checkoutOverviewPage.finishBox.click(); // 10


        extentLogger.info("Click finish and assert the success shopping message");
        checkoutCompletePage.verifyMessageText(); // 11

        extentLogger.pass("Pass!");




    }
    /*
    1-  Create a project from scratch. Design all packages, classes and ohter files...
    2- Navigate to https://www.saucedemo.com/
    3-Login with valid credentials (shown on the page)
    4-Sort the products high to  low
    5- Add to cart at least two item
    6- Assert that the basket shows the true number of product
    7-Navigate to Your cart page
    8-Assert that the right items added to cart
    9-go to checkout page and fill the form
    10-click continue button and go to the checkout overview page
    11-Click finish and assert the success shopping message

    notes:     a. every new page should be asserted by page title
           b.   use parameterized methods.
         c. This is an e2e test so u can create one test method for this task called success shopping  test
    good luck.

     */
}
