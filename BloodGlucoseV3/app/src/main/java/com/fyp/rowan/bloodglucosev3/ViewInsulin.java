package com.fyp.rowan.bloodglucosev3;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import java.util.List;

public class ViewInsulin extends Activity {
    EditText theDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_insulin);
    }

    public void insulinData (View view){
        theDate = (EditText) findViewById(R.id.datePicker);

        if (theDate.getText().toString().trim().equals("")){
            theDate.setError("Date is Required!");
        }else {

            String dateParse = theDate.getText().toString();
            theDate.setText("");

            DatabaseHandler db;
            db = new DatabaseHandler(getApplicationContext());

            List<String> InsulinList = db.getDaysInsulin(dateParse);

            ListView lv = (ListView) findViewById(R.id.list_insulin);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                    this,
                    android.R.layout.simple_list_item_1,
                    InsulinList);

            lv.setAdapter(arrayAdapter);
        }
    }
}
