package com.example.gescov.DomainLayer.Classmodels;


public class SchoolRequest {


    public enum SchoolRequestStatus {
        ACCEPTED ("ACCEPTED"),
        REJECTED ("REJECTED"),
        PENDING ("PENDING");

        private final String value;
        private SchoolRequestStatus(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }

        public static SchoolRequestStatus getStatusFromString(String value) {
            for (SchoolRequestStatus status : values()) {
                if (status.value.equals(value)) return status;
            }
            return null;
        }
    }


    private String schoolId;
    private String userId;
    private String id;
    private String requestDate;
    private SchoolRequestStatus status;
    private String userName;

    public SchoolRequest(String id, String schoolId, String userId, String userName, String status, String requestDate) {
        this.id = id;
        this.schoolId = schoolId;
        this.userId = userId;
        this.userName = userName;
        this.status = SchoolRequestStatus.getStatusFromString(status);
        this.requestDate = requestDate;
    }

    public String getId() {
        return id;
    }
    public String getUserName() {
        return userName;
    }

    public SchoolRequestStatus getStatus() {
        return status;
    }
    public String getSchoolId() {
        return schoolId;
    }


}
