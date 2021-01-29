package com.example.androidapp;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.androidapp.R;


public class MainActivity extends Activity implements View.OnClickListener {
    Button buttonNext,buttonExit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        buttonNext.setOnClickListener(this);
        buttonExit.setOnClickListener(this);
    }
    private void init(){
        buttonNext = (Button)findViewById(R.id.mbtn_next);
        buttonExit = (Button)findViewById(R.id.mbtn_exit);
    }

    @Override
    public  void onClick(View v){
        switch (v.getId()){
            case R.id.mbtn_next:
                Intent intent = new Intent(getApplicationContext(), MyDragdrop.class);
                startActivity(intent);
                MainActivity.this.finish();
                break;
            case R.id.mbtn_exit:
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("confirm Exit program");
                alertDialogBuilder
                        .setMessage("Click yes to exit")
                        .setCancelable(false)
                        .setPositiveButton("Yes",new DialogInterface.OnClickListener(){
                            public  void onClick(DialogInterface dialog,int id){
                                MainActivity.this.finish();
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
        }
    }
}