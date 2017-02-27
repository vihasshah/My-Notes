package com.mynotes;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mynotes.Helper.DatabaseHelper;

import java.util.ArrayList;

/**
 * Created by Vihas on 25-02-2017.
 */

public class NoteAdapter extends BaseAdapter {
    Context context;
    ArrayList<NoteModel> arrayList;
    public NoteAdapter(Context context, ArrayList<NoteModel> arrayList){
        this.context = context;
        this.arrayList = arrayList;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    static class ViewHolder{
        TextView titleTV,messageTV,dateTimeTV;
        ImageView deleteIV;
    }
    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        final ViewHolder viewHolder;
        if(convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.single_row_notes,parent,false);
            viewHolder.titleTV = (TextView) convertView.findViewById(R.id.single_row_note_title);
            viewHolder.messageTV = (TextView) convertView.findViewById(R.id.single_row_note_message);
            viewHolder.dateTimeTV = (TextView) convertView.findViewById(R.id.single_row_date_time);
            viewHolder.deleteIV = (ImageView) convertView.findViewById(R.id.single_row_delete);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if((position % 2) == 0){
            convertView.setBackgroundColor(context.getResources().getColor(R.color.list_color));
        }else{
            convertView.setBackgroundColor(Color.WHITE);
        }
        viewHolder.titleTV.setText(arrayList.get(position).getNoteTitle());
        viewHolder.messageTV.setText(arrayList.get(position).getNoteMessage());
        String dateTime = arrayList.get(position).getDate()+" "+arrayList.get(position).getTime();
        viewHolder.dateTimeTV.setText(dateTime);
        viewHolder.deleteIV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("myapp","selected notetitle: "+ arrayList.get(position).getNoteTitle());
                if(new DatabaseHelper(context).deleteData(arrayList.get(position).getNoteTitle())){
                    arrayList.remove(position);
                    notifyDataSetChanged();
                }
            }
        });
        return convertView;
    }
}
