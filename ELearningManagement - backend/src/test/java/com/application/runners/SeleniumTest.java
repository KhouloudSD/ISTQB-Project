package SeleniumTests;

import com.application.ELearningManagementApplication;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig
@SpringBootTest(classes = ELearningManagementApplication.class ,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SeleniumTest {

  @Test
  public void testLogin() throws InterruptedException {
    // Create an instance of ChromeDriver
    WebDriverManager.edgedriver().setup();

    WebDriver webDriver = new EdgeDriver();
    webDriver.get("http://localhost:4200/login");
    webDriver.findElement(By.id("userbtn")).click();
    webDriver.findElement(By.cssSelector("input[placeholder='enter email']")).sendKeys("khouloud@gmail.com");
    webDriver.findElement(By.cssSelector("input[placeholder='enter password']")).sendKeys("123");
    WebElement loginButton = webDriver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div[1]/form/button"));

    // Click the button using JavaScript to bypass the "disabled" attribute
    clickElementByJavaScript(webDriver, loginButton);

    WebDriverWait wait = new WebDriverWait(webDriver, 10);
    WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-userdashboard/div[2]/div/div/div[1]/b")));
    System.out.println("Enrollment Successful: " + welcomeMessage.getText());

    assertEquals("Welcome to User Dashboard",welcomeMessage.getText());

  }



    @Test
  public void testEnrollement() throws InterruptedException {
    // Set up ChromeDriver using WebDriverManager
    //System.setProperty("webdriver.chrome.driver", "C:\\Program Files\\Google\\Chrome\\Application\\chrome.exe");
    WebDriverManager.edgedriver().setup();
    //WebDriverManager.chromedriver().browserVersion("119.0.6045.124").setup();

    // Create an instance of ChromeDriver
    WebDriver webDriver = new EdgeDriver();
    webDriver.get("http://localhost:4200/login");
    webDriver.findElement(By.id("userbtn")).click();
    webDriver.findElement(By.cssSelector("input[placeholder='enter email']")).sendKeys("khouloud@gmail.com");
    webDriver.findElement(By.cssSelector("input[placeholder='enter password']")).sendKeys("123");
    WebElement loginButton = webDriver.findElement(By.xpath("/html/body/app-root/app-login/div/div/div[1]/form/button"));

    // Click the button using JavaScript to bypass the "disabled" attribute
    clickElementByJavaScript(webDriver, loginButton);

    WebDriverWait wait = new WebDriverWait(webDriver, 10);
    WebElement welcomeMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-userdashboard/div[2]/div/div/div[1]/b")));
    System.out.println("Enrollment Successful: " + welcomeMessage.getText());

    //assertEquals("Welcome to User Dashboard \uD83D\uDC4B ",welcomeMessage.getText());

    webDriver.get("http://localhost:4200/courselist");
    webDriver.navigate().refresh();
    WebElement moreInfoButton = webDriver.findElement(By.id("youtubecard"));
    clickElementByJavaScript(webDriver, moreInfoButton);

  String enrolled = webDriver.findElement(By.xpath("/html/body/app-root/app-courselist/div[3]/div/div[8]/div/a")).getText();

    String courseName = webDriver.findElement(By.xpath("/html/body/app-root/app-courselist/div[3]/div/h4/b")).getText();
    System.out.println( "course enrolled in : "+courseName);
  if(enrolled == "Enrolled"){
    WebElement enrollButton = webDriver.findElement(By.id("enrollbtn"));
    clickElementByJavaScript(webDriver, enrollButton);

  }

    //test if user is enrolled
    webDriver.get("http://localhost:4200/mycourses");
    WebElement myLearning = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body/app-root/app-mycourses/div[1]/b")));
    System.out.println(myLearning);
    wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/app-root/app-mycourses/div[2]/owl-carousel-o/div/div[1]/owl-stage/div/div/div[3]/div/a")));
    String expectedCourseName = webDriver.findElement(By.xpath("/html/body/app-root/app-mycourses/div[2]/owl-carousel-o/div/div[1]/owl-stage/div/div/div[3]/div/h4/b")).getText();
    System.out.println("expectedCourseName:"+expectedCourseName);

    assertEquals(courseName, expectedCourseName);


    // Close the browser
    webDriver.quit();
  }
  // Method to click an element using JavaScript
  private static void clickElementByJavaScript(WebDriver driver, WebElement element) {
    String script = "arguments[0].click();";
    ((EdgeDriver) driver).executeScript(script, element);
  }
}
