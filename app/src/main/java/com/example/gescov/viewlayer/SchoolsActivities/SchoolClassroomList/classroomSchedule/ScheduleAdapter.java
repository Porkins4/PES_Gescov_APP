package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.classroomSchedule;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.anychart.core.Base;
import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class ScheduleAdapter extends BaseAdapter {
    private List<Subject> subjects;
    private List<String> subjectsOfDay;
    private LayoutInflater mInflater;

    public ScheduleAdapter(Context c, List<String> list) {
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

    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.schedule_item,null);
        Button edit = v.findViewById(R.id.modify_schedule);

        TextView time = v.findViewById(R.id.time);
        TextView subjectName = v.findViewById(R.id.subject);
        if ( !subjectsOfDay.isEmpty() && subjectsOfDay.get(position) != null ) subjectName.setText(subjectsOfDay.get(position));
        Integer from = position + 8;
        Integer end = from +1 ;
        time.setText(from.toString() + "AM " +"- "+ end.toString()+"AM");
        setListenerEdit(edit,v);

        return v;

    }

    private void setListenerEdit(Button edit, View v) {
        edit.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
            String [] options = new String[subjects.size()];
            for (int i = 0 ; i  < subjects.size(); ++i)
                options[i] = subjects.get(i).getName();
            builder.setItems(options,(dialog, which) -> {
                subjectsOfDay.add( subjects.get(which).getName());
                notifyDataSetChanged();
            });
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }

    public void setSubjects (List<Subject> subjects) {
        this.subjects = subjects;
    }
}
