package robos;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class API {
    public String URI               = "";   // https://reqres.in/api/users
    public String parametro         = "";   // /2
    public String bodyResult        = "";
    public String statusResult      = "";
    public String ResponseCodePost  = "";

    public ChromeDriver APIDriver;

    public API(){
        //Construtor - executa no new
        WebDriverManager.chromedriver().setup();
        APIDriver = new ChromeDriver();
    }

    public String apiGet(String URI, String parametro){
        RestAssured.baseURI = URI;

        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get(parametro);
        ResponseBody body = response.getBody();

        System.out.println(response.getStatusLine());
        System.out.println(body.asString());

        bodyResult = body.asString();
        statusResult =  response.getStatusLine();

        return statusResult;

    }

    public String apiPost(String BaseUrl, String parametro) throws Exception{
        URL url = new URL(BaseUrl);
        HttpURLConnection http = (HttpURLConnection)url.openConnection();
        http.setRequestMethod("POST");
        http.setDoOutput(true);
        http.setRequestProperty("Content-Type", "application/json");

        String data = parametro;

        byte[] out = data.getBytes(StandardCharsets.UTF_8);

        OutputStream stream = http.getOutputStream();
        stream.write(out);

        System.out.println(http.getResponseCode() + " " + http.getResponseMessage());
        http.disconnect();



        ResponseCodePost = http.getResponseCode() + " " + http.getResponseMessage();
        return ResponseCodePost;

    }

}
