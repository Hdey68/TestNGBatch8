package com.syntax.class02;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.concurrent.TimeUnit;

public class HomeWork {

    /*
    TC 1: HRMS Add Employee:
Open chrome browser
Go to “http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login”
Login into the application
Click on Add Employee
Verify labels: Full Name, Employee Id, Photograph are displayed
Provide Employee First and Last Name
Add a picture to the profile
Verify Employee has been added successfully
Close the browser
     */

    WebDriver driver;
    String username = "Admin";
    String password = "Hum@nhrm123";
    String employeeFirstname="Sofiane";
    String employeeLastname="Ouniche";


    @BeforeMethod(alwaysRun = true)
    public void openBrowser ( ) {
        System.setProperty ( "webdriver.chrome.driver", "drivers\\chromedriver.exe" );
        driver = new ChromeDriver ( );
        driver.manage ( ).timeouts ( ).implicitlyWait ( 20, TimeUnit.SECONDS );
        driver.navigate ( ).to ( "http://hrmstest.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login" );
        driver.findElement ( By.id ( "txtUsername" ) ).sendKeys ( "Admin" );
        driver.findElement ( By.id ( "txtPassword" ) ).sendKeys ( "Hum@nhrm123" );
        driver.findElement ( By.id ( "btnLogin" ) ).click ( );
    }
        @Test(priority = 1,groups = "smoke")
        public void validAdminLogin ( ) {
            WebElement welcomeUsername = driver.findElement ( By.cssSelector ( "a#welcome" ) );

            SoftAssert softAssert = new SoftAssert ( );
            softAssert.assertTrue ( welcomeUsername.isDisplayed ( ), "Welcome with the username is not displayed" );
            softAssert.assertEquals ( welcomeUsername.getText ( ), "Welcome " + username, "Welcome with the username is not matching" );
            softAssert.assertAll ( );
        }
        @Test(priority =2,groups = "regression")
        public void addEmployee() throws InterruptedException {
        driver.findElement ( By.xpath ( "//*[@id=\"menu_pim_viewPimModule\"]/b" ) ).click ();
        Thread.sleep ( 2000 );
        driver.findElement ( By.id ( "menu_pim_addEmployee" ) ).click ();
        Thread.sleep ( 2000 );

        WebElement fullName=driver.findElement ( By.xpath ( "//*[@id=\"frmAddEmp\"]/fieldset/ol/li[1]/label" ) );
        SoftAssert softAssert=new SoftAssert ();
        softAssert.assertTrue ( fullName.isDisplayed ( ),"Full Name text  not displayed" );

        WebElement employeeId=driver.findElement ( By.xpath ( "//*[@id=\"frmAddEmp\"]/fieldset/ol/li[2]/label" ) );
        softAssert.assertTrue ( employeeId.isDisplayed (),"Employee Id text not displayed" );

        WebElement photograph=driver.findElement ( By.xpath ( "//*[@id=\"frmAddEmp\"]/fieldset/ol/li[3]/label[1]" ) );
        softAssert.assertTrue ( photograph.isDisplayed (),"Photograph text not displayed" );
        softAssert.assertAll ();
      }
        @Test(priority = 3,groups = "regression")
        public void employeeFirstAndLastName() throws InterruptedException {
            driver.findElement ( By.xpath ( "//*[@id=\"menu_pim_viewPimModule\"]/b" ) ).click ( );
            Thread.sleep ( 2000 );
            driver.findElement ( By.id ( "menu_pim_addEmployee" ) ).click ( );

            driver.findElement ( By.xpath ( "//*[@id=\"firstName\"]" ) ).sendKeys ( employeeFirstname );
            driver.findElement ( By.xpath ( "//*[@id=\"lastName\"]" ) ).sendKeys ( employeeLastname );

            WebElement addPhotoButton = driver.findElement ( By.xpath ( "//*[@id=\"photofile\"]" ) );
            Assert.assertTrue ( addPhotoButton.isDisplayed ( ), "Photograph button not provided" );

            driver.findElement ( By.xpath ( "//*[@id=\"btnSave\"]" ) ).click ( );

            WebElement employeeFirstAndLastname = driver.findElement ( By.xpath ( "//*[@id=\"profile-pic\"]/h1" ) );
            Thread.sleep ( 2000 );

            SoftAssert softAssert = new SoftAssert ( );
            softAssert.assertTrue ( employeeFirstAndLastname.isDisplayed ( ), "Employee not Added" );
            softAssert.assertEquals ( employeeFirstAndLastname.getText ( ), employeeFirstname+" "+ employeeLastname, "Employee First and lastname not matching" );
            System.out.println ( "End of all test cases" );
            softAssert.assertAll ( );
        }
    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
    }
        }



