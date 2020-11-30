package com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.gridlayout.widget.GridLayout;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.DomainLayer.Classmodels.Assignment;
import com.example.gescov.DomainLayer.Classmodels.Classroom;
import com.example.gescov.R;
import com.example.gescov.viewlayer.ClassroomActivities.MarkPositionInClassroom.MarkPositionInClassroom;

import java.util.List;

public class ClassroomDistributionActivity extends AppCompatActivity {

    private ClassroomDistributionController controller;
    private GridLayout gridLayout;
    private ClassroomDistributionTableWidget [][] distribution;
    private ClassroomDsitributionViewModel classroomDsitributionViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classroom_ditribution);
        controller = new ClassroomDistributionController();
        initViewComponents();
        //showDistribution();
    }

    private void initViewComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.distribution_activity_toolbar);
        setSupportActionBar(toolbar);
        gridLayout = (GridLayout) findViewById(R.id.show_distribution_grid);
        initResponseListener();


    }

    private void initResponseListener() {
        String classroomId = getIntent().getStringExtra("classroom");
        classroomDsitributionViewModel = new ViewModelProvider(this).get(ClassroomDsitributionViewModel.class);
        classroomDsitributionViewModel.getData(classroomId,null,null).observe(this,
                new Observer<ClassroomDistributionInfo>() {
                    @Override
                    public void onChanged(ClassroomDistributionInfo classroomDistributionInfo) {
                        if (!classroomDistributionInfo.isError()) {
                            System.out.println("tot ok");
                            getClassroomInfo();
                        } else {//class without assignments
                            getClassroomInfo();
                        }
                    }
                }
        );
    }

    private void getClassroomInfo() {
        classroomDsitributionViewModel.getClassroom(getIntent().getStringExtra("classroom")).observe(this,
                new Observer<ClassroomDistributionClassInfo>() {
                    @Override
                    public void onChanged(ClassroomDistributionClassInfo classroomDistributionClassInfo) {
                        System.out.println("tot ok x2");
                        if (!classroomDistributionClassInfo.isError()) {
                            showDistribution();
                        }
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


    public void launchMarkPosition(String studentId, int rowPos, int colPos) {
        Intent i = new Intent(this, MarkPositionInClassroom.class);
        i.putExtra("row",rowPos);
        i.putExtra("col",colPos);
        i.putExtra("classSessionID","5fbbc7b717c2475bc4de4f48");
        startActivity(i);
    }
}