package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class CheckoutCompletePage extends BasePage{
    @FindBy(tagName = "h2")
    public WebElement message;

    public void verifyMessageText(){
        String actualMessage = message.getText();
        String expectedMessage ="Thank you for your order!";
        Assert.assertEquals(actualMessage,expectedMessage);
    }
}
