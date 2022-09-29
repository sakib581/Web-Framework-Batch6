package com.woa.tests;

import com.woa.core.DBConnection;
import com.woa.core.TestBase;
import com.woa.pages.HomePage;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.SQLException;
import java.util.List;

public class NewTests extends TestBase {

    @Test
    public void validateUserCanSearch() {
        HomePage homePage = PageFactory.initElements(driver, HomePage.class);
        homePage.typeOnSearchBar("Java Books");
        homePage.clickOnSearchButton();
    }
  /*  @DataProvider()
        public Object[][] getDB() throws SQLException{
        DBConnection dbConnection=DBConnection.getInstance();
        dbConnection.getConnection();
        dbConnection.getStatement();
        List<String> data= dbConnection.getListOfDataFromDB("Select * From classicmodels.test","user");
        for (int i=0;i< data.size();i++){
            return new Object[][]{{"user"+data},{"user"+data}};
        }
    return getDB();
    }*/

}
