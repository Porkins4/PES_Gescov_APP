package com.example.gescov.ViewLayer;

import com.example.gescov.ViewLayer.SchoolsAdministration.SchoolsCrontroller;

public class PresentationControlFactory {
    private static SchoolsCrontroller schoolsCrontroller;

    public static SchoolsCrontroller getSchoolsCrontroller() {
        if (schoolsCrontroller != null)
            return schoolsCrontroller;
        schoolsCrontroller = new SchoolsCrontroller();
        return schoolsCrontroller;
    }
}
