package com.ucudal.tarea1;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class SecondaryController {

    @FXML
    private Button cancelButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField userNameID;

    @FXML
    void CancelSearch(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
    }

    @FXML
    void search(ActionEvent event) {
        String user = this.userNameID.getText();
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        console1.appendText("Searching User: " + user);
        console2.appendText("User: " + user);
        String output;
        try {
            int userID = Integer.parseInt(user);
            if (OS.userExists(userID)) {
                console1.appendText("User " + user + " exist!");
                console2.appendText("User " + user + " exist!");
            }
        } catch (Exception e) {
            if (OS.userExists(user)) {
                console1.appendText("User " + user + " exist!");
                console2.appendText("User " + user + " exist!");
            }
        }

    }

}