package com.example.gescov.ViewLayer.ClassroomDistribution;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.MarkPositionInClassroom.MarkPositionInClassroom;

public class ClassroomDistributionTableWidget {

    private int rowPos, colPos;
    private String studentId;
    private Button table;
    private TextView studentName;
    private View inflated_Layout;
    private ClassroomDistributionFragment parentFragment;

    public ClassroomDistributionTableWidget() {

    }

    public ClassroomDistributionTableWidget(View k) {
        inflated_Layout = k;
        table = k.findViewById(R.id.table_button);
        studentName = k.findViewById(R.id.student_name);
    }

    public View getTableLayout () {
        return inflated_Layout;
    }

    public void initTable(String s) {
        if (s.equals("-1")) {
            table.setBackgroundColor(table.getResources().getColor(R.color.unabled_site));
            table.setText("x");
            studentName.setText("");
        } else if (s.equals("0")) {
            table.setBackgroundColor(table.getResources().getColor(R.color.free_site));
            table.setText(table.getResources().getText(R.string.catalan_tableStatus_Free));
            studentName.setText("");
            createListener();
        } else {
            table.setBackgroundColor(table.getResources().getColor(R.color.occuped_site));
            studentName.setText(s);
        }
    }

    private void createListener() {
        table.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        parentFragment.launchMarkPosition(studentId,rowPos,colPos);
                    }
                }
        );
    }

    public void setFragment(ClassroomDistributionFragment classroomDistributionFragment) {
        parentFragment = classroomDistributionFragment;
    }
}
