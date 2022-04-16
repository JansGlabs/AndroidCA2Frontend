package com.example.androidca2frontend2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.ColorSpace;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

//import com.android.volley.Request;
//import com.android.volley.RequestQueue;
//import com.android.volley.Response;
//import com.android.volley.VolleyError;
//import com.android.volley.toolbox.JsonArrayRequest;
//import com.android.volley.toolbox.StringRequest;
//import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button action, story, racing, survival;
    private TextView gameResult;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        action = findViewById(R.id.action);
        gameResult = findViewById(R.id.gameResult);
//        story = findViewById(R.id.story);
//        racing = findViewById(R.id.racing);
//        survival = findViewById(R.id.survival);
//
//        LView = findViewById(R.id.LView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eadgamesapi.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI.class);

        // Callback
        Call<List<Games>> call = jsonPlaceHolderAPI.getGames();
        call.enqueue(new Callback<List<Games>>() {
            // Checks if successful and displays
            @Override
            public void onResponse(Call<List<Games>> call, Response<List<Games>> response) {
                if (!response.isSuccessful()) {
                    gameResult.setText("Code: " + response.code());
                    return;
                }

                // Gets JSON Objects
                List<Games> games = response.body();

                // Iterates through each JSON item
                for (Games games1 : games) {
                    String content = "";
                    content += games1.getId() + ",\t";
                    content += games1.getGame() + ",\t";
                    content += games1.getGenre() + ",\t";
                    content += "Likes: " + games1.getLike() + "\n\n";

                    gameResult.append(content);
                }
            }
            // Checks if unsuccessful and displays error
            @Override
            public void onFailure(Call<List<Games>> call, Throwable t) {
                gameResult.setText(t.getMessage());
            }
        });
    }
}