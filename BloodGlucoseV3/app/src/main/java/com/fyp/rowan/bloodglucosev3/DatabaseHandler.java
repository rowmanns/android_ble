package com.fyp.rowan.bloodglucosev3;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Locale;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DatabaseHandler extends SQLiteOpenHelper {

    // DB version
    private static final int DATABASE_VERSION = 2;

    // DB name
    private static final String DATABASE_NAME = "GlucoseMeterDB";

    // table names
    private static final String TABLE_GLUCOSE = "readings";
    private static final String TABLE_INSULIN = "insulin";
    private static final String TABLE_MEALS = "meals";


    // common column names
    private static final String COLUMN_ID = "id";
    private static final String KEY_CREATED_AT = "created_at";

    // glucose column name
    private static final String READING = "glucose_reading";

    // Insulin Column name
    private static final String INSULIN = "insulin";

    // Meal column names
    private static final String MEAL_NAME = "meal_description";
    private static final String CALORIES = "meal_calories";
    private static final String CARBS = "meal_carbs";
    private static final String SUGARS = "meal_sugars";

    // Table creation statements
    // Glucose table creation statement
    private static final String CREATE_TABLE_GLUCOSE = "CREATE TABLE " + TABLE_GLUCOSE
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + READING
            + " INTEGER," + KEY_CREATED_AT + " DATETIME" + ")";

    // Insulin table creation statement
    private static final String CREATE_TABLE_INSULIN = "CREATE TABLE " + TABLE_INSULIN
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + INSULIN
            + " INTEGER," + KEY_CREATED_AT + " DATETIME" + ")";

    private static final String CREATE_TABLE_MEALS = "CREATE TABLE " + TABLE_MEALS
            + "(" + COLUMN_ID + " INTEGER PRIMARY KEY," + MEAL_NAME + " TEXT,"
            + CALORIES + " INTEGER," + CARBS + " INTEGER," + SUGARS + " INTEGER,"
            + KEY_CREATED_AT + " DATETIME" + ")";


    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Creating Tables
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Category table create query
        /*String CREATE_ITEM_TABLE = "CREATE TABLE " + TABLE_GLUCOSE + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY," + READING + " TEXT,"
                + KEY_CREATED_AT + " DATETIME" +")";*/
        db.execSQL(CREATE_TABLE_GLUCOSE);
        db.execSQL(CREATE_TABLE_INSULIN);
        db.execSQL(CREATE_TABLE_MEALS);
    }

    // Upgrading database
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_GLUCOSE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_INSULIN);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_MEALS);
        // Create tables again
        onCreate(db);
    }

    /**
     * Inserting new reading into readings table
     * */
    public void insertGluReading(String reading){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(READING, reading);   //column name, column value
        values.put(KEY_CREATED_AT, getDateTime());

        // Inserting Row
        db.insert(TABLE_GLUCOSE, null, values);//tableName, nullColumnHack, CotentValues
        db.close(); // Closing database connection
    }

    /**
     * Inserting new insulin injection into insulin table
     * */
    public void insertInsulReading(String insulin){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(INSULIN, insulin);   //column name, column value
        values.put(KEY_CREATED_AT, getDateTime());

        // Inserting Row
        db.insert(TABLE_INSULIN, null, values);//tableName, nullColumnHack, CotentValues
        db.close(); // Closing database connection
    }

    /**
     * Inserting new meal into meals table
     * */

    public void insertMeal(String name, String sugars, String carbs, String cals){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(MEAL_NAME, name);
        values.put(SUGARS, sugars);
        values.put(CARBS, carbs);
        values.put(CALORIES, cals);
        values.put(KEY_CREATED_AT, getDateTime());

        db.insert(TABLE_MEALS, null, values);
        db.close();
    }


    /**
     *  Find a days Meals
     */
    public List<String> getDaysMeals(String date){

        String dates = date;
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> list = new ArrayList<String>();



        // Finds the data from the user input date.
        String selectQuery = "SELECT * FROM " + TABLE_MEALS  + " WHERE " + KEY_CREATED_AT + " LIKE " + "'" + dates +"%'";

        Cursor cursor = db.rawQuery(selectQuery, null); //selectQuery, selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add("Meal Name: " + cursor.getString(1));
                list.add("Time of Meal: " + cursor.getString(5));
                list.add(cursor.getString(2) + " Grams of sugar");
                list.add(cursor.getString(3) + " Grams of carbohydrates");
                list.add(cursor.getString(4) + " Calories");
            } while (cursor.moveToNext());
        }

        // closing connections
        cursor.close();
        db.close();

        return list;
    }

    /**
     * Return a specific days insulin log
     * */
    public List<String> getDaysInsulin(String date) {
        String dates = date;
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> list = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + TABLE_INSULIN + " WHERE " + KEY_CREATED_AT + " LIKE " + "'" + dates +"%'";;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // add each columns data to the list
                list.add(cursor.getString(1) + " ML");
                list.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        // returning insulin
        return list;

    }

    /**
     * Return all meals that have been logged
     * */
    public List<String> getMeals() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> list = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + TABLE_MEALS;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // add each columns data to the list
                list.add("Meal Name: " + cursor.getString(1));
                list.add("Time of Meal: " + cursor.getString(5));
                list.add(cursor.getString(2) + " Grams of sugar");
                list.add(cursor.getString(3) + " Grams of carbohydrates");
                list.add(cursor.getString(4) + " Calories");

            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        // returning meals
        return list;
    }

    /**
     * Return all insulin logs
     * */
    public List<String> getInsulin() {
        SQLiteDatabase db = this.getReadableDatabase();
        List<String> list = new ArrayList<String>();

        String selectQuery = "SELECT * FROM " + TABLE_INSULIN;

        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                // add each columns data to the list
                list.add(cursor.getString(1));
                list.add(cursor.getString(2));
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        // returning insulin
        return list;

    }

    /**
     * Getting all glucose readings
     *
     * */
    public List<String> getAllReadings(){
        List<String> list = new ArrayList<String>();

        // Select All Query
        //String selectQuery = "SELECT  * FROM " + TABLE_NAME;
        String selectQuery = "SELECT  * FROM " + TABLE_GLUCOSE;


        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);//selectQuery,selectedArguments

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                list.add(cursor.getString(1));//adding 2nd column data
            } while (cursor.moveToNext());
        }
        // closing connection
        cursor.close();
        db.close();

        // returning readings
        return list;
    }


    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat(
                "yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();
        return dateFormat.format(date);
    }
}

