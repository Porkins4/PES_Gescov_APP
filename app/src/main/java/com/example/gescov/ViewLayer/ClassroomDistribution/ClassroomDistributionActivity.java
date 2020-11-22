package com.example.gescov.ViewLayer.ClassroomDistribution;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;

import com.example.gescov.R;
import com.example.gescov.ViewLayer.MarkPositionInClassroom.MarkPositionInClassroom;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ClassroomDistributionActivity extends AppCompatActivity {

    //my atributes
    private ClassroomDistributionController controller;
    private GridLayout gridLayout;
    private ClassroomDistributionTableWidget [][] distribution;
    private String classroomId;
    //-----------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_ditribution);
        classroomId = getIntent().getStringExtra("classroom_position");
        controller = new ClassroomDistributionController();
        showDistribution();
    }

    private void showDistribution() {
        gridLayout = (GridLayout) findViewById(R.id.show_distribution_grid);
        JSONObject jsonDimensions = getClassDimensionsFromController();
        int rows = getJSONvalue(jsonDimensions,"first");
        int cols = getJSONvalue(jsonDimensions,"second");
        gridLayout.setRowCount(rows);//filas del grid
        gridLayout.setColumnCount(cols); //columnas del grid
        distribution = new ClassroomDistributionTableWidget[rows][cols];

        for(int i = 0; i < rows; ++i) {
            for (int j = 0; j < cols; ++j) {
                ClassroomDistributionTableWidget y = new ClassroomDistributionTableWidget(getLayoutInflater().inflate(R.layout.table_item,null,false));
                //y.setValues("userId","fila","columna"); -> estos serian los valores que guardaria en eun widget
                y.initTable("0"); //de momento solo se utiliza esta llamada.
                y.setFragment(this);
                y.setColPos(j);
                y.setRowPos(i);
                distribution[i][j] = y;
                gridLayout.addView(y.getTableLayout());
            }
        }

        showStudents();
    }

    private void showStudents() {
        JSONArray response = null;
        try {
            response = new JSONArray(controller.getStudentsInClassroom("hardcodeClassroom"));
            System.out.println(response.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < response.length(); ++i) {
            JSONObject o = null;
            try {
                o = response.getJSONObject(i);
                int row = Integer.parseInt(o.getString("posRow"));
                int col = Integer.parseInt(o.getString("posCol"));
                System.out.println("row: " + row + " col: " + col);
                JSONObject student = new JSONObject(o.getString("student"));
                distribution[row][col].initTable(student.getString("name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private int getJSONvalue (JSONObject o, String field) {
        try {
            return Integer.parseInt(o.getString(field));
        } catch (JSONException e) {
            System.out.println("Error while getting dimension parameter");
        }
        return -1;
    }

    private JSONObject getClassDimensionsFromController() {
        String dimensions = controller.getClassDimensions("harcoded school","class Id");
        System.out.println(dimensions);//HEY, BORRAME
        try {
            return new JSONObject(dimensions);
        } catch (JSONException e) {
            System.out.println("Error while trying to create JSON object with dimensions");
        }
        return null;
    }

    public void launchMarkPosition(String studentId, int rowPos, int colPos) {
        Intent i = new Intent(this, MarkPositionInClassroom.class);
        i.putExtra("row",rowPos);
        i.putExtra("col",colPos);
        i.putExtra("studentName", "Marquitos");
        startActivity(i);
    }
}