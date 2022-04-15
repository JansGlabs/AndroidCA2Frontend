package com.example.androidca2frontend2;

import android.content.Context;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GameDataService {

    public static final String QUERY_FOR_GAMES = "https://eadgamesapi.azurewebsites.net/api/Games/genre/";

    Context context;
    String gameName;

    public GameDataService(Context context) {
        this.context = context;
    }

    public interface VolleyResponseListener {
        void onError(String message);

        void onResponse(String gameName);
    }

    public void getActionGames(VolleyResponseListener volleyResponseListener) {
        String url = QUERY_FOR_GAMES + "action";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                gameName = "";
                try {
                    JSONObject game = response.getJSONObject(0);
                    gameName = game.getString("game");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                // This worked but didn't return
                //Toast.makeText(context, "Game Name: " + gameName, Toast.LENGTH_SHORT).show();
                volleyResponseListener.onResponse(gameName);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show();
                volleyResponseListener.onError("Something went wrong");
            }
        });

        // Add a request (in this example, called stringRequest) to your RequestQueue.
        MySingleton.getInstance(context).addToRequestQueue(request);

        // returned a NULL
        //return gameName;
    }

//    public List<GamesReportModel> getGameByName {
//
//    }
//
//    public List<GamesReportModel> getGamesByGenre {
//
//    }
}
