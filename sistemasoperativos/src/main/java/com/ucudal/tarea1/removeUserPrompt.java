package com.ucudal.tarea1;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class removeUserPrompt {

    @FXML
    private Button cancelButton;

    @FXML
    private Button removeButton;

    @FXML
    private TextField userNameID;

    @FXML
    void CancelRemove(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
    }

    @FXML
    void remove(ActionEvent event) {
        String user = this.userNameID.getText();
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        console1.appendText("Searching User: " + user + '\n');
        console2.appendText("Searching User: " + user + '\n');
        if (!OS.userExists(user)) {
            console1.appendText("User " + user + " does not exist" + '\n');
            console2.appendText("User " + user + " does not exist" + '\n');
            console1.appendText("User removal aborted" + '\n');
            console2.appendText("User removal aborted" + '\n');
        }
        else{
            console1.appendText("User " + user + "  exist" + '\n');
            console2.appendText("User " + user + "  exist" + '\n');
            console1.appendText("Removing user " + user + '\n');
            console2.appendText("Removing user " + user + '\n');
            if (OS.removeUser(user)) {
                console1.appendText("User " + user + " successfully removed!" + '\n');
                console2.appendText("User " + user + " successfully removed!" + '\n');
            } else {
                console1.appendText("Error removing " + user + " user" + '\n');
                console2.appendText("Error removing " + user + " user" + '\n');
            }
            
        }
    }

}