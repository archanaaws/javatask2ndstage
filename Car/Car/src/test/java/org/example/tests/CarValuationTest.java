package org.example.tests;

import com.aventstack.extentreports.*;

import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.example.actions.ReadInputFile;
import org.example.actions.ReadOutputFile;
import org.example.actions.CarDetailsFetcher;
import org.example.utils.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class CarValuationTest {
    private ExtentReports extent;
    private ExtentTest test;
    private WebDriver driver;

    @BeforeSuite
    public void setupReport() {
        ExtentSparkReporter html = new ExtentSparkReporter ("test-output/CarValuationReport.html");
        extent = new ExtentReports();
        extent.attachReporter(html);
    }

    @BeforeMethod
    public void setup() {

        System.setProperty("webdriver.chrome.driver",
                "C:\\Users\\Windows 11\\Desktop\\Car\\Car\\chromedriver.exe");

        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    public static String takeScreenshot(WebDriver driver, String regNumber) {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String path = "reports/screenshots/" + regNumber + "_" + System.currentTimeMillis() + ".png";

        return path;
    }

    @Test
    public void runAllInputFiles() throws Exception {
        List<File> inputs = FileUtils.getInputFiles();
        for (File inFile : inputs) {
            File outFile = FileUtils.getMatchingOutputFile(inFile);
            Assert.assertNotNull(outFile, "Missing output for " + inFile.getName());

            List<String> regs = ReadInputFile.read(FileUtils.getAbsolutePath(inFile));
            Map<String, Map<String, String>> expected = ReadOutputFile.read(FileUtils.getAbsolutePath(outFile));

            for (String reg : regs) {
                test = extent.createTest("Validate " + reg + " (" + inFile.getName() + ")");
                Map<String, String> actual = CarDetailsFetcher.fetch(driver, reg);

                // Normalize registration number
                String normalizedReg = reg.replaceAll("\\s", "").toUpperCase();
                Map<String, String> exp = expected.get(normalizedReg);

                if (exp == null) {
                    test.fail("No expected data found for: " + reg);
                    System.err.println(" No expected data for: " + reg);
                    continue; // Skip this one
                }

                try {
                    Assert.assertEquals(actual.get("make"), exp.get("make"), "Make mismatch for " + reg);
                    Assert.assertEquals(actual.get("model"), exp.get("model"), "Model mismatch for " + reg);
                    Assert.assertEquals(actual.get("year"), exp.get("year"), "Year mismatch for " + reg);
                    test.pass(" Car details matched for: " + reg);
                } catch (AssertionError e) {
                    String screenshotPath = takeScreenshot(driver, reg);
                    test.fail(" Mismatch for " + reg + ": " + e.getMessage())
                            .addScreenCaptureFromPath(screenshotPath);
                    throw e;
                }
            }

        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @AfterSuite
    public void flush() {
        extent.flush();
    }
}