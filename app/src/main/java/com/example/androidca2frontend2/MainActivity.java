package com.example.androidca2frontend2;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    Button changeLang, games, action, adventure, fps, story, survival, racing, search, like, console;
    private TextView gameResult;
    private TextView consolesResult;
    private EditText dataInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        loadLocale();
        setContentView(R.layout.activity_main);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(getResources().getString(R.string.app_name));

        games = findViewById(R.id.games);
        gameResult = findViewById(R.id.gameResult);
        consolesResult = findViewById(R.id.consolesResult);
        search = findViewById(R.id.search);
        dataInput = findViewById(R.id.dataInput);
        like = findViewById(R.id.like);
        console = findViewById(R.id.console);

        action = findViewById(R.id.action);
        adventure = findViewById(R.id.adventure);
        fps = findViewById(R.id.fps);
        story = findViewById(R.id.story);
        survival = findViewById(R.id.survival);
        racing = findViewById(R.id.racing);
        changeLang = findViewById(R.id.changeLang);

//        LView = findViewById(R.id.LView);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://eadgamesapi.azurewebsites.net/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI.class);

        changeLang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showChangeLanguageDialog();
            }
        });

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
                    content += games1.getGame() + ", ";
                    content += games1.getGenre() + ", ";
                    content += "Likes: " + games1.getLike() + "\n\n";

                    gameResult.append(content);
                    gameResult.setMovementMethod(new ScrollingMovementMethod());
                }
            }
            // Checks if unsuccessful and displays error
            @Override
            public void onFailure(Call<List<Games>> call, Throwable t) {
                gameResult.setText(t.getMessage());
            }
        });

        //get api/consoles
        console.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameResult.setText("");
                consolesResult.setText("");
                Call<List<Consoles>> callConsoles = jsonPlaceHolderAPI.getConsoles();
                callConsoles.enqueue(new Callback<List<Consoles>>() {
                    @Override
                    public void onResponse(Call<List<Consoles>> callConsoles, Response<List<Consoles>> response) {
                        if (!response.isSuccessful()) {
                            consolesResult.setText("Code: " + response.code());
                            return;
                        }

                        List<Consoles> consoles = response.body();

                        for (Consoles allConsoles : consoles) {
                            String content = "";
                            content += allConsoles.getId() + ", ";
                            content += allConsoles.getName() + "\n\n";

                            consolesResult.append(content);
                            consolesResult.setMovementMethod(new ScrollingMovementMethod());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Consoles>> callConsoles, Throwable t) {
                        consolesResult.setText(t.getMessage());
                    }
                });
            }
        });

        //get api/games by filter
        games.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameResult.setText("");
                consolesResult.setText("");
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
                            content += games1.getGame() + ", ";
                            content += games1.getGenre() + ", ";
                            content += "Likes: " + games1.getLike() + "\n\n";

                            gameResult.append(content);
                            gameResult.setMovementMethod(new ScrollingMovementMethod());
                        }
                    }
                    // Checks if unsuccessful and displays error
                    @Override
                    public void onFailure(Call<List<Games>> call, Throwable t) {
                        gameResult.setText(t.getMessage());
                    }
                });
            }
        });

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameResult.setText("");
                consolesResult.setText("");
                Call<List<Games>> callGameByName = jsonPlaceHolderAPI.getGameByName(dataInput.getText().toString());
                callGameByName.enqueue(new Callback<List<Games>>() {
                    // Checks if successful and displays
                    @Override
                    public void onResponse(Call<List<Games>> callGameByName, Response<List<Games>> response) {
                        if (!response.isSuccessful()) {
                            dataInput.setText("Code: " + response.code());
                            return;
                        }

                        // Gets JSON Objects
                        List<Games> games = response.body();

                        // Iterates through each JSON item
                        for (Games games1 : games) {
                            String content = "";
                            content += games1.getGame() + ", ";
                            content += games1.getGenre() + ", ";
                            content += "Likes: " + games1.getLike() + "\n\n";

                            gameResult.append(content);
                            gameResult.setMovementMethod(new ScrollingMovementMethod());
                        }
                    }
                    // Checks if unsuccessful and displays error
                    @Override
                    public void onFailure(Call<List<Games>> callGameByName, Throwable t) {
                        dataInput.setText(t.getMessage());
                    }
                });
            }
        });

        like.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameResult.setText("");
                consolesResult.setText("");
                Call<List<Games>> likeGame = jsonPlaceHolderAPI.likeGame(dataInput.getText().toString());
                likeGame.enqueue(new Callback<List<Games>>() {
                    // Checks if successful and displays
                    @Override
                    public void onResponse(Call<List<Games>> likeGame, Response<List<Games>> response) {
                        if (!response.isSuccessful()) {
                            dataInput.setText("Code: " + response.code());
                            return;
                        }
                    }
                    // Checks if unsuccessful and displays error
                    @Override
                    public void onFailure(Call<List<Games>> likeGame, Throwable t) {
                        dataInput.setText(t.getMessage());
                    }
                });
                //
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
                            content += games1.getGame() + ", ";
                            content += games1.getGenre() + ", ";
                            content += "Likes: " + games1.getLike() + "\n\n";

                            gameResult.append(content);
                            gameResult.setMovementMethod(new ScrollingMovementMethod());
                        }
                    }
                    // Checks if unsuccessful and displays error
                    @Override
                    public void onFailure(Call<List<Games>> call, Throwable t) {
                        gameResult.setText(t.getMessage());
                    }
                });
            }
        });

        //filters
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameResult.setText("");
                consolesResult.setText("");
                String buttonText = "action";
                Call<List<Games>> filterGame = jsonPlaceHolderAPI.filterGame(buttonText);
                filterGame.enqueue(new Callback<List<Games>>() {
                    @Override
                    public void onResponse(Call<List<Games>> callGameByName, Response<List<Games>> response) {
                        if (!response.isSuccessful()) {
                            dataInput.setText("Code: " + response.code());
                            return;
                        }
                        List<Games> games = response.body();

                        for (Games games1 : games) {
                            String content = "";
                            content += games1.getGame() + ", ";
                            content += games1.getGenre() + ", ";
                            content += "Likes: " + games1.getLike() + "\n\n";

                            gameResult.append(content);
                            gameResult.setMovementMethod(new ScrollingMovementMethod());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Games>> callGameByName, Throwable t) {
                        dataInput.setText(t.getMessage());
                    }
                });
            }
        });

        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameResult.setText("");
                consolesResult.setText("");
                String buttonText = "adventure";
                Call<List<Games>> filterGame = jsonPlaceHolderAPI.filterGame(buttonText);
                filterGame.enqueue(new Callback<List<Games>>() {
                    @Override
                    public void onResponse(Call<List<Games>> callGameByName, Response<List<Games>> response) {
                        if (!response.isSuccessful()) {
                            dataInput.setText("Code: " + response.code());
                            return;
                        }
                        List<Games> games = response.body();

                        for (Games games1 : games) {
                            String content = "";
                            content += games1.getGame() + ", ";
                            content += games1.getGenre() + ", ";
                            content += "Likes: " + games1.getLike() + "\n\n";

                            gameResult.append(content);
                            gameResult.setMovementMethod(new ScrollingMovementMethod());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Games>> callGameByName, Throwable t) {
                        dataInput.setText(t.getMessage());
                    }
                });
            }
        });

        fps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameResult.setText("");
                consolesResult.setText("");
                String buttonText = "fps";
                Call<List<Games>> filterGame = jsonPlaceHolderAPI.filterGame(buttonText);
                filterGame.enqueue(new Callback<List<Games>>() {
                    @Override
                    public void onResponse(Call<List<Games>> callGameByName, Response<List<Games>> response) {
                        if (!response.isSuccessful()) {
                            dataInput.setText("Code: " + response.code());
                            return;
                        }
                        List<Games> games = response.body();

                        for (Games games1 : games) {
                            String content = "";
                            content += games1.getGame() + ", ";
                            content += games1.getGenre() + ", ";
                            content += "Likes: " + games1.getLike() + "\n\n";

                            gameResult.append(content);
                            gameResult.setMovementMethod(new ScrollingMovementMethod());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Games>> callGameByName, Throwable t) {
                        dataInput.setText(t.getMessage());
                    }
                });
            }
        });

        story.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameResult.setText("");
                consolesResult.setText("");
                String buttonText = "story";
                Call<List<Games>> filterGame = jsonPlaceHolderAPI.filterGame(buttonText);
                filterGame.enqueue(new Callback<List<Games>>() {
                    @Override
                    public void onResponse(Call<List<Games>> callGameByName, Response<List<Games>> response) {
                        if (!response.isSuccessful()) {
                            dataInput.setText("Code: " + response.code());
                            return;
                        }
                        List<Games> games = response.body();

                        for (Games games1 : games) {
                            String content = "";
                            content += games1.getGame() + ", ";
                            content += games1.getGenre() + ", ";
                            content += "Likes: " + games1.getLike() + "\n\n";

                            gameResult.append(content);
                            gameResult.setMovementMethod(new ScrollingMovementMethod());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Games>> callGameByName, Throwable t) {
                        dataInput.setText(t.getMessage());
                    }
                });
            }
        });

        survival.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameResult.setText("");
                consolesResult.setText("");
                String buttonText = "survival";
                Call<List<Games>> filterGame = jsonPlaceHolderAPI.filterGame(buttonText);
                filterGame.enqueue(new Callback<List<Games>>() {
                    @Override
                    public void onResponse(Call<List<Games>> callGameByName, Response<List<Games>> response) {
                        if (!response.isSuccessful()) {
                            dataInput.setText("Code: " + response.code());
                            return;
                        }
                        List<Games> games = response.body();

                        for (Games games1 : games) {
                            String content = "";
                            content += games1.getGame() + ", ";
                            content += games1.getGenre() + ", ";
                            content += "Likes: " + games1.getLike() + "\n\n";

                            gameResult.append(content);
                            gameResult.setMovementMethod(new ScrollingMovementMethod());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Games>> callGameByName, Throwable t) {
                        dataInput.setText(t.getMessage());
                    }
                });
            }
        });

        racing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gameResult.setText("");
                consolesResult.setText("");
                String buttonText = "racing";
                Call<List<Games>> filterGame = jsonPlaceHolderAPI.filterGame(buttonText);
                filterGame.enqueue(new Callback<List<Games>>() {
                    @Override
                    public void onResponse(Call<List<Games>> callGameByName, Response<List<Games>> response) {
                        if (!response.isSuccessful()) {
                            dataInput.setText("Code: " + response.code());
                            return;
                        }
                        List<Games> games = response.body();

                        for (Games games1 : games) {
                            String content = "";
                            content += games1.getGame() + ", ";
                            content += games1.getGenre() + ", ";
                            content += "Likes: " + games1.getLike() + "\n\n";

                            gameResult.append(content);
                            gameResult.setMovementMethod(new ScrollingMovementMethod());
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Games>> callGameByName, Throwable t) {
                        dataInput.setText(t.getMessage());
                    }
                });
            }
        });
    }

    private void showChangeLanguageDialog() {
        final String[] listLangs = {"Bosnian", "Russian", "English"};
        AlertDialog.Builder mBuilder = new AlertDialog.Builder(MainActivity.this);
        mBuilder.setTitle("Choose Language...");
        mBuilder.setSingleChoiceItems(listLangs, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (i == 0){
                    // Bosnian
                    setLocale("bs");
                    recreate();
                }
                else if (i == 1){
                    // Russian
                    setLocale("ru");
                    recreate();
                }
                else if (i == 2){
                    // English
                    setLocale("en");
                    recreate();
                }

                dialogInterface.dismiss();
            }
        });

        AlertDialog mDialog = mBuilder.create();

        mDialog.show();
    }

    private void setLocale(String lang) {
        Locale locale = new Locale(lang);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        // Save data
        SharedPreferences.Editor editor = getSharedPreferences("Settings", MODE_PRIVATE).edit();
        editor.putString("My_Lang", lang);
        editor.apply();
    }

    public void loadLocale() {
        SharedPreferences prefs = getSharedPreferences("Settings", Activity.MODE_PRIVATE);
        String language = prefs.getString("My_Lang", "");
        setLocale(language);

    }
}