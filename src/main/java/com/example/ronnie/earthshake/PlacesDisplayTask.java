package com.example.ronnie.earthshake;

import android.content.res.Resources;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;

public class PlacesDisplayTask  extends AsyncTask<Object, Integer, List<HashMap<String, String>>>  {
    JSONObject PlacesJson;
    GoogleMap googleMap;

    @Override
    protected List<HashMap<String, String>> doInBackground(Object... inputObj) {

        List<HashMap<String, String>> PlacesList = null;
        Places placeJsonParser = new Places();

        try {
            googleMap = (GoogleMap) inputObj[0];
            PlacesJson = new JSONObject((String) inputObj[1]);
            PlacesList = placeJsonParser.parse(PlacesJson);

        } catch (JSONException e) {
            Log.d("Exception", e.toString());

        }
        return PlacesList;
    }

    @Override
    protected void onPostExecute(List<HashMap<String, String>> list) {
        googleMap.clear();
        googleMap.setMapType(3);
        for (int i = 0; i < list.size(); i++) {
            MarkerOptions markerOptions = new MarkerOptions();
            HashMap<String, String> place = list.get(i);
            double lat = Double.parseDouble(place.get("lat"));
            double lng = Double.parseDouble(place.get("lng"));
            String magnitude = place.get("magnitude");
            String vicinity = place.get("place_name");
            String tsunami = place.get("tsunami");
            LatLng latLng = new LatLng(lat, lng);

            /*markerOptions.position(latLng);
            markerOptions.title(vicinity);
            markerOptions.snippet("Magnitude : "+magnitude+" "+"Tsunami : "+tsunami);
            markerOptions.icon(BitmapDescriptorFactory.fromResource(R.drawable.vector_drawable_square_pin));
            googleMap.addMarker(markerOptions);*/


            Marker marker = googleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title(vicinity)
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_AZURE))
                    .snippet("Magnitude : "+magnitude+" "+"Tsunami : "+tsunami));

            //marker.setIcon(BitmapDescriptorFactory.fromResource(R.drawable.square_pin));

            CircleOptions circleOptions = new CircleOptions().center(latLng)
                    .radius(Float.parseFloat(magnitude)*100000)
                    .strokeWidth(10)
                    .strokeColor(Color.argb(150,183,28,28))
                    .fillColor(Color.argb(150,239,83,80));

            Circle circle = googleMap.addCircle(circleOptions);

           // googleMap.addMarker(new MarkerOptions().position(new LatLng(lat, lng)).title(vicinity));
        }

    }


}
