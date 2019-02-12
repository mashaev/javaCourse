package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class AddTeacher {

private int teacherId = 0;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txrPhone;

    @FXML
    private Button btnOk;

    @FXML
    private Button btnCancel;

    @FXML
    private Button btnBack;

    @FXML
    private TextField txtEMail;

    @FXML
    private CheckBox checkIsActive;

    @FXML
    private TableView<Teachers> tbTeachers;

    @FXML
    private TableColumn<Teachers, Integer>clmId;

    @FXML
    private TableColumn<Teachers, String>clmName;

    @FXML
    private TableColumn<Teachers, String>clmPhone;

    @FXML
    private TableColumn<Teachers, String> clmEMail;

    @FXML
    private TableColumn<Teachers, Integer> clmIsActive;

    @FXML
    private MenuItem barEdit;

    @FXML
    void cliknOnEdit(ActionEvent event) {
        Teachers teachers = tbTeachers.getSelectionModel().getSelectedItem();
        teacherId = teachers.getTeacherid();
        txtName.setText(teachers.getName());
        txrPhone.setText(teachers.getPhone());
        txtEMail.setText(teachers.geteMail());
        if (teachers.getIsActive() == 1){
            checkIsActive.setSelected(true);
        }else {
            checkIsActive.setSelected(false);
        }

    }

    @FXML
    void initialize() {
        DbHandler dbHandler = new DbHandler();
        clmId.setCellValueFactory(new PropertyValueFactory<>("teacherid"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        clmEMail.setCellValueFactory(new PropertyValueFactory<>("eMail"));
        clmIsActive.setCellValueFactory(new PropertyValueFactory<>("isActive"));
        ObservableList<Teachers> teachers = FXCollections.observableArrayList(dbHandler.getTeacher());
        tbTeachers.setItems(teachers);
        btnOk.setOnAction(event -> {
         creatTeacher();
         refresh();});
        btnBack.setOnAction(event -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("dataBaseView.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                btnOk.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void creatTeacher(){
        String name = txtName.getText().trim();
        String phone = txrPhone.getText().trim();
        String eMail = txtEMail.getText().trim();

        int isActive = 0;
        if (checkIsActive.isSelected()){
            isActive = 1;
        }
        DbHandler dbHandler = new DbHandler();
        dbHandler.addTeacher(new Teachers(teacherId,name,phone,eMail,isActive));
        teacherId = 0;
        txtName.clear();
        txrPhone.clear();
        txtEMail.clear();
        checkIsActive.setSelected(true);
    }
    @FXML
    void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2 ){
        Teachers teachers = tbTeachers.getSelectionModel().getSelectedItem();
        teacherId = teachers.getTeacherid();
        txtName.setText(teachers.getName());
        txrPhone.setText(teachers.getPhone());
        txtEMail.setText(teachers.geteMail());
        if (teachers.getIsActive() == 1){
            checkIsActive.setSelected(true);
        }else {
            checkIsActive.setSelected(false);
        }
        refresh();
    }
}
    public void refresh(){
        DbHandler dbHandler = new DbHandler();
        ObservableList<Teachers>list = FXCollections.observableArrayList(dbHandler.getTeacher());
        tbTeachers.setItems(list);


    }

}
