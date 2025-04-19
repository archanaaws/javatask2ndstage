package org.example.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CarValuationPage {
    private final WebDriver driver;
    public CarValuationPage(WebDriver driver) { this.driver = driver; }


    public void searchRegistration(String reg) {
        WebElement input = driver.findElement(By.xpath("//*[@id='subForm1']"));
        input.clear();
        input.sendKeys(reg);
        input.submit();
    }

    public String getMake() {
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div/div/div[2]/table/tbody/tr[1]/td[2]")).getText().split(" ")[0];
    }
    public String getModel() {
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div/div/div[2]/table/tbody/tr[2]/td[2]")).getText();
    }
    public String getYear() {
        return driver.findElement(By.xpath("//*[@id=\"content\"]/div[1]/div[3]/div/div/div[2]/table/tbody/tr[4]/td[2]")).getText();
    }
}