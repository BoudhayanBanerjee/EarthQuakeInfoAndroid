package com.example.ronnie.earthshake;


import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;


import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import android.util.Log;
import android.view.View;;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import java.util.Date;
import java.util.GregorianCalendar;


/**
 * This shows how to create a simple activity with a map and a marker on the map.
 */
public class MainActivity extends AppCompatActivity implements OnMapReadyCallback,LocationListener {

    GoogleMap googleMap;
    double latitude = 0;
    double longitude = 0;
    private int PROXIMITY_RADIUS = 5000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SupportMapFragment mapFragment =
                (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        //mapFragment.getMapAsync(this);

        googleMap = mapFragment.getMap();
        googleMap.setMapType(3);
        googleMap.getUiSettings().setMapToolbarEnabled(false);

        CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(new LatLng(30.3937,69.9629))      // Sets the center of the map to Mountain View
                .zoom(3)
                .tilt(45)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));





        //show error dialog if GoolglePlayServices not available
        if (!isGooglePlayServicesAvailable()) {
            finish();
        }


        final FloatingActionButton dayButton = (FloatingActionButton) findViewById(R.id.actionButtonDay);

        dayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String startDate;
                String endDate;

                Calendar calendar = new GregorianCalendar();

                int year       = calendar.get(Calendar.YEAR);
                int month      = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
                int dayEnd = calendar.get(Calendar.DAY_OF_MONTH);
                int dayStart = calendar.get(Calendar.DAY_OF_MONTH)-1;

                endDate = year+"-"+month+"-"+dayEnd;
                startDate = year+"-"+month+"-"+dayStart;
                StringBuilder url = new StringBuilder("http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + startDate + "&endtime=" + endDate+"&minmagnitude=4");

                GetPlace getPlace = new GetPlace();
                Object[] topass = new Object[2];
                topass[0] = googleMap;
                topass[1] = url.toString();
                getPlace.execute(topass);
            }
        });

        final FloatingActionButton weekButton = (FloatingActionButton) findViewById(R.id.actionButtonWeek);

        weekButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String startDate;
                String endDate;


                Calendar calendar = new GregorianCalendar();

                calendar.add(Calendar.WEEK_OF_MONTH, -1);
                int year       = calendar.get(Calendar.YEAR);
                int month      = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                startDate = year+"-"+month+"-"+day;


                Calendar calendar2 = new GregorianCalendar();
                calendar.add(Calendar.WEEK_OF_MONTH, 1);
                int year2       = calendar.get(Calendar.YEAR);
                int month2      = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
                int day2 = calendar.get(Calendar.DAY_OF_MONTH);
                endDate = year2+"-"+month2+"-"+day2;
                StringBuilder url = new StringBuilder("http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + startDate + "&endtime=" + endDate+"&minmagnitude=5");

                Log.d("URL",url.toString());
                GetPlace getPlace = new GetPlace();
                Object[] topass = new Object[2];
                topass[0] = googleMap;
                topass[1] = url.toString();
                getPlace.execute(topass);
            }
        });

        /*****************/

        final FloatingActionButton monthButton = (FloatingActionButton) findViewById(R.id.actionButtonMonth);

        monthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String startDate;
                String endDate;


                Calendar calendar = new GregorianCalendar();

                calendar.add(Calendar.MONTH, -1);
                int year       = calendar.get(Calendar.YEAR);
                int month      = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                startDate = year+"-"+month+"-"+day;


                Calendar calendar2 = new GregorianCalendar();
                calendar.add(Calendar.MONTH, 1);
                int year2       = calendar.get(Calendar.YEAR);
                int month2      = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
                int day2 = calendar.get(Calendar.DAY_OF_MONTH);
                endDate = year2+"-"+month2+"-"+day2;
                StringBuilder url = new StringBuilder("http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + startDate + "&endtime=" + endDate+"&minmagnitude=6");

                Log.d("URL",url.toString());
                GetPlace getPlace = new GetPlace();
                Object[] topass = new Object[2];
                topass[0] = googleMap;
                topass[1] = url.toString();
                getPlace.execute(topass);
            }
        });

        /*************************/

        final FloatingActionButton yearButton = (FloatingActionButton) findViewById(R.id.actionButtonYear);

        yearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String startDate;
                String endDate;


                Calendar calendar = new GregorianCalendar();

                calendar.add(Calendar.YEAR, -1);
                int year       = calendar.get(Calendar.YEAR);
                int month      = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
                int day = calendar.get(Calendar.DAY_OF_MONTH);

                startDate = year+"-"+month+"-"+day;


                Calendar calendar2 = new GregorianCalendar();
                calendar.add(Calendar.YEAR, 1);
                int year2       = calendar.get(Calendar.YEAR);
                int month2      = calendar.get(Calendar.MONTH) + 1; // Jan = 0, dec = 11
                int day2 = calendar.get(Calendar.DAY_OF_MONTH);
                endDate = year2+"-"+month2+"-"+day2;
                StringBuilder url = new StringBuilder("http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&starttime=" + startDate + "&endtime=" + endDate+"&minmagnitude=7");

                Log.d("URL",url.toString());
                GetPlace getPlace = new GetPlace();
                Object[] topass = new Object[2];
                topass[0] = googleMap;
                topass[1] = url.toString();
                getPlace.execute(topass);
            }
        });


    }



    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we
     * just add a marker near Africa.
     */
    @Override
    public void onMapReady(GoogleMap map) {





    }

    private boolean isGooglePlayServicesAvailable() {
        int status = GooglePlayServicesUtil.isGooglePlayServicesAvailable(this);
        if (ConnectionResult.SUCCESS == status) {
            return true;
        } else {
            GooglePlayServicesUtil.getErrorDialog(status, this, 0).show();
            return false;
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        latitude = location.getLatitude();
        longitude = location.getLongitude();
        LatLng latLng = new LatLng(latitude, longitude);
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
        googleMap.animateCamera(CameraUpdateFactory.zoomTo(12));
    }


}
