package com.mynotes;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.mynotes.Helper.DatabaseHelper;

import java.util.ArrayList;

public class AddNotesActivity extends AppCompatActivity {
    DatabaseHelper helper = new DatabaseHelper(this);
    EditText titleET,dateET,timeET,messageET;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_notes);
        // init
        titleET = (EditText) findViewById(R.id.add_notes_title);
        dateET = (EditText) findViewById(R.id.add_notes_date);
        timeET = (EditText) findViewById(R.id.add_notes_time);
        messageET = (EditText) findViewById(R.id.add_notes_details);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.add_notes_save_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.add_notes_save_icon){
            // save to database
            String titleStr = titleET.getText().toString();
            String dateStr = dateET.getText().toString();
            String timeStr = timeET.getText().toString();
            String messageStr = messageET.getText().toString();
            // creating model of data
            NoteModel model = new NoteModel();
            model.setNoteTitle(titleStr);
            model.setDate(dateStr);
            model.setTime(timeStr);
            model.setNoteMessage(messageStr);
            // store object to arraylist
            ArrayList<NoteModel> arrayList = new ArrayList<>();
            arrayList.add(model);
            // store to database
            if(helper.insertData(arrayList)){
                Toast.makeText(this, "Note Saved", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }else{
                Toast.makeText(this, "Note not saved", Toast.LENGTH_SHORT).show();
            }
        }
        return super.onOptionsItemSelected(item);
    }
}
