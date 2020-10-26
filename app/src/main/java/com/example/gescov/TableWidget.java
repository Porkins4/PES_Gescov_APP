package com.example.gescov;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.LinearLayout;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class TableWidget {

    private Button table;
    private TextView studentName;
    private View inflated_Layout;

    public TableWidget() {

    }

    public TableWidget(View k) {
        inflated_Layout = k;
        table = k.findViewById(R.id.TableButton);
        studentName = k.findViewById(R.id.StudentName);
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
