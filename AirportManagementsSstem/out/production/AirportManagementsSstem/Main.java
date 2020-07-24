import controller.LoginPageController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        AnchorPane root = FXMLLoader.load(Main.class.getResource("view/LoginPage.fxml"));
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        LoginPageController login = new LoginPageController(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
