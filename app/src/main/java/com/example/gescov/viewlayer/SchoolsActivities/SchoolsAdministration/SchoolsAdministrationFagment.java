package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Exceptions.AdapterNotSetException;
import com.example.gescov.viewlayer.SchoolsActivities.studentschools.allSchools.SchoolListViewAdapter;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SchoolsAdministrationFagment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SchoolsAdministrationFagment extends Fragment {

    SchoolsCrontroller controller;
    FloatingActionButton createSchoolButton;

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

        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View thisView = inflater.inflate(R.layout.fragment_school_administration, container, false);
        createSchoolButton = thisView.findViewById(R.id.add_forum_entry_button);
        ListView list = (ListView) thisView.findViewById(R.id.news_list);
        createSchoolListViewAdapter(list.getContext());
        SchoolListViewAdapter adapter = getAdapter();

        list.setAdapter(adapter);
        refreshSchoolsList();
        PresentationControlFactory.getLoadingProfileController().refreshLoggedUser();

        createSchoolButton.setOnClickListener(e -> {
            setCreateButtonActions();
        });
        return thisView;
    }

    protected void setCreateButtonActions() {
        Intent intent = new Intent(getActivity(), CreateSchoolFormActivity.class);
        startActivity(intent);
    }

    protected void createSchoolListViewAdapter(Context context) {
        controller.createAdminSchoolListViewAdapter(context);
    }

    protected SchoolListViewAdapter getAdapter() {
        return controller.getAdminSchoolListViewAdapter();
    }

    protected void refreshSchoolsList() {
        try {
            controller.refreshAllSchoolsList();
        } catch (JSONException  e) {
            e.printStackTrace();
        }
    }

    protected void enableCreateSchoolButton(boolean b) {
        createSchoolButton.setVisibility(b ? View.VISIBLE : View.INVISIBLE);
    }

    protected SchoolsCrontroller getController() {
        return controller;
    }
}