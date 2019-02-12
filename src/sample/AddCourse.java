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
import javafx.util.StringConverter;


public class AddCourse {
    private int courseId = 0;

        @FXML
        private ResourceBundle resources;

        @FXML
        private URL location;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtPrice;

        @FXML
        private Button btnOk;

        @FXML
        private Button btnCancel;

    @FXML
    private Button btnBack;

        @FXML
    private TableView<Course> tbCourse;

        @FXML
        private TableColumn<Course, Integer> clmId;

        @FXML
        private TableColumn<Course, String> clmName;

        @FXML
        private TableColumn<Course, Integer> clmPrice;

        @FXML
        private TableColumn<Course, String>clmDay;

        @FXML
        private TableColumn<Course, String> clmTime;
    @FXML
    private MenuItem menuEdit;

    @FXML
    private MenuItem menuNew;

    @FXML
    private ComboBox<Days> cbDays;

    @FXML
    private ComboBox<Times> cbTimes;

    @FXML
    void onEditlicked(ActionEvent event) {
        Course course = tbCourse.getSelectionModel().getSelectedItem();
        courseId =  course.getCourseid();
        txtName.setText( course.getName());
        txtPrice.setText(String.valueOf(course.getPrice()));

    }

    @FXML
    void onNewClicked(ActionEvent event) {
        courseId = 0;

    }


        @FXML
        void initialize() {
        DbHandler d = new DbHandler();
        cbDays.setConverter(new StringConverter<Days>() {
            @Override
            public String toString(Days object) {
                return object.getDay();
            }

            @Override
            public Days fromString(String string) {
                return cbDays.getItems().stream().filter(ap -> ap.getDay().equals(string)).findFirst().orElse(null);
            }
        });
        ObservableList<Days>days = FXCollections.observableArrayList(d.getDay());
        cbDays.setItems(days);
        DbHandler handler = new DbHandler();
        cbTimes.setConverter(new StringConverter<Times>() {
            @Override
            public String toString(Times object) {
                return object.getTime();
            }

            @Override
            public Times fromString(String string) {
                return cbTimes.getItems().stream().filter(ap-> ap.getTime().equals(string)).findFirst().orElse(null);
            }
        });

        ObservableList <Times> list = FXCollections.observableArrayList(handler.getTime());
        cbTimes.setItems(list);



                    DbHandler dbHandler = new DbHandler();
                    clmId.setCellValueFactory(new PropertyValueFactory<>("courseid"));
                    clmName.setCellValueFactory(new PropertyValueFactory<>("name"));
                    clmPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
            clmDay.setCellValueFactory(new PropertyValueFactory<>("day"));
            clmTime.setCellValueFactory(new PropertyValueFactory<>("time"));
            ObservableList<Course> courses = FXCollections.observableArrayList(dbHandler.courses());

            tbCourse.setItems(courses);

                    dbHandler.ShowCourses();
                    btnOk.setOnAction(event -> {
                creatCourse();
                refresh();
            });
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
        private void creatCourse(){

            String name = txtName.getText();
            int price = Integer.parseInt(txtPrice.getText());
           Days days = cbDays.getValue();
           Times times = cbTimes.getValue();
            DbHandler dbHandler = new DbHandler();
            dbHandler.addCourses(new Course(courseId,name,price,days.getDay(),times.getTime()),new Days(days.getDay()),new Times(times.getTime()));
            courseId = 0;
            txtName.clear();
            txtPrice.clear();
        }
    @FXML
    void mouseClicked(MouseEvent event) {
        if (event.getClickCount() == 2) {
            Course course = tbCourse.getSelectionModel().getSelectedItem();
            courseId = course.getCourseid();
            txtName.setText(course.getName());
            txtPrice.setText(String.valueOf(course.getPrice()));
            refresh();

        }
    }
    public void refresh(){
        DbHandler dbHandler = new DbHandler();
        ObservableList<Course>list = FXCollections.observableArrayList(dbHandler.courses());
        tbCourse.setItems(list);
    }



}
