package com.example.gescov.viewlayer.forum;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gescov.R;
import com.example.gescov.viewlayer.Exceptions.AdapterNotSetException;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONException;

import androidx.fragment.app.Fragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ForumFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ForumFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
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
    // TODO: Rename and change types and number of parameters
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
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (AdapterNotSetException e) {
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
        PresentationControlFactory.getForumController().refreshList();
        controller.setListViewAdapter(this.getContext());
        ForumEntryListViewAdapter adapter = (ForumEntryListViewAdapter) controller.getListViewAdapter();

        list.setAdapter(adapter);
        refreshList();

        fab.setOnClickListener(e -> {
            setCreateButtonActions();
        });
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