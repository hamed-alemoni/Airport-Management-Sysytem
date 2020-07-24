package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


import java.io.IOException;
import java.net.URL;
import java.util.Random;
import java.util.ResourceBundle;

public class ForgetPasswordController implements Initializable {
    @FXML ChoiceBox<String> forgetPasswordBox;
    @FXML Button enterBTN;
    @FXML Button cancelBTN;
    @FXML TextField answerTXF;
    @FXML TextField emailTXF;
    @FXML TextField passwordPSF;
    @FXML TextField confirmationPSF;

    Random rand = new Random();
    int num = rand.nextInt(100);
    int num1 = rand.nextInt(100);

    //make security question
    private String questionOne = Integer.toString(num) + " + " + Integer.toString(num1) + " = ";
    private String questionTwo = "What year are we ?";
    private String questionThree = "What country do you live ?";
    private String firstValue = "Select...";
    //this method is for initialize choice box
    private void setForgetPasswordBox(){


        //add questions to choice box
        forgetPasswordBox.getItems().addAll(firstValue,questionOne,questionTwo,questionThree);
        //set first value to show in the choice box
        forgetPasswordBox.setValue(firstValue);

    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setForgetPasswordBox();
    }

   // set on action for enter button
    public void setEnterBTN(ActionEvent event) throws IOException {
        //get current Stage
        final Stage[] stage = {(Stage) enterBTN.getScene().getWindow()};

        if(emailTXF.getText().equals("hamed") ){
            //choice box and answer text field will be visible
            forgetPasswordBox.setDisable(false);
            answerTXF.setDisable(false);
            //make enter text field disable
            emailTXF.setDisable(true);

            //set on action for enter button after user answer security question
            enterBTN.setOnAction(event1 -> {

                //get choice value from choice box
                String value = forgetPasswordBox.getValue();

                //if...else statement for each security question

                if(value.equals(firstValue)){
                    ErrorPageController.text = "Please select a question from choice box";
                    AnchorPane root = null;
                    try {
                        root = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    stage[0] = new Stage(StageStyle.UNDECORATED);
                    stage[0].initStyle(StageStyle.TRANSPARENT);
                    Scene scene = new Scene(root);
                    scene.setFill(Color.TRANSPARENT);
                    stage[0].setScene(scene);
                    stage[0].setTitle("Error");
                    stage[0].show();


                }
                if(value.equals(questionOne)){
                    if(answerTXF.getText().equals(Integer.toString(num1+num))){
                        //choice box and answer text field will be disable
                        forgetPasswordBox.setDisable(true);
                        answerTXF.setDisable(true);

                        //make both password fields visible
                        confirmationPSF.setVisible(true);
                        passwordPSF.setVisible(true);


                        enterBTN.setOnAction(event2 -> {
                            //get current window
                            Stage stage1 = (Stage)enterBTN.getScene().getWindow();

                            //show appropriate error
                            if(passwordPSF.getText().equals("") || confirmationPSF.getText().equals("")){
                                //if at least one of field is empty
                                ErrorPageController.text = "Please fill all of the fields";
                                AnchorPane root1 = null;
                                try {
                                    root1 = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage1 = new Stage(StageStyle.UNDECORATED);
                                stage1.initStyle(StageStyle.TRANSPARENT);
                                Scene scene = new Scene(root1);
                                scene.setFill(Color.TRANSPARENT);
                                stage1.setScene(scene);
                                stage1.show();
                            }else if(!(passwordPSF.getText().equals(confirmationPSF.getText()))){
                                //if new password is not equal to its confirmation
                                ErrorPageController.text = "Please enter correct confirmation";
                                AnchorPane root1 = null;

                                try {
                                    root1 = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage1 = new Stage(StageStyle.UNDECORATED);
                                stage1.initStyle(StageStyle.TRANSPARENT);
                                Scene scene = new Scene(root1);
                                scene.setFill(Color.TRANSPARENT);
                                stage1.setScene(scene);
                                stage1.show();
                            }else{
                                LoginPageController.passWord = passwordPSF.getText();
                                try {
                                    LoginPageController.write();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage1.close();
                            }
                        });

                    }else{
                        //if user does not answer security question error window will open
                        ErrorPageController.text = "Please fill all of the field carefully";
                        AnchorPane root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage[0] = new Stage(StageStyle.UNDECORATED);
                        stage[0].initStyle(StageStyle.TRANSPARENT);
                        Scene scene = new Scene(root);
                        scene.setFill(Color.TRANSPARENT);
                        stage[0].setScene(scene);
                        stage[0].setTitle("Error");
                        stage[0].show();
                    }


                }
                if(value.equals(questionTwo)){
                    if(answerTXF.getText().equals("Iran")){
                        //choice box and answer text field will be disable
                        forgetPasswordBox.setDisable(true);
                        answerTXF.setDisable(true);

                        //make both password fields visible
                        confirmationPSF.setVisible(true);
                        passwordPSF.setVisible(true);


                        enterBTN.setOnAction(event2 -> {
                            //get current window
                            Stage stage1 = (Stage)enterBTN.getScene().getWindow();

                            //show appropriate error
                            if(passwordPSF.getText().equals("") || confirmationPSF.getText().equals("")){
                                //if at least one of field is empty
                                ErrorPageController.text = "Please fill all of the fields";
                                AnchorPane root1 = null;
                                try {
                                    root1 = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage1 = new Stage(StageStyle.UNDECORATED);
                                stage1.initStyle(StageStyle.TRANSPARENT);
                                Scene scene = new Scene(root1);
                                scene.setFill(Color.TRANSPARENT);
                                stage1.setScene(scene);
                                stage1.show();
                            }else if(!(passwordPSF.getText().equals(confirmationPSF.getText()))){
                                //if new password is not equal to its confirmation
                                ErrorPageController.text = "Please enter correct confirmation";
                                AnchorPane root1 = null;

                                try {
                                    root1 = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage1 = new Stage(StageStyle.UNDECORATED);
                                stage1.initStyle(StageStyle.TRANSPARENT);
                                Scene scene = new Scene(root1);
                                scene.setFill(Color.TRANSPARENT);
                                stage1.setScene(scene);
                                stage1.show();
                            }else{
                                LoginPageController.passWord = passwordPSF.getText();
                                try {
                                    LoginPageController.write();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage1.close();
                            }
                        });
                    }else{
                        ErrorPageController.text = "Please fill all of the field carefully";
                        //if user does not answer security question error window will open
                        AnchorPane root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage[0] = new Stage(StageStyle.UNDECORATED);
                        stage[0].initStyle(StageStyle.TRANSPARENT);
                        Scene scene = new Scene(root);
                        scene.setFill(Color.TRANSPARENT);
                        stage[0].setScene(scene);
                        stage[0].setTitle("Error");
                        stage[0].show();
                    }

                }
                if(value.equals(questionThree)){
                    if(answerTXF.getText().equals("2020")){
                        //choice box and answer text field will be disable
                        forgetPasswordBox.setDisable(true);
                        answerTXF.setDisable(true);

                        //make both password fields visible
                        confirmationPSF.setVisible(true);
                        passwordPSF.setVisible(true);


                        enterBTN.setOnAction(event2 -> {
                            //get current window
                            Stage stage1 = (Stage)enterBTN.getScene().getWindow();

                            //show appropriate error
                            if(passwordPSF.getText().equals("") || confirmationPSF.getText().equals("")){
                                //if at least one of field is empty
                                ErrorPageController.text = "Please fill all of the fields";
                                AnchorPane root1 = null;
                                try {
                                    root1 = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage1 = new Stage(StageStyle.UNDECORATED);
                                stage1.initStyle(StageStyle.TRANSPARENT);
                                Scene scene = new Scene(root1);
                                scene.setFill(Color.TRANSPARENT);
                                stage1.setScene(scene);
                                stage1.show();
                            }else if(!(passwordPSF.getText().equals(confirmationPSF.getText()))){
                                //if new password is not equal to its confirmation
                                ErrorPageController.text = "Please enter correct confirmation";
                                AnchorPane root1 = null;

                                try {
                                    root1 = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage1 = new Stage(StageStyle.UNDECORATED);
                                stage1.initStyle(StageStyle.TRANSPARENT);
                                Scene scene = new Scene(root1);
                                scene.setFill(Color.TRANSPARENT);
                                stage1.setScene(scene);
                                stage1.show();
                            }else{
                                LoginPageController.passWord = passwordPSF.getText();
                                try {
                                    LoginPageController.write();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                stage1.close();
                            }
                        });
                    }else{
                        ErrorPageController.text = "Please fill all of the field carefully";
                        //if user does not answer security question error window will open
                        AnchorPane root = null;
                        try {
                            root = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        stage[0] = new Stage(StageStyle.UNDECORATED);
                        stage[0].initStyle(StageStyle.TRANSPARENT);
                        Scene scene = new Scene(root);
                        scene.setFill(Color.TRANSPARENT);
                        stage[0].setScene(scene);
                        stage[0].setTitle("Error");
                        stage[0].show();
                    }
                }

            });

        }else{
            ErrorPageController.text = "Please enter correct email";
            //if user does not answer security question error window will open
            AnchorPane root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage[0] = new Stage(StageStyle.UNDECORATED);
            stage[0].initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage[0].setScene(scene);
            stage[0].setTitle("Error");
            stage[0].show();
        }
    }

    //set on action for cancel button
    public void setCancelBTN(ActionEvent event){
        Stage stage = (Stage) cancelBTN.getScene().getWindow();
        stage.close();
    }

}
