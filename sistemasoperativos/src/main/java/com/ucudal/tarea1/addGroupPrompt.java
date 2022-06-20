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
 * Controller for adding group prompt
 * 
 * Please see the {@link com.ucudal.tarea2.System.createGroup} to see the
 * command
 * 
 * @author Rafael Rodriguez
 * @since 1.0
 * @version %I%, %G%
 */
public class addGroupPrompt {

    @FXML
    private Button cancelButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField groupNameID;

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
        String group = this.groupNameID.getText();
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        App.printConsole("Searching Group: " + group + '\n');
        if (OS.groupExist(group)) {
            App.printConsole("Group " + group + " allready exist!" + '\n');
            App.printConsole("Group creation aborted." + '\n');
        } else {
            App.printConsole("Group " + group + " does not exist" + '\n');
            App.printConsole("Creating group " + group + '\n');
            if (OS.createGroup(group)) {
                App.printConsole("Group " + group + " successfully created!" + '\n');
            } else {
                App.printConsole("Error creating " + group + " group" + '\n');
            }

        }
    }

}