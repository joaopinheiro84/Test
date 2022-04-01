package robos;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Robo {

    public ChromeDriver CHDriver;

    public Robo(){
        //Construtor - executa no new
        WebDriverManager.chromedriver().setup();
        CHDriver = new ChromeDriver();
    }

    public void acessarSite(String site){
        CHDriver.get(site);
    }

    //Fazer Compra
    public void fazerCompra(String Item, String cor, int QuantidadeSolicitada, String Tamanho) {

        //Localizar Item
        System.out.println("Iniciando compra do Item: "+ Item);
        CHDriver.findElement(By.id("search_query_top")).clear();
        CHDriver.findElement(By.id("search_query_top")).sendKeys(Item);
        CHDriver.findElement(By.name("submit_search")).click();
        CHDriver.findElement(By.cssSelector("[title='"+ Item +"']")).click();

        alteraCor(cor);

        //Alterar Tamanho
        if (Tamanho != "na"){
            Select selectDays = new Select(CHDriver.findElement(By.name("group_1")));
            selectDays.selectByVisibleText(Tamanho);
            System.out.println("Tamanho Alterado para: "+ Tamanho);
        }else{
            System.out.println("Compra sem tamanho definido pelo usuário para o Item: "+ Item +"");
        }

        //Alterar Quantidade
        for (int i = 1; i < QuantidadeSolicitada; i++) {
            CHDriver.findElement(By.cssSelector("[class='icon-plus']")).click();
            System.out.println("Quantidade do item: "+ Item +" foi alterado para: "+ QuantidadeSolicitada);
        }

        //Adicionar ao Cart
        CHDriver.findElement(By.name("Submit")).click();
        try {
            TimeUnit.SECONDS.sleep(12);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //Prosseguir para check out
        CHDriver.findElement(By.xpath("//a[@class='btn btn-default button button-medium']")).click();


        System.out.println("-------Final do ciclo do produto: "+ Item);
    }

    //Validar Texto em tela
    public boolean validarRoboTexto(String validarTexto){
        return CHDriver.getPageSource().contains(validarTexto);
    }
    //Validar Tamanho da roupa
    public String validarTamanho(String Tamanho){
        WebElement TamanhoAtual = CHDriver.findElement(By.id("group_1"));

        String selected = TamanhoAtual.getText();
        System.out.println("-------Final do ciclo do produto: "+ Tamanho);
        List<WebElement> options = TamanhoAtual.findElements(By.tagName("option"));

        Select selecionado = new Select(TamanhoAtual);
        selecionado.selectByVisibleText(selected);

        return Tamanho;
    }

    //Abre o carrinho de compras
    public void abrirCarrinho(){
        CHDriver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=order']")).click();
    }

    public String alteraCor(String cor) {
        //Alterar Cor
        String corFinal = "";

        if (cor != "na") {
            CHDriver.findElement(By.xpath("//a[@name='"+ cor +"']")).click();
        }
        else{
            System.out.println("Compra sem cor definida pelo usuário");
        }

        return corFinal;
    }

    public void removeItem(){
        CHDriver.findElement(By.xpath("//a[@href='http://automationpractice.com/index.php?controller=cart&delete=1&id_product=7&ipa=38&id_address_delivery=0&token=e817bb0705dd58da8db074c69f729fd8']")).click();
    }

    public String clicaLink(String link){
        CHDriver.findElement(By.xpath("//a[@href='"+link+"']")).click();

        return link;
    }

    public String selectCheckBox(String selecionar){
        CHDriver.findElement(By.xpath("//input[@name='"+selecionar+"']")).click();

        return selecionar;
    }
}
