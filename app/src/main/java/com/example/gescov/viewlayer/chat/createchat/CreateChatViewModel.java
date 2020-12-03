package com.example.gescov.viewlayer.chat.createchat;

import android.content.Context;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Classmodels.User;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class CreateChatViewModel extends ViewModel {
    private MutableLiveData<Boolean> getSchoolsRequest;
    private MutableLiveData<Boolean> getUserContactsRequest;
    private List<School> userschools;
    private List<User> contacts;


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

    public String getIthSchoolName(int position) {
        return userschools.get(position).getName();
    }

    public LiveData<Boolean> getContactsFromIthCenter(int position) {
        getUserContactsRequest = new MutableLiveData<>();
        PresentationControlFactory.getCreateChatController().getContactsFromCenter(userschools.get(position).getId());
        return getUserContactsRequest;
    }

    public CreateChatAdapter getContactsAdapter(Context c) {
        contacts = PresentationControlFactory.getCreateChatController().getContacts();
        for (User x: contacts) {
            x.print();
        }
        return new CreateChatAdapter(c,contacts);
    }

    public CreateChatAdapter getCleanAdapter(Context c) {
        return new CreateChatAdapter(c,new ArrayList<>());
    }

    public void notifyContactsReceived() {
        getUserContactsRequest.setValue(true);
    }

    public String getUserName(int position) {
        return contacts.get(position).getName();
    }
}
