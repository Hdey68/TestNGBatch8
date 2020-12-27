package com.syntax.myTestingArea;

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

public class AnotherTestClass03Task {

    WebDriver driver;
   String username = "Admin";
   String password = "Hum@nhrm123";


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

    @Test(priority = 1)
    public void validAdminLogin ( ) {
        WebElement welcomeUsername = driver.findElement ( By.cssSelector ( "a#welcome" ) );

        SoftAssert softAssert = new SoftAssert ( );
        softAssert.assertTrue ( welcomeUsername.isDisplayed ( ), "Welcome with the username is not displayed" );
        softAssert.assertEquals ( welcomeUsername.getText ( ), "Welcome " + username, "Welcome with the username is not matching" );
        softAssert.assertAll ( );
    }

    @Test(priority = 1,dataProvider = "addEmployeeNames")

    public void employeeFirstAndLastName (String firstname, String lastname, String username, String password, String comfirmedPassword ) throws InterruptedException {


        driver.findElement ( By.xpath ( "//*[@id=\"menu_pim_viewPimModule\"]/b" ) ).click ( );
        Thread.sleep ( 2000 );
        driver.findElement ( By.id ( "menu_pim_addEmployee" ) ).click ( );

        driver.findElement ( By.xpath ( "//*[@id=\"firstName\"]" ) ).sendKeys ( firstname );
        driver.findElement ( By.xpath ( "//*[@id=\"lastName\"]" ) ).sendKeys ( lastname );


        WebElement addPhotoButton = driver.findElement ( By.xpath ( "//*[@id=\"photofile\"]" ) );
        Assert.assertTrue ( addPhotoButton.isDisplayed ( ), "Photograph button not provided" );
        driver.findElement ( By.id ( "chkLogin" ) ).click ( );
        Thread.sleep ( 2000 );
        driver.findElement ( By.id ( "user_name" ) ).sendKeys ( username );
        driver.findElement ( By.id ( "user_password" ) ).sendKeys ( password );
        driver.findElement ( By.id ( "re_password" ) ).sendKeys ( comfirmedPassword );
        Thread.sleep ( 2000 );


        driver.findElement ( By.xpath ( "//*[@id=\"btnSave\"]" ) ).click ( );

        WebElement employeeFirstAndLastname = driver.findElement ( By.xpath ( "//*[@id=\"profile-pic\"]/h1" ) );
        Thread.sleep ( 2000 );

        SoftAssert softAssert = new SoftAssert ( );
        softAssert.assertTrue ( employeeFirstAndLastname.isDisplayed ( ), "Employee not Added" );
        softAssert.assertEquals ( employeeFirstAndLastname.getText ( ), firstname + " " + lastname, "Employee First and lastname not matching" );
        softAssert.assertAll ( );
    }
    @Test(priority = 2,dataProvider="loginCredentials")
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
    public Object[][] addEmployeeNames ( ) {

        Object[][] data = new Object[5][5];

        data[0][0] = "Alimo";
        data[0][1] = "Gouba";
        data[0][2] = "AliGouba";
        data[0][3] = "GoubaAli@123";
        data[0][4] = "GoubaAli@123";

        data[1][0] = "salim";
        data[1][1] = "Dekkiche";
        data[1][2] = "salimDekkiche";
        data[1][3] = "SyntaxDekkiche@123";
        data[1][4] = "SyntaxDekkiche@123";

        data[2][0] = "Kamel";
        data[2][1] = "Nabou";
        data[2][2] = "KamelNabou";
        data[2][3] = "SyntaxNabou@123";
        data[2][4] = "SyntaxNabou@123";

        data[3][0] = "Nabil";
        data[3][1] = "Kawa";
        data[3][2] = "NabilKawa";
        data[3][3] = "SyntaxKawa@123";
        data[3][4] = "SyntaxKawa@123";

        data[4][0] = "Nabila";
        data[4][1] = "Salma";
        data[4][2] = "NabilaSalma";
        data[4][3] = "SyntaxSalma@123";
        data[4][4] = "SyntaxSalma@123";

        return data;
    }
    @DataProvider
    public Object[][] loginCredentials(){
        Object[][] data = new Object[5][2];
        data[0][0] = "AliGouba";
        data[0][1] = "GoubaAli@123";
        data[1][0] = "salimDekkiche";
        data[1][1] = "SyntaxDekkiche@123";
        data[2][0] = "KamelNabou";
        data[2][1] = "SyntaxNabou@123";
        data[3][0] = "NabilKawa";
        data[3][1] = "SyntaxKawa@123";
        data[4][0] = "NabilaSalma";
        data[4][1] = "SyntaxSalma@123";

        return data;

    }
    @AfterMethod(alwaysRun = true)
    public void closeBrowser ( ) {
        driver.quit ( );
    }
}