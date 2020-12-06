package com.example.gescov.viewlayer.SchoolsActivities.schooluserslist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.domainlayer.Classmodels.School;
import com.example.gescov.domainlayer.Classmodels.User;
import com.example.gescov.R;
import com.example.gescov.viewlayer.Singletons.PresentationControlFactory;
import com.example.gescov.viewlayer.Templates.ModelListViewAdapter;

import java.util.List;

public class SchoolUsersListViewAdapter extends ModelListViewAdapter {

    public SchoolUsersListViewAdapter(Context context, List list) {
        super(context, list);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = inflater.inflate(R.layout.user_list_item, null);
        TextView userName = v.findViewById(R.id.user_list_item_name);
        TextView userProfile = v.findViewById(R.id.user_list_item_profile);
        User user = (User) modelList.get(position);
        School currentSchool = PresentationControlFactory.getSchoolsCrontroller().getCurrentSchool();
        userName.setText(user.getName());
        userProfile.setText(user.getProfileType() != null ? user.getProfileType().getText() : "");

        if (isLoggedUserCreator(currentSchool) && user.getProfileType().equals(User.UserProfileType.TEACHER) && !user.getId().equals(currentSchool.getCreator())) {
            v.setOnLongClickListener(e -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(e.getContext());
                        builder.setTitle(e.getResources().getString(R.string.options))
                                .setItems(isUserAdmin(currentSchool, user) ? R.array.admin_option_menu_items : R.array.user_option_menu_items, (dialog, which) -> {
                                    if (which == 0) confirmSetAdministratorPrompt(e, user, isUserAdmin(currentSchool, user));
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        return false;
                    }
            );
        }
        return v;
    }

    private boolean isUserAdmin(School school, User user) {
        return school.getAdministratorsList().contains(user.getId());
    }

    private boolean isLoggedUserCreator(School school) {
        return school.getCreator().equals(PresentationControlFactory.getViewLayerController().getLoggedUserInfo().getId());
    }

    private void confirmSetAdministratorPrompt(View v, User user, boolean isUserAdmin) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                if (!isUserAdmin) {
                    PresentationControlFactory.getSchoolsCrontroller().addNewAdminToSchool(user.getId());
                } else {
                    PresentationControlFactory.getSchoolsCrontroller().deleteSchoolAdmin(user.getId());
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        String question;
        if (!isUserAdmin)
            question = v.getResources().getString(R.string.user_school_make_admin_message_1) +" " + user.getName() + " " +  v.getResources().getString(R.string.user_school_make_admin_message_2);
        else
            question = v.getResources().getString(R.string.user_school_unmake_admin_message_1) +" " + user.getName() + " " +  v.getResources().getString(R.string.user_school_unmake_admin_message_2);
        builder.setMessage(question)
                .setPositiveButton(v.getResources().getString(R.string.apply), dialogClickListener)
                .setNegativeButton(v.getResources().getString(R.string.cancel), dialogClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
