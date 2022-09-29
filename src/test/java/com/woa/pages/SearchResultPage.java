package com.woa.pages;

import com.woa.core.ExtentTestManager;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

public class SearchResultPage {

 pri
    @FindBy(xpath = "//h1[@class='srp-controls__count-heading']")
    private WebElement searchResultCount;
    public void validateSearchResultDisplayed(){
        Assert.assertTrue(searchResultCount.isDisplayed());
        ExtentTestManager.info("searchResultCount is Displayed "+ searchResultCount.getText());
    }


}
