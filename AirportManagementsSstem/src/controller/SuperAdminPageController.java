package controller;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SuperAdminPageController implements Initializable {
    @FXML Button personalBTN;
    @FXML Button changeBTN;
    @FXML Button changeBTN1;
    @FXML Button informationBTN;
    @FXML Button airportManagerBTN;
    @FXML Button exitBTN;
    @FXML Button minimizeBTN;
//    @FXML Button userInformationBTN;
    @FXML PasswordField passwordPSF;
    @FXML PasswordField confirmationPSF;
    @FXML Label successfulLBL;
//    @FXML AnchorPane anchor;

    //this variables help that make window movable
    private double xOffset = 0;
    private double yOffset = 0;

    //this boolean is for personal profile management button
    private boolean checkPersonalBTN = false;

    //this boolean is for change password button
    private boolean checkChangeBTN = false;

    //this boolean is for airport manager management button
    private boolean checkAirportManagerBTN = false;

    //this boolean is for anchor of user information
    private boolean checkAnchor = false;


    //set on action for user information button
//    public  void setOnActionUserInformationBTN(ActionEvent event){
//        if(!checkAnchor){
//            makeAnchorVisible();
//            checkAnchor = true;
//        }else if (checkAnchor){
//            makeAnchorInvisible();
//            checkAnchor = false;
//        }
//    }

    //set on action for minimize button
    public void setOnActionMinimizeBTN(ActionEvent event){

        ((Stage)((Button)event.getSource()).getScene().getWindow()).setIconified(true);

    }

    //set on action for airport manager management button
    public void setOnActionAirportManagerBTN(ActionEvent event){
        if(!checkAirportManagerBTN){
            //for first click
            makeInformationBTNVisible();
            checkAirportManagerBTN = true;
        }else if(checkAirportManagerBTN){
            //for second click
            makeInformationBTNInvisible();
            checkAirportManagerBTN = false;
        }
    }

    //set on action for see manager information
    public void setOnActionInformationBTN(ActionEvent event) throws IOException {
        //get current stage
        Stage stage = (Stage)informationBTN.getScene().getWindow();

        //load manage information page
        BorderPane root = FXMLLoader.load(getClass().getResource("/view/ManagerInformationPage.fxml"));

        //get position of mouse
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });
        //set position of mouse
        Stage finalPrimaryStage = stage;
        root.setOnMouseDragged(e -> {
            finalPrimaryStage.setX(e.getScreenX() - xOffset);
            finalPrimaryStage.setY(e.getScreenY() - yOffset);
        });


        stage.setScene(new Scene(root));

        stage.show();
    }

    //set on action for personal profile management button
    public void setOnActionPersonalBTN(ActionEvent event){
        if(!checkPersonalBTN) {
            //for first click
            makeChangeBTNVisible();
            checkPersonalBTN = true;
        }else if (checkPersonalBTN){
            //for second click
            makeSuccessfulLBLInvisible();
            makeChangeBTNInvisible();
            makeChangeBTN1FInvisible();
            makeConfirmationPSFInvisible();
            makePasswordPSFInvisible();
            checkPersonalBTN = false;
        }

    }

    //set on action for change password button
    public void setOnActionChangeBTN(ActionEvent event) {
        if (!checkChangeBTN) {
            //for first click
            makePasswordPSFVisible();
            makeConfirmationPSFVisible();
            makeChangeBTN1FVisible();
            checkChangeBTN = true;
        }else if(checkChangeBTN){
            //for second click
            makeSuccessfulLBLInvisible();
            makeChangeBTN1FInvisible();
            makeConfirmationPSFInvisible();
            makePasswordPSFInvisible();
            checkChangeBTN = false;
        }
    }



    //set on action for change button
    public void setOnActionChangeBTN1(ActionEvent event) {

//        makeSuccessfulLBLInvisible();

        //get current window
        Stage stage1 = (Stage) changeBTN1.getScene().getWindow();

        //show appropriate error
        if (passwordPSF.getText().equals("") || confirmationPSF.getText().equals("")) {
            //if at least one of field is empty

            //set message of error
            ErrorPageController.text = "Please fill all of the fields";

            AnchorPane root1 = null;

            //load error page
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
        } else if (!(passwordPSF.getText().equals(confirmationPSF.getText()))) {
            //if new password is not equal to its confirmation

            //set message of error
            ErrorPageController.text = "Please enter correct confirmation";

            AnchorPane root1 = null;

            //load error page
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

            //change password and write it in file
            LoginPageController.passWord = passwordPSF.getText();
            try {
                LoginPageController.write();
            } catch (IOException e) {
                e.printStackTrace();
            }


            makeSuccessfulLBLVisible();

        }
    }

    //set on action for exit button
    public void setOnActionExitBTN(ActionEvent event){
        //get current stage
        Stage stage = (Stage)exitBTN.getScene().getWindow();

        stage.close();
    }





    //this method make change button visible
    private void makeChangeBTNVisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.75),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                changeBTN.setVisible(true);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    //this method make change button invisible
    private void makeChangeBTNInvisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(1.40),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                changeBTN.setVisible(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }


    //this method make password, password field visible
    private void makePasswordPSFVisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.75),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                passwordPSF.setVisible(true);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    //this method make password, password field invisible
    private void makePasswordPSFInvisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(1.25),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                passwordPSF.setVisible(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    //this method make subset change button visible
    private void makeChangeBTN1FVisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(1.25),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                changeBTN1.setVisible(true);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    //this method make subset change button invisible
    private void makeChangeBTN1FInvisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.75),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                changeBTN1.setVisible(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    //this method make confirmation, password field visible
    private void makeConfirmationPSFVisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.95),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                confirmationPSF.setVisible(true);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    //this method make confirmation password field invisible
    private void makeConfirmationPSFInvisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.95),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                confirmationPSF.setVisible(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }

    //this method make successful label visible
    private void makeSuccessfulLBLVisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.1),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                successfulLBL.setVisible(true);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }


    //this method make successful label invisible
    private void makeSuccessfulLBLInvisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.50),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                successfulLBL.setVisible(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }


    //this method make See Manger Information button invisible
    private void makeInformationBTNInvisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.55),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                informationBTN.setVisible(false);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }


    //this method make See Manger Information button visible
    private void makeInformationBTNVisible () {

        final Timeline animation = new Timeline(
                new KeyFrame(Duration.seconds(0.55),
                        new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent actionEvent) {
                                //make it visible after a few second
                                informationBTN.setVisible(true);
                            }
                        }));
        animation.setCycleCount(1);
        animation.play();
    }



    //this method make anchor of user information button visible
//    private void makeAnchorVisible () {
//
//        final Timeline animation = new Timeline(
//                new KeyFrame(Duration.seconds(0.001),
//                        new EventHandler<ActionEvent>() {
//                            @Override
//                            public void handle(ActionEvent actionEvent) {
//                                //make it visible after a few second
//                                anchor.setVisible(true);
//                            }
//                        }));
//        animation.setCycleCount(1);
//        animation.play();
//    }


    //this method make anchor of user information button invisible
//    private void makeAnchorInvisible () {
//
//        final Timeline animation = new Timeline(
//                new KeyFrame(Duration.seconds(0.001),
//                        new EventHandler<ActionEvent>() {
//                            @Override
//                            public void handle(ActionEvent actionEvent) {
//                                //make it visible after a few second
//                                anchor.setVisible(false);
//                            }
//                        }));
//        animation.setCycleCount(1);
//        animation.play();
//    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }
}
