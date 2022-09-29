package com.woa.practice;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

public class Practice2 extends TestBase{

    @Test
    public void testAmazon()throws InterruptedException{
        openBrowser("https://amazon.com");
        waitFor(2);
        quitBrowser();
    }
    @Test
    public void testEbay() throws InterruptedException{
        openBrowser("https://ebay.com");
        waitFor(2);
        chromeDriver.findElement(By.linkText("register")).click();
        waitFor(5);
        quitBrowser();
    }
@Test
    public void textEbaySearch() throws InterruptedException{
        openBrowser("https://ebay.com");
        chromeDriver.findElement(By.id("gh-ac")).sendKeys("Java Books");
        waitFor(1);
        chromeDriver.findElement(By.id("gh-btn")).click();
        waitFor(5);
        quitBrowser();
    }

@Test
    public void textXPath() throws InterruptedException {
        openBrowser("https://ebay.com");
        waitFor(2);
        chromeDriver.findElement(By.xpath("//a[@href='https://signin.ebay.com/ws/eBayISAPI.dll?SignIn&ru=https%3A%2F%2Fwww.ebay.com%2F']")).click();
        quitBrowser();

    }

    @Test
    public void clickHelp() throws InterruptedException {
        openBrowser("https://ebay.com");
        chromeDriver.findElement(By.xpath("(//a[@class='gh-p'])[1]")).click();
        waitFor(5);
        quitBrowser();
    }

  @Test
    public void listOfXPath() throws InterruptedException{
        openBrowser("https://ebay.com");
      List<WebElement> elementList= chromeDriver.findElements(By.xpath("//a[@class='gh-p']"));
      System.out.println(elementList.size());
      WebElement[] list=new WebElement[elementList.size()];
      for (WebElement name: elementList){
          System.out.println(name.getText());
          name.click();

         // chromeDriver.navigate().back();
      }


  }
@Test
  public void listAllDeals() throws InterruptedException{
        openBrowser("https://www.ebay.com");
      List<WebElement> elementList1= chromeDriver.findElements(By.xpath("//a[@class='gh-p']"));
        for (int i=0;i<elementList1.size();i++){
            elementList1=chromeDriver.findElements(By.xpath("//a[@class='gh-p']"));
           elementList1.get(i).click();
           chromeDriver.navigate().back();
        }

  }

}

