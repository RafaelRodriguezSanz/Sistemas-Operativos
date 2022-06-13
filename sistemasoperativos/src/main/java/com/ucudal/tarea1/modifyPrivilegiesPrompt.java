package com.ucudal.tarea1;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for adding user prompt
 * 
 * Please see the {@link com.ucudal.tarea2.System.createUser} to see the command
 * 
 * @author Rafael Rodriguez
 * @since 1.0
 * @version %I%, %G%
 */
public class modifyPrivilegiesPrompt {

    @FXML
    private Button cancelButton;

    @FXML
    private Button modifyButton;

    @FXML
    private TextField privilegies;

    /**
     * @param event
     */
    @FXML
    void CancelModify(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
    }

    /**
     * @param event
     */
    @FXML
    void modify(ActionEvent event) {
        String privilegies = this.privilegies.getText();
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        TextArea console3 = (TextArea) main.lookup("#Console3");
        console1.appendText("Modifying Privilegies" + '\n');
        console2.appendText("Modifying Privilegies" + '\n');
        console3.appendText("Modifying Privilegies" + '\n');
        if (OS.modifyPrivilegie(privilegies)) {
            console1.appendText("Privilegies could not be modified!" + '\n');
            console2.appendText("Privilegies could not be modified!" + '\n');
            console3.appendText("Privilegies could not be modified!" + '\n');
        } else {
            console1.appendText("Privilegies were modified successfully!" + '\n');
            console2.appendText("Privilegies were modified successfully!" + '\n');
            console3.appendText("Privilegies were modified successfully!" + '\n');
        }
    }

}