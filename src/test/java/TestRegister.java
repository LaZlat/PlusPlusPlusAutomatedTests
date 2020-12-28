import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Random;

import static java.lang.Thread.dumpStack;
import static java.lang.Thread.sleep;

public class TestRegister {
    WebElement registerChooser, nameField, surnameField, emailField, phoneField, dateField, passwordField, repeatField,
        errorlabel, registerButton;
    WebDriver driver;

    protected String getSaltString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 10) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    protected String getRandomNumberString() {
        Random rnd = new Random();
        int number = rnd.nextInt(9999999);

        return String.format("%07d", number);
    }

    @Before
    public void setupTest() {
        System.setProperty("webdriver.chrome.driver","C:\\chromedriver.exe");
        driver = new ChromeDriver();

        driver.get("http://localhost:8080/#/signin");

        registerChooser = driver.findElement(By.xpath("/html/body/div/div/div/div[1]/button[2]"));
        nameField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[1]"));
        surnameField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[2]"));
        emailField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[3]"));
        phoneField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[4]"));
        dateField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[5]"));
        passwordField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[6]"));
        repeatField = driver.findElement(By.xpath("/html/body/div/div/div/div[2]/form/input[7]"));
        registerButton = driver.findElement(By.xpath("/html/body/div/div/div/button"));
    }

    @Test
    public void straightThroughRegister() throws InterruptedException {
        registerChooser.click();

        nameField.sendKeys(getSaltString());
        surnameField.sendKeys(getSaltString());
        emailField.sendKeys(getSaltString() + "@gmail.com");
        phoneField.sendKeys("86" + getRandomNumberString());
        dateField.sendKeys("2020");
        dateField.sendKeys(Keys.TAB);
        dateField.sendKeys("12");
        dateField.sendKeys("12");
        passwordField.sendKeys("123456");
        repeatField.sendKeys("123456");

        registerButton.click();

        sleep(5000);

        errorlabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Registration successfull. You can Login.", errorlabel.getText());
    }

    @Test
    public void registerWithEmptyName() throws InterruptedException {
        registerChooser.click();

        surnameField.sendKeys(getSaltString());
        emailField.sendKeys(getSaltString() + "@gmail.com");
        phoneField.sendKeys("86" + getRandomNumberString());
        dateField.sendKeys("2020");
        dateField.sendKeys(Keys.TAB);
        dateField.sendKeys("12");
        dateField.sendKeys("12");
        passwordField.sendKeys("123456");
        repeatField.sendKeys("123456");

        registerButton.click();

        sleep(1000);

        errorlabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Fill in all the fields.", errorlabel.getText());
    }

    @Test
    public void registerWithEmptySurname() throws InterruptedException {
        registerChooser.click();

        nameField.sendKeys(getSaltString());
        emailField.sendKeys(getSaltString() + "@gmail.com");
        phoneField.sendKeys("86" + getRandomNumberString());
        dateField.sendKeys("2020");
        dateField.sendKeys(Keys.TAB);
        dateField.sendKeys("12");
        dateField.sendKeys("12");
        passwordField.sendKeys("123456");
        repeatField.sendKeys("123456");

        registerButton.click();

        sleep(1000);

        errorlabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Fill in all the fields.", errorlabel.getText());
    }

    @Test
    public void registerWithEmptyEmail() throws InterruptedException {
        registerChooser.click();

        nameField.sendKeys(getSaltString());
        surnameField.sendKeys(getSaltString());
        phoneField.sendKeys("86" + getRandomNumberString());
        dateField.sendKeys("2020");
        dateField.sendKeys(Keys.TAB);
        dateField.sendKeys("12");
        dateField.sendKeys("12");
        passwordField.sendKeys("123456");
        repeatField.sendKeys("123456");

        registerButton.click();

        sleep(1000);

        errorlabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Fill in all the fields.", errorlabel.getText());
    }

    @Test
    public void registerWithEmptyPhone() throws InterruptedException {
        registerChooser.click();

        nameField.sendKeys(getSaltString());
        surnameField.sendKeys(getSaltString());
        emailField.sendKeys(getSaltString() + "@gmail.com");
        dateField.sendKeys("2020");
        dateField.sendKeys(Keys.TAB);
        dateField.sendKeys("12");
        dateField.sendKeys("12");
        passwordField.sendKeys("123456");
        repeatField.sendKeys("123456");

        registerButton.click();

        sleep(1000);

        errorlabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Fill in all the fields.", errorlabel.getText());
    }

    @Test
    public void registerWithEmptyDate() throws InterruptedException {
        registerChooser.click();

        nameField.sendKeys(getSaltString());
        surnameField.sendKeys(getSaltString());
        emailField.sendKeys(getSaltString() + "@gmail.com");
        phoneField.sendKeys("86" + getRandomNumberString());
        passwordField.sendKeys("123456");
        repeatField.sendKeys("123456");

        registerButton.click();

        sleep(1000);

        errorlabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Fill in all the fields.", errorlabel.getText());
    }

    @Test
    public void registerWithEmptyPasswords() throws InterruptedException {
        registerChooser.click();

        nameField.sendKeys(getSaltString());
        surnameField.sendKeys(getSaltString());
        emailField.sendKeys(getSaltString() + "@gmail.com");
        phoneField.sendKeys("86" + getRandomNumberString());
        dateField.sendKeys("2020");
        dateField.sendKeys(Keys.TAB);
        dateField.sendKeys("12");
        dateField.sendKeys("12");

        registerButton.click();

        sleep(1000);

        errorlabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Fill in all the fields.", errorlabel.getText());
    }

    @Test
    public void registerWithDifferentPass() throws InterruptedException {
        registerChooser.click();

        nameField.sendKeys(getSaltString());
        surnameField.sendKeys(getSaltString());
        emailField.sendKeys(getSaltString() + "@gmail.com");
        phoneField.sendKeys("86" + getRandomNumberString());
        dateField.sendKeys("2020");
        dateField.sendKeys(Keys.TAB);
        dateField.sendKeys("12");
        dateField.sendKeys("12");
        passwordField.sendKeys("123456");
        repeatField.sendKeys("654321");

        registerButton.click();

        sleep(1000);

        errorlabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Passwords do not match.", errorlabel.getText());
    }

    @Test
    public void registerWithExistingEmail() throws InterruptedException {
        registerChooser.click();

        nameField.sendKeys(getSaltString());
        surnameField.sendKeys(getSaltString());
        emailField.sendKeys("laurynas.zlatkus@gmail.com");
        phoneField.sendKeys("86" + getRandomNumberString());
        dateField.sendKeys("2020");
        dateField.sendKeys(Keys.TAB);
        dateField.sendKeys("12");
        dateField.sendKeys("12");
        passwordField.sendKeys("123456");
        repeatField.sendKeys("123456");

        registerButton.click();

        sleep(1000);

        errorlabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("User with this email already exists.", errorlabel.getText());
    }

    @Test
    public void registerWithAllEmptyFields() throws InterruptedException {
        registerChooser.click();

        registerButton.click();

        sleep(1000);

        errorlabel = driver.findElement(By.xpath("/html/body/div/div/div/p"));
        Assert.assertEquals("Fill in all the fields.", errorlabel.getText());
    }

    @After
    public void closeTheTest() throws InterruptedException {
        driver.quit();
    }
}
