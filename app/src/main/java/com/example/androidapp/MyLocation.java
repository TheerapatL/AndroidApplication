package com.example.androidapp;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import com.example.androidapp.R;


public class MyLocation extends Activity implements View.OnClickListener {
    Button gbuttonBack, gbuttonExit, btnShowLocation;
    TextView lbalLat, lblLon;

    private static final int REQUEST_LOCATION = 1;
    LocationManager locationManager;
    String lattitude, longitude;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.location);
        init();
        gbuttonExit.setOnClickListener(this);
        gbuttonBack.setOnClickListener(this);
        btnShowLocation.setOnClickListener(this);
    }

    private void init() {
        gbuttonExit = (Button) findViewById(R.id.gbtn_exit);
        gbuttonBack = (Button) findViewById(R.id.gbtn_back);
        btnShowLocation = (Button) findViewById(R.id.gbtn_lo);
        lbalLat = (TextView) findViewById(R.id.txt_lat);
        lblLon = (TextView) findViewById(R.id.txt_long);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.gbtn_back:
                intent = new Intent(getApplicationContext(),MyDragdrop.class);
                startActivity(intent);
                MyLocation.this.finish();
                break;
            case R.id.gbtn_exit:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("confirm Exit program");
                alertDialogBuilder
                        .setMessage("Click yes to exit")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                            public  void onClick(DialogInterface dialog,int id){
                                MyLocation.this.finish();
                            }

                        })
                        .setNegativeButton("No",new DialogInterface.OnClickListener() {
                            public  void onClick(DialogInterface dialog,int id){
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();

                break;
            case R.id.gbtn_lo:
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                if (!locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    buildAlertMassageNoGps();
                } else if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
                    getLocation();
                }
                break;
        }

    }


    protected void buildAlertMassageNoGps(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Please Turn on your GPS connection")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                { public void onClick(final DialogInterface dialog,final  int id){
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener()
                { public void onClick(final DialogInterface dialog,final  int id){
                    dialog.cancel();
                }
                });
        final AlertDialog alert = builder.create();
        alert.show();
    }

    private void getLocation(){
        if(ActivityCompat.checkSelfPermission(MyLocation.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission
                (MyLocation.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION) !=
                PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(MyLocation.this,new String[]
                    {Manifest.permission.ACCESS_FINE_LOCATION},REQUEST_LOCATION);
        }else{
            Location location =
                    locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
            Location location1 =
                    locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            Location location2 =
                    locationManager.getLastKnownLocation(LocationManager.PASSIVE_PROVIDER);
            if(location != null){
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                lblLon.setText("Longitude = "+longitude);
                lbalLat.setText("Lattitude = "+lattitude);
            }else if(location1 != null) {
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                lblLon.setText("Longitude = "+longitude);
                lbalLat.setText("Lattitude = "+lattitude);
            }else if (location2 != null){
                double latti = location.getLatitude();
                double longi = location.getLongitude();
                lattitude = String.valueOf(latti);
                longitude = String.valueOf(longi);
                lblLon.setText("Longitude = "+longitude);
                lbalLat.setText("Lattitude = "+lattitude);
            }else{
                Toast.makeText(this,"Unble to trace your location",
                        Toast.LENGTH_SHORT).show();
            }
        }
    }



}