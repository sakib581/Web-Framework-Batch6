package com.woa.practice;

import org.openqa.selenium.chrome.ChromeDriver;

public class TestBase {

    ChromeDriver chromeDriver;

    public void openBrowser(String url){
        System.setProperty("webdriver.chrome.driver","src/main/resources/chromedriver");
        chromeDriver=new ChromeDriver();
        chromeDriver.get(url);
    }

    public void waitFor(int seconds) throws InterruptedException{
        Thread.sleep(seconds* 1000L);
    }

    public void quitBrowser(){
        chromeDriver.quit();
    }
}
