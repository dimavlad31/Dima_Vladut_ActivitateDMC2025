package com.example.myapplication;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.*;
import java.net.*;
import java.util.HashMap;

public class ActivitateLaborator10 extends AppCompatActivity {

    EditText editCity;
    Button btnSearch;
    TextView txtResult, txtCityCode;
    Spinner spinner;

    final String API_KEY = "KTYvKEAPT7lIrmBA0IsFAkK2AGkGCsDI";
    String[] zileOptiuni = {"1 zi", "5 zile", "10 zile"};
    HashMap<String, String> mapZile = new HashMap<String, String>() {{
        put("1 zi", "1day");
        put("5 zile", "5day");
        put("10 zile", "10day");
    }};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activitate_laborator10);

        editCity = findViewById(R.id.editCity);
        btnSearch = findViewById(R.id.btnSearch);
        txtResult = findViewById(R.id.txtResult);
        spinner = findViewById(R.id.spinner);
        txtCityCode = findViewById(R.id.txtCityCode);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, zileOptiuni);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        btnSearch.setOnClickListener(v -> {
            String city = editCity.getText().toString().trim();
            if (!city.isEmpty()) {
                new GetCityCodeTask().execute(city);
            }
        });
    }

    class GetCityCodeTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... strings) {
            String city = strings[0].trim();
            HttpURLConnection conn = null;

            try {
                String encodedCity = URLEncoder.encode(city, "UTF-8");
                String urlStr = "https://dataservice.accuweather.com/locations/v1/cities/search?apikey=" +
                        API_KEY + "&q=" + encodedCity;

                URL url = new URL(urlStr);
                conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                int responseCode = conn.getResponseCode();
                if (responseCode != 200) {
                    Log.e("DEBUG_API", "Cod răspuns HTTP: " + responseCode);
                    return null;
                }

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                reader.close();

                String json = result.toString().trim();
                Log.d("DEBUG_API", "Răspuns JSON: " + json);

                if (!json.startsWith("[")) {
                    return null;
                }

                JSONArray jsonArray = new JSONArray(json);
                if (jsonArray.length() == 0) {
                    return null;
                }

                JSONObject obj = jsonArray.getJSONObject(0);
                return obj.getString("Key");

            } catch (Exception e) {
                Log.e("DEBUG_API", "Eroare: " + e.getMessage(), e);
                return null;
            } finally {
                if (conn != null) conn.disconnect();
            }
        }

        @Override
        protected void onPostExecute(String cityCode) {
            Log.d("CITY_CODE", "Codul orașului extras: " + cityCode);

            if (cityCode != null && !cityCode.isEmpty()) {
                txtCityCode.setText("City Code: " + cityCode);
                String optiune = spinner.getSelectedItem().toString();
                new GetWeatherTask().execute(cityCode, mapZile.get(optiune));
            } else {
                txtResult.setText("Orașul nu a fost găsit.");
                txtCityCode.setText("");
            }
        }
    }

    class GetWeatherTask extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            String cityCode = params[0];
            String days = params[1];
            try {
                String urlStr = "https://dataservice.accuweather.com/forecasts/v1/daily/" +
                        days + "/" + cityCode + "?apikey=" + API_KEY + "&metric=true";

                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("GET");

                BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                StringBuilder result = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) result.append(line);
                reader.close();

                JSONObject json = new JSONObject(result.toString());
                JSONArray dailyForecasts = json.getJSONArray("DailyForecasts");

                StringBuilder builder = new StringBuilder();
                for (int i = 0; i < dailyForecasts.length(); i++) {
                    JSONObject forecast = dailyForecasts.getJSONObject(i);
                    JSONObject temperature = forecast.getJSONObject("Temperature");
                    double min = temperature.getJSONObject("Minimum").getDouble("Value");
                    double max = temperature.getJSONObject("Maximum").getDouble("Value");

                    String date = forecast.getString("Date").split("T")[0];

                    builder.append("[").append(date).append("] ")
                            .append("Min: ").append(min).append("°C, ")
                            .append("Max: ").append(max).append("°C\n");
                }

                return builder.toString();

            } catch (Exception e) {
                e.printStackTrace();
                return "Eroare la obținerea vremii.";
            }
        }

        @Override
        protected void onPostExecute(String result) {
            txtResult.setText(result);
        }
    }
}
