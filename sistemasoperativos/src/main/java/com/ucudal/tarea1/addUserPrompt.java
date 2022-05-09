package com.ucudal.tarea1;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class addUserPrompt {

    @FXML
    private CheckBox isSudo;

    @FXML
    private TextField Password;

    @FXML
    private Button cancelButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField userNameID;

    @FXML
    void CancelAdd(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
    }

    @FXML
    void add(ActionEvent event) {
        String user = this.userNameID.getText();
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        TextArea console3 = (TextArea) main.lookup("#Console3");
        console1.appendText("Searching User: " + user + '\n');
        console2.appendText("Searching User: " + user + '\n');
        console3.appendText("Searching User: " + user + '\n');
        if (OS.userExists(user)) {
            console1.appendText("User " + user + " allready exist!" + '\n');
            console2.appendText("User " + user + " allready exist!" + '\n');
            console3.appendText("User " + user + " allready exist!" + '\n');
            console1.appendText("User creation aborted." + '\n');
            console2.appendText("User creation aborted." + '\n');
            console3.appendText("User creation aborted." + '\n');
        } else {
            console1.appendText("User " + user + " does not exist" + '\n');
            console2.appendText("User " + user + " does not exist" + '\n');
            console3.appendText("User " + user + " does not exist" + '\n');
            console1.appendText("Creating user " + user + '\n');
            console2.appendText("Creating user " + user + '\n');
            console3.appendText("Creating user " + user + '\n');
            if (OS.createUser(user, Password.getText(), isSudo.isSelected())) {
                console1.appendText("User " + user + " successfully created!" + '\n');
                console2.appendText("User " + user + " successfully created!" + '\n');
                console3.appendText("User " + user + " successfully created!" + '\n');
            } else {
                console1.appendText("Error creating " + user + " user" + '\n');
                console2.appendText("Error creating " + user + " user" + '\n');
                console3.appendText("Error creating " + user + " user" + '\n');
            }

        }
    }

}