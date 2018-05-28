package com.example.a16022653.p06taskmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TaskAdapter extends ArrayAdapter<Tasks> {
    Context context;
    ArrayList<Tasks> tasks;
    int resource;
    TextView tvName, tvDesc, tvID;


    public TaskAdapter(Context context, int resource, ArrayList<Tasks> tasks) {
        super(context, resource, tasks);
        this.context = context;
        this.tasks = tasks;
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(resource, parent, false);

        //Match the UI components with Java variables
        tvName = rowView.findViewById(R.id.tvName);
        tvDesc = rowView.findViewById(R.id.tvDesc);
        tvID = rowView.findViewById(R.id.tvID);

        Tasks task = tasks.get(position);

        tvName.setText(task.getName() + "");
        tvDesc.setText(task.getDesc() + "");
        tvID.setText(task.getId() + "");

        return rowView;
    }
}
