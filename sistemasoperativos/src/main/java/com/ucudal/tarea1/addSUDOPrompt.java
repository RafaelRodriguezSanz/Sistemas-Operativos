package com.ucudal.tarea1;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

/**
 * Controller for adding user prompt
 * 
 * Please see the {@link com.ucudal.tarea1.OS.createuser} to see the command
 * 
 * @author Rafael Rodriguez
 * @since 1.0
 * @version %I%, %G%
 */
public class addSUDOPrompt {

    @FXML
    private Button cancelButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField userNameID;

    /**
     * @param event
     */
    @FXML
    void CancelAdd(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
    }

    /**
     * @param event
     */
    @FXML
    void add(ActionEvent event) {
        String user = this.userNameID.getText();
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        TextArea console3 = (TextArea) main.lookup("#Console3");
        console1.appendText("Searching user: " + user + '\n');
        console2.appendText("Searching user: " + user + '\n');
        console3.appendText("Searching user: " + user + '\n');
        if (!OS.userExists(user)) {
            console1.appendText("User " + user + " does not exist!" + '\n');
            console2.appendText("User " + user + " does not exist!" + '\n');
            console3.appendText("User " + user + " does not exist!" + '\n');
            console1.appendText("User SUDO addition aborted." + '\n');
            console2.appendText("User SUDO addition aborted." + '\n');
            console3.appendText("User SUDO addition aborted." + '\n');
        } else {
            console1.appendText("User " + user + " allready exist." + '\n');
            console2.appendText("User " + user + " allready exist." + '\n');
            console3.appendText("User " + user + " allready exist." + '\n');
            if (OS.makeSUDO(user)) {
                console1.appendText("User " + user + " is now a SUDO user." + '\n');
                console2.appendText("User " + user + " is now a SUDO user." + '\n');
                console3.appendText("User " + user + " is now a SUDO user." + '\n');
            } else {
                console1.appendText("Error makin " + user + " a SUDO user" + '\n');
                console2.appendText("Error makin " + user + " a SUDO user" + '\n');
                console3.appendText("Error makin " + user + " a SUDO user" + '\n');
            }
        }
    }
}