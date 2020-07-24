package model;

import javafx.beans.property.SimpleStringProperty;

import java.util.ArrayList;

public class Manager extends Person{
    public static ArrayList<Manager> managers = new ArrayList<>();
    private SimpleStringProperty phoneNumber;
    private SimpleStringProperty address;
    private SimpleStringProperty salary;

    public Manager() {
    }

    public Manager(String ID, String name, String lastName, String userName, String password,  String phoneNumber, String address,String email, String salary) {
        super(ID, name, lastName, userName, password, email);
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
        this.address = new SimpleStringProperty(address);
        this.salary = new SimpleStringProperty(salary);
    }

    public String getPhoneNumber() {
        return phoneNumber.get();
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = new SimpleStringProperty(phoneNumber);
    }

    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address = new SimpleStringProperty(address);
    }

    public String getSalary() {
        return salary.get();
    }

    public void setSalary(String salary) {
        this.salary = new SimpleStringProperty(salary);
    }

    //this method check that username is valid or not
    public static boolean checkUsername(String username) {
        for(int i = 0 ; i < managers.size() ; i++){
            if(managers.get(i).getUserName().equals(username))
                return false;
        }
        return true;
    }
    //this method check that salary is valid or not
    public static boolean checkSalary(String salary){
        return salary.matches("[0-9]+");
    }
}
