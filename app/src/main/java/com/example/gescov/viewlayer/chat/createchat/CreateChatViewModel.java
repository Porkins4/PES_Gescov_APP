package com.example.gescov.viewlayer.chat.createchat;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.List;

public class CreateChatViewModel extends ViewModel {
    private MutableLiveData<Boolean> getSchoolsRequest;
    private List<School> userschools;

    public LiveData<Boolean> getSchoolsRequest() {
        if (getSchoolsRequest == null) {
            getSchoolsRequest = new MutableLiveData<>();
            PresentationControlFactory.getCreateChatController().setCreateChatViewModel(this);
            PresentationControlFactory.getCreateChatController().updateSchools();
        }
        return getSchoolsRequest;
    }

    public void notifySchoolsReceived() {
        userschools = PresentationControlFactory.getCreateChatController().getUserSchools();
        getSchoolsRequest.setValue(true);
    }

    public void test() {
        for (School s: userschools) s.print();
    }

    public String[] getSchoolNames() {
        String[] res = new String[userschools.size()];
        for (int i = 0; i < res.length; ++i) {
            res[i] = userschools.get(i).getName();
        }
        return res;
    }
}
