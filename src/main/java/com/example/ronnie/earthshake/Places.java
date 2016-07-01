package com.example.ronnie.earthshake;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
public class Places {

    public List<HashMap<String, String>> parse(JSONObject jsonObject) {
        JSONArray jsonArray = null;
        try {
            jsonArray = jsonObject.getJSONArray("features");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return getPlaces(jsonArray);
    }

    private List<HashMap<String, String>> getPlaces(JSONArray jsonArray) {
        int placesCount = jsonArray.length();
        List<HashMap<String, String>> placesList = new ArrayList<HashMap<String, String>>();


        for (int i = 0; i < placesCount; i++) {
            try {
                //placeMap = getPlace((JSONObject) jsonArray.get(i));
                //placesList.add(placeMap);
                HashMap<String, String> placeMap = new HashMap<String, String>();

                JSONObject data = jsonArray.getJSONObject(i);
                JSONObject property = data.getJSONObject("properties");
                JSONObject geometry = data.getJSONObject("geometry");

                double magnitude = property.getDouble("mag");
                String place = property.getString("place");
                int tsunami = property.getInt("tsunami");

                //Log.d("DATA",place+" "+Double.toString(magnitude)+" "+Integer.toString(tsunami));

                JSONArray placeArray = geometry.getJSONArray("coordinates");
                Double longitude = placeArray.getDouble(0);
                Double latitude = placeArray.getDouble(1);

                placeMap.put("place_name", place);
                placeMap.put("magnitude", Double.toString(magnitude));
                placeMap.put("lat", Double.toString(latitude));
                placeMap.put("lng", Double.toString(longitude));
                placeMap.put("tsunami", Integer.toString(tsunami));
                placesList.add(placeMap);
                //Log.d("MAP",placeMap.toString());

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }


        //Log.d("LIST",placesList.toString());
        return placesList;
    }
}
