package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.classroomSchedule;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Subject;

import java.util.List;

public class ScheduleAdapter extends BaseAdapter {
    private List<Subject> subjects;
    private List<Subject> subjectsOfDay;
    private LayoutInflater mInflater;
    private Boolean admin;

    public ScheduleAdapter(Context c, List<Subject> list) {
        subjectsOfDay = list;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    @Override
    public int getCount() {
        return subjectsOfDay.size();
    }

    @Override
    public Object getItem(int position) {
        return subjectsOfDay.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public List<Subject> getSubjectsOfDay() {
        return subjectsOfDay;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.schedule_item,null);
        Button edit = v.findViewById(R.id.modify_schedule);
        if ( admin ) edit.setVisibility(View.VISIBLE);
        else edit.setVisibility(View.INVISIBLE);

        TextView time = v.findViewById(R.id.time);
        TextView subjectName = v.findViewById(R.id.subject);
        if ( !subjectsOfDay.isEmpty() && subjectsOfDay.get(position) != null ) subjectName.setText(subjectsOfDay.get(position).getName());
        Integer from = position + 8;
        Integer end = from +1 ;
        if ( from == 11 )time.setText(from.toString() + "AM " +"- "+ end.toString()+"PM");
        else if ( from < 11) time.setText(from.toString() + "AM " +"- "+ end.toString()+"AM");
        else time.setText(from.toString() + "PM " +"- "+ end.toString()+"PM");
        setListenerEdit(edit,v, position);

        return v;

    }

    private void setListenerEdit(Button edit, View v, int position) {
        edit.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            String [] options = new String[subjects.size()];
            for (int i = 0 ; i  < subjects.size(); ++i)
                options[i] = subjects.get(i).getName();
            builder.setItems(options,(dialog, which) -> {
                subjectsOfDay.set(position, subjects.get(which));
                notifyDataSetChanged();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    public void setSubjects (List<Subject> subjects) {
        this.subjects = subjects;
    }


    public void sendTypeUser(Boolean admin) {
        this.admin = admin;
    }
}
