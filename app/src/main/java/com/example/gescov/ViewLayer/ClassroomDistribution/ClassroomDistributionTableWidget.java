package com.example.gescov.ViewLayer.ClassroomDistribution;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.gescov.R;

public class ClassroomDistributionTableWidget {

    private Button table;
    private TextView studentName;
    private View inflated_Layout;

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
        } else {
            table.setBackgroundColor(table.getResources().getColor(R.color.occuped_site));
            studentName.setText(s);
        }
    }
}
