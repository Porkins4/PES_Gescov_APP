package com.example.gescov.ViewLayer.SchoolsAdministration;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gescov.ViewLayer.PresentationControlFactory;
import com.example.gescov.R;
import com.example.gescov.ViewLayer.SchoolClassroomList.SchoolClassroomsCrontroller;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SchoolsAdministrationFagment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchoolsAdministrationFagment extends Fragment {

    SchoolsCrontroller controller;
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SchoolsAdministrationFagment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SchoolsAdministrationFagment.
     */
    // TODO: Rename and change types and number of parameters
    public static SchoolsAdministrationFagment newInstance(String param1, String param2) {
        SchoolsAdministrationFagment fragment = new SchoolsAdministrationFagment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        controller = PresentationControlFactory.getSchoolsCrontroller();
        controller.setSchoolsAdministrationFragment(this);

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View thisView = inflater.inflate(R.layout.fragment_school_administration, container, false);
        FloatingActionButton fab = thisView.findViewById(R.id.add_school_button);
        ListView list = (ListView) thisView.findViewById(R.id.schools_list);

        controller.setSchoolListViewAdapter(list.getContext());
        SchoolListViewAdapter adapter = controller.getSchoolListViewSchoolAdapter();
        list.setAdapter(adapter);
        try {
            controller.refresh();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        fab.setOnClickListener(e -> {
            Intent intent = new Intent(getActivity(), CreateSchoolFormActivity.class);
            startActivity(intent);
        });
        return thisView;
    }

    public void updateData(String info) throws JSONException {
        JSONArray response = new JSONArray(info);
        for (int i = 0; i < response.length(); ++i) {
            JSONObject aux = response.getJSONObject(i);
            String nameSchool = aux.getString("name");
            controller.getSchoolListViewSchoolAdapter().addItem(nameSchool);
            //I/System.out: {"id":{"timestamp":1603915614,"date":"2020-10-28T20:06:54.000+00:00"},"name":"FIB","state":"open","address":"Carrer Jordi Girona, 1-3","longitude":0,"latitude":0,"creator":"Xicu Torres"}el objeto enetro

        }
        controller.getSchoolListViewSchoolAdapter().notifyDataSetChanged();
    }
}