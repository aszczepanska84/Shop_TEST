package Cucumber;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static java.lang.System.*;

public class Zadanie1Steps {
    private WebDriver driver;

    public void main() {
    }

    @Given("uzytkownik jest zalogowany")
    public void userLogIn() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
        driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a")).click();
        driver.findElement(By.id("field-email")).sendKeys("testujaca@malpa.com");
        driver.findElement(By.id("field-password")).sendKeys("Testy123");
        driver.findElement(By.id("submit-login")).submit();
    }

    @When("uzytkownik dodaje adres do swojego konta {word} {word} {word} {word} {word}")
    public void fillAddress(String alias, String address, String city, String zipCode, String phone) {
        this.driver.findElement(By.id("addresses-link")).click();
        this.driver.findElement(By.xpath("//*[@id=\"content\"]/div[3]/a")).click();
        this.driver.findElement(By.id("field-alias")).sendKeys(alias);
        this.driver.findElement(By.id("field-address1")).sendKeys(address);
        this.driver.findElement(By.id("field-city")).sendKeys(city);
        this.driver.findElement(By.id("field-postcode")).sendKeys(zipCode);
        this.driver.findElement(By.id("field-phone")).sendKeys(phone);
        this.driver.findElement(By.className("btn-primary")).click();
    }

    @Then("adres zostaje dodany {word} {word} {word} {word} {word}")
    public void checkIfAddressWasAdded(String alias, String address, String city, String zipCode, String phone) {
        try {
            WebElement createdAddress = this.driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/div[2]/article/div[1]"));
            String aktualnyTekst = createdAddress.getText();
            Assert.assertTrue(aktualnyTekst.contains(alias));
            Assert.assertTrue(aktualnyTekst.contains(address));
            Assert.assertTrue(aktualnyTekst.contains(city));
            Assert.assertTrue(aktualnyTekst.contains(zipCode));
            Assert.assertTrue(aktualnyTekst.contains(phone));
        } catch (AssertionError e) {
            System.out.println("DANE ADRESOWE NIEPOPRAWNE" + e.getMessage());
        }
    }

    @And("adres zostaje usuniety {word} {word} {word} {word} {word}")
    public void deleteAdres(String alias, String address, String city, String zipCode, String phone) {
        try {
            this.driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/div[2]/article/div[2]/a[2]")).click();
            WebElement poleAdresow = this.driver.findElement(By.xpath("//*[@id=\"content\"]/div[2]"));
            String wszystkieAdresy = poleAdresow.getText();
            Assert.assertFalse(wszystkieAdresy.contains(alias));
            Assert.assertFalse(wszystkieAdresy.contains(address));
            Assert.assertFalse(wszystkieAdresy.contains(city));
            Assert.assertFalse(wszystkieAdresy.contains(zipCode));
            Assert.assertFalse(wszystkieAdresy.contains(phone));
        } catch (AssertionError e) {
            System.out.println("ADRES NIE ZOSTAŁ USUNIĘTY" + e.getMessage());
        }
    }
}




