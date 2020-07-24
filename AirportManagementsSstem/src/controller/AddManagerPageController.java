package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Database;
import model.Manager;

import java.io.IOException;

public class AddManagerPageController {
    @FXML TextField firstNameTXF;
    @FXML TextField lastNameTXF;
    @FXML TextField phoneNumberTXF;
    @FXML TextField addressTXF;
    @FXML TextField usernameTXF;
    @FXML TextField emailTXF;
    @FXML TextField salaryTXF;
    @FXML PasswordField passwordPSF;
    @FXML PasswordField confirmationPSF;
    @FXML Button addBTN;
    @FXML Button exitBTN;
    @FXML Label invalidPhoneNumberLBL;
    @FXML Label invalidEmailLBL;
    @FXML Label invalidUsername;
    @FXML Label invalidSalaryLBL;


    //set action for minimize button
    public void setOnActionMinimizeBTN(ActionEvent event){
        ((Stage)((Button)event.getSource()).getScene().getWindow()).setIconified(true);
    }


    //set on action for exit button
    public void setOnActionExitBTN(ActionEvent event){
        //get current stage
        Stage stage = (Stage)exitBTN.getScene().getWindow();

        stage.close();
    }


    //set on action for add button
    public void setOnActionAddBTN(ActionEvent event)  {
        //get current stage
        Stage stage = (Stage)addBTN.getScene().getWindow();

        if(firstNameTXF.getText().equals("") || lastNameTXF.getText().equals("") || phoneNumberTXF.getText().equals("") || addressTXF.getText().equals("") || usernameTXF.getText().equals("") || emailTXF.getText().equals("") || salaryTXF.getText().equals("") || passwordPSF.getText().equals("") || confirmationPSF.getText().equals("")){
           //show error if one of the fields be empty
            ErrorPageController.text = "Please fill all parts of the fields";

            AnchorPane root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
            } catch (IOException e) {
                System.out.println("we cannot load error page " + " " + e.getMessage());
                e.printStackTrace();
            }

            stage = new Stage(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }else if(!(passwordPSF.getText().equals(confirmationPSF.getText()))){
            ErrorPageController.text = "Please enter correct confirmation";

            AnchorPane root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
            } catch (IOException e) {
                System.out.println("we cannot load error page " + " " + e.getMessage());
                e.printStackTrace();
            }

            stage = new Stage(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }else{
            //to check that some input value is correct or not
            boolean isPhoneNumber = false;
            boolean isUsername = false;
            boolean isEmail = false;
            boolean isSalary = false;

            Manager manager = new Manager();

            //check input salary is valid or not
            isSalary = Manager.checkSalary(salaryTXF.getText());
            if(isSalary){
                makeInvalidSalaryLBLInvisible();
                manager.setSalary(salaryTXF.getText());
            }else {
                //visible error label
                makeInvalidSalaryLBLVisible();
            }

            //check input email is valid or not
            isEmail = Manager.checkEmail(emailTXF.getText());
            if(isEmail){
                makeInvalidEmailLBLInvisible();
                manager.setEmail(emailTXF.getText());
            }else {
                //visible error label
                makeInvalidEmailLBLVisible();
            }

            //check input username is valid or not
            isUsername = Manager.checkUsername(usernameTXF.getText());
            if(isUsername){
                makeInvalidUsernameLBLInvisible ();
                manager.setUserName(usernameTXF.getText());
            }else{
                //visible error label
                makeInvalidUsernameLBLVisible();
            }


            //check input phone number is valid or not
            isPhoneNumber = Manager.checkPhoneNumber(phoneNumberTXF.getText());
            if(isPhoneNumber) {
                makeInvalidPhoneNumberLBLInvisible ();
                manager.setPhoneNumber(phoneNumberTXF.getText());
            }else{
                //visible error label
                makeInvalidPhoneNumberLBLVisible();
            }


            if(isEmail && isPhoneNumber && isUsername && isSalary) {
                makeInvalidPhoneNumberLBLInvisible();
                makeInvalidUsernameLBLInvisible ();
                makeInvalidEmailLBLInvisible ();

                manager.setName(firstNameTXF.getText());
                manager.setLastName(lastNameTXF.getText());
                manager.setPassword(passwordPSF.getText());
                manager.setAddress(addressTXF.getText());
                manager.setSalary(salaryTXF.getText());

                //save information on database
                Database database = new Database(manager);
                database.setInformation();
                Manager.managers.add(manager);
                Database.getInformation();
                ManagerInformationPageController m = new ManagerInformationPageController();
                m.addInformationTableView(Manager.managers.get(Manager.managers.size() - 1).getID(),firstNameTXF.getText(),lastNameTXF.getText(),usernameTXF.getText(),passwordPSF.getText(),phoneNumberTXF.getText(),addressTXF.getText(),emailTXF.getText(),salaryTXF.getText());
                stage.close();
            }
        }
    }



    //this method make invalid PhoneNumber label visible
    private void makeInvalidPhoneNumberLBLVisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.75),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                invalidPhoneNumberLBL.setVisible(true);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }


    //this method make invalid PhoneNumber label invisible
    private void makeInvalidPhoneNumberLBLInvisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.01),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it invisible after a few second
                                invalidPhoneNumberLBL.setVisible(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }


    //this method make invalid username label visible
    private void makeInvalidUsernameLBLVisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.75),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                invalidUsername.setVisible(true);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    //this method make invalid username label invisible
    private void makeInvalidUsernameLBLInvisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.01),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it invisible after a few second
                                invalidUsername.setVisible(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }


    //this method make invalid email label visible
    private void makeInvalidEmailLBLVisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.75),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                invalidEmailLBL.setVisible(true);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }


    //this method make invalid email label invisible
    private void makeInvalidEmailLBLInvisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.01),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it invisible after a few second
                                invalidEmailLBL.setVisible(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    //this method make invalid email label invisible
    private void makeInvalidSalaryLBLInvisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.01),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it invisible after a few second
                                invalidSalaryLBL.setVisible(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    //this method make invalid email label visible
    private void makeInvalidSalaryLBLVisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.75),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it invisible after a few second
                                invalidSalaryLBL.setVisible(true);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }



}
