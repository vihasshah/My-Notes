package com.mynotes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.mynotes.Helper.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    NoteAdapter adapter;
    ListView listView;
    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        // creating data
//        ArrayList<NoteModel> arrayList = new ArrayList<>();
//        NoteModel noteModel = new NoteModel();
//        noteModel.setNoteTitle("Fist note");
//        noteModel.setNoteMessage("Also please don’t use fillVendorFeedback\n"+
//                "We will remove 2 screens i.e. for feedback and rating\n" +
//                "Let customer put feedback and rating from 1 screen only. So now we will have only one screen of Ratings.\n" +
//                "Let customer put feedback and rating from 1 screen only. So now we will have only one screen of Ratings.\n" +
//                "You work accordingly and make the field Comments in Rating screen mandatory.\n");
//
//        noteModel.setDate("Feb 25, 2017");
//        noteModel.setTime("13:54");
//        arrayList.add(noteModel);
//        NoteModel noteModel1 = new NoteModel();
//        noteModel1.setNoteTitle("Fist note");
//        noteModel1.setNoteMessage("Also please don’t use fillVendorFeedback\n"+
//                "We will remove 2 screens i.e. for feedback and rating\n" +
//                "Let customer put feedback and rating from 1 screen only. So now we will have only one screen of Ratings.\n" +
//                "Let customer put feedback and rating from 1 screen only. So now we will have only one screen of Ratings.\n" +
//                "You work accordingly and make the field Comments in Rating screen mandatory.\n");
//
//        noteModel1.setDate("Feb 25, 2017");
//        noteModel1.setTime("13:54");
//        arrayList.add(noteModel1);


        listView = (ListView) findViewById(R.id.main_list_view);
//        if(arrayList.size() > 0) {


//        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<NoteModel> arrayList = helper.getData();
        adapter = new NoteAdapter(this, arrayList);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_add_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.menu_add_icon){
            Intent intent = new Intent(MainActivity.this,AddNotesActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
