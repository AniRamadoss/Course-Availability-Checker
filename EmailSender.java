package SeleniumProj;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Sends an email to the requested email once the course is ready.
 * 
 * @author Aniruthan Ramadoss
 *
 */
public class EmailSender {
    public static WebDriver driver = null;
    private String username = "";
    private String password = "";

    /**
     * Put the login info of username and password of any yahoomail account as
     * the fields. I used my own to test, but I am not disclosing that info.
     */
    public EmailSender() {
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\Aniruthan Ramadoss\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);

        driver.navigate().to("https://yahoomail.com/");
        driver.manage().window().maximize();
        driver.findElement(By.xpath(
            "//*[@id=\"signin-main\"]/div[1]/a[2]/span")).click();
        driver.findElement(By.xpath("//*[@id=\"login-username\"]")).sendKeys(
            username);
        driver.findElement(By.xpath("//*[@id=\"login-signin\"]")).click();

        driver.findElement(By.xpath("//*[@id=\"login-passwd\"]")).sendKeys(
            password);

        driver.findElement(By.xpath("//*[@id=\"login-signin\"]")).click();

        driver.findElement(By.xpath(
            "//*[@id=\"app\"]/div[2]/div/div[1]/nav/div/div[1]/a")).click();
        driver.findElement(By.xpath("//*[@id=\"message-to-field\"]")).sendKeys(
            ClassChecker.email);
        driver.findElement(By.xpath(
            "//*[@id=\"mail-app-component\"]/div/div/div[1]/div[3]/div/div/input"))
            .sendKeys("Your course was found!");
        driver.findElement(By.xpath("//*[@id=\"editor-container\"]/div[1]"))
            .sendKeys("Your course \nCourse Department: "
                + ClassChecker.classDepartment + "\nCourse Tag: "
                + ClassChecker.classNumber + "\nCRN (if requested): "
                + ClassChecker.courseRegistrationNumber + "\nwas found!");
        driver.findElement(By.xpath(
            "//*[@id=\"mail-app-component\"]/div/div/div[2]/div[2]/div/button/span"))
            .click();
    }

}
