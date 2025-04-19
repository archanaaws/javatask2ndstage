package org.example.actions;

import org.example.pages.CarValuationPage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import java.util.HashMap;
import java.util.Map;

public class CarDetailsFetcher {

    public static Map<String, String> fetch(WebDriver driver, String regNumber) throws InterruptedException {
        driver.get("https://car-checking.com");
        CarValuationPage page = new CarValuationPage(driver);
        page.searchRegistration(regNumber);
        Thread.sleep(5000);

        Map<String, String> details = new HashMap<>();
        try {
            // Attempt to extract details
            String make = page.getMake();
            String model = page.getModel();
            String year = page.getYear();

            details.put("make", make);
            details.put("model", model);
            details.put("year", year);
        } catch (Exception e) {
            // If any element is missing, mark as NOT FOUND
            details.put("make", "NOT FOUND");
            details.put("model", "NOT FOUND");
            details.put("year", "NOT FOUND");
        }
        return details;
    }
}
