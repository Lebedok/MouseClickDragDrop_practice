package Selenium.HomeWork;

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

    public void test4() throws InterruptedException {

        // -5000
        driver.get("http://demo.guru99.com/test/drag_drop.html");
        js.executeScript("window.scrollBy(0,300)");
        WebElement orangeButton = driver.findElement(By.xpath("//li[@id='credit']/child::a"));     //      "//*[@id='credit']/a "
        actions.clickAndHold(orangeButton).moveByOffset(0,-50).perform();

        WebElement text = driver.findElement(By.id("e1"));
        String actualText = text.getText();
        String expectedText = "Please select another block";
        softAssert.assertEquals(actualText,expectedText);
        System.out.println(actualText);
        System.out.println(expectedText);

        Thread.sleep(2000);


        // first 5000

        WebElement orangeButton2 = driver.findElement(By.xpath("//li[@id='fourth']/child::a[text()=' 5000 ']"));
        WebElement destinationDebit = driver.findElement(By.xpath("//*[@id='amt7']/li"));
        actions.dragAndDrop(orangeButton2,destinationDebit).perform();




    }


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


    /*
    Navigate to "http://www.popuptest.com/popuptest4.html"
    Hover over to "Mouseover PopUp"
    Validate the size of opened window is equals to 3
    Validate new window titles are matching with today's date
    Validate new window url contains "popup9"
    Validate other new window url contains "popup10"
     */

    @Test
    public void test5(){
        driver.get("http://www.popuptest.com/popuptest4.html/");
        js.executeScript("window.scrollBy(0,300)");



    }

    /*
    Navigate to "http://seleniumpractise.blogspot.com/2017/"
Click the text "Click here for Facebook" Test Link
Enter First name "Techtorial"
Enter Last name "Academy"
Enter phone number "2223334455"
Enter the password "techtorial123"
Select "April" + "20" + "2002"
Select Gender Custom
Close the facebook page
Validate the title equals to "Selenium Practise: 2017"
     */

    @Test

    public void test6(){
        driver.get("http://seleniumpractise.blogspot.com/2017/");
        WebElement facebook = driver.findElement(By.linkText("Click here for Facebook"));
        facebook.click();

        driver.findElement(By.cssSelector("//a[contains(@id,'')]")).click();





        /*WebElement firstName = driver.findElement(By.id("email"));
        firstName.sendKeys("Techtorial");

        WebElement lastName = driver.findElement(By.id("pass"));
        lastName.sendKeys("Academy");

         */



    }







}

