import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.*;
import java.net.URL;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.util.List;
import java.util.concurrent.TimeUnit;


public class test1 {

    private ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rt-ed.com/");
        driver.manage().window().maximize();
        //driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.MILLISECONDS);

    }

    @After
    public void cleanUp() {
        if (driver != null) driver.quit();
    }


    @Test
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
        WebElement submit = driver.findElement(By.xpath("//button[@name='submit']"));
        System.out.println("Click on Send button");
        submit.click();
    }

/*    @Test // checks the "Maslulim" tab
    public void maslulim() throws IOException, InterruptedException {

        WebElement masl = driver.findElement(By.xpath("//a[@href='https://rt-ed.com/sample-page/']"));
        masl.click();
        List<WebElement> list = driver.findElements(By.xpath("//ul[@class='dropdown-menu blue-color depth_0 show']/li/a"));
        ;
        int sizeList = list.size();

        for (int i = 0; i < sizeList; i++) {
            String value_adr = list.get(i).getAttribute("href"); // link address URL indicated on the home page

            String waitText = list.get(i).getText();
            System.out.println(waitText); // expected text from the list on the home page

            list.get(i).click();
            String actual = driver.getCurrentUrl(); // actual address URL after clicking on the button

            try {
                Assert.assertEquals(value_adr, actual);
            } catch (AssertionError e) {
                System.out.println("Error");
            }

            String actualText = driver.getTitle(); // actual text from the list on the home page
            System.out.println(actualText);

            try {
                URL url = new URL(value_adr);
                //Now we will be creating url connection and getting the response code
                HttpURLConnection httpURLConnect = (HttpURLConnection) url.openConnection();
                httpURLConnect.setConnectTimeout(5000);
                httpURLConnect.connect();
                if (httpURLConnect.getResponseCode() >= 400) {
                    System.out.println(value_adr + " - " + httpURLConnect.getResponseMessage() + "is a broken link");
                }
                //Fetching and Printing the response code obtained
                else {
                    System.out.println(value_adr + " - " + httpURLConnect.getResponseMessage());
                }
            } catch (Exception e) {
            }

            driver.navigate().back();
            masl = driver.findElement(By.xpath("//a[@href='https://rt-ed.com/sample-page/']"));

            if (i == sizeList - 1)
                break; //breaks out of the loop after checking the last item and returns to the home page
            masl.click();
            list = driver.findElements(By.xpath("//ul[@class='dropdown-menu blue-color depth_0 show']/li/a"));
        }
    }*/

    @Test
    public void primaryMenu() {

        List<WebElement> list = driver.findElements(By.xpath("//ul[@id='primar-menu']//li[@id='nav-item']//a"));
        int sizeList = list.size();
        System.out.println(sizeList);

        for (int i = 0; i < 3; i++) {
            String value_adr = list.get(i).getAttribute("href");
            System.out.println(value_adr);

            list.get(i).click();

            driver.navigate().back();
            list = driver.findElements(By.xpath("//ul[@id='primar-menu']//li[@id='nav-item']//a"));

            WebElement element = list.get(i);
            float waitingTime = 0;
            long startLoadingTime = System.currentTimeMillis();
            while (element.isDisplayed()) {
                // if element is still presented, verify, if the maximum of waiting time is reached

                if (waitingTime <= 5) { //MAX_WAITING_TIME
                    waitingTime = System.currentTimeMillis() - startLoadingTime;
                } else {
                    System.out.println("Condition was not executed with time limit");
                    break;
                }
            }
            // if the element disappeared, log the loading time of the
            if (!element.isDisplayed()) {
                System.out.println("Condition was executed in " + waitingTime + " seconds");
            }
        }
    }

    @Test // masluli ahshara ve-asama
    public void ahshara() {
        System.out.println("testing of block:  מסלולי הכשרה והשמה has started");
        driver.manage().window().maximize();
        List<WebElement> list = driver.findElements(By.xpath("//div[@id='paths-we-offer']/div/div/div/a"));
        int sz = list.size();
        System.out.println("number of elements in a block: " + sz + "\n");

        for (int i = 0; i < sz; i++) {
            String adress = list.get(i).getAttribute("href"); // link stored in the list of the home page

            String waitText = list.get(i).getText(); // expected text from the list on the home page
            driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);

            try {
                list.get(i).click();
                System.out.println("page of course: " + waitText + "\n" + " was load succsefully! \n" + "link: " + adress + " is worked!" + "\n____________________________________________________________________"
                );

            } catch (Exception e) {
                System.out.println("page of course: " + waitText + "\n" + " wasn`t load! \n" + "time waiting (5 seconds) for loading the page is over \n" + "link: " + adress + "\n" + "haven`t loaded" + "\n____________________________________________________________________");
                continue;
            }

            driver.navigate().back();
            list = driver.findElements(By.xpath("//div[@id='paths-we-offer']/div/div/div/a"));
        }
    }


    @Test
    public void search() {
        driver.findElement(By.id("nav-search-string")).sendKeys("DevOps"); //input[@id='nav-search-string']
        WebElement search = driver.findElement(By.xpath("//button[@class='btn btn-outline-light order-2 my-2 my-sm-0 col-3']"));
        search.click();
        System.out.println(driver.getTitle());
        WebElement masl1 = driver.findElement(By.xpath("//div[@class='page-header mt-3']/h2[@class='page-title text-right']"));
        String result1 = masl1.getText();
        WebElement masl2 = driver.findElement(By.xpath("//div[@class='page-content']/div/h3[@class='text-right mb-4']"));
        String result2 = masl2.getText();
        System.out.println(result1 + "\n" + result2);
    }

    @Test
    public void navbar() {
        List<WebElement> list = driver.findElements(By.xpath("//icon[@class='navbar-brand icon-small']"));
        int sizeList = list.size();
        System.out.println("Number of elements for checking are: " + sizeList);
        for (int i = 0; i < sizeList - 1; i++) {
            String value_adr = list.get(i).getAttribute("href");
            if (value_adr == null) {
                System.out.println("such link does`not exists");
            }

            System.out.println(value_adr);
            WebDriverWait wait = new WebDriverWait(driver, 10);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//icon[@class='navbar-brand icon-small']")));
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable(list.get(i)));
            el.click();
        }
    }

        @Test
        public void studentPortal() {
            WebDriverWait wait = new WebDriverWait(driver, 1);
            //WebElement studentPortal = driver.findElement((By.id("student-portal")));
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("student-portal")));
            WebElement el = wait.until(ExpectedConditions.elementToBeClickable((By.id("student-portal"))));
            el = wait.until(ExpectedConditions.elementToBeClickable((By.xpath("//button[@class='submit-button orange-color col-lg-2 col-12']"))));
            //Assert.assertTrue(el.isDisplayed());
            //studentPortal.click();
            el.click();
        }

        @Test
        public void kursimNosafim(){
            WebDriverWait wait = new WebDriverWait(driver, 1);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("student-portal")));
            WebElement kursNos = wait.until(ExpectedConditions.elementToBeClickable((By.id("student-portal"))));
            kursNos.click();
        }
}




