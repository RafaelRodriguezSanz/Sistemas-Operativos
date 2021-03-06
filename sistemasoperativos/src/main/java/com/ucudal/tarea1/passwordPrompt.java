package com.ucudal.tarea1;

import com.ucudal.tarea1.CommandExecutor.CommandExecutor;
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
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;

/**
 * Controller for prompt that checks if a user exist, if it is SUDO and if the
 * password is correct
 * 
 * Please see the {@link com.ucudal.tarea2.System.checkSudo} to see the command
 * 
 * @author Rafael Rodriguez
 * @since 1.0
 * @version %I%, %G%
 */
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

    /**
     * @param event
     */
    @FXML
    void Login(ActionEvent event) {
        if (OS.checkSudo(this.userNameID.getText(), this.password.getText())) {
            Node prompt = (Node) event.getSource();
            prompt.getScene().getWindow().hide();
            Scene main = App.getScene();

            TextArea console1 = (TextArea) main.lookup("#Console1");
            TextArea console2 = (TextArea) main.lookup("#Console2");
            TextArea console3 = (TextArea) main.lookup("#Console3");
            console1.appendText("User Loging successfully!" + '\n');
            console2.appendText("User Loging successfully!" + '\n');
            console3.appendText("User Loging successfully!" + '\n');
            TabPane all = (TabPane) main.lookup("#all");
            all.setDisable(false);
            CommandExecutor.setSudo(this.userNameID.getText(), this.password.getText());
            // OS.addChrontab();
        } else {
            this.message.setVisible(true);
        }
    }

    /**
     * @param event
     */
    @FXML
    void cancelLogin(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        main.getWindow().hide();
    }

    /**
     * @param event
     */
    @FXML
    void enter(KeyEvent event) {
        if (event.toString().contains("ENTER")) {
            if (OS.checkSudo(this.userNameID.getText(), this.password.getText())) {
                Node prompt = (Node) event.getSource();
                prompt.getScene().getWindow().hide();
                Scene main = App.getScene();

                TextArea console1 = (TextArea) main.lookup("#Console1");
                TextArea console2 = (TextArea) main.lookup("#Console2");
                TextArea console3 = (TextArea) main.lookup("#Console3");
                console1.appendText("User Loging successfully!" + '\n');
                console2.appendText("User Loging successfully!" + '\n');
                console3.appendText("User Loging successfully!" + '\n');
                TabPane all = (TabPane) main.lookup("#all");
                all.setDisable(false);
                CommandExecutor.setSudo(this.userNameID.getText(), this.password.getText());
                // OS.addChrontab();
            } else {
                this.message.setVisible(true);
            }
        }
    }
}
