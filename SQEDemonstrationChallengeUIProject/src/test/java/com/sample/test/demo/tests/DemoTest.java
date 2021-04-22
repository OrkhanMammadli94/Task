package com.sample.test.demo.tests;

import com.sample.test.demo.Configuration;
import com.sample.test.demo.constants.PizzaToppings;
import com.sample.test.demo.constants.PizzaTypes;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import com.sample.test.demo.TestBase;

import java.util.List;

public class DemoTest extends TestBase {

    @Test
    public void happyPathOrder() {

        WebElement pizza = driver.findElement(By.id("pizza1Pizza"));
        Select select = new Select(pizza);
        select.selectByVisibleText(PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName()+" "+"$"+PizzaTypes.MEDIUM_TWOTOPPINGS.getCost());

        WebElement toppings1 = driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings1']"));
        Select select2 = new Select(toppings1);
        select2.selectByVisibleText(PizzaToppings.OLIVES.getDisplayName());

        WebElement toppings2 = driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings2']"));
        Select select3 = new Select(toppings2);
        select3.selectByVisibleText(PizzaToppings.MUSHROOMS.getDisplayName());

        driver.findElement(By.id("pizza1Qty")).sendKeys("1");
        driver.findElement(By.id("pizza1Cost")).sendKeys("9.75");
        driver.findElement(By.id("name")).sendKeys("Orkhan");
        driver.findElement(By.id("email")).sendKeys("orxanovic@gmail.com");
        driver.findElement(By.id("phone")).sendKeys("2159003965");
        driver.findElement(By.id("ccpayment")).click();
        driver.findElement(By.id("placeOrder")).click();

        String correct = driver.findElement(By.id("dialog")).getText();
        Assert.assertTrue(correct.contains("Thank you for your order!"));


    }

    @Test
    public void negativePathOrder() {

        WebElement pizza = driver.findElement(By.id("pizza1Pizza"));
        Select select = new Select(pizza);
        select.selectByVisibleText(PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName()+" "+"$"+PizzaTypes.MEDIUM_TWOTOPPINGS.getCost());

        WebElement toppings1 = driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings1']"));
        Select select2 = new Select(toppings1);
        select2.selectByVisibleText(PizzaToppings.OLIVES.getDisplayName());

        WebElement toppings2 = driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings2']"));
        Select select3 = new Select(toppings2);
        select3.selectByVisibleText(PizzaToppings.MUSHROOMS.getDisplayName());

        driver.findElement(By.id("pizza1Qty")).sendKeys("1");
        driver.findElement(By.id("pizza1Cost")).sendKeys("9.75");
        driver.findElement(By.id("email")).sendKeys("orxanovic@gmail.com");
        driver.findElement(By.id("phone")).sendKeys("2159003965");
        driver.findElement(By.id("ccpayment")).click();
        driver.findElement(By.id("placeOrder")).click();

        WebElement error = driver.findElement(By.id("dialog"));
        Assert.assertEquals(error.getText(), "Missing name");

    }

    @Test
    public void boundaryTest(){

        WebElement pizza = driver.findElement(By.id("pizza1Pizza"));
        Select select = new Select(pizza);
        select.selectByVisibleText(PizzaTypes.MEDIUM_TWOTOPPINGS.getDisplayName()+" "+"$"+PizzaTypes.MEDIUM_TWOTOPPINGS.getCost());

        WebElement toppings1 = driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings1']"));
        Select select2 = new Select(toppings1);
        select2.selectByVisibleText(PizzaToppings.OLIVES.getDisplayName());

        WebElement toppings2 = driver.findElement(By.xpath("//div[@id='pizza1']//select[@class='toppings2']"));
        Select select3 = new Select(toppings2);
        select3.selectByVisibleText(PizzaToppings.MUSHROOMS.getDisplayName());

        driver.findElement(By.id("pizza1Qty")).sendKeys("0");
        driver.findElement(By.id("pizza1Cost")).sendKeys("9.75");
        driver.findElement(By.id("name")).sendKeys("Orkhan");
        driver.findElement(By.id("email")).sendKeys("orxanovic@gmail.com");
        driver.findElement(By.id("phone")).sendKeys("2159003965");
        driver.findElement(By.id("ccpayment")).click();
        driver.findElement(By.id("placeOrder")).click();

        WebElement errorQuantity = driver.findElement(By.id("dialog"));
        Assert.assertEquals(errorQuantity.getText(), "Thank you for your order! TOTAL: 0");

    }
}
