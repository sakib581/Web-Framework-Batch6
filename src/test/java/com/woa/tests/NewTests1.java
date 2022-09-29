package com.woa.tests;

import com.woa.core.TestBase;
import com.woa.pages.HomePage;
import com.woa.pages.SearchResultPage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class NewTests1 extends TestBase {

    @DataProvider
    public Object[][] getData(){
        return new Object[][]{{"Java Books"},{"Selenium Books"}};
    }

    @Test(dataProvider = "getData",enabled = true)
    public void validateAUserCanSearch(String data) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        SearchResultPage searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        homePage.typeOnSearchBar(data);
        homePage.clickOnSearchButton();
        searchResultPage.validateSearchResultDisplayed();


}}