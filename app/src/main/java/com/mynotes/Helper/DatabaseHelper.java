package com.mynotes.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.mynotes.NoteModel;

import java.util.ArrayList;

/**
 * Created by Vihas on 26-02-2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "collection.db";
    private static final String TABLE_NAME = "mynotes";
    private static final String CLM_ID = "id";
    private static final String CLM_TITLE = "title";
    private static final String CLM_DATE = "date";
    private static final String CLM_TIME = "time";
    private static final String CLM_MESSAGE = "message";
    private static final int DATABASE_VERSION = 1;

    public DatabaseHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String query = "create table mynotes(title varchar(255),date varchar(20),time varchar(20),message varchar(255))";
        String query = "create table "+ TABLE_NAME +"("
                + CLM_ID +" integer primary key autoincrement,"
                + CLM_TITLE +" varchar(255),"
                + CLM_DATE +" varchar(20),"
                + CLM_TIME +" varchar(20),"
                + CLM_MESSAGE +" varchar(255))";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query  = "DROP TABLE IF EXISTS "+TABLE_NAME;
        db.execSQL(query);
        onCreate(db);
    }

    public boolean insertData(ArrayList<NoteModel> arrayList){
        SQLiteDatabase db = this.getWritableDatabase();
        int count = arrayList.size();
        for (int i = 0; i < count; i++) {
            ContentValues values = new ContentValues();
            values.put(CLM_TITLE,arrayList.get(i).getNoteTitle());
            values.put(CLM_DATE,arrayList.get(i).getDate());
            values.put(CLM_TIME,arrayList.get(i).getTime());
            values.put(CLM_MESSAGE,arrayList.get(i).getNoteMessage());
            db.insert(TABLE_NAME,null,values);
        }
        return true;
    }

    public ArrayList<NoteModel> getData(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select * from "+TABLE_NAME;
        Cursor cursor = db.rawQuery(query,null);
        Log.d("myapp","cursor length: "+cursor.getCount());
        ArrayList<NoteModel> arrayList = new ArrayList<>();
        if(cursor != null){
            // initialize
            cursor.moveToFirst();
            while (!cursor.isAfterLast()){
                NoteModel noteModel = new NoteModel();
                noteModel.setNoteTitle(cursor.getColumnName(cursor.getColumnIndexOrThrow(CLM_TITLE)));
                noteModel.setDate(cursor.getColumnName(cursor.getColumnIndexOrThrow(CLM_DATE)));
                noteModel.setTime(cursor.getColumnName(cursor.getColumnIndexOrThrow(CLM_TIME)));
                noteModel.setNoteMessage(cursor.getColumnName(cursor.getColumnIndexOrThrow(CLM_MESSAGE)));
                arrayList.add(noteModel);
                cursor.moveToNext();
            }
            cursor.close();
            return arrayList;
        }else{
            return null;
        }
    }

    public void deleteData(int id){
        String query = "delete from "+TABLE_NAME +" where "+CLM_ID +" = "+id;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }

    public void deleteTable(){
        String query = "delete from "+TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(query);
    }
}
