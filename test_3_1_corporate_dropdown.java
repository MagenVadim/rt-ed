import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.List;


public class test_3_1_corporate_dropdown {
    private ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\tools\\chromedriver\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("https://rt-ed.com/");
        driver.manage().window().maximize();
        //driver.manage().timeouts().pageLoadTimeout(5000, TimeUnit.MILLISECONDS);

    }

    @Test //
    public void primaryMenuMaslulim() throws InterruptedException {

        List<WebElement> list = driver.findElements(By.xpath("//ul[@id='primar-menu']/li"));
        for(int u = list.size() - 1; u >= 0; u--) {
            int uP = u + 1;
            System.out.println("BUTTON " + uP);
            WebElement list1 = driver.findElement(By.xpath("//ul[@id='primar-menu']/li["+uP+"]"));
            //System.out.println(list1.getText());

            String attribute = list1.getAttribute("class");

            if (attribute.contains("dropdown")){
                String nameDropdown = list1.getText();

                list1.click();
                list1.click();

                List<WebElement> listMasl = driver.findElements(By.xpath("//ul[@class='dropdown-menu depth_0 show']/li"));

                for (int nav = 0; nav < listMasl.size(); nav++) {
                    int m = nav + 1;
                    List<WebElement> listRT = driver.findElements(By.xpath("//ul[@id='primar-menu']/li[" + uP + "]/ul/li[" + m + "]//ul//li")); // list of specialization in every faculty
                    WebElement RT1 = driver.findElement(By.xpath("//ul[@id='primar-menu']/li[" + uP + "]/ul/li[" + m + "]")); // name of faculty in every button

                    System.out.println("\n" + "Tab \"" + RT1.getText() + "  contains " + listRT.size() + " elements:");

                    Actions actions = new Actions(driver);
                    actions.moveToElement(RT1).build().perform();

                    int y = 1;
                    for (int i = 0; i < listRT.size(); i++) {
                        System.out.println(y + ") " + listRT.get(i).getAttribute("innerText"));
                        y++;
                    }
                    System.out.println();

                    for (int i = 0; i < listRT.size(); i++) {
                        WebElement insideRT = listRT.get(i);
                        System.out.println(insideRT.getAttribute("innerText"));
                        insideRT.click();

                        list = driver.findElements(By.xpath("//ul[@id='primar-menu']/li"));
                        list1 = list.get(u);
                        System.out.println(list1.getText());

                        list1.click();
                        System.out.println("You have successfully entered the section with the title: " + "\n" + "     " + driver.getTitle());

                        List<WebElement> cities = driver.findElements(By.xpath("//ul[@class='nav nav-tabs px-0']//li"));
                        for (int mm = 0; mm < cities.size(); mm++) {
                            System.out.println(cities.get(mm).getText());
                            try {
                                WebElement month = driver.findElement(By.xpath("//div[@id='course-variation-duration-tlv']"));
                                completeCities.completeness(month);
                                WebElement meeting = driver.findElement(By.xpath("//div[@id='course-variation-meetings-tlv']"));
                                completeCities.completeness(meeting);
                                WebElement hours = driver.findElement(By.xpath("//div[@id='course-variation-time-tlv']"));
                                completeCities.completeness(hours);
                                WebElement totalHours = driver.findElement(By.xpath("//div[@id='total-hours-tlv']"));
                                completeCities.completeInfo(totalHours);
                                WebElement practice = driver.findElement(By.xpath("//div[@id='practice-promiss-tlv']"));
                                completeCities.completeInfo(practice);
                                WebElement workExp = driver.findElement(By.xpath("//div[@id='work-exp-promiss-tlv']"));
                                completeCities.completeInfo(workExp);
                                System.out.println("__________________________________");
                                cities.get(mm).click();
                            } catch (Exception e) {
                                System.out.println("BUG!!! NoSuchElementException...");
                                cities.get(mm).click();
                                continue;
                            }
                        }
                        list1.click();
                        System.out.println();

                        listRT = driver.findElements(By.xpath("//ul[@id='primar-menu']/li[" + uP + "]/ul/li[" + m + "]//ul//li"));
                        RT1 = driver.findElement(By.xpath("//ul[@id='primar-menu']/li[" + uP + "]/ul/li[" + m + "]"));

                        actions = new Actions(driver);
                        actions.moveToElement(RT1).build().perform();
                    }
                }


                System.out.println();

            } else {
                System.out.println("BUTTON DOES NOT CONTENT dropdown");
                List<WebElement> corp = driver.findElements(By.xpath("//div[@style]//table[@class]//td[@class]//a[@href]"));
                for (int num = 0; num<corp.size(); num++){
                    String adr_link = corp.get(num).getAttribute("href");
                    String name_link = corp.get(num).getText();
                    System.out.println(name_link + ": " + adr_link);
                    corp.get(num).click();
                    driver.navigate().back();
                    corp = driver.findElements(By.xpath("//div[@style]//table[@class]//td[@class]//a[@href]"));
                }
            }
            System.out.println("_________________________________________");

            try {
                list1.click();
            }
            catch(Exception ex)
            {
                list1 = driver.findElement(By.xpath("//ul[@id='primar-menu']/li["+uP+"]"));
                list1.click();
            }
        }
    }
}
