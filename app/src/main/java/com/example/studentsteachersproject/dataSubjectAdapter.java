package com.example.studentsteachersproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class dataSubjectAdapter extends BaseAdapter {
    ArrayList<dataSubject> dataSubjects;
    Context context;

    public dataSubjectAdapter(ArrayList<dataSubject> dataSubjects, Context context) {
        this.dataSubjects = dataSubjects;
        this.context = context;
    }

    @Override
    public int getCount() {
        return dataSubjects.size();
    }

    @Override
    public dataSubject getItem(int position) {
        return dataSubjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        dataSubject tmp = dataSubjects.get(position);
        convertView = LayoutInflater.from(context).inflate(R.layout.row_list_subject, null);

        TextView tv_subject_name = convertView.findViewById(R.id.tv_subject_name);
        TextView tv_teacher_name = convertView.findViewById(R.id.tv_teacher_name);
        TextView tv_teacher_last_name = convertView.findViewById(R.id.tv_teacher_last_name);


        tv_teacher_name.setText(tmp.getTeacherName());
        tv_teacher_last_name.setText(tmp.getTeacherLastName());
        tv_subject_name.setText(tmp.getSubjectName());

        return convertView;

    }
}
