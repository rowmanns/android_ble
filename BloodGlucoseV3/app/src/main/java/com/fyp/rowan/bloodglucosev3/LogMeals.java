package com.fyp.rowan.bloodglucosev3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class LogMeals extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_meals);
    }

    public void storeMeal(View view){
        DatabaseHandler db;
        db = new DatabaseHandler(getApplicationContext());

        EditText text1 = (EditText) findViewById(R.id.mealName);
        EditText text2 = (EditText) findViewById(R.id.sugars);
        EditText text3 = (EditText) findViewById(R.id.carbs);
        EditText text4 = (EditText) findViewById(R.id.calories);

        String message1 = text1.getText().toString();
        String message2 = text2.getText().toString();
        String message3 = text3.getText().toString();
        String message4 = text4.getText().toString();

        db.insertMeal(message1, message2, message3, message4);

        text1.getText().clear();
        text2.getText().clear();
        text3.getText().clear();
        text4.getText().clear();
    }
}
