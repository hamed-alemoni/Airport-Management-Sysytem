package model;

import javafx.beans.property.SimpleStringProperty;

public class Person {
   private SimpleStringProperty ID;
   private SimpleStringProperty name;
   private SimpleStringProperty lastName;
   private SimpleStringProperty userName;
   private SimpleStringProperty Password;
   private SimpleStringProperty email;

    public Person() {

    }

    public Person(String ID, String name, String lastName, String userName, String password, String email) {
        this.ID = new SimpleStringProperty(ID);
        this.name = new SimpleStringProperty(name);
        this.lastName = new SimpleStringProperty(lastName);
        this.userName = new SimpleStringProperty(userName);
        Password = new SimpleStringProperty(password);
        this.email = new SimpleStringProperty(email);
    }

    public String getID() {
        return ID.get();
    }

    public void setID(String ID) {
        this.ID = new SimpleStringProperty(ID);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name = new SimpleStringProperty(name);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName = new SimpleStringProperty(lastName);
    }

    public String getUserName() {
        return userName.get();
    }

    public void setUserName(String userName) {
        this.userName =  new SimpleStringProperty(userName);
    }

    public String getPassword() {
        return Password.get();
    }

    public void setPassword(String password) {
        Password = new SimpleStringProperty(password);
    }

    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email = new SimpleStringProperty(email);
    }

    //this method check phone number is valid or not
    public static boolean checkPhoneNumber(String phoneNumber){

       return ((phoneNumber.length() == 11) && (phoneNumber.matches("[0-9]+")));
    }
    //this method check email is valid or not
    public static boolean checkEmail(String email){
        return email.matches("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
    }

}
