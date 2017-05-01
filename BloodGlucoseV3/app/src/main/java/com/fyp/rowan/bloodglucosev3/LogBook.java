package com.fyp.rowan.bloodglucosev3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogBook extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_book);
    }

    public void insulin (View view){
        Intent intent = new Intent(this, LogInsulin.class);
        startActivity(intent);

    }

    public void meals (View view){
        Intent intent = new Intent(this, LogMeals.class);
        startActivity(intent);

    }


}
