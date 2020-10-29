package com.example.gescov.ViewLayer.ClassroomDistribution;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.gescov.R;

public class ClassroomDistributionTableWidget {

    private int rowPos;
    private int colPos;

    public void setRowPos(int rowPos) {
        this.rowPos = rowPos;
    }

    public void setColPos(int colPos) {
        this.colPos = colPos;
    }

    private String studentId;
    private Button table;
    private TextView studentView;
    private View inflated_Layout;
    private ClassroomDitributionActivity parentActivity;

    public ClassroomDistributionTableWidget() {

    }

    public ClassroomDistributionTableWidget(View k) {
        inflated_Layout = k;
        table = k.findViewById(R.id.table_button);
        studentView = k.findViewById(R.id.student_name);
    }

    public View getTableLayout () {
        return inflated_Layout;
    }

    public void initTable(String s) {
        if (table.getText().equals("x") || table.getText().equals(table.getResources().getText(R.string.catalan_tableStatus_Free))) table.setText("");
        if (s.equals("-1")) {
            table.setBackgroundColor(table.getResources().getColor(R.color.unabled_site));
            table.setText("x");
            studentView.setText("");

        } else if (s.equals("0")) {
            table.setBackgroundColor(table.getResources().getColor(R.color.free_site));
            table.setText(table.getResources().getText(R.string.catalan_tableStatus_Free));
            studentView.setText("");
            createListener();
        } else {
            table.setBackgroundColor(table.getResources().getColor(R.color.occuped_site));
            studentView.setText(s);
        }
    }

    private void createListener() {
        table.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //------------------------------------------------------------------
                        //messages in case that the table is not available for a reservation, otherwise launchs activity to reserve a table
                        int toastTime = Toast.LENGTH_SHORT;
                        if (table.getText().equals("x")) Toast.makeText(parentActivity,table.getResources().getText(R.string.Catalan_table_not_available),toastTime).show();
                        else if (table.getText().equals("")) Toast.makeText(parentActivity,table.getResources().getText(R.string.Catalan_table_already_assigned),toastTime).show();
                        else parentActivity.launchMarkPosition(studentId,rowPos,colPos);
                        //------------------------------------------------------------------

                    }
                }
        );
    }

    public void setFragment(ClassroomDitributionActivity classroomDistributionFragment) {
        parentActivity = classroomDistributionFragment;
    }
}
