package com.escom.Creadordecasos.Util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum Role {
    STUDENT( Arrays.asList(Permission.CASE_ACCESS)),
    TEACHER(Arrays.asList( Permission.CASE_MANAGEMENT, Permission.CASE_MANAGEMENT )),
    ADMINISTRATOR(Arrays.asList( Permission.USER_MANAGEMENT ));

    private List<Permission> permissions;

    public List<Permission> getPermissions() {
        return permissions;
    }


}
