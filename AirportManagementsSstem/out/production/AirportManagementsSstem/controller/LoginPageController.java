package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginPageController implements Initializable {
    private Stage primaryStage;

    public LoginPageController(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    @FXML Button exitBTN;


    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setActionExitBTN(ActionEvent event) {
        primaryStage.close();
    }
}
