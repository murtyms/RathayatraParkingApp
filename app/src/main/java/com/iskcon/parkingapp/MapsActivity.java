package com.iskcon.parkingapp;

import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    // Might be null if Google Play services APK is not available.

    Geocoder coder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        coder = new Geocoder(this);
        setContentView(R.layout.activity_maps);
        setUpMapIfNeeded();
    }

    @Override
    protected void onResume() {
        super.onResume();

        setUpMapIfNeeded();
    }

    /**
     * Sets up the map if it is possible to do so (i.e., the Google Play services APK is correctly
     * installed) and the map has not already been instantiated.. This will ensure that we only ever
     * call {@link #setUpMap()} once when {@link #mMap} is not null.
     * <p/>
     * If it isn't installed {@link SupportMapFragment} (and
     * {@link com.google.android.gms.maps.MapView MapView}) will show a prompt for the user to
     * install/update the Google Play services APK on their device.
     * <p/>
     * A user can return to this FragmentActivity after following the prompt and correctly
     * installing/updating/enabling the Google Play services. Since the FragmentActivity may not
     * have been completely destroyed during this process (it is likely that it would only be
     * stopped or paused), {@link #onCreate(Bundle)} may not be called again so we should call this
     * method in {@link #onResume()} to guarantee that it will be called.
     */
    private void setUpMapIfNeeded() {
    // Try to obtain the map from the SupportMapFragment.
    ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map))
                    .getMapAsync(this);
    }

   private void addParkingLot(String address, GoogleMap map, String title)
   {
       java.util.List<Address> addressList;


       try{
           addressList = coder.getFromLocationName(address, 1);
           if(null != addressList){
               Address location = addressList.get(0);
               Marker m = map.addMarker(new MarkerOptions().position(new LatLng(location.getLatitude(), location.getLongitude())));
               m.setTitle(title);
               //builder.include(m.getPosition());
           }

       }catch(Exception e){}

   }
    /**
     * This is where we can add markers or lines, add listeners or move the camera. In this case, we
     * just add a marker near Africa.
     * <p/>
     * This should only be called once and when we are sure that {@link #mMap} is not null.
     */
    private void setUpMap(GoogleMap mMap) {
       // Uri gmmuri =  Uri.parse("geo:0,0?q=101 J Morris Commons Ln, Morrisville, North+Carolina");
        //Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmuri);
      //  mapIntent.setPackage("com.google.android.apps.maps");
       // startActivity(mapIntent);
        //mMap.addMarker(new MarkerOptions().position(new LatLng(51.508742,-0.120850)).title("Marker"));
        addParkingLot("101 J Morris Commons Ln, Morrisville, North+Carolina", mMap, "TriProperties");
        addParkingLot("6011 McCrimmon Parkway, Morrisville, North+Carolina", mMap, "Church");
        addParkingLot("309 Aviation Parkway, Morrisville, North+Carolina", mMap, "HSNC");
        addParkingLot("1020 Aviation Parkway, Morrisville, North+Carolina", mMap, "BAPS");

       // LatLngBounds bounds = builder.build();
        //CameraUpdate cu = CameraUpdateFactory.newLatLngBounds(bounds, 8);
       // mMap.moveCamera(cu);
    }

    public void onMapReady(GoogleMap mMap)
    {
        if (mMap != null) {
            setUpMap(mMap);
        }
    }
}
