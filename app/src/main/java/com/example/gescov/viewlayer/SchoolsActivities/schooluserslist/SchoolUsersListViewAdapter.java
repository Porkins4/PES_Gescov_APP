package com.example.gescov.viewlayer.SchoolsActivities.schooluserslist;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.gescov.DomainLayer.Classmodels.School;
import com.example.gescov.DomainLayer.Classmodels.User;
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
        boolean isUserAdmin = currentSchool.getAdministratorsList().contains(user.getId());
        boolean isLoggedUserCreator = currentSchool.getCreator().equals(PresentationControlFactory.getViewLayerController().getLoggedUserInfo().getId());
        userName.setText(user.getName());
        userProfile.setText(user.getProfileType() != null ? user.getProfileType().getText() : "");

        if (isLoggedUserCreator && user.getProfileType().equals(User.UserProfileType.TEACHER)) {
            v.setOnLongClickListener(e -> {
                        AlertDialog.Builder builder = new AlertDialog.Builder(e.getContext());
                        builder.setTitle(e.getResources().getString(R.string.options))
                                .setItems(isUserAdmin ? R.array.admin_option_menu_items : R.array.user_option_menu_items, (dialog, which) -> {
                                    if (which == 0) confirmSetAdministratorPrompt(e, user, isUserAdmin);
                                });
                        AlertDialog dialog = builder.create();
                        dialog.show();
                        return false;
                    }
            );
        }
        return v;
    }

    private void confirmSetAdministratorPrompt(View v, User user, boolean isUserAdmin) {
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            if (which == DialogInterface.BUTTON_POSITIVE) {
                if (!isUserAdmin) {
                    PresentationControlFactory.getSchoolsCrontroller().addNewAdminToSchool(user.getId());
                }
            }
        };

        AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
        String question = v.getResources().getString(R.string.user_school_make_admin_message_1) + user.getName() +  v.getResources().getString(R.string.user_school_make_admin_message_2);
        builder.setMessage(question)
                .setPositiveButton(v.getResources().getString(R.string.apply), dialogClickListener)
                .setNegativeButton(v.getResources().getString(R.string.cancel), dialogClickListener);
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
