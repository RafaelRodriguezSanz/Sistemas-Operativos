package com.ucudal.tarea1;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class passwordPrompt {
    @FXML
    private Button cancelBtn;

    @FXML
    private Label label;

    @FXML
    private ImageView lockImage;

    @FXML
    private Button loginBtn;

    @FXML
    private Text message;

    @FXML
    private PasswordField password;

    @FXML
    private TextField userNameID;

    @FXML
    void Login(ActionEvent event) {
        Scene prompt = App.getScene();
        Text message = (Text) prompt.lookup("#message");
        message.setVisible(false);
        if (OS.checkSudo(this.userNameID.getText(), this.password.getText())) {
            prompt.getWindow().hide();
            Scene main = App.getScene();

            TextArea console1 = (TextArea) main.lookup("#Console1");
            TextArea console2 = (TextArea) main.lookup("#Console2");
            console1.appendText("User Loging successfully!" + '\n');
            console2.appendText("User Loging successfully!" + '\n');
            TabPane all = (TabPane) main.lookup("#all");
            all.setDisable(false);
        } else {

            message.setVisible(true);
        }
    }

    @FXML
    void cancelLogin(ActionEvent event) {

    }

}
