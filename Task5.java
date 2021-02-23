package Selenium

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

    /*
     Navigate to "https://demos.telerik.com/kendo-ui/dragdrop/area"
     Click and hold the blue circle
     Move the circle left side of it
     Validate blue and orange box are displayed (Drop here) text
    */

public class Task5 {

    WebDriver driver;
    Actions actions;
    SoftAssert softAssert;
    JavascriptExecutor js;

    @BeforeMethod
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        actions = new Actions(driver);
        softAssert = new SoftAssert();
        js = (JavascriptExecutor) driver;



    }

    @Test
    public void task1(){

        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/area");
        js.executeScript("window.scrollBy(0,300)");
        WebElement circle = driver.findElement(By.id("draggable"));
        actions.clickAndHold(circle).moveByOffset(-200,0).perform();

        WebElement text1 = driver.findElement(By.xpath("//div[@class='test1']"));
        String blueBox = text1.getText();
        String expectedText = "(Drop here)";
        softAssert.assertEquals(blueBox,expectedText);
        System.out.println(blueBox);
        System.out.println(expectedText);

        boolean isDisplayed = text1.isDisplayed();
        softAssert.assertTrue(isDisplayed);
        System.out.println(isDisplayed);

        WebElement text2 = driver.findElement(By.xpath("//div[@class='test2']"));
        softAssert.assertEquals(text2.getText(), "(Drop here)");

        boolean isdisplayed2 = text2.isDisplayed();
        softAssert.assertTrue(isdisplayed2);
        System.out.println(isdisplayed2);

    }



        /*
          Navigate to "https://demos.telerik.com/kendo-ui/dragdrop/area"
          Click and hold the blue circle
          Move the circle on top of Blue box
          Validate "Now you can drop it." text is displayed
          Validate Orange box has (Drop here) text
         */

        
    @Test

    public void test2(){



        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/area");
        js.executeScript("window.scrollBy(0,300)");
        WebElement circle = driver.findElement(By.id("draggable"));
        actions.clickAndHold(circle).moveByOffset(-50,100).perform();

        WebElement text1 = driver.findElement(By.xpath("//div[@class='test1']"));
        softAssert.assertEquals(text1.getText(),"Now you can drop it");
        boolean isDisplayed = text1.isDisplayed();
        softAssert.assertTrue(isDisplayed);
        System.out.println(isDisplayed);

        WebElement text2 = driver.findElement(By.xpath("//div[@class='test2']"));
        softAssert.assertEquals(text2,"(Drop here)");
        boolean isdisplayed2 = text2.isDisplayed();
        softAssert.assertTrue(isdisplayed2);
        System.out.println(isdisplayed2);

    }

    @Test
    public void test3(){
        /*
        Navigate to "https://demos.telerik.com/kendo-ui/dragdrop/area"
        Click and hold the blue circle
        Move the circle on top of Orange box
        Validate "Now you can drop it." text is displayed in orange box
        Validate Blue box has (Drop here) text
         */

        driver.get("https://demos.telerik.com/kendo-ui/dragdrop/area");
        js.executeScript("window.scrollBy(0,300)");
        WebElement circle = driver.findElement(By.id("draggable"));
        actions.clickAndHold(circle).moveByOffset(100,50).perform();

        WebElement text2 = driver.findElement(By.xpath("//div[@class='test2']"));
        String orangeBox = text2.getText();
        String expectedText = "Now you can drop it";
        softAssert.assertEquals(orangeBox,expectedText);
        System.out.println(orangeBox);
        System.out.println(expectedText);

        boolean isDisplayed = text2.isDisplayed();
        softAssert.assertTrue(isDisplayed);
        System.out.println(isDisplayed);

        WebElement text1 = driver.findElement(By.xpath("//div[@class='test1']"));
        String  bluBox = text1.getText();
        String expectedText3 = "(Drop here)";
        softAssert.assertEquals(bluBox,expectedText3);
        System.out.println(bluBox);
        System.out.println(expectedText3);

        boolean isdisplayed2 = text2.isDisplayed();
        softAssert.assertTrue(isdisplayed2);
        System.out.println(isdisplayed2);

    }

    /*
      Navigate to "http://demo.guru99.com/test/drag_drop.html"
      Click and hold the first -5000
      Validate "Please select another block" is displayed

      Drag and drop first 5000 to Debit side Amount
      Validate "Debit Movement 5000 Credit Movement 0"
      Drag and drop Second 5000 to Credit side Amount
      Validate "Debit Movement 5000 Credit Movement 5000"
      Drag and drop the "BANK" Debit side Account
      Validate BANK is displayed
      Drag and drop the "SALES" Credit side Account
      Validate SALES is displayed (edited)
     */


 


        @Test
        public void task4() throws InterruptedException {
            driver.navigate().to("http://demo.guru99.com/test/drag_drop.html");
            WebElement first_5000 = driver.findElement(By.xpath("//li[@id='credit']/child::a"));
            actions.clickAndHold(first_5000).moveByOffset(20,0).release().perform();

            WebElement anotherBlockText = driver.findElement(By.xpath("//div[text()='Please select another block']"));
            Assert.assertEquals(anotherBlockText.getText(),"Please select another block");
            WebElement first5000 = driver.findElement(By.xpath("//li[@id='fourth']/child::a[text()=' 5000 ']"));
            WebElement DebitSide = driver.findElement(By.xpath("//ol[@id='amt7']"));
            actions.clickAndHold(first5000).moveToElement(DebitSide).release().perform();

            WebElement debitMovement = driver.findElement(By.xpath("//table[@align='center']"));
            Assert.assertEquals(debitMovement.getText(),"Debit Movement\n5000\nCredit Movement\n0");
            WebElement second5000 = driver.findElement(By.xpath("//li[@id='fourth']/child::a[text()=' 5000']"));
            WebElement creditSide = driver.findElement(By.xpath("//ol[@id='amt8']"));
            actions.clickAndHold(second5000).moveToElement(creditSide).release().perform();

            WebElement secondCreditMovement = driver.findElement(By.xpath("//table[@align='center']"));
            Assert.assertEquals(secondCreditMovement.getText(),"Debit Movement\n5000\nCredit Movement\n5000");
            WebElement bank = driver.findElement(By.xpath("//li[@id='credit2']"));

            WebElement bankDebitMovement = driver.findElement(By.xpath("//ol[@class='field14 ui-droppable ui-sortable']/parent::div"));
            actions.clickAndHold(bank).moveToElement(bankDebitMovement).release().perform();
            Assert.assertEquals(bank.getText(),"BANK");

            WebElement sales = driver.findElement(By.xpath("//li[@id='credit1']"));
            WebElement salesCreditMovement = driver.findElement(By.xpath("//ol[@class='field15 ui-droppable ui-sortable']/parent::div"));
            actions.clickAndHold(sales).moveToElement(salesCreditMovement).release().perform();
            Assert.assertEquals(sales.getText(),"SALES");
    }






}

