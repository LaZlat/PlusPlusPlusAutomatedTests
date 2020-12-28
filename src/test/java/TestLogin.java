import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.Thread.sleep;

public class TestLogin {
    WebElement loginChooser, emailField, passwordField, loginButton, errorLabel;
    WebDriver driver;

    @Before
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://localhost:8080/#/signin");

        loginChooser = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/button[1]"));
    }

    @Test
    public void straightThroughLogIn() throws InterruptedException {
        loginChooser.click();

        emailField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[1]"));
        passwordField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[2]"));
        loginButton = driver.findElement(By.xpath("/html/body/div/div/div/button"));

        emailField.sendKeys("laurynas.zlatkus@gmail.com");
        passwordField.sendKeys("123456");
        loginButton.click();

        sleep(1000);

        Assert.assertEquals("http://localhost:8080/#/", driver.getCurrentUrl());
    }

    @Test
    public void logInWithWrongEmailAndPassword() throws InterruptedException {
        loginChooser.click();

        emailField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[1]"));
        passwordField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[2]"));
        loginButton = driver.findElement(By.xpath("/html/body/div/div/div/button"));

        emailField.sendKeys("baba.zose@gmail.com");
        passwordField.sendKeys("aaee");
        loginButton.click();

        sleep(1000);

        errorLabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("No such user.", errorLabel.getText());
    }

    @Test
    public void logInWithEmptyPassword() throws InterruptedException {
        loginChooser.click();

        emailField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[1]"));
        loginButton = driver.findElement(By.xpath("/html/body/div/div/div/button"));

        emailField.sendKeys("laurynas.zlatkus@gmail.com");
        loginButton.click();

        sleep(1000);

        errorLabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Fill in all the fields.", errorLabel.getText());
    }

    @Test
    public void logInWithEmptyEmail() throws InterruptedException {
        loginChooser.click();

        passwordField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[2]"));
        loginButton = driver.findElement(By.xpath("/html/body/div/div/div/button"));

        passwordField.sendKeys("123456");
        loginButton.click();

        sleep(1000);

        errorLabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Fill in all the fields.", errorLabel.getText());
    }

    @Test
    public void logInWithWrongPassword() throws InterruptedException {
        loginChooser.click();

        emailField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[1]"));
        passwordField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[2]"));
        loginButton = driver.findElement(By.xpath("/html/body/div/div/div/button"));

        emailField.sendKeys("laurynas.zlatkus@gmail.com");
        passwordField.sendKeys("aneane");
        loginButton.click();

        sleep(1000);

        errorLabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("No such user.", errorLabel.getText());
    }

    @Test
    public void logInWithWrongEmail() throws InterruptedException {
        loginChooser.click();

        emailField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[1]"));
        passwordField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[2]"));
        loginButton = driver.findElement(By.xpath("/html/body/div/div/div/button"));

        emailField.sendKeys("baba.zlatkus@gmail.com");
        passwordField.sendKeys("123456");
        loginButton.click();

        sleep(1000);

        errorLabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("No such user.", errorLabel.getText());
    }

    @After
    public void closeTheTest() throws InterruptedException {
        driver.quit();
    }
}
