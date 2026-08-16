package com.rizgo.utils;

public class UserSession {

    private static String selectedRole = "";

    public static void setSelectedRole(String role) {
        selectedRole = role;
    }

    public static String getSelectedRole() {
        return selectedRole;
    }

    public static void clear() {
        selectedRole = "";
    }
}