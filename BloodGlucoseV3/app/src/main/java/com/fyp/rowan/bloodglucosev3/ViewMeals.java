package com.fyp.rowan.bloodglucosev3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class ViewMeals extends Activity {

    EditText theDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_meals);
    }


    public void mealData(View View){

        theDate = (EditText) findViewById(R.id.datePicker);

        if (theDate.getText().toString().trim().equals("")){
            theDate.setError("Date is Required!");
        }else {

            String dateParse = theDate.getText().toString();
            theDate.setText("");
            DatabaseHandler db;
            db = new DatabaseHandler(getApplicationContext());


            List<String> MealsList = db.getDaysMeals(dateParse);

            // A bit of data validation to notify user if there is no data to display
            // However it doesn't set the hint... need to fix.
            if(MealsList == null){
                theDate.setHint("No data stored :( Try a different date!");
            }else {

                ListView lv = (ListView) findViewById(R.id.list_meals);

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                        this,
                        android.R.layout.simple_list_item_1,
                        MealsList);

                lv.setAdapter(arrayAdapter);

            }
        }

    }
}
