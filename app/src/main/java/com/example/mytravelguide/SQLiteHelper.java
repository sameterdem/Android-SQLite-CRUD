package com.example.mytravelguide;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Samet ERDEM on 25.06.2020.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final String database_NAME = "travel_db";
    private static final String table_NAME ="tbl_travel";
    private static final int database_VERSION = 1;

    private static final String travel_ID = "ID";
    private static final String travel_TITLE = "Title";
    private static final String travel_COUNTRY = "Country";
    private static final String travel_CITY = "City";
    private static final String travel_DESCRIPTION = "Description";
    private static final String travel_VISITED = "Visited";
    private static final String[]  COLUMNS = {travel_ID, travel_TITLE, travel_COUNTRY, travel_CITY, travel_DESCRIPTION, travel_VISITED};

    public SQLiteHelper(Context context) {
        super(context, database_NAME, null, database_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + table_NAME + "(ID INTEGER PRIMARY KEY AUTOINCREMENT, Title TEXT, Country TEXT, City TEXT, Description TEXT, Visited INTEGER)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_NAME);
    }

    public void AddTravel (String Title, String Country, String City, String Description, int Visited ) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Title", Title);
        cv.put("Country", Country);
        cv.put("City", City);
        cv.put("Description", Description);
        cv.put("Visited", Visited);

        db.insert(table_NAME, null, cv);
    }

    public List<TravelModel> GetTravelList (int visited) {

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table_NAME, COLUMNS, " Visited = ?", new String[] {String.valueOf(visited)}, null, null, null);

        TravelModel travelModel = null;
        List<TravelModel> travelList = new ArrayList<>();


        if (cursor.moveToFirst()) {
            do {
                travelModel = new TravelModel();
                travelModel.setID(Integer.valueOf(cursor.getString(0)));
                travelModel.setTitle(cursor.getString(1));
                travelModel.setCountry(cursor.getString(2));
                travelModel.setCity(cursor.getString(3));
                travelModel.setDescription(cursor.getString(4));
                travelModel.setVisited(Integer.valueOf(cursor.getString(5)));

                travelList.add(travelModel);

            } while (cursor.moveToNext());
        }

        return travelList;

    }

    public TravelModel GetTravelDetail (int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(table_NAME, COLUMNS, " id = ?", new String[] {String.valueOf(id)}, null, null, null);
        if (cursor != null){
            cursor.moveToFirst();
        }
            TravelModel travelModel = new TravelModel();
            travelModel.setID(Integer.valueOf(cursor.getString(0)));
            travelModel.setTitle(cursor.getString(1));
            travelModel.setCountry(cursor.getString(2));
            travelModel.setCity(cursor.getString(3));
            travelModel.setDescription(cursor.getString(4));
            travelModel.setVisited(Integer.valueOf(cursor.getString(5)));

            return travelModel;
    }

    public void DeleteTravel (int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(table_NAME, "ID = ?", new String[] {String.valueOf(id)});
        db.close();
    }

    public int UpdateTravel (int id, String Title, String Country, String City, String Description, int Visited) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("Title", Title);
        cv.put("Country", Country);
        cv.put("City", City);
        cv.put("Description", Description);
        cv.put("Visited", Visited);

       int i = db.update(table_NAME, cv, "ID = ?",  new String[] {String.valueOf(id)});
       db.close();
        return i;
    }
}
