package com.example.gescov.PresentationLayer.ContagionList;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.gescov.PresentationLayer.ContagionList.Controllers.ContagionController;
import com.example.gescov.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ContagionListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ContagionListFragment extends Fragment {
    private ListView ListaCont;
    private ContagionController controller;
    private View thisview;
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
        thisview = inflater.inflate(R.layout.contagionlist, container, false);
         names = new ArrayList<>();
         contagionDate = new ArrayList<>();
         ListaCont = (ListView) thisview.findViewById(R.id.ListaCont);
         adapter = new ContagionListAdapter(ListaCont.getContext(), names, contagionDate);

        ListaCont.setAdapter(adapter);
        try {
            System.out.println("otra veh");
            refresh();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return thisview;
    }

    private void refresh() throws JSONException {
        controller.refresh();
    }
    public void updateData(String info) throws JSONException {
        JSONArray response = new JSONArray(info);

        for (int i = 0; i < response.length(); ++i) {
            JSONObject aux = response.getJSONObject(i);
            String nameaux = aux.getString("nameInfected");
            JSONObject student = new JSONObject(nameaux);
            String date = aux.getString("startContagion");
            String filter = date.substring(0,10);
            names.add(student.getString("name"));
            String school = student.getString("school");
            JSONObject studentSchool = new JSONObject(school);
            contagionDate.add(studentSchool.getString("name")+"-Positiu des de " + filter);
        }
        adapter.notifyDataSetChanged();
    }
}