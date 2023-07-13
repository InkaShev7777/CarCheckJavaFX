package com.example.carcheck.Models;

public class User {
    private int Id;
    private String Login;
    private String Password;
    private int Id_Role;
    public User(int id,String login,String password,int id_role){
        this.Id = id;
        this.Login = login;
        this.Password = password;
        this.Id_Role = id_role;
    }

    public int getId() {
        return Id;
    }

    public int getId_Role() {
        return Id_Role;
    }

    public String getPassword() {
        return Password;
    }

    public String getLogin() {
        return Login;
    }
}
