package com.saucedemo.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class YourCartPage extends BasePage{


    @FindBy(id = "checkout")
    public WebElement checkoutBox;

    @FindBy(css = ".inventory_item_name")
    public List<WebElement> cartProducts;





}
