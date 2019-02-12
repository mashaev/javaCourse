package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;


public class AddStudent {
    private int studentid = 0;
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtName;

    @FXML
    private TextField txtPhone;

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
    private TableView<Students> tbStudents;

    @FXML
    private TableColumn<Course, Integer> clmId;

    @FXML
    private TableColumn<Course, String>clmName;

    @FXML
    private TableColumn<Course, String>clmPhone;
    @FXML
    private MenuItem barCreate;

    @FXML
    private MenuItem barUpdate;
    private ActionEvent event;

    @FXML
    void onEditClicked(ActionEvent event) {
        Students students = tbStudents.getSelectionModel().getSelectedItem();
        studentid = students.getStudentid();
        txtName.setText(students.getName());
        txtPhone.setText(students.getPhone());
        txtEMail.setText(students.getE_mail());
        if (students.getIsActive() == 1){
            checkIsActive.setSelected(true);
        }else {
            checkIsActive.setSelected(false);
        }
    }

    @FXML
    void onNewClicked(ActionEvent event) {
studentid = 0;
    }

    @FXML
    void initialize() {
        DbHandler dbHandler = new DbHandler();
        clmId.setCellValueFactory(new PropertyValueFactory<>("studentid"));
        clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
        clmPhone.setCellValueFactory(new PropertyValueFactory<>("phone"));
        ObservableList<Students>students = FXCollections.observableArrayList(dbHandler.getStudent());
        tbStudents.setItems(students);


        btnOk.setOnAction(event ->{
            creatStudent();
            refresh();});
        btnBack.setOnAction(event1 -> {
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
    private void creatStudent(){
        String name = txtName.getText().trim();
        String phone = txtPhone.getText().trim();
        String eMail = txtEMail.getText().trim();

        int isActive = 0;
        if (checkIsActive.isSelected()){
            isActive = 1;
        }
        DbHandler dbHandler = new DbHandler();
        dbHandler.addStudent(new Students(studentid,name,phone,eMail,isActive));
        studentid = 0;
        txtName.clear();
        txtPhone.clear();
        txtEMail.clear();
        checkIsActive.setSelected(true);

    }
    @FXML
    void onMouseClicked(MouseEvent event) {

        if (event.getClickCount() == 2){
            Students students = tbStudents.getSelectionModel().getSelectedItem();
            studentid = students.getStudentid();
            txtName.setText(students.getName());
            txtPhone.setText(students.getPhone());
            txtEMail.setText(students.getE_mail());
            if (students.getIsActive() == 1){
               checkIsActive.setSelected(true);
            }else {
                checkIsActive.setSelected(false);
            }
            refresh();
        }
    }
        public void refresh(){
            DbHandler dbHandler = new DbHandler();
            ObservableList<Students>list = FXCollections.observableArrayList(dbHandler.getStudent());
            tbStudents.setItems(list);


    }
}
