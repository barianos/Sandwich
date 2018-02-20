package com.udacity.sandwichclub.utils;


import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private JSONArray ingredients;

    public static Sandwich parseSandwichJson(String json) {
         try {
            JSONObject mainObject = new JSONObject(json);
            String test = mainObject.getString("name");
            JSONObject nameObject = new JSONObject(test);

            String mainName = nameObject.optString("mainName");
            JSONArray alsoKnownAs = nameObject.getJSONArray("alsoKnownAs");
            List<String> knownList = JSONArrayToList(alsoKnownAs);
            String placeOfOrigin = mainObject.optString("placeOfOrigin");
            String description = mainObject.optString("description");
            String image = mainObject.optString("image");
            JSONArray ingredients = mainObject.getJSONArray("ingredients");
            List<String> ingList = JSONArrayToList(ingredients);

            return new Sandwich(mainName, knownList, placeOfOrigin, description, image, ingList);


        } catch (JSONException je) {
            je.printStackTrace();
            return null;
        }

    }

    private static List<String> JSONArrayToList(JSONArray array) {
        List<String> list = new ArrayList<>();
        if (null != array) {
            for (int i = 0; i < array.length(); i++) {
                try {
                    list.add(array.getString(i));
                } catch (JSONException e) {
                    e.printStackTrace();
                    return null;
                }
            }
            return list;
        }
        return null;
    }

}
