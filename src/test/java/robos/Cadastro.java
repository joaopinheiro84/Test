package robos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Cadastro {

    public String email     = "Meu@emailFake.com";
    public String firstName = "João";
    public String lastName  = "Pinheiro";
    public String dia = "31";
    public String mes = "August ";
    public String ano = "1984";
    public String zipCode = "";
    public String phone = "";
    public String phone_mobile = "";
    public ChromeDriver CHDriver;

    public Cadastro(){
        //Construtor - executa no new
        WebDriverManager.chromedriver().setup();
        CHDriver = new ChromeDriver();
    }

    public void acessarSite(String site){
        CHDriver.get(site);
    }

    public void cadastraUsuario(){

        //Abre Menu Sign in

        CHDriver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=my-account']")).click();

        //email
        CHDriver.findElement(By.id("email_create")).sendKeys(email);

        CHDriver.findElement(By.id("SubmitCreate")).click();

        //Preenchimento de cadastro

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Title
        CHDriver.findElement(By.xpath("//input[@id='id_gender1']")).click();

        //First Name
        CHDriver.findElement(By.id("customer_firstname")).sendKeys(firstName);

        //Last Name
        CHDriver.findElement(By.id("customer_lastname")).sendKeys(lastName);

        //Password
        CHDriver.findElement(By.id("passwd")).sendKeys("xxxxx");

        //Date of Birth
        Select selectDays = new Select(CHDriver.findElement(By.name("days")));
        selectDays.selectByValue(dia);

        Select selectMonths = new Select(CHDriver.findElement(By.name("months")));
        selectMonths.selectByVisibleText(mes);

        Select selectYears = new Select(CHDriver.findElement(By.name("years")));
        selectYears.selectByValue(ano);

        //Checkbox
        CHDriver.findElement(By.xpath("//input[@id='newsletter']")).click();
        CHDriver.findElement(By.xpath("//input[@id='optin']")).click();

        //YOUR ADDRESS
        CHDriver.findElement(By.id("company")).sendKeys("DBC Company");
        CHDriver.findElement(By.id("address1")).sendKeys("Avenida da DBC Company");
        CHDriver.findElement(By.id("address2")).sendKeys("Prédio1");
        CHDriver.findElement(By.id("city")).sendKeys("São Paulo");

        String state= "Alaska";
        Select selectIdState = new Select(CHDriver.findElement(By.name("id_state")));
        selectIdState.selectByVisibleText(state);

        CHDriver.findElement(By.id("postcode")).sendKeys(zipCode);

        Select selectCountry = new Select(CHDriver.findElement(By.name("id_country")));
        selectCountry.selectByVisibleText("United States");

        CHDriver.findElement(By.id("other")).sendKeys("Informações adicionais");

        CHDriver.findElement(By.id("phone")).sendKeys(phone);
        CHDriver.findElement(By.id("phone_mobile")).sendKeys(phone_mobile);

        CHDriver.findElement(By.id("alias")).clear();
        CHDriver.findElement(By.id("alias")).sendKeys("João São Paulo DBC Company");

        //Registrar
        CHDriver.findElement(By.xpath("//button[@id='submitAccount']")).click();

    }

    public boolean ValidarTexto(String validarTexto){
        return CHDriver.getPageSource().contains(validarTexto);
    }

    public void entraCadastro(){
        CHDriver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=my-account']")).click();

        CHDriver.findElement(By.id("email_create")).sendKeys(email);
        CHDriver.findElement(By.id("SubmitCreate")).click();
    }

}