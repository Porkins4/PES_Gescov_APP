package com.example.gescov.viewlayer.tracingTestResult;

import android.content.Context;
import android.widget.ListAdapter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.domainlayer.Classmodels.TracingTest;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class TracingTestViewModel extends ViewModel {

    private MutableLiveData<Boolean> received;
    private List<TracingTest> answers;
    private TracingTestAdapter adapter;

    public  ListAdapter getAdapter(Context c) {
        adapter = new TracingTestAdapter(c,answers);
        return adapter;
    }

    public LiveData<Boolean> getResults(String userID)  {
        received = new MutableLiveData<>();
        PresentationControlFactory.getTracingTestController().setViewRankingModel(this);
        PresentationControlFactory.getTracingTestController().getResults(userID);
        return  received;
    }


    public void sendTestAnswers(List<TracingTest> results) {
        answers = results;
        received.setValue(true);
    }

    public TracingTest getIthElemnt(int position) {
         return (TracingTest) adapter.getItem(position);
    }

    public boolean empty() {
        return answers.isEmpty();
    }
}
