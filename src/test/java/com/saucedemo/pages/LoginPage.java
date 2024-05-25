package com.saucedemo.pages;

import com.saucedemo.utilities.ConfigReader;
import com.saucedemo.utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends BasePage {

    @FindBy(id = "user-name")
    public WebElement usernameBox;

    @FindBy(id = "password")
    public WebElement passwordBox;

    @FindBy(id = "login-button")
    public WebElement loginBox;

    public void login(String username,String password){
        usernameBox.sendKeys(username);
        passwordBox.sendKeys(password);
        loginBox.click();
    }
    public void login(){
        usernameBox.sendKeys(ConfigReader.get("username"));
        passwordBox.sendKeys(ConfigReader.get("password"));
        loginBox.click();
    }


}
