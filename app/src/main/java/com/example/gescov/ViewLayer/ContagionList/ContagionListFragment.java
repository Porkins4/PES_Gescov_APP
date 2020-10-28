package com.example.gescov.ViewLayer.ContagionList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gescov.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContagionListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContagionListFragment extends Fragment {
    private ListView contagionList;
    private ContagionController controller;
    private View thisView;
    private List<String> names;
    private List<String> contagionDate;
    private  ContagionListAdapter adapter;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ContagionListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BlankFragment2.
     */
    // TODO: Rename and change types and number of parameters
    public static ContagionListFragment newInstance(String param1, String param2) {
        ContagionListFragment fragment = new ContagionListFragment();
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
        controller = new ContagionController(this);
        thisView = inflater.inflate(R.layout.contagionlist, container, false);
        names = new ArrayList<>();
        contagionDate = new ArrayList<>();
        contagionList = (ListView) thisView.findViewById(R.id.contagion_list);
        adapter = new ContagionListAdapter(contagionList.getContext(), names, contagionDate);

        contagionList.setAdapter(adapter);
        try {
            refresh();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return thisView;
    }

    private void refresh() throws JSONException {
        controller.refresh();
    }
    public void updateData(String info) throws JSONException {
        JSONArray response = new JSONArray(info);

        for (int i = 0; i < response.length(); ++i) {
            JSONObject aux = response.getJSONObject(i);
            System.out.println(aux.toString()+"el objeto enetro");
            String nameaux = aux.getString("infected");

            JSONObject student = new JSONObject(nameaux);
            System.out.println(student.getString("name"));
            String date = aux.getString("startContagion");
            String filter = date.substring(0,10);
            names.add(student.getString("name"));

            String school = student.getString("schools");
            System.out.println(school);
            JSONArray userSchools = new JSONArray(school);
            JSONObject school1 = userSchools.getJSONObject(0);

            contagionDate.add(school1.getString("name")+"-Positiu des de " + filter);
        }
        adapter.notifyDataSetChanged();
    }
}