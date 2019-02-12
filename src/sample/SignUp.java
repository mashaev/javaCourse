package sample;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignUp {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField txtname;

    @FXML
    private PasswordField txtpassword;

    @FXML
    private TextField txtlogin;

    @FXML
    private PasswordField txtpassword1;

    @FXML
    private Button btnsignin;

    @FXML
    private Button btncancel;

    @FXML
    void initialize() {
        btnsignin.setOnAction(event -> {
            creatUsers();
                Parent root;
                try {
                    root = FXMLLoader.load(getClass().getResource("sample.fxml"));
                    Stage stage = new Stage();
                    stage.setScene(new Scene(root));
                    stage.show();
                    btnsignin.getScene().getWindow().hide();
                } catch (IOException e) {
                    e.printStackTrace();
                }

             });
    }
    private void creatUsers(){
         String pwd = txtpassword.getText().trim();
         String pwd1 = txtpassword1.getText().trim();
         if (!comparePwd(pwd,pwd1)){
             Alert alert = new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Ошибка");
             alert.setTitle("Пароли не совпадают");
             alert.showAndWait();
         }
         DbHandler dbHandler = new DbHandler();
         dbHandler.creatUsers(txtlogin.getText(),txtpassword.getText());
    }

    private boolean comparePwd(String psw1, String psw2) {
        return psw1.equals(psw2);
    }

}
