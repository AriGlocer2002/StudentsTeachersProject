package com.example.studentsteachersproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class studentAdapter extends BaseAdapter {
    ArrayList<Student> students;
    Context context;

    public studentAdapter(ArrayList<Student> students, Context context) {
        this.students = students;
        this.context = context;
    }

    @Override
    public int getCount() {
        return students.size();
    }

    @Override
    public Student getItem(int position) {
        return students.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Student tmp = students.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.row_list_student, null);

        TextView tv_lname = convertView.findViewById(R.id.tv_last_name);
        TextView tv_name = convertView.findViewById(R.id.tv_name);
        TextView tv_id = convertView.findViewById(R.id.tv_id);
        TextView tv_classroom = convertView.findViewById(R.id.tv_classroom);
        TextView tv_average = convertView.findViewById(R.id.tv_average);

        tv_lname.setText(tmp.getLastName());
        tv_name.setText(tmp.getName());
        tv_id.setText(String.valueOf(tmp.getId()));
        tv_classroom.setText(tmp.getClassName());
        tv_average.setText(String.valueOf(tmp.getAverage()));

        return convertView;
    }
}
