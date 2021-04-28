package com.project.wchallenge.models;

public enum AccessType {

    CREATE(1L), UPDATE(2L), DELETE(3L), FIND(4L), NO_ACCESS(0L);

    private Long idAccess;

    private AccessType(Long idAccess) {
        this.idAccess = idAccess;
    }

    public Long getIdAccess() {
        return idAccess;
    }

    public static AccessType getByIdAccess(Long idAccess) {
        for (AccessType accessType : values()) {
            Long valueIdAccess = accessType.idAccess;
            if (valueIdAccess.equals(idAccess)) {
                return accessType;
            }
        }
        return NO_ACCESS;
    }
}
