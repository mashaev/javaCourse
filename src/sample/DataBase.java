package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class DataBase {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private ListView<String> listView;

    @FXML
    void initialize() {
        ObservableList<String> list = FXCollections.observableArrayList("Студенты", "Преподаватели","Курсы","Пользователи");
        listView.setItems(list);
        listView.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                showForm(listView.getSelectionModel().getSelectedIndex());
                listView.getScene().getWindow().hide();
            }
        });

    }
    private void showForm(int index) {

        String adress = new String();
     switch (index){
         case 0:
             adress = "/sample/addStudent.fxml";
             break;
         case 1:
             adress = "/sample/addTeacher.fxml";
             break;
             case 2:
             adress = "/sample/addCourse.fxml";
             break;
             case 3:
             adress = "/sample/SignUp.fxml";
             break;
     }

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getResource(adress));
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
