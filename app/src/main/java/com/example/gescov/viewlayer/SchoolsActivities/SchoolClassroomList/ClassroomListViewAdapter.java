package com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Classroom;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution.ClassroomDistributionActivity;
import com.example.gescov.viewlayer.ClassroomActivities.StudentsInClassRecord.StudentsInClassRecordActivity;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolClassroomList.classroomSchedule.InsertScheduleActivity;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class ClassroomListViewAdapter extends BaseAdapter {

    private List<Classroom> classroomList;
    private LayoutInflater mInflater;
    private Context context;
    private String schoolID;
    private Boolean admin;

    public ClassroomListViewAdapter(Context c, List<Classroom> l) {
        context = c;
        classroomList = l;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }


    public List<Classroom> getNameList() {
        if (classroomList != null)
            return classroomList;
        classroomList = new ArrayList<>();
        return classroomList;
    }


    @Override
    public int getCount() {
        return classroomList.size();
    }

    @Override
    public Object getItem(int position) {
        return classroomList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = mInflater.inflate(R.layout.classroom_list_item, null);
        TextView name = (TextView) v.findViewById(R.id.wall_entry_list_item_title);
        TextView capacity = (TextView) v.findViewById(R.id.wall_item_reply_list_item_content);
        TextView rows = (TextView) v.findViewById(R.id.classroom_rows);
        TextView columns = (TextView) v.findViewById(R.id.classroom_columns);
        Button editButton = v.findViewById(R.id.classroom_list_item_edit_button);

        if ( admin ) {
            editButton.setOnClickListener(e -> {
                SchoolClassroomsCrontroller controller = PresentationControlFactory.getClassroomsCrontroller();
                Intent intent = new Intent(context, EditClassroomFormActivity.class);
                intent.putExtra("classroom_position", position);
                intent.putExtra("classID", classroomList.get(position).getId());
                intent.putExtra("schoolID", schoolID);

                context.startActivity(intent);
            });
        } else {
            editButton.setText(context.getString(R.string.see_schedule));
            editButton.setOnClickListener(v1 -> {
                Intent intent = new Intent(context, InsertScheduleActivity.class);
                intent.putExtra("classID", classroomList.get(position).getId());
                intent.putExtra("schoolID", schoolID);
                intent.putExtra("admin",admin);
                context.startActivity(intent);
            });
        }
        //-----------------------------------------------------------------------
        if (PresentationControlFactory.getUpdateUserProfileController().getUserType() == User.UserProfileType.TEACHER) {
            name.setOnClickListener(e-> {
                Intent intent = new Intent(context, StudentsInClassRecordActivity.class);
                intent.putExtra("classroom", classroomList.get(position).getId());
                context.startActivity(intent);
            });
        } else {
            name.setOnClickListener(e-> {
                Intent intent = new Intent(context, ClassroomDistributionActivity.class);
                intent.putExtra("classroom", classroomList.get(position).getId());
                context.startActivity(intent);
            });
        }
        //-----------------------------------------------------------------------


        name.setText(classroomList.get(position).getName());
        capacity.setText(String.valueOf(classroomList.get(position).getCapacity() + " " + v.getResources().getText(R.string.classroom_capacity)));
        rows.setText(String.valueOf(classroomList.get(position).getRows() + " " + v.getResources().getText(R.string.classroom_rows)));
        columns.setText(String.valueOf(classroomList.get(position).getColumns() + " " + v.getResources().getText(R.string.classroom_cols)));
        return v;
    }


    public void setList(List<Classroom> classroomsList) {
        this.classroomList = classroomsList;
        notifyDataSetChanged();
    }

    public void setSchoolID(String schooldID) {
        this.schoolID = schooldID;
    }

    public void setIfAdmin(boolean admin) {
        this.admin = admin;

    }
}