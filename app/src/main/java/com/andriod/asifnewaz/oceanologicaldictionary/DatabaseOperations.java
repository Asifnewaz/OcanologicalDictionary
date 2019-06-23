package com.andriod.asifnewaz.oceanologicaldictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseOperations {
    public static final String LOGTAG = "EMP_MNGMNT_SYS";

    DatabaseHelper dbhandler;
    SQLiteDatabase database;

    private static final String[] allColumns = {
            DatabaseHelper.COLUMN_ID,
            DatabaseHelper.COLUMN_WORD,
            DatabaseHelper.COLUMN_DEFINATION,
            DatabaseHelper.COLUMN_IS_BOOKMARKED
    };

    public DatabaseOperations(Context context) {
        dbhandler = new DatabaseHelper(context);
    }

    public void open() {
        Log.i(LOGTAG, "Database Opened");
        database = dbhandler.getWritableDatabase();

    }

    public void close() {
        Log.i(LOGTAG, "Database Closed");
        dbhandler.close();
    }

    public void addEmployee(Model model) {
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ID, model.getId());
        values.put(DatabaseHelper.COLUMN_WORD, model.getWord());
        values.put(DatabaseHelper.COLUMN_DEFINATION, model.getDefinition());
        values.put(DatabaseHelper.COLUMN_IS_BOOKMARKED, model.getIsBookmarked());
        database.insert(DatabaseHelper.TABLE_BOOKMARKS, null, values);

        close();
    }

    // Getting single Employee


    public ArrayList<Model> getAllModelValues() {
        open();
        Cursor cursor = database.query(DatabaseHelper.TABLE_BOOKMARKS, allColumns, null, null, null, null, null);
        ArrayList<Model> models = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Model model = new Model();
                model.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                model.setWord(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_WORD)));
                model.setDefinition(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DEFINATION)));
                model.setBookmarked(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_IS_BOOKMARKED)));
                models.add(model);
            }
        }
        close();
        // return All Employees
        return models;

    }

    public ArrayList<Model> getAllDataFromDictionary1(Context context) {

        dbhandler =new DatabaseHelper(context);
        try {
            dbhandler.createDataBase();
            dbhandler.openDataBase();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        database = dbhandler.getReadableDatabase();
//        open();/

        Cursor cursor = database.query(DatabaseHelper.TABLE_DICTION, allColumns, null, null, null, null, null);
        ArrayList<Model> models = new ArrayList<>();
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                Model model = new Model();
                model.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
                model.setWord(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_WORD)));
                model.setDefinition(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DEFINATION)));
                model.setBookmarked(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_IS_BOOKMARKED)));

                models.add(model);

            }
        }
        close();
        // return All Employees
        return models;

    }

    // Updating Employee
    public int updateModelValues(Model model,String tableName) {
        open();
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_ID, model.getId());
        values.put(DatabaseHelper.COLUMN_WORD, model.getWord());
        values.put(DatabaseHelper.COLUMN_DEFINATION, model.getDefinition());
        values.put(DatabaseHelper.COLUMN_IS_BOOKMARKED, model.getIsBookmarked());

//        close();
        // updating row
        return database.update(tableName, values,
                DatabaseHelper.COLUMN_ID + "=?",new String[] { String.valueOf(model.getId())});

    }


    // Deleting Employee
    public void removeSingleModelValue(Model model) {
        open();
        database.delete(DatabaseHelper.TABLE_BOOKMARKS, DatabaseHelper.COLUMN_ID + "=" + model.getId(), null);
        close();
    }

//    public Model searchSingleModelValueByWord(String word){
//        open();
//        Model model = new Model();
//        Cursor cursor = database.query(DatabaseHelper.TABLE_DICTIONARY, allColumns, DatabaseHelper.COLUMN_WORD+"="+"LIKE %"+ word, null, null, null, null);
//        if (cursor.getCount() > 0) {
//            while (cursor.moveToNext()) {
//                model.setId(cursor.getLong(cursor.getColumnIndex(DatabaseHelper.COLUMN_ID)));
//                model.setWord(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_WORD)));
//                model.setDefinition(cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DEFINATION)));
//                model.setBookmarked(cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_IS_BOOKMARKED)));
//            }
//        }
//        close();
//        // return All Employees
//        return model;
//    }
}
