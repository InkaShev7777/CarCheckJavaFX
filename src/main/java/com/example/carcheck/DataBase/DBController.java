package com.example.carcheck.DataBase;

import com.example.carcheck.Models.Car;
import com.example.carcheck.Models.User;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DBController {
   private String dbUrl = "jdbc:mysql://localhost:3306/db?useUnicode=true&serverTimezone=UTC&useSSL=false";
    private String user = "root";
    private String password = "Ilya2905#";
    private Connection c;
    private List<User> users;
    public DBController() throws ClassNotFoundException, SQLException {
        this.users = new ArrayList<User>();
        Class.forName("com.mysql.cj.jdbc.Driver");
        this.c = DriverManager.getConnection(dbUrl, user, password);
        getAllUser();
    }
    private void getAllUser() throws SQLException {
        Statement s = c.createStatement();
        ResultSet r = s.executeQuery("SELECT * FROM db.User");
        while (r.next()){
            this.users.add(new User(
                    Integer.parseInt(r.getString("id")) ,
                    r.getString("login"),
                    r.getString("password"),
                    Integer.parseInt(r.getString("id_role"))));
        }
        s.close();
    }
    public int Authorize(String login,String password) throws SQLException {
        getAllUser();
        int validIdUser = -1;
        for(User u : this.users){
            if(login.equals(u.getLogin()) && password.equals(u.getPassword())){
                validIdUser = u.getId();
            }
        }
        return validIdUser;
    }
    public int SaveSearchInDB(int idUser,String mark,String model, String year, String vin, String lastDate, String url) throws SQLException {
        Statement s  = c.createStatement();
        int r = s.executeUpdate("insert into db.SaveCars(id_user, mark, model, year, last_registration, vin, url_photo) values ("+idUser+",'"+ mark+"','"+model+"','"+year+"','"+lastDate+"','"+vin+"','"+url+"')");
        return r;
    }
    public boolean checkNewUser(String login){
        for(User u : this.users){
            if(u.getLogin().equals(login)){
                return false;
            }
        }
        return true;
    }
    public int RegistrateUser(String login, String password) throws SQLException {
        Statement s  = c.createStatement();
        int r = s.executeUpdate("insert into db.User(login, password, id_role) values('"+login+"','"+password+"',1)");
        return r;
    }
    public List<Car> getListCars(int id) throws SQLException {
        List<Car> carList = new ArrayList<Car>();
        Statement s = c.createStatement();
        ResultSet r = s.executeQuery("SELECT * FROM db.SaveCars");
        while (r.next()){
            int id_user = Integer.parseInt(r.getString("id_user"));
            int _id = Integer.parseInt(r.getString("id"));
            if(id_user == id){
                Car car = new Car(_id,id_user,r.getString("mark"),r.getString("model"),r.getString("year"),r.getString("last_registration"),r.getString("vin"),r.getString("url_photo"));
                carList.add(car);
            }
        }
        return carList;
    }
    public int DeleteCar(int id) throws SQLException {
        Statement s  = c.createStatement();
        int r = s.executeUpdate("DELETE FROM db.SaveCars WHERE id =" + id);
        return r;
    }
}
