package com.woa.practice;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class Practice1 {

    @Test
    public  void testEbay() throws InterruptedException {
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        ChromeDriver chromeDriver=new ChromeDriver();
        chromeDriver.get("https://ebay.com");
        Thread.sleep(5000);
        chromeDriver.quit();

    }
    @Test
    public void testAmazon() throws InterruptedException{
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        ChromeDriver chromeDriver=new ChromeDriver();
        chromeDriver.get("https://amazon.com");
        Thread.sleep(5000);
        chromeDriver.quit();
    }


}
