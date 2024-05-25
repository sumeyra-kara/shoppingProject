package com.saucedemo.pages;

import com.saucedemo.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;

public class ProductPage extends BasePage {
    public static int productCounter;

    public void productSorting(){
        WebElement dropDownMenu = Driver.get().findElement(By.xpath("//select[@class='product_sort_container']"));
        Select select = new Select(dropDownMenu);
        select.selectByVisibleText("Price (high to low)");

    }

    public void verifyProductName (String name){
        WebElement product = Driver.get().findElement(By.xpath("//div[text()='"+name+"']"));
        String expectedName = name;
        String actualName=product.getText();
        Assert.assertEquals(actualName,expectedName);
    }

    public void addtoCartWithName(String name){
        WebElement product = Driver.get().findElement(By.xpath("//div[text()='" + name + "']"));
        product.click();
        Driver.get().findElement(By.id("add-to-cart")).click();
        Driver.get().navigate().back();
        productCounter++;

    }

    public void verifyTrueNumberOfProduct(int productSayisi){
        WebElement productNumber = Driver.get().findElement(By.xpath("//span[text()='"+productSayisi+"']"));
        String actualText = productNumber.getText();
        String expectedText= String.valueOf(productSayisi);
        Assert.assertEquals(actualText,expectedText);

    }




















}
