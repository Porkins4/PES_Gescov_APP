package com.example.gescov.viewlayer.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.example.gescov.GescovUtils;
import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.Exceptions.AdapterNotSetException;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForumFragment extends Fragment {


    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";


    private String mParam1;
    private String mParam2;

    private ForumController controller;

    public ForumFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ForumFragment.
     */

    public static ForumFragment newInstance(String param1, String param2) {
        ForumFragment fragment = new ForumFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        controller = PresentationControlFactory.getForumController();
        try {
            PresentationControlFactory.getSchoolsCrontroller().refreshAllSchoolsList();
        } catch (JSONException  e) {
            e.printStackTrace();
        }
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_school_administration, container, false);
        FloatingActionButton fab = view.findViewById(R.id.add_forum_entry_button);
        ListView list = (ListView) view.findViewById(R.id.news_list);
        controller.setListViewAdapter(this.getContext());
        ForumEntryListViewAdapter adapter = (ForumEntryListViewAdapter) controller.getListViewAdapter();

        list.setAdapter(adapter);
        refreshList();

        User user = PresentationControlFactory.getLoadingProfileController().getLoggedInUser();

        //if the user is not admin of any school, hide the fab
        boolean isUserAdmin = false;
        if (user != null)
            for (String schoolId : user.getSchoolsID()) {
                School school = PresentationControlFactory.getSchoolsCrontroller().getSchoolById(schoolId);
                if (school != null && GescovUtils.isUserSchoolAdmin(user, school)) {
                    isUserAdmin = true;
                    break;
                }
            }

        if (isUserAdmin)
            fab.setOnClickListener(e -> {
                setCreateButtonActions();
            });
        else fab.setVisibility(View.INVISIBLE);
        return view;
    }

    protected void setCreateButtonActions() {
        Intent intent = new Intent(getActivity(), CreateForumEntryFormActivity.class);
        startActivity(intent);
    }

    protected void refreshList() {
        controller.refreshList();
    }

}