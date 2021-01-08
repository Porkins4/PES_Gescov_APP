package com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Assignment;
import com.example.gescov.domainlayer.Classmodels.Classroom;
import com.example.gescov.viewlayer.ClassroomActivities.MarkPositionInClassroom.MarkPositionInClassroom;

import java.util.List;

public class ClassroomDistributionActivity extends AppCompatActivity {

    private static final int SUCCESS_RESERVATION_REQUEST_CODE = 200;
    private GridLayout gridLayout;
    private ClassroomDistributionTableWidget [][] distribution;
    private ClassroomDsitributionViewModel classroomDsitributionViewModel;
    private int row;
    private int col;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_ditribution);
        initViewComponents();
    }

    private void initViewComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.title_activity_classroom_distribution);
        gridLayout = (GridLayout) findViewById(R.id.show_distribution_grid);
        findViewById(R.id.information).setOnClickListener(
                v -> {
                    Intent i = new Intent(this, InformationActivity.class);
                    startActivity(i);
                }
        );
        initResponseListener();
    }

    private void initResponseListener() {
        classroomDsitributionViewModel = new ViewModelProvider(this).get(ClassroomDsitributionViewModel.class);
        classroomDsitributionViewModel.getData(getIntent().getStringExtra("classSession")).observe(this,
                classroomDistributionInfo -> getClassroomInfo()
        );
    }

    private void getClassroomInfo() {
        classroomDsitributionViewModel.getClassroom(getIntent().getStringExtra("classroom")).observe(this,
                classroomDistributionClassInfo -> {
                    if (!classroomDistributionClassInfo.isError()) {
                        showDistribution();
                    }
                }
        );
    }

    private void showDistribution() {
        Classroom c = classroomDsitributionViewModel.getClassroomInfo();//mejor no poner datos del modelo aquí
        gridLayout.setRowCount(c.getRows());//filas del grid
        gridLayout.setColumnCount(c.getColumns()); //columnas del grid

        distribution = new ClassroomDistributionTableWidget[c.getRows()][c.getColumns()];

        for(int i = 0; i < c.getRows(); ++i) {
            for (int j = 0; j < c.getColumns(); ++j) {
                ClassroomDistributionTableWidget y = new ClassroomDistributionTableWidget(getLayoutInflater().inflate(R.layout.table_item,null,false));
                //y.setValues("userId","fila","columna"); -> estos serian los valores que guardaria en eun widget
                y.initTable("0"); //de momento solo se utiliza esta llamada.
                y.setParent(this);
                y.setColPos(j);
                y.setRowPos(i);
                distribution[i][j] = y;
                gridLayout.addView(y.getTableLayout());
            }
        }
        showStudents();
    }

    private void showStudents() {
        List<Assignment> assignments = classroomDsitributionViewModel.getAssignmentsList();
        for (int i = 0; i < assignments.size(); ++i) {
            distribution[assignments.get(i).getRow()-1][assignments.get(i).getCol()-1].initTable(assignments.get(i).getStudentName());
        }
    }


    public void launchMarkPosition(int rowPos, int colPos) {
        Intent i = new Intent(this, MarkPositionInClassroom.class);
        row = rowPos;
        col = colPos;
        i.putExtra("row",rowPos);
        i.putExtra("col",colPos);
        i.putExtra("classSessionID",getIntent().getStringExtra("classSession"));
        startActivityForResult(i,SUCCESS_RESERVATION_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == SUCCESS_RESERVATION_REQUEST_CODE && resultCode == RESULT_OK) {
                distribution[row][col].initTable(classroomDsitributionViewModel.getUserName().getName());
        }
    }
}