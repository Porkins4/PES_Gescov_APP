package com.example.gescov.viewlayer.ClassroomActivities.ClassroomDistribution;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.gescov.R;
import com.example.gescov.viewlayer.ClassroomActivities.MarkPositionInClassroom.MarkPositionInClassroom;

import androidx.gridlayout.widget.GridLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ClassroomDistributionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ClassroomDistributionFragment extends Fragment {

    //-----------------------------
    //my atributes
    private ClassroomDistributionController controller;
    private GridLayout gridLayout;
    private ClassroomDistributionTableWidget [][] distribution;
    //-----------------------------

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private String [][] distr = {
            {"-1","Albert","-1","Ponç"},
            {"Pablo","-1","0","-1"},
            {"-1","Anas","-1","Isaac"},
    };

    public ClassroomDistributionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AllSchoolsListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ClassroomDistributionFragment newInstance(String param1, String param2) {
        ClassroomDistributionFragment fragment = new ClassroomDistributionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View thisView = inflater.inflate(R.layout.fragment_show_distribution, container, false);
        controller = new ClassroomDistributionController();
        showDistribution(thisView);
        return thisView;
    }

    @Override
    public void onResume() {
        super.onResume();
        int toastTime = Toast.LENGTH_SHORT;
        Toast x = Toast.makeText(this.getContext(), getResources().getText(R.string.Catalan_successful_reservation),toastTime);
        x.show();
    }

    private void showDistribution(View thisView) {
        gridLayout = (GridLayout) thisView.findViewById(R.id.show_distribution_grid);
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
                //y.setFragment(this);
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
        Intent i = new Intent(getActivity(), MarkPositionInClassroom.class);
        i.putExtra("row",rowPos);
        i.putExtra("col",colPos);
        i.putExtra("studentName", "Marquitos");
        startActivity(i);
    }
}