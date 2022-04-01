package executores;

import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import robos.API;
import robos.Cadastro;
import robos.Robo;

import java.util.concurrent.TimeUnit;

@DisplayName("Classe de teste para a pagina inicial")
public class PaginaInicialTest {

    //Testes Compras
    @Test
    @DisplayName("Validar Abrir Carrinho Vazio")
    public void validarAbrirCarrinhoVazio() {

        Robo testeRobo = new Robo();

        testeRobo.acessarSite("http://automationpractice.com/");
        testeRobo.abrirCarrinho();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(true,testeRobo.validarRoboTexto("Your shopping cart is empty."));

    }

    @Test
    @DisplayName("Validar Carrinho Vazio")
    public void validarCarrinhoVazio() {

        Robo testeRobo = new Robo();

        testeRobo.acessarSite("http://automationpractice.com/");

        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(true,testeRobo.validarRoboTexto("(empty)"));

    }

    @Test
    @DisplayName("Validar link Women")
    public void validarlinkWomenProdutos() {

        Robo testeRobo = new Robo();

        testeRobo.acessarSite("http://automationpractice.com/");
        testeRobo.clicaLink("http://automationpractice.com/index.php?id_category=3&controller=category");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String tituloWoman = testeRobo.CHDriver.getTitle();
        Assertions.assertEquals("Women - My Store",testeRobo.CHDriver.getTitle());
    }

    @Test
    @DisplayName("Validar link Catalog")
    public void validarCatalog() {

        Robo testeRobo = new Robo();

        testeRobo.acessarSite("http://automationpractice.com/");
        testeRobo.clicaLink("http://automationpractice.com/index.php?id_category=3&controller=category");
        testeRobo.selectCheckBox("layered_category_4");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String tituloWoman = testeRobo.CHDriver.getTitle();
        Assertions.assertEquals("Women > Categories Tops - My Store",testeRobo.CHDriver.getTitle());
    }

    @Test
    @DisplayName("Validar link Catalog filtros")
    public void validarCatalogFiltros() {

        Robo testeRobo = new Robo();

        testeRobo.acessarSite("http://automationpractice.com/");
        testeRobo.clicaLink("http://automationpractice.com/index.php?id_category=3&controller=category");
        testeRobo.selectCheckBox("layered_category_4");
        testeRobo.selectCheckBox("layered_id_attribute_group_2");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String tituloWoman = testeRobo.CHDriver.getTitle();
        Assertions.assertEquals("Women > Categories Tops > Size M - My Store",testeRobo.CHDriver.getTitle());
    }

    @Test
    @DisplayName("Validar Carrinho 1 Compra")
    public void validarCarrinho1Compra() {

        Robo testeRobo = new Robo();

        testeRobo.acessarSite("http://automationpractice.com/");
        testeRobo.fazerCompra("Printed Chiffon Dress","Green", 1,"M");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(true,testeRobo.validarRoboTexto("1 Product"));

    }

    @Test
    @DisplayName("Validar Carrinho 1 Compra Removida")
    public void validarCarrinho1CompraRemovida() {

        Robo testeRobo = new Robo();

        testeRobo.acessarSite("http://automationpractice.com/");
        testeRobo.fazerCompra("Printed Chiffon Dress","Green", 1,"M");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        testeRobo.removeItem();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(true,testeRobo.validarRoboTexto("Your shopping cart is empty."));

    }

    @Test
    @DisplayName("Validar Carrinho 5 Compras - Total de itens") // Exercício Compra itens específicos
    public void validarCarrinho5CompraItens() {

        Robo testeRobo = new Robo();

        testeRobo.acessarSite("http://automationpractice.com/");
        testeRobo.fazerCompra("Printed Chiffon Dress","Green", 1,"M");
        testeRobo.fazerCompra("Faded Short Sleeve T-shirts","Blue", 1, "na");
        testeRobo.fazerCompra("Blouse","na", 2, "na");
        testeRobo.fazerCompra("Printed Dress","na", 1, "na");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(true,testeRobo.validarRoboTexto("5 Products"));
    }

    @Test
    @DisplayName("Validar Carrinho 5 Compras - Valor Total") // Exercício Compra itens específicos
    public void validarCarrinho5CompraTotalValor() {

        Robo testeRobo = new Robo();

        testeRobo.acessarSite("http://automationpractice.com/");
        testeRobo.fazerCompra("Printed Chiffon Dress","Green", 1,"M");
        testeRobo.fazerCompra("Faded Short Sleeve T-shirts","Blue", 1, "na");
        testeRobo.fazerCompra("Blouse","na", 2, "na");
        testeRobo.fazerCompra("Printed Dress","na", 1, "na");
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(true,testeRobo.validarRoboTexto("$139.90"));
    }

    //Testes Cadastro

    @Test
    @DisplayName("Validar página do cadastro pré cadastrado")
    public void validarPreCadastro() {
        Cadastro cadastroRobo = new Cadastro();

        cadastroRobo.acessarSite("http://automationpractice.com/");
        cadastroRobo.email = "joaopinheiro84@gmail.com";

        cadastroRobo.entraCadastro();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(true, cadastroRobo.ValidarTexto("Authentication"));
    }

    @Test
    @DisplayName("Validar página do cadastro  Email já cadastrado")
    public void validarEmailJaCadastrado() {
        Cadastro cadastroRobo = new Cadastro();

        cadastroRobo.acessarSite("http://automationpractice.com/");
        cadastroRobo.email = "joaopinheiro84@gmail.com";

        cadastroRobo.entraCadastro();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(true, cadastroRobo.ValidarTexto("An account using this email address has already been registered"));
    }

    @Test
    @DisplayName("Validar página do cadastro  Email Invalido")
    public void validarEmailInvalido() {
        Cadastro cadastroRobo = new Cadastro();

        cadastroRobo.acessarSite("http://automationpractice.com/");
        cadastroRobo.email = "Joaop84ARROBAgmail.com";

        cadastroRobo.entraCadastro();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assertions.assertEquals(true, cadastroRobo.ValidarTexto("Invalid email address"));
    }

    @Test
    @DisplayName("Validar página do cadastro firstName lastName invalido 2 erros")
    public void validarFirstLastNameInvalido() {
        Cadastro cadastroRobo = new Cadastro();

        cadastroRobo.acessarSite("http://automationpractice.com/");

        cadastroRobo.firstName="J040";
        cadastroRobo.lastName="Pinheiro123";
        cadastroRobo.zipCode="00000";
        cadastroRobo.cadastraUsuario();
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Assertions.assertEquals(true, cadastroRobo.ValidarTexto("There are 2 errors"));
    }

    @Test
    @DisplayName("Validar página do cadastro ZipCode invalido")
    public void validarZipCodeInvalido() {
        Cadastro cadastroRobo = new Cadastro();

        cadastroRobo.acessarSite("http://automationpractice.com/");

        cadastroRobo.zipCode = "12345678";
        cadastroRobo.cadastraUsuario();


        Assertions.assertEquals(true, cadastroRobo.ValidarTexto("The Zip/Postal code you've entered is invalid. It must follow this format: 00000"));
    }

    @Test
    @DisplayName("Validar página do cadastro Telefone faltando")
    public void validarTelefoneFaltando() {
        Cadastro cadastroRobo = new Cadastro();

        cadastroRobo.acessarSite("http://automationpractice.com/");

        cadastroRobo.cadastraUsuario();


        Assertions.assertEquals(true, cadastroRobo.ValidarTexto("You must register at least one phone number"));
    }

    @Test
    @DisplayName("Validar página do cadastro Ano Nascimento")
    public void validarAnoNasc() {
        Cadastro cadastroRobo = new Cadastro();

        cadastroRobo.acessarSite("http://automationpractice.com/");
        cadastroRobo.ano="1984";
        cadastroRobo.cadastraUsuario();


        Assertions.assertEquals(true, cadastroRobo.ValidarTexto("1984"));
    }
    //Test API

    @Test
    @DisplayName("Validar página do cadastro Mes Nascimento")
    public void validarMesNasc() {
        Cadastro cadastroRobo = new Cadastro();

        cadastroRobo.acessarSite("http://automationpractice.com/");

        cadastroRobo.cadastraUsuario();


        Assertions.assertEquals(true, cadastroRobo.ValidarTexto("Augusto "));
    }

    //Test API

    @Test
    @DisplayName("API")
    public void validarAPIStatus200(){
        API APIDriver = new API();
        APIDriver.apiGet("https://reqres.in/api/users", "/2");

        Assertions.assertEquals("HTTP/1.1 200 OK", APIDriver.statusResult);

    }

    @Test
    @DisplayName("API 404")
    public void validarAPINotFound404(){
        API APIDriver = new API();
        APIDriver.apiGet("https://reqres.in/api/users", "/a");

        Assertions.assertEquals("HTTP/1.1 404 Not Found", APIDriver.statusResult);

    }

    @Test
    @DisplayName("API 200")
    public void validarAPIResponse(){
        API APIDriver = new API();
        APIDriver.apiGet("https://reqres.in/api/users", "/2");

        Assert.assertTrue(APIDriver.bodyResult.contains("janet.weaver@reqres.in"));
    }

    @Test
    @DisplayName("API Post 200")
    public void validarAPIPost200(){
        API APIDriver = new API();

        try {
            APIDriver.apiPost("https://reqres.in/api/register", "{\n    \"email\": \"eve.holt@reqres.in\",\n    \"password\": \"pistol\"\n}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(APIDriver.ResponseCodePost.contains("200"));
    }

    @Test
    @DisplayName("API Post 400")
    public void validarAPIPost400(){
        API APIDriver = new API();

        try {
            APIDriver.apiPost("https://reqres.in/api/register", "{\n    \"Xemail\": \"eve.holt@reqres.in\",\n    \"password\": \"pistol\"\n}");
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.assertTrue(APIDriver.ResponseCodePost.contains("400"));
    }



}
