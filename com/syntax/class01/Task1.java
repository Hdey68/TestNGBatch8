package com.syntax.class01;

import org.testng.annotations.*;

public class Task1 {

    @BeforeClass
    public void beforeTask1(){
        System.out.println ("Tested" );

    }
    @BeforeMethod
    public void beforeMethod1(){
        System.out.println ("worked" );
    }
    @Test
    public void test(){
        System.out.println ("Pass" );
    }
    @Test
    public void test2(){
        System.out.println ("Pass2" );
    }
    @AfterMethod
    public void afterMethod1(){
        System.out.println ("Worked1" );
    }
    @AfterClass
    public void afterClass1(){
        System.out.println ("Tested2" );

    }

}
