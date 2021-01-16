package com.syntax.class03;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class ClassTask {
    /*
    TC 1: HRMS Add Employee:

Open chrome browser
Go to HRMS
Login into the application
Add 5 different Employees and create login credentials by providing:
First Name
Last Name
Username
Password
Verify Employee has been added successfully and take screenshot (you must have 5 different screenshots)
Close the browser
Specify group for this test case, add it into specific suite and execute from xml file.
     */
    WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void openAndNavigate ( ) {
        System.setProperty ( "webdriver.chrome.driver", "drivers/chromedriver.exe" );
        driver = new ChromeDriver ( );
        driver.get ( "http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login" );
        driver.manage ( ).window ( ).maximize ( );
        driver.manage ( ).timeouts ( ).implicitlyWait ( 15, TimeUnit.SECONDS );
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser ( ) {
        driver.quit ( );
    }
    @Test(dataProvider="loginCredientialData")
    public void login(String username,String password) throws InterruptedException {

        driver.findElement ( By.id ( "txtUsername" ) ).sendKeys ( username );
        driver.findElement ( By.id ( "txtPassword" ) ).sendKeys ( password );
        driver.findElement ( By.id ( "btnLogin" ) ).click ( );
        Thread.sleep ( 2000 );
        WebElement welcomeUsername = driver.findElement ( By.cssSelector ( "a#welcome" ) );
        SoftAssert softAssert = new SoftAssert ( );
        softAssert.assertTrue ( welcomeUsername.isDisplayed ( ), "Welcome with the username is not displayed" );
        softAssert.assertEquals ( welcomeUsername.getText ( ), "Welcome " + username, "Welcome with the username is not matching" );
        softAssert.assertAll ( );
    }
    @DataProvider
public Object[][] loginCredientialData() {

        Object[][] data = new Object[6][2];
        data[0][0] = "Admin";
        data[0][1] = "Hum@nhrm123";
        data[1][0] = "sofiane";
        data[1][1] = "Syntax@123";
        data[2][0] = "salim";
        data[2][1] = "Syntax!123";
        data[3][0] = "Kamel";
        data[3][1] = "Syntax#123";
        data[4][0] = "Nabil";
        data[4][1] = "Syntax$123";
        data[5][0] = "Nabila";
        data[5][1] = "Syntax@1234";
        return data;
    }
}