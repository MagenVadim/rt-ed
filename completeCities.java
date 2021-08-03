import org.openqa.selenium.WebElement;

public class completeCities {
    public static void completeness (WebElement wb){
        String m = wb.getAttribute("innerText");
        String [] m1 = m.split(":");
        if (m1.length<2) {
            System.out.println("BUG!!! VALUE IS EMPTY!!!  " + m);
        } else {
            System.out.println(m);
        }
    }

    public static void completeInfo (WebElement wb){
        String m = wb.getAttribute("innerText");
        String [] m1 = m.split("0");
        if (m1.length<1) {
            System.out.println("BUG!!! VALUE IS EMPTY!!!  " + m);
        } else {
            System.out.println(m);
        }
    }

}
