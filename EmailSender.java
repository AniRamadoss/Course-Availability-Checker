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

    /**
     * Put the login info of username and password of any yahoomail account as
     * the fields. This email will be used to send you the email. I used my own
     * to test, but I am not disclosing that info.
     */
    private String username =
        "Replace this with the yahoo mail username or email you will use to send yourself an email.";
    private String password =
        "Replace this with the yahoo mail password of the account you will use to send yourself an email.";

    private ClassChecker classChecker;

    public EmailSender(ClassChecker classChecker) {
        this.classChecker = classChecker;
        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\Aniruthan Ramadoss\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        sendEmail();
    }


    private void sendEmail() {
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
            classChecker.getEmail());

        driver.findElement(By.xpath(
            "//*[@id=\"mail-app-component\"]/div/div/div[1]/div[3]/div/div/input"))
            .sendKeys("Your course was found!");

        driver.findElement(By.xpath("//*[@id=\"editor-container\"]/div[1]"))
            .sendKeys("Your course \nCourse Department: " + classChecker
                .getClassDepartment() + "\nCourse Tag: " + classChecker
                    .getClassNumber() + "\nCRN (if requested): " + classChecker
                        .getCourseRegistrationNumber() + "\nwas found!");

        driver.findElement(By.xpath(
            "//*[@id=\"mail-app-component\"]/div/div/div[2]/div[2]/div/button/span"))
            .click();
    }


    public ClassChecker getClassCheckerObject() {
        return classChecker;
    }

}
