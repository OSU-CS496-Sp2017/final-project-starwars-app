package com.example.cs496finalproject.finalprojectstarwars.utils;

import android.net.Uri;
import android.text.TextUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by pranavramesh on 6/9/17.
 */

public class StarWarsUtils {
    private final static String SW_SEARCH_BASE_URL = "swapi.co";
    private final static String SW_SEARCH_QUERY_PARAM = "search";
    private final static String SW_SEARCH_QUERY_PLANETS = "planets";
    private final static String SW_SEARCH_QUERY_PEOPLES = "people";
    private final static String SW_SEARCH_QUERY_VEHICLES = "vehicles";
    //private final static String SW_SEARCH_SORT_PARAM = "sort";

    public static class SearchResult implements Serializable {
        public static final String EXTRA_SEARCH_RESULT = "StarWarsUtils.SearchResult";
        public String name;
        public String diameter;
    }

    //Call this function twice with two different strings to compare
    public static String buildSWSearchURL(String search, String thing) {
        Uri.Builder builder = new Uri.Builder();
        if (thing.compareToIgnoreCase("planets") == 0){
            builder.scheme("https")
                    .authority(SW_SEARCH_BASE_URL)
                    .appendPath("api")
                    .appendPath(SW_SEARCH_QUERY_PLANETS)
                    .appendPath("")
                    .appendQueryParameter(SW_SEARCH_QUERY_PARAM, search);
        }
        else if (thing.compareToIgnoreCase("people") == 0){

            builder.scheme("https")
                    .authority(SW_SEARCH_BASE_URL)
                    .appendPath("api")
                    .appendPath(SW_SEARCH_QUERY_PEOPLES)
                    .appendPath("")
                    .appendQueryParameter(SW_SEARCH_QUERY_PARAM, search);
        }
        else if (thing.compareToIgnoreCase("vehicles") == 0){
            builder.scheme("https")
                    .authority(SW_SEARCH_BASE_URL)
                    .appendPath("api")
                    .appendPath(SW_SEARCH_QUERY_VEHICLES)
                    .appendPath("")
                    .appendQueryParameter(SW_SEARCH_QUERY_PARAM, search);
        }

        return builder.build().toString();
    }

    public static ArrayList<SearchResult> parseStarWarsSearchResultsJSON(String searchResultsJSON) {
        try {
            JSONObject searchResultsObj = new JSONObject(searchResultsJSON);
            JSONArray searchResultsItems = searchResultsObj.getJSONArray("items");

            ArrayList<SearchResult> searchResultsList = new ArrayList<SearchResult>();
            for (int i = 0; i < searchResultsItems.length(); i++) {
                SearchResult searchResult = new SearchResult();
                JSONObject searchResultItem = searchResultsItems.getJSONObject(i);
                searchResult.name = searchResultItem.getString("name");
                searchResult.diameter = searchResultItem.getString("diameter");
                searchResultsList.add(searchResult);
            }
            return searchResultsList;
        } catch (JSONException e) {
            return null;
        }
    }
}
