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
 * Please see the {@link com.ucudal.tarea2.System.createuser} to see the command
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
        App.printConsole("Searching user: " + user + '\n');
        if (!OS.userExists(user)) {
            App.printConsole("User " + user + " does not exist!" + '\n');
            App.printConsole("User SUDO addition aborted." + '\n');
        } else {
            App.printConsole("User " + user + " already exist." + '\n');
            if (OS.makeSUDO(user)) {
                App.printConsole("User " + user + " is now a SUDO user." + '\n');
            } else {
                App.printConsole("Error making " + user + " a SUDO user" + '\n');
            }
        }
    }
}