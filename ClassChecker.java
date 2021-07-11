
package SeleniumProj;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

/**
 * 
 * @author Aniruthan Ramadoss
 *         Change the value of the public static String fields according to the
 *         class
 *         in question and run the program.
 * 
 *         If not searching by CRN, leave the courseRegistrationNumber field as
 *         an empty String.
 */
public class ClassChecker {
    private WebDriver driver = null;
    private String classDepartment;
    private String classNumber;
    private String term;
    private String courseRegistrationNumber;
    // Don't change the openOnly variable
    private final String openOnly = "ONLY OPEN Sections";

    /*
     * Put the email you would like to receive an email reminder to here.
     */
    public String email;

    /**
     * 
     * Connects to the chrome driver, boots up chrome, navigates to the website,
     * and maximizes the window.
     */
    public ClassChecker(
        String classDepartment,
        String classNumber,
        String term,
        String courseRegistrationNumber,
        String email) {
        this.classDepartment = classDepartment;
        this.classNumber = classNumber;
        this.term = term;
        this.courseRegistrationNumber = courseRegistrationNumber;
        this.email = email;

        System.setProperty("webdriver.chrome.driver",
            "C:\\Users\\Aniruthan Ramadoss\\chromedriver.exe");
        driver = new ChromeDriver();
        // Tells the driver to wait for a maximum of 15 seconds to wait for a
        // web element to load.
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        driver.navigate().to(
            "https://apps.es.vt.edu/ssb/HZSKVTSC.P_DispRequest");
        driver.manage().window().maximize();
        classCheck();
        EmailSender completedTask = new EmailSender(this);
    }


    private void classCheck() {

        WebElement semester = driver.findElement(By.name("TERMYEAR"));
        Select termSelector = new Select(semester);
        termSelector.selectByVisibleText(term);

        if (courseRegistrationNumber.length() > 0) {
            WebElement crn = driver.findElement(By.name("crn"));
            crn.sendKeys(courseRegistrationNumber);

        }
        else {
            WebElement category = driver.findElement(By.name("subj_code"));
            Select categorySelector = new Select(category);
            categorySelector.selectByVisibleText(classDepartment);

            WebElement courseNumber = driver.findElement(By.name(
                "CRSE_NUMBER"));
            courseNumber.sendKeys(classNumber);
        }

        WebElement open = driver.findElement(By.name("open_only"));
        Select openOnlySelector = new Select(open);
        openOnlySelector.selectByVisibleText(openOnly);

        // The height of the table should be 16.0 if there are no classes
        // available.
        // If there is one class available, the height should be 46.0.

        double numRows = 16.0;
        while (numRows == 16.0) {
            WebElement searchClass = driver.findElement(By.xpath(
                "//*[@id=\"ttform\"]/table/tbody/tr[10]/td/center/input[1]"));
            searchClass.click();

            numRows = driver.findElement(By.xpath(
                "/html/body/center[1]/div/table[2]/tbody")).getSize()
                .getHeight();
            try {
                Thread.sleep(10000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        System.out.println("Class found!");

    }


    public String getClassDepartment() {
        return classDepartment;
    }


    public String getClassNumber() {
        return classNumber;
    }


    public String getTerm() {
        return term;
    }


    public String getCourseRegistrationNumber() {
        return courseRegistrationNumber;
    }


    public String getEmail() {
        return email;
    }


    /**
     * Logs into VT. Not currently used but may be useful in the future.
     */
//    public void login() {
//        driver.navigate().to(
//            "https://banweb.banner.vt.edu/ssomanager_prod/c/SSB");
//        Scanner logins = null;
//        try {
//            logins = new Scanner(new File(
//                "hokiespa_username_and_password.txt"));
//        }
//        catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
//        String user = logins.nextLine();
//        String pass = logins.nextLine();
//
//        WebElement username = driver.findElement(By.name("j_username"));
//        WebElement password = driver.findElement(By.name("j_password"));
//        WebElement login = driver.findElement(By.name("_eventId_proceed"));
//        username.sendKeys(user);
//        password.sendKeys(pass);
//        login.click();
//    }
}
