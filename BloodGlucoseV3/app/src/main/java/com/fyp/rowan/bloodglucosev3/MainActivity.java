package com.fyp.rowan.bloodglucosev3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void takeReading (View view){
        Intent intent = new Intent(this, TakeReading.class);
        startActivity(intent);

    }

    public void logBook (View view){
        Intent intent = new Intent(this, LogBook.class);
        startActivity(intent);

    }

    public void viewLog (View view){
        Intent intent = new Intent(this, ViewLog.class);
        startActivity(intent);

    }
}
