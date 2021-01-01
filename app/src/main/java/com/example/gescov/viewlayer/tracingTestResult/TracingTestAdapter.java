package com.example.gescov.viewlayer.tracingTestResult;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.TracingTest;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class TracingTestAdapter extends ModelListViewAdapter {
    public TracingTestAdapter(Context c, List list) {
        super(c, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.tracing_test_list_item,null);
        TextView testDate = (TextView) v.findViewById(R.id.date_of_test);
        TracingTest tracingTest = (TracingTest) modelList.get(position);
        testDate.setText(context.getString(R.string.test_done_in) + " "+ tracingTest.getDate());
        return v;
    }
}
