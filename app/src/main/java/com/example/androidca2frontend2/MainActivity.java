package com.example.androidca2frontend2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    Button action, story, racing, survival;
    ListView LView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        action = findViewById(R.id.action);
        story = findViewById(R.id.story);
        racing = findViewById(R.id.racing);
        survival = findViewById(R.id.survival);

        LView = findViewById(R.id.LView);

        GameDataService gameDataService = new GameDataService(MainActivity.this);

        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // this returned null
                gameDataService.getActionGames("action");
            }
        });

        story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameDataService.getGameByID("action", new GameDataService.GameByIDResponse() {
                    @Override
                    public void onError(String message) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResponse(GameReportModel gameReportModel) {
                        Toast.makeText(MainActivity.this, gameReportModel.toString(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        racing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked me 3", Toast.LENGTH_SHORT).show();
            }
        });

        survival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "You clicked me 4", Toast.LENGTH_SHORT).show();
            }
        });
    }
}