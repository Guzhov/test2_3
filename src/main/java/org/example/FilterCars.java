package org.example;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;


public class FilterCars {


    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    public WebDriver driver;
    public FilterCars(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver; }


    /**
     * поиск локатора списка всех брендов
     */
    @FindBy(xpath = "//div[@class='css-589fou e154wmfa0']//div[@class='css-1qalmmh e1x0dvi12']")
    private List<WebElement> carBrand;

    /**
     * поиск локатора кнопки перехода на страницу с выбором региона
     */
    @FindBy(xpath = "//a[@data-ga-stats-name='HomeRegionChange']")
    private WebElement choiceRegion;

    /**
     * поиск локатора кнопки для смены региона
     */
    @FindBy(xpath = "//a[text()='Приморский край']")
    private WebElement changeRegionPrimorye;

    /**
     * Определение локатора поля выбора марки
     */
    @FindBy(xpath = "//button[text()='Марка']")
    private WebElement brand;

    /**
     * Функция для сортировки массива, содержащего количество объявлений каждого автомобиля из списка марок
     */
    public static void bubbleSort(int[] array) {
        boolean sorted = false;
        int temp;
        while(!sorted) {
            sorted = true;
            for (int i = 0; i < array.length - 1; i++) {
                if (array[i] < array[i+1]) {
                    temp = array[i];
                    array[i] = array[i+1];
                    array[i+1] = temp;
                    sorted = false;
                }
            }
        }
    }


    /**
     * Функция нажатия кнопки для получения списка всех фирм на сайте
     */
    public void clickBrand(){
        brand.click();
    }

    /**
     * Функция определяет 20 популярных брендов автомобилей, формирует таблицу и выводи ее на экран
     */
    public void brandTable(){
        Dictionary popularBrands = new Hashtable();
        String brandText, brandName, brandCount;
        int[] arrCount = new int[carBrand.size()];
        for (int i =0; i < carBrand.size(); i++){
            brandText = carBrand.get(i).getAttribute("textContent");
            int index = brandText.indexOf("(");
            int lenght = brandText.length();

            brandName = brandText.substring(0, index-1);
            brandCount = brandText.substring(index+1, lenght-1);
            popularBrands.put(brandCount, brandName);
            arrCount[i] = Integer.parseInt(brandCount);

        }
        bubbleSort(arrCount);
        System.out.printf("| %-15s| ", "Марка");
        System.out.printf("%-21s|\n","Количество объявлений");
        for (int i=0; i <20; i++){

            System.out.printf("| %-15s| ", popularBrands.get(Integer.toString(arrCount[i])));
            System.out.printf("%-21s|\n", arrCount[i]);
        }

    }
    /**
     * Функция для изменения текущего региона на регион "Приморский край"
     */
    public void changeRegion(){
        choiceRegion.click();
        changeRegionPrimorye.click();
    }



}
