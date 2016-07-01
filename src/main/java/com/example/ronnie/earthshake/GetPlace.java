package com.example.ronnie.earthshake;

import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;

/**
 * Created by Ronnie on 6/29/2016.
 */
public class GetPlace extends AsyncTask<Object, Integer, String> {

    GoogleMap googleMap;
    String earthQuakeData = null;

    @Override
    protected String doInBackground(Object... inputObj) {
        try {
            googleMap = (GoogleMap) inputObj[0];
            String PlacesUrl = (String) inputObj[1];
            Http http = new Http();
            earthQuakeData = http.read(PlacesUrl);
        } catch (Exception e) {
            Log.d("Place Read", e.toString());
        }
        return earthQuakeData;
    }

    @Override
    protected void onPostExecute(String result) {
        PlacesDisplayTask placesDisplayTask = new PlacesDisplayTask();
        Object[] toPass = new Object[2];
        toPass[0] = googleMap;
        toPass[1] = result;
        placesDisplayTask.execute(toPass);
    }
}
