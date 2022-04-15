package com.example.androidca2frontend2;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GameDataService {

    public static final String QUERY_FOR_GAMES = "https://eadgamesapi.azurewebsites.net/api/Games/genre/";
    public static final String QUERY_FOR_GAMES_BY_ID = "https://eadgamesapi.azurewebsites.net/api/Games/find/";

    Context context;
    String gameName;

    public GameDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String gameName);
    }

    public void getActionGames(String genre) {
        String url = QUERY_FOR_GAMES + "action";

        List<GameReportModel> report = new ArrayList<>();

        // Get JSON object
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {

            @Override
            public void onResponse(JSONArray response) {
                Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        MySingleton.getInstance(context).addToRequestQueue(request);
    }

    public interface GameByIDResponse {
        void onError(String message);

        void onResponse(GameReportModel gameReportModel);
    }

    public void getGameByID(String gameID, GameByIDResponse gameByIDResponse) {
        List<GameReportModel> report = new ArrayList<>();

        String url = QUERY_FOR_GAMES + gameID;
        // Get JSON object
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();

                try {
                    JSONArray gamesList = response.getJSONArray("");

                    GameReportModel grm1 = new GameReportModel();

                    JSONObject fgame = (JSONObject) gamesList.get(0);

                    grm1.setId(fgame.getInt("id"));
                    grm1.setGame(fgame.getString("game"));
                    grm1.setGenre(fgame.getString("genre"));
                    grm1.setLike(fgame.getInt("like"));

                    gameByIDResponse.onResponse(grm1);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(context).addToRequestQueue(request);
        // Get each item in the array
    }
//
//    public List<GamesReportModel> getGamesByGenre {
//
//    }

//    public void getGameByID(int gameID, GameByIDResponse gameByIDResponse) {
//        List<GameReportModel> report = new ArrayList<>();
//
//        String url = QUERY_FOR_GAMES_BY_ID + gameID;
//        // Get JSON object
//        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
//
//            @Override
//            public void onResponse(JSONObject response) {
//                //Toast.makeText(context, response.toString(), Toast.LENGTH_SHORT).show();
//
//                try {
//                    JSONArray gamesList = new JSONArray();
//
//                    GameReportModel grm1 = new GameReportModel();
//
//                    JSONObject fgame = (JSONObject) gamesList.get(1);
//
//                    grm1.setId(fgame.getInt("id"));
//                    grm1.setGame(fgame.getString("game"));
//                    grm1.setGenre(fgame.getString("genre"));
//                    grm1.setLike(fgame.getInt("like"));
//
//                    gameByIDResponse.onResponse(grm1);
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//            }
//        });
//        MySingleton.getInstance(context).addToRequestQueue(request);
//        // Get each item in the array
//    }
}
