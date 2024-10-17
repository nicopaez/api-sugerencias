package com.nicopaez.apisugerencias.proveedores;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.nicopaez.apisugerencias.dominio.ProveedorTemperatura;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.util.HashMap;
import java.util.Map;

public class ProveedorTemperaturaOpenWeather implements ProveedorTemperatura {
    //final static String ENDPOINT = "http://api.openweathermap.org/data/2.5/weather";

    private final String openweatherBaseurl;

    public ProveedorTemperaturaOpenWeather(String openweatherBaseurl) {
        this.openweatherBaseurl = openweatherBaseurl;
    }
    @Override
    public Integer getTemperaturaEnCelcius() throws RuntimeException {
        OkHttpClient client = new OkHttpClient();
        ObjectMapper mapper = new ObjectMapper();

        String apiKey = System.getenv("API_KEY");

        String url = openweatherBaseurl + "?lat=45&lon=36&APPID=" + apiKey; //"?lat=#{@lat}&lon=#{@lon}&APPID=#{@api_key};

        Request request = new Request.Builder()
                .url(url)
                .get()
                .build();

        try {
            Map<String, Map<String, Object>> result;
            Response response = client.newCall(request).execute();
                ObjectReader reader = mapper.readerFor(Map.class);
                result = reader.readValue(response.body().string());
            String temperaturaEnKelvin = result.get("main").get("temp").toString();
            Float temperaturaEnCelcius = Float.parseFloat(temperaturaEnKelvin) - 273.15F;
            return temperaturaEnCelcius.intValue();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
