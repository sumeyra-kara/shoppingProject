package com.saucedemo.pages;

import com.saucedemo.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public abstract class BasePage {
    public BasePage(){
        PageFactory.initElements(Driver.get(),this);
    }

    @FindBy(id = "shopping_cart_container")
    public WebElement basketBox;

    public String getPageTitle(String pageName){
        return Driver.get().findElement(By.xpath("//span[text()='"+pageName+"']")).getText();

    }


}
