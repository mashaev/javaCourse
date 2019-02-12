package sample;

import java.io.IOException;
import java.net.URL;
import java.util.Observable;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.stage.Stage;
import javafx.util.StringConverter;


public class SignIn {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtlogin;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private Button btnsignin;

    @FXML
    private Button btncancel;

    @FXML
    private Button btnsignup;

    @FXML
    private ComboBox<User> cbCombo;

    @FXML
    void initialize() {

        btnsignup.setOnAction(event -> {
            Parent root;
            try {
                root = FXMLLoader.load(getClass().getResource("signUpView.fxml"));
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.show();
                btnsignup.getScene().getWindow().hide();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        btnsignin.setOnAction(event ->checkRight());

        DbHandler dbHandler = new DbHandler();
        cbCombo.setConverter(new StringConverter<User>() {
            @Override
            public String toString(User object) {
                return object.getLogin();
            }

            @Override
            public User fromString(String string) {
                return cbCombo.getItems().stream().filter(ap -> ap.getLogin().equals(string)).findFirst().orElse(null);
            }
        });
        ObservableList<User> list = FXCollections.observableList(dbHandler.getUser());
        cbCombo.setItems(list);
    }


        private void checkRight() {

            String pwd = txtpassword.getText();
            User user = cbCombo.getValue();

            if (pwd.equals(user.getPassword())) {
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("dataBaseView.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    btnsignin.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("ошибка");
                alert.setContentText("Неверные пароли");
                alert.showAndWait();
            }


        }}