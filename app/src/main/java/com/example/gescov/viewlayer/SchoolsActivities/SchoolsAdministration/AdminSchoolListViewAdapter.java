package com.example.gescov.viewlayer.SchoolsActivities.SchoolsAdministration;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;

import com.example.gescov.R;
import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.viewlayer.SchoolsActivities.studentschools.allSchools.SchoolListViewAdapter;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;

import java.util.ArrayList;
import java.util.List;

public class AdminSchoolListViewAdapter extends SchoolListViewAdapter {
    public AdminSchoolListViewAdapter(Context context, List<School> schoolsList) {
        super(context, schoolsList);
    }

    @Override
    public void setList(List<School> schoolList) {
        User loggedUser = PresentationControlFactory.getViewLayerController().getLoggedUserInfo();
        List<School> adminSchoolList = new ArrayList<>();
        for (School school : schoolList) {
            if (school.getAdministratorsList().contains(loggedUser.getId())) {
                adminSchoolList.add(school);
            }
        }
        super.setList(adminSchoolList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = super.getView(position, convertView, parent);
        School school = schoolList.get(position);
        if (isLoggedUserCreator(school)) {
            v.setOnLongClickListener(e -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(e.getContext());
                        builder.setTitle(e.getResources().getString(R.string.options))
                                .setItems(R.array.school_administration_menu_items, (dialog, which) -> {
                                    if (which == 0) confirmDeleteSchoolPrompt(v, school);
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        return false;
                    }
            );
        }

        return v;
    }

    private void confirmDeleteSchoolPrompt(View v, School school) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                PresentationControlFactory.getSchoolsCrontroller().deleteSchool(school);
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        builder.setMessage(v.getContext().getString(R.string.school_details_confirm_delete_message)).setPositiveButton(v.getContext().getString(R.string.delete), dialogClickListener)
                .setNegativeButton(v.getContext().getString(R.string.cancel), dialogClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private boolean isLoggedUserCreator(School school) {
        return school.getCreator().equals(PresentationControlFactory.getViewLayerController().getLoggedUserInfo().getId());
    }
}
