
package com.guide.hophack.guide;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class HostNavigator extends FragmentActivity{

    private GoogleMap map;
    private final LatLng JHU = new LatLng(39.336638,-76.621173);

    ImageView iv;
    Bitmap bmp;
    Canvas canvas;
    Paint paint;
    float downx, downy, movex, movey, upx, upy = 0 ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host_navigator);
        try{
           setUpMapIfNeeded();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    private void setUpMapIfNeeded() {
        // Do a null check to confirm that we have not already instantiated the map.
        if (map != null) {
            // Try to obtain the map from the SupportMapFragment.
            map = ((SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.mapView))
                    .getMap();
            // Check if we were successful in obtaining the map.
            if (map == null) {
                setUpMap();
            }
        }
    }

    private void setUpMap() {
        map.addMarker(new MarkerOptions().position(new LatLng(0, 0)).title("Marker"));
    }

    public void onClick_share(View v) {

        if (map != null) {


/* iv = (ImageView) findViewById(R.id.map);
        Display d = getWindowManager().getDefaultDisplay();
        float dw = d.getWidth();
        float dh = d.getHeight();
        bmp = Bitmap.createBitmap((int)dw, (int) dh, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bmp);
        paint = new Paint();
        paint.setColor(Color.BLUE);
        //paint.setShadowLayer(5,2,2,Color.RED);
        iv.setImageBitmap(bmp);
        iv.setOnTouchListener(this);*/


            /*LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();

            ArrayList<Marker> markers = new ArrayList<Marker>();
            if (locationManager != null) {
                Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
                if (location != null) {
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(
                            new LatLng(location.getLatitude(), location.getLongitude()), 13));

                    CameraPosition cameraPosition = new CameraPosition.Builder()
                            .target(new LatLng(location.getLatitude(), location.getLongitude()))      // Sets the center of the map to location user
                            .zoom(17)                   // Sets the zoom
                            .bearing(90)                // Sets the orientation of the camera to east
                            .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                            .build();                   // Creates a CameraPosition from the builder


                    map.getUiSettings().setMyLocationButtonEnabled(false);
                    map.setMyLocationEnabled(true);
                    CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLngZoom(JHU, 16);
                    map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    //}
                    //}
                }

            }*/
            CameraUpdate cameraUpdate = CameraUpdateFactory.newLatLng(JHU);
            map.animateCamera(cameraUpdate);
        }
    }

    public void onClick_goBack(View v) {
        Intent intent = new Intent("com.guide.hophack.guide.DrawActivity");
        startActivity(intent);
    }

/*@Override
    public boolean onTouch(View arg0, MotionEvent arg1){
        switch(arg1.getAction()){
            case MotionEvent.ACTION_DOWN :
                downx = arg1.getX();
                downy = arg1.getY();
                break;
            case MotionEvent.ACTION_MOVE :
                movex = arg1.getX();
                movey = arg1.getY();
                break;
            case MotionEvent.ACTION_UP :
                upx = arg1.getX();
                upy = arg1.getY();
                canvas.drawLine(downx,downy,upx,upy,paint);
                iv.invalidate();
                break;

        }
        return true;
    }*/


    @Override
    protected void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_host_navigator, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}


