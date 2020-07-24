package controller;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {

    public LoginPageController() {

    }
    //password of super admin
    public static String passWord = "123456";
    private double xOffset = 0;
    private double yOffset = 0;

    @FXML Button exitBTN;
    @FXML Button FPBTN;
    @FXML Button enterBTN;
    @FXML TextField userNameTXF;
    @FXML TextField passwordTXF;

    //this method is for write new password in file
    public static void write() throws IOException {
        FileWriter writer = new FileWriter("password.txt");
        BufferedWriter buffered = new BufferedWriter(writer);
        buffered.write(passWord);
        buffered.close();
    }
    //this method is for read the new password from file
    public static void read() throws IOException{
        FileReader reader = new FileReader("password.txt");
        BufferedReader buffered = new BufferedReader(reader);
        String temp = "";
        while ((temp = buffered.readLine()) != null){
            passWord = temp;
        }
        buffered.close();
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }


    //set on action for exit button
    public void setActionExitBTN(ActionEvent event) {
        //get current Stage
        Stage stage = (Stage) exitBTN.getScene().getWindow();
        //close the current window
        stage.close();
    }

    //set on action for forget password button
    public void setOnActionForgetPasswordBTN(ActionEvent event) throws IOException {
        //get current Stage
        Stage stage = (Stage) FPBTN.getScene().getWindow();
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/ForgetPassword.fxml"));
        stage = new Stage(StageStyle.UNDECORATED);
        stage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();
    }

    //this method is for set on action for enter button
   public void setOnActionEnterBTN(ActionEvent event) throws IOException {
        //get current stage
        Stage stage = (Stage) enterBTN.getScene().getWindow();

        
        //these statements is for check password and username of admin and show appropriate message if one of them is wrong
        if(userNameTXF.getText().equals("admin")){

            if(passwordTXF.getText().equals(passWord)){
                stage.close();
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/SuperAdminPage.fxml"));
                stage = new Stage(StageStyle.UNDECORATED);

                stage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);

                root.setOnMousePressed(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        xOffset = event.getSceneX();
                        yOffset = event.getSceneY();
                    }
                });

                Stage finalStage = stage;
                root.setOnMouseDragged(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                      finalStage.setX(event.getScreenX() - xOffset);
                      finalStage.setY(event.getScreenY() - yOffset);
                    }
                });

                stage.setScene(scene);
                stage.show();
            }else{
                ErrorPageController.text = "Please enter correct password";
                AnchorPane root = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
                stage = new Stage(StageStyle.UNDECORATED);
                stage.initStyle(StageStyle.TRANSPARENT);
                Scene scene = new Scene(root);
                scene.setFill(Color.TRANSPARENT);
                stage.setScene(scene);
                stage.show();
            }

        }else{
            ErrorPageController.text = "Please enter correct username";
            AnchorPane root = FXMLLoader.load(getClass().getResource("/view/ErrorPage.fxml"));
            stage = new Stage(StageStyle.UNDECORATED);
            stage.initStyle(StageStyle.TRANSPARENT);
            Scene scene = new Scene(root);
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            stage.show();
        }
   }
}
