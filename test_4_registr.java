import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class test_4_registr {
    private ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rt-ed.com/");
        driver.manage().window().maximize();
        //driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.MILLISECONDS);

    }


    @Test // Netunim pratim - Lishloah
    public void registerToCourse() {
        System.out.println("Enter PhoneNumber");
        driver.findElement(By.id("phone")).sendKeys("0529370346");

        System.out.println("Enter FirstName");
        driver.findElement(By.id("fname")).sendKeys("זאב");

        System.out.println("Enter LastName");
        driver.findElement(By.id("lname")).sendKeys("נדיב");

        System.out.println("Enter e-mail");
        driver.findElement(By.id("email")).sendKeys("magenvadim@yahoo.com");

        System.out.println("choose a course");
        Select dropdown = new Select(driver.findElement(By.id("target-track")));
        dropdown.selectByIndex(2);

        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        checkbox.click();


        WebElement submit = driver.findElement(By.xpath("//button[@name='submit']"));
        System.out.println("Click on Send button");
        submit.click();



    }
}
