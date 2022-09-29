package com.woa.pages;

import com.woa.core.ExtentTestManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage {

    // WebElement element= driver.findElement(findby.
@FindBy(id="gh-ac")
    private WebElement searchBar;

@FindBy(id="gh-btn")
    private WebElement searchButton;

public void typeOnSearchBar(String data){
    searchBar.sendKeys(data);
    ExtentTestManager.info("Type on Search Bar: " + data);
}

public void clickOnSearchButton(){
    searchButton.click();
    ExtentTestManager.info("Click on Search Button");
}

//    public void typeSearchBar(String data) {
    }

