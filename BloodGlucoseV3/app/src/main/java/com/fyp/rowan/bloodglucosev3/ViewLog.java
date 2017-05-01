package com.fyp.rowan.bloodglucosev3;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ViewLog extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_log);
    }

    public void glucoseReadings (View view){
        Intent intent = new Intent(this, ViewGlucose.class);
        startActivity(intent);
    }

    public void injectedInsulin (View view){
        Intent intent = new Intent(this, ViewInsulin.class);
        startActivity(intent);
    }

    public void mealsLog (View view){
        Intent intent = new Intent(this, ViewMeals.class);
        startActivity(intent);
    }
}
