package com.woa.tests;

import com.woa.core.DBConnection;
import com.woa.core.TestBase;
import com.woa.pages.HomePage;
import com.woa.pages.SearchResultPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;
public class DBConnect extends TestBase {

  private static final Logger logger = Logger.getLogger(DBConnect.class);
    @DataProvider
    public Object[][] getDB() throws SQLException {
        DBConnection dbConnection = DBConnection.getInstance();
        dbConnection.getConnection();
        dbConnection.getStatement();
        List<String> data =  dbConnection.getListOfDataFromDB("SELECT * FROM classicmodels.test","user");
        Object[][] dataArray=new Object[data.size()][1];
        for (int i=0; i<data.size(); i++){
            dataArray[i][0]=data.get(i);
        }
        return dataArray;
    }

    @Test(dataProvider = "getDB",enabled = true)
    public void testingDBConnection(String data){
        HomePage homePage= PageFactory.initElements(driver,HomePage.class);
        SearchResultPage searchResultPage = PageFactory.initElements(driver, SearchResultPage.class);
        homePage.typeOnSearchBar(data);
        homePage.clickOnSearchButton();
        logger.info("whatever you want to see in the console and in the test.log.file");
        searchResultPage.validateSearchResultDisplayed();

    }

}
