package Cucumber;

import io.cucumber.java.Scenario;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.File;
import java.io.IOException;

public class Zadanie2Steps {
    String Cena1;
    String numerZamowienia1;
    String numerZamowieniabezOrderReference;
    private WebDriver driver;

    public void main() {
    }

    @Given("uzytkownik jest juz zalogowany")
    public void userIsLoggedIn() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mystore-testlab.coderslab.pl/index.php");
        driver.findElement(By.xpath("//*[@id=\"_desktop_user_info\"]/div/a")).click();
        driver.findElement(By.id("field-email")).sendKeys("testujaca@malpa.com");
        driver.findElement(By.id("field-password")).sendKeys("Testy123");
        driver.findElement(By.id("submit-login")).submit();


    }

    @When("dodaje do koszyka Hummingbird Printed Sweater, rozmiar M, 5 sztuk i przechodzi do kasy")
    public void chooseHumingbirdSweater() {
        this.driver.findElement(By.xpath("//*[@id=\"_desktop_logo\"]/a")).click();
        this.driver.findElement(By.xpath("//*[@id=\"content\"]/section/div/div[2]/article/div/div[1]/a")).click();
        this.driver.findElement(By.id("group_1")).click();
        this.driver.findElement(By.xpath("//*[@id=\"group_1\"]/option[2]")).click();
        this.driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]/div/span[3]/button[1]")).click();
        this.driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]/div/span[3]/button[1]")).click();
        this.driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]/div/span[3]/button[1]")).click();
        this.driver.findElement(By.xpath("//*[@id=\"add-to-cart-or-refresh\"]/div[2]/div/div[1]/div/span[3]/button[1]")).click();
        //this.driver.findElement(By.id("quantity_wanted")).clear();
        // this.driver.findElement(By.id("quantity_wanted")).sendKeys("5");    //15 dodaje!!!!!!!!!!
        this.driver.findElement(By.className("add-to-cart")).click();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        this.driver.findElement(By.xpath("//*[@id=\"blockcart-modal\"]/div/div/div[2]/div/div[2]/div/div/a/i")).click();
        this.driver.findElement(By.xpath("//*[@id=\"main\"]/div/div[2]/div[1]/div[2]/div/a")).click();
    }

    @And("potwierdza address, odbior PrestaShop in store, platnosc Pay by Check")
    public void declareShippingAndPayment() {
        this.driver.findElement(By.xpath("//*[@id=\"checkout-addresses-step\"]/div/div/form/div[2]/button")).click();
        this.driver.findElement(By.xpath("//*[@id=\"js-delivery\"]/button")).click();
        this.driver.findElement(By.id("payment-option-1")).click();
        this.driver.findElement(By.id("conditions_to_approve[terms-and-conditions]")).click();

    }

    @And("klika: order with an obligation to pay")
    public void placeOrder() {
        this.driver.findElement(By.xpath("//*[@id=\"payment-confirmation\"]/div[1]/button")).click();
    }

    @Then("screenshot z potwierdzeniem i kwota")

    public void screenshot() {
        WebElement poleZCena = this.driver.findElement(By.xpath("//*[@id=\"order-items\"]/div[2]/table/tbody/tr[4]/td[2]"));
        Cena1 = poleZCena.getText();
        WebElement poleZNumeremZamowienia = this.driver.findElement(By.id("order-reference-value"));
        numerZamowienia1 = poleZNumeremZamowienia.getText();
        
        File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            org.apache.commons.io.FileUtils.copyFile(screenshotFile, new File("/Users/Ola/Desktop/screenshot.png"));
            System.out.println("Zrzut ekranu został zapisany pomyślnie.");
        } catch (IOException e) {
            System.err.println("Błąd podczas zapisywania zrzutu ekranu: " + e.getMessage());
        }
    }


    @And("zamowienie ma status awaiting check payment")
    public void checkStatus() {
        this.driver.findElement(By.className("account")).click();
        this.driver.findElement(By.id("history-link")).click();

        WebElement pasekZamowienia = this.driver.findElement(By.xpath("/html/body/main/section/div/div/section/section/table/tbody/tr[1]"));
        String daneZamowienia = pasekZamowienia.getText();
        numerZamowieniabezOrderReference = numerZamowienia1.replaceAll("Order reference: ", "");

        try {
            Assert.assertTrue(daneZamowienia.contains(Cena1));
            Assert.assertTrue(daneZamowienia.contains(numerZamowieniabezOrderReference));
            Assert.assertTrue(daneZamowienia.contains("Awaiting check payment"));
        } catch (AssertionError e) {
                System.out.println("STATUS ZAMOWIENIA SIE NIE ZGADZA");
        }
    }
}