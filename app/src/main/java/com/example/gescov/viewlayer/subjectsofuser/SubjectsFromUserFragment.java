package com.example.gescov.viewlayer.subjectsofuser;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.Subject;
import com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration.Subjects.SubjectDetailActivity;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SubjectsFromUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubjectsFromUserFragment extends Fragment {

    private View v;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ListView listView;
    private LinearLayout error;
    private TextView errorMessage;
    private SubjectsFromUserViewModel viewModel;

    public SubjectsFromUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SubjectsFromUserFragment newInstance(String param1, String param2) {
        SubjectsFromUserFragment fragment = new SubjectsFromUserFragment();
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
        v = inflater.inflate(R.layout.fragment_subjects, container, false);
        initComponents();
        return v;
    }

    private void initComponents() {
        initViewComponents();
        viewModel = new ViewModelProvider(this).get(SubjectsFromUserViewModel.class);
        getSubjectsFromUser();
    }

    private void getSubjectsFromUser() {
        viewModel.getSubjectsFromUser().observe(getViewLifecycleOwner(),
                error -> {
                    if (!error) showSubjects();
                    else showErrorUI(getString(R.string.general_error_message));
                });
    }

    private void showSubjects() {
        if (viewModel.resultEmpty()) showErrorUI(getString(R.string.no_subjects_from_user));
        else listView.setAdapter(viewModel.getAdapter(this.getContext()));

    }

    private void showErrorUI(String errorText) {
        errorMessage.setText(errorText);
        error.setVisibility(View.VISIBLE);
    }

    private void initViewComponents() {
        initListView();
        this.error = (LinearLayout) v.findViewById(R.id.error);
        this.errorMessage = (TextView) v.findViewById(R.id.error_message);
    }

    private void initListView() {
        this.listView = (ListView) v.findViewById(R.id.list_view);
        listView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Intent i = new Intent(getContext(),SubjectDetailActivity.class);
                    i.putExtra("subjectID", viewModel.getSubjectID(position));
                    i.putExtra("schoolID", viewModel.getSchoolID(position));
                    i.putExtra("subjectName", viewModel.getSubjectName(position));
                    startActivity(i);
                }
        );

    }
}