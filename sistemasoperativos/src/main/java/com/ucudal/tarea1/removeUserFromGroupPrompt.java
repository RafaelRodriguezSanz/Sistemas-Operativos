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
 * Controller for removeing user prompt
 * 
 * Please see the {@link com.ucudal.tarea2.System.createUser} to see the command
 * 
 * @author Rafael Rodriguez
 * @since 1.0
 * @version %I%, %G%
 */
public class removeUserFromGroupPrompt {

    @FXML
    private Button cancelButton;

    @FXML
    private Button removeButton;

    @FXML
    private TextField userNameID;

    @FXML
    private TextField groupNameID;

    /**
     * @param event
     */
    @FXML
    void CancelRemove(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
    }

    /**
     * @param event
     */
    @FXML
    void remove(ActionEvent event) {
        String user = this.userNameID.getText();
        String group = this.groupNameID.getText();
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        TextArea console3 = (TextArea) main.lookup("#Console3");
        console1.appendText("Searching User: " + user + '\n');
        console2.appendText("Searching User: " + user + '\n');
        console3.appendText("Searching User: " + user + '\n');
        if (!OS.userExists(user)) {
            console1.appendText("User " + user + " does not exist!" + '\n');
            console2.appendText("User " + user + " does not exist!" + '\n');
            console3.appendText("User " + user + " does not exist!" + '\n');
            console1.appendText("User removal to group aborted." + '\n');
            console2.appendText("User removal to group aborted." + '\n');
            console3.appendText("User removal to group aborted." + '\n');
        } else {
            if (!OS.groupExist(group)) {
                console1.appendText("Group " + group + " does not exist!" + '\n');
                console2.appendText("Group " + group + " does not exist!" + '\n');
                console3.appendText("Group " + group + " does not exist!" + '\n');
                console1.appendText("User removal to group aborted." + '\n');
                console2.appendText("User removal to group aborted." + '\n');
                console3.appendText("User removal to group aborted." + '\n');
            } else {
                console1.appendText("Group " + group + " exist" + '\n');
                console2.appendText("Group " + group + " exist" + '\n');
                console3.appendText("Group " + group + " exist" + '\n');
                console1.appendText("Removing user " + user + " from group " + group + '\n');
                console2.appendText("Removing user " + user + " from group " + group + '\n');
                console3.appendText("Removing user " + user + " from group " + group + '\n');
                if (OS.removeUserFromGroup(user, group)) {
                    console1.appendText("User " + user + " successfully removed!" + '\n');
                    console2.appendText("User " + user + " successfully removed!" + '\n');
                    console3.appendText("User " + user + " successfully removed!" + '\n');
                } else {
                    console1.appendText("Error removing " + user + " user" + " from group " + group + '\n');
                    console2.appendText("Error removing " + user + " user" + " from group " + group + '\n');
                    console3.appendText("Error removing " + user + " user" + " from group " + group + '\n');
                }
            }
        }
    }

}