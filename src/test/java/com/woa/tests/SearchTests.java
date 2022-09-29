package com.woa.tests;

import com.woa.core.TestBase;
import com.woa.pages.HomePage;
import com.woa.pages.SearchResultPage;
import org.omg.CORBA.Object;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class SearchTests extends TestBase {

  //  public java.lang.Object[][] getData(){
//        return new java.lang.Object[][]{{"Java Books"},{"Selenium Books"}};
   // }

    @Test(dataProvider = "getData",enabled = false)
    public void validateAUserCanSearch(String data) {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        SearchResultPage searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        homePage.typeOnSearchBar(data);
        homePage.clickOnSearchButton();
        searchResultPage.validateSearchResultDisplayed();
    }


  //  @DataProvider
   // public java.lang.Object[][] getMultipleData() {
      //  return new Object[][]{{"user001", "testpass1"}, {"user002", "testpass2"}};
   // }

    @Test(dataProvider = "getMultipleData",enabled = false)
    public void validateAUserCanLogin(String username, String password) {

        System.out.println(username + password);
    }



    @Test(enabled = false)
    public void validateUserCanSearch(){
        HomePage homePage= PageFactory.initElements(driver,HomePage.class);
        homePage.typeOnSearchBar("Java Books");
        homePage.clickOnSearchButton();


    }
 @Test
    public void validateUserCanSearch2(){
    HomePage homePage= PageFactory.initElements(driver,HomePage.class);
    SearchResultPage searchResultPage= PageFactory.initElements(driver, SearchResultPage.class);
    homePage.typeOnSearchBar("Java Books");
    homePage.clickOnSearchButton();
    searchResultPage.validateSearchResultDisplayed();

    }

}



