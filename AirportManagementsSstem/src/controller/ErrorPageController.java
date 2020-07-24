package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ErrorPageController implements Initializable {
    @FXML Button okBTN;
    @FXML Label errorLBL;

    public static String text = "";



    private void setErrorLBL(){
        errorLBL.setText(this.text);
    }

    public void setOkBTN(ActionEvent event) {
        Stage stage = (Stage)okBTN.getScene().getWindow();
        stage.close();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setErrorLBL();
    }
}
