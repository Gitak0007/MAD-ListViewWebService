package academic.example.com.academicsfinder;


import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class ActNavigation extends Activity {


    GoogleMap map;
    Context ctx = ActNavigation.this;

    PolylineOptions polylineOptions;
    private ArrayList<LatLng> arrayPoints = new ArrayList<LatLng>();


    private static final long MIN_DISTANCE = 5;
    private static final long MIN_TIME = 5000;

    private static final int RC_LOCATION_APP_PERM = 124;
    LocationManager locationManager;
    MyLocationClass myLocationListener;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_navigation);

        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.map);
        map = mapFragment.getMap();

        map.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        // get current location
        map.setMyLocationEnabled(true);


        requestPermissions();

        String title = "AO Clock Tower";
        String detail = "AO Tower Bridge Bus Stop.";
        LatLng mLatLong = new LatLng(24.925382, 67.032195);
        setMarker(map, mLatLong, title, detail);
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(mLatLong, 17));


        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        myLocationListener = new MyLocationClass();


//        final Handler myHandler = new Handler();
//        Timer myTimer = new Timer();
//        myTimer.schedule(new TimerTask() {
//            @Override
//            public void run() {
//                myHandler.post(new Runnable() {
//                    @Override
//                    public void run() {
//
//                        animateCamera();
//
//                    }
//                });
//            }
//        }, 5000);



//        map.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
//            @Override
//            public void onMapLongClick(LatLng latLng) {
//
//                map.clear();
//            }
//        });
//
//
//        map.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
//            @Override
//            public void onMapClick(LatLng point) {
//
//                MarkerOptions marker = new MarkerOptions();
//                marker.position(point);
//                map.addMarker(marker);
//                polylineOptions = new PolylineOptions();
//                polylineOptions.color(Color.RED);
//                polylineOptions.width(5);
//                arrayPoints.add(point);
//                polylineOptions.addAll(arrayPoints);
//                map.addPolyline(polylineOptions);
//
//
//            }
//        });




    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(RC_LOCATION_APP_PERM)
    private void requestPermissions() {
        String[] perms = { Manifest.permission.INTERNET, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION  };
        if (EasyPermissions.hasPermissions(this, perms)) {

//            locationManager.requestLocationUpdates(
//                    LocationManager.GPS_PROVIDER,
//                    MIN_TIME,
//                    MIN_DISTANCE,
//                    myLocationListener);



        } else {
            EasyPermissions.requestPermissions(this, "This app needs access to your location", RC_LOCATION_APP_PERM, perms);
        }
    }



    private void setMarker(GoogleMap map, LatLng mLatLong, String title, String detail) {

        map.addMarker(new MarkerOptions()
                .title(title)
                .snippet(detail)
                .position(mLatLong));
    }
















    private void animateCamera() {

        final LatLng mLatLng = new LatLng(24.925546, 67.030590);
        CameraPosition cameraPosition =
                new CameraPosition.Builder()
                        .target(mLatLng)
                        .bearing(0)
                        .tilt(90)
                        .zoom(map.getCameraPosition().zoom)
                        .build();

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition),
                3000,
                new GoogleMap.CancelableCallback() {
                    @Override
                    public void onFinish() {
                        map.addMarker(new MarkerOptions()
                                .title("J.U.W")
                                .snippet("Block 5, Karachi, Pakistan")
                                .position(mLatLng));
                    }

                    @Override
                    public void onCancel() {
                    }
                }
        );



    }





    public class MyLocationClass implements android.location.LocationListener {

        public void onLocationChanged(Location location) {
            String message = String.format(
                    "Current Location \n Longitude: %1$s \n Latitude: %2$s",
                    location.getLongitude(), location.getLatitude()
            );
            Toast.makeText(ctx, message, Toast.LENGTH_LONG).show();
            Log.d("000999", "" + message);
        }

        public void onStatusChanged(String s, int i, Bundle b) {
            Toast.makeText(ctx, "Provider status changed",Toast.LENGTH_LONG).show();
        }

        public void onProviderDisabled(String s) {
            Toast.makeText(ctx,"Provider disabled by the user. GPS turned off",Toast.LENGTH_LONG).show();
        }

        public void onProviderEnabled(String s) {
            Toast.makeText(ctx,"Provider enabled by the user. GPS turned on",Toast.LENGTH_LONG).show();
        }
    }


}
