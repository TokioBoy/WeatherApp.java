package Pogodnik;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ResourceBundle;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField gorod;

    @FXML
    private Button pogoda;

    @FXML
    private Text infopogoda;

    @FXML
    private Text tempnow;

    @FXML
    private Text feellike;

    @FXML
    private Text Maxtemp;

    @FXML
    private Text MinTemp;

    @FXML
    private Text pressure;

    @FXML
    private ImageView img1;

    @FXML
    private Text pozelanie;

    @FXML
    private ImageView img2;

    @FXML
    void initialize() {
        pogoda.setOnAction(event -> {
            String getUserCity = gorod.getText().trim();
            if(!getUserCity.equals("")) {
                String output = getUrlContent("http://api.openweathermap.org/data/2.5/weather?q="+ getUserCity +"&appid=7e71e0840dd0f838aa183f8a5cfcbc96&units=metric");

                if (!output.isEmpty()) {
                    JSONObject obj = new JSONObject(output);
                    tempnow.setText("Температура: " + obj.getJSONObject("main").getDouble("temp"));
                    feellike.setText("Ощущается: " + obj.getJSONObject("main").getDouble("feels_like"));
                    Maxtemp.setText("Максимум: " + obj.getJSONObject("main").getDouble("temp_max"));
                    MinTemp.setText("Минимум: " + obj.getJSONObject("main").getDouble("temp_min"));
                    pressure.setText("Давление: " + obj.getJSONObject("main").getDouble("pressure"));
                }
            }
        });
    }
    private static String getUrlContent(String urlAdress) {
        StringBuffer content = new StringBuffer();

        try {
            URL url = new URL(urlAdress);
            URLConnection urlConn = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConn.getInputStream()));
            String line;

            while((line = bufferedReader.readLine()) != null) {
                content.append(line + "\n");
            }
            bufferedReader.close();
        } catch(Exception e) {
            System.out.println("Такой город был не найден!");
        }
        return content.toString();
    }

}