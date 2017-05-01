package com.fyp.rowan.bloodglucosev3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogInsulin extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_insulin);
    }

    // Store insulin data
    public void storeInsulin (View view){
        DatabaseHandler db;
        db = new DatabaseHandler(getApplicationContext());

        EditText insulinReading = (EditText) findViewById(R.id.insulin);

        String message = insulinReading.getText().toString();

        db.insertInsulReading(message);

        insulinReading.getText().clear();
    }
}
