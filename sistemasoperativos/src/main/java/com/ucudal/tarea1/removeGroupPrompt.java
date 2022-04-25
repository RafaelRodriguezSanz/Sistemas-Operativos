package com.ucudal.tarea1;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class removeGroupPrompt {

    @FXML
    private Button cancelButton;

    @FXML
    private Button removeButton;

    @FXML
    private TextField groupNameID;

    @FXML
    void CancelRemove(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
    }

    @FXML
    void remove(ActionEvent event) {
        String group = this.groupNameID.getText();
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        console1.appendText("Searching Group: " + group + '\n');
        console2.appendText("Searching Group: " + group + '\n');
        if (!OS.groupExist(group)) {
            console1.appendText("Group " + group + " does not exist" + '\n');
            console2.appendText("Group " + group + " does not exist" + '\n');
            console1.appendText("Group removal aborted" + '\n');
            console2.appendText("Group removal aborted" + '\n');
        }
        else{
            console1.appendText("Group " + group + "  exist" + '\n');
            console2.appendText("Group " + group + "  exist" + '\n');
            console1.appendText("Removing group " + group + '\n');
            console2.appendText("Removing group " + group + '\n');
            if (OS.removeGroup(group)) {
                console1.appendText("Group " + group + " successfully removed!" + '\n');
                console2.appendText("Group " + group + " successfully removed!" + '\n');
            } else {
                console1.appendText("Error removing " + group + " group" + '\n');
                console2.appendText("Error removing " + group + " group" + '\n');
            }
            
        }
    }

}