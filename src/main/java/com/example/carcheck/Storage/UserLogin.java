package com.example.carcheck.Storage;

public class UserLogin {
    private static int idUser = 0;
    private static String Login;

    public static String getLogin() {
        return Login;
    }

    public static int getIdUser() {
        return idUser;
    }

    public static void setIdUser(int idUser) {
        UserLogin.idUser = idUser;
    }

    public static void setLogin(String login) {
        Login = login;
    }
}
