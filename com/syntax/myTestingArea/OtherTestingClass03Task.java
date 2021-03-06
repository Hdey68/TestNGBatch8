package com.syntax.myTestingArea;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class OtherTestingClass03Task {

    WebDriver driver;
    String username = "Admin";

    @BeforeMethod(alwaysRun = true)

    public void openAndNavigate ( ) {
        System.setProperty ( "webdriver.chrome.driver", "drivers/chromedriver.exe" );
        driver = new ChromeDriver ( );
        driver.manage ( ).timeouts ( ).implicitlyWait ( 20, TimeUnit.SECONDS );
        driver.navigate ( ).to ( "http://hrm.syntaxtechs.net/humanresources/symfony/web/index.php/auth/login" );
        driver.manage ( ).window ( ).maximize ( );
    }

    @AfterMethod(alwaysRun = true)
    public void quitBrowser ( ) {
        // driver.quit ( );
    }

    @Test(priority = 1)
    public void adminLogin ( ) throws InterruptedException {
        driver.findElement ( By.id ( "txtUsername" ) ).sendKeys ( "Admin" );
        driver.findElement ( By.id ( "txtPassword" ) ).sendKeys ( "Hum@nhrm123" );
        driver.findElement ( By.id ( "btnLogin" ) ).click ( );
        Thread.sleep ( 2000 );
        // WebElement welcomeUsername = driver.findElement ( By.cssSelector ( "a#welcome" ) );
        // SoftAssert softAssert = new SoftAssert ( );
        // softAssert.assertTrue ( welcomeUsername.isDisplayed ( ), "Welcome with the username is not displayed" );
        // softAssert.assertEquals ( welcomeUsername.getText ( ), "Welcome " + username, "Welcome with the username is not matching" );
        // softAssert.assertAll ( );
    }

    @Test(priority = 2, dataProvider = " addEmployeeNames")
    public void addEmployee (String firstname, String lastname, String username, String password, String comfirmedPassword) throws InterruptedException {
        driver.findElement ( By.xpath ( "//*[@id=\"menu_pim_viewPimModule\"]/b" ) ).click ( );
        Thread.sleep ( 2000 );
        driver.findElement ( By.id ( "menu_pim_addEmployee" ) ).click ( );
        Thread.sleep ( 2000 );

        driver.findElement ( By.xpath ( "//*[@id=\"firstName\"]" ) ).sendKeys ( firstname );
        driver.findElement ( By.xpath ( "//*[@id=\"lastName\"]" ) ).sendKeys ( lastname );
        driver.findElement ( By.id ( "chkLogin" ) ).click ( );
        Thread.sleep ( 2000 );
        driver.findElement ( By.id ( "user_name" ) ).sendKeys ( username );
        driver.findElement ( By.id ( "user_password" ) ).sendKeys ( password );
        driver.findElement ( By.id ( "re_password" ) ).sendKeys ( comfirmedPassword );
        Thread.sleep ( 2000 );

        driver.findElement ( By.xpath ( "//*[@id=\"btnSave\"]" ) ).click ( );

       // WebElement employeeFirstAndLastname = driver.findElement ( By.xpath ( "//*[@id=\"profile-pic\"]/h1" ) );
      //  Thread.sleep ( 2000 );

      //  SoftAssert softAssert = new SoftAssert ( );
      //  softAssert.assertTrue ( employeeFirstAndLastname.isDisplayed ( ), "Employee not Added" );
      //  softAssert.assertEquals ( employeeFirstAndLastname.getText ( ), firstname + " " + lastname, "Employee First and lastname not matching" );
    //    System.out.println ( "End of all test cases" );
     //   softAssert.assertAll ( );
    }

    @DataProvider
    public Object[][] addEmployeeNames ( ) {

        Object[][] data = new Object[5][5];

        data[0][0] = "alia";
        data[0][1] = "Goubaa";
        data[0][2] = "aliGoubaa";
        data[0][3] = "Gouba@123a";
        data[0][4] = "Gouba@123a";

        data[1][0] = "salima";
        data[1][1] = "Dekkichea";
        data[1][2] = "salimDekkichea";
        data[1][3] = "Dekkiche@123a";
        data[1][4] = "Dekkiche@123a";

        data[2][0] = "Kamela";
        data[2][1] = "Naboua";
        data[2][2] = "KamelNaboua";
        data[2][3] = "Nabou@123a";
        data[2][4] = "Nabou@123a";

        data[3][0] = "Nabila";
        data[3][1] = "Kawaa";
        data[3][2] = "NabilKawaa";
        data[3][3] = "Kawa@123a";
        data[3][4] = "Kawa@123a";

        data[4][0] = "Nabilaa";
        data[4][1] = "Salmaa";
        data[4][2] = "NabilaSalmaa";
        data[4][3] = "Salma@123a";
        data[4][4] = "Salma@123a";

        return data;
    }

   /* @Test(priority =3, dataProvider="addEmployeeNames")
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


}*/
}