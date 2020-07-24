package controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Database;
import model.Manager;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ManagerInformationPageController implements Initializable {
   private  @FXML Button addBTN;
   private  @FXML Button exitBTN;
   @FXML private TableView<Manager> managerTableView;
   private @FXML TableColumn<Manager,String> id;
   private  @FXML TableColumn<Manager,String> name;
   private @FXML TableColumn<Manager,String> lastName;
   private @FXML TableColumn<Manager,String> username;
   private @FXML TableColumn<Manager,String> password;
   private @FXML TableColumn<Manager,String> email;
   private @FXML TableColumn<Manager,String> address;
   private @FXML TableColumn<Manager,String> phoneNumber;
   private @FXML TableColumn<Manager,String> salary;
   private Manager manager = null;

    //this variables help that make window movable
    private double xOffset = 0;
    private double yOffset = 0;

    //add information of an object to table view
    public  void addInformationTableView(String ID, String name, String lastName, String userName, String password,  String phoneNumber, String address,String email, String salary){
        manager = new Manager(ID , name , lastName , userName , password,phoneNumber,address,email,salary);

        managerTableView.getItems().add(manager);

    }

    //set on action for remove button
    public void setOnActionRemoveBTN(ActionEvent event){
        ObservableList<Manager> allManagers , selectionManager;
        allManagers = managerTableView.getItems();
        selectionManager = managerTableView.getSelectionModel().getSelectedItems();
        for (int i = 0; i < selectionManager.size(); i++) {
            for (int j = 0; j < Manager.managers.size(); j++) {
                if(selectionManager.get(i).equals(Manager.managers.get(j))){
                    Database.removeInformation(Manager.managers.get(j).getUserName());
                    Manager.managers.remove(j);
                }
            }
        }
        selectionManager.forEach(allManagers::remove);
    }

    //set on action for exit button
    public void setOnActionExitBTN(ActionEvent event){
        //get current stage
        Stage stage = (Stage)exitBTN.getScene().getWindow();
        stage.close();
    }


    //set on action for add button
    public void setOnActionAddBTN(ActionEvent event) throws IOException {
        //get current stage
        Stage stage = (Stage)addBTN.getScene().getWindow();
        stage = new Stage(StageStyle.UNDECORATED);

        //load manage information page
        AnchorPane root = FXMLLoader.load(getClass().getResource("/view/AddManagerPage.fxml"));

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


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        id.setCellValueFactory(new PropertyValueFactory<>("iD"));
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        lastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
        username.setCellValueFactory(new PropertyValueFactory<>("userName"));
        password.setCellValueFactory(new PropertyValueFactory<>("password"));
        email.setCellValueFactory(new PropertyValueFactory<>("email"));
        address.setCellValueFactory(new PropertyValueFactory<>("address"));
        phoneNumber.setCellValueFactory(new PropertyValueFactory<>("phoneNumber"));
        salary.setCellValueFactory(new PropertyValueFactory<>("salary"));
        for(int i = 0 ; i < Manager.managers.size() ; i++) {
            managerTableView.getItems().add(Manager.managers.get(i));
        }



    }

}
//we have to save information into table view