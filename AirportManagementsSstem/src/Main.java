import controller.*;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Database;


public class Main extends Application {

    //this variables help that make window movable
    private double xOffset = 0;
    private double yOffset = 0;

    @Override
    public void start(Stage primaryStage) throws Exception{


        AnchorPane root = FXMLLoader.load(getClass().getResource("view/LoginPage.fxml"));
        primaryStage = new Stage(StageStyle.UNDECORATED);
        primaryStage.initStyle(StageStyle.TRANSPARENT);
        Scene scene = new Scene(root);
        scene.setFill(Color.TRANSPARENT);

        LoginPageController.read();

        //get position of mouse
        root.setOnMousePressed(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                xOffset = event.getSceneX();
                yOffset = event.getSceneY();
            }
        });

        Stage finalPrimaryStage = primaryStage;
        //set position of mouse
        root.setOnMouseDragged(event -> {
            finalPrimaryStage.setX(event.getScreenX() - xOffset);
            finalPrimaryStage.setY(event.getScreenY() - yOffset);
        });

        primaryStage.setScene(scene);
        primaryStage.show();


    }



    public static void main(String[] args) {

        Database.getInformation();


            launch(args);

    }


}
