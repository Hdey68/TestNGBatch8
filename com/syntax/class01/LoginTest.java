package com.syntax.class01;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {



        WebDriver driver;

        @BeforeMethod(alwaysRun = true)
        public void openAndNavigate () {
            System.setProperty ( "webdriver.chrome.driver","drivers\\chromedriver.exe" );
            driver = new ChromeDriver ( );
            driver.get ( "http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login" );
            driver.manage ( ).window ( ).maximize ( );
            driver.manage ( ).timeouts ( ).implicitlyWait ( 15, TimeUnit.SECONDS );
        }

        @Test(groups = "smoke")
        public void validAdminLogin () {
            driver.findElement ( By.id ( "txtUsername" ) ).sendKeys ( "Admin" );
            driver.findElement ( By.id ( "txtPassword" ) ).sendKeys ( "Hum@nhrm123" );
            driver.findElement ( By.id ( "btnLogin" ) ).click ( );
            WebElement welcomeMessage=driver.findElement ( By.cssSelector ( "a#welcome" ) );

            if (welcomeMessage.isDisplayed ( )){
                System.out.println ("Test pass" );
            }else{
                System.out.println ("Test fail" );
            }

        }
        @Test(groups = "regression")
        public void titleValidation(){
            String expectedTitle="Human Management System";
            String actualTitle=driver.getTitle ();
            if(expectedTitle.equals ( actualTitle )){
                System.out.println ("Title is valid. Test passed ");
            }else{
                System.out.println ("Title not valid" );
            }
        }
        @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
            driver.quit ();
        }
    }

