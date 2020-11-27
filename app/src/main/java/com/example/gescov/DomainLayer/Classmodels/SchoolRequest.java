package com.example.gescov.DomainLayer.Classmodels;


public class SchoolRequest {

    public enum SchoolRequestStatus {
        ACCEPTED ("accepted"),
        REJECTED ("rejected"),
        PENDING ("pending");

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

    public SchoolRequest(String id, String schoolId, String userId, String status, String requestDate) {
        this.id = id;
        this.schoolId = schoolId;
        this.userId = userId;
        this.status = SchoolRequestStatus.getStatusFromString(status);
        this.requestDate = requestDate;
    }

    public String getId() {
        return id;
    }

    public SchoolRequestStatus getStatus() {
        return status;
    }


}
