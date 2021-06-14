package org.example;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class FilterTest {

    public static FilterCars filterCars;
    public static WebDriver driver;

    @BeforeClass
    public static void setup() {

        System.setProperty("webdriver.chrome.driver", ConfProperties.getProperty("chromedriver"));
        driver = new ChromeDriver();
        filterCars = new FilterCars(driver);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.get(ConfProperties.getProperty("webpage"));
    }

    /**
     * Тест №3 Вывод таблиц 20 популярных брендов
     */

    @Test
    public void brandTable(){
        filterCars.changeRegion();
        filterCars.clickBrand();
        filterCars.brandTable();

    }

    @AfterClass
    public static void tearDown() {
        driver.quit();
    }

}
