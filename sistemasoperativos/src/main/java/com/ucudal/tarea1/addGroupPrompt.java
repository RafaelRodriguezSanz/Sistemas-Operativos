package com.ucudal.tarea1;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class addGroupPrompt {

    @FXML
    private Button cancelButton;

    @FXML
    private Button addButton;

    @FXML
    private TextField groupNameID;

    @FXML
    void CancelAdd(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
    }

    @FXML
    void add(ActionEvent event) {
        String group = this.groupNameID.getText();
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        TextArea console3 = (TextArea) main.lookup("#Console3");
        console1.appendText("Searching Group: " + group + '\n');
        console2.appendText("Searching Group: " + group + '\n');
        console3.appendText("Searching Group: " + group + '\n');
        if (OS.groupExist(group)) {
            console1.appendText("Group " + group + " allready exist!" + '\n');
            console2.appendText("Group " + group + " allready exist!" + '\n');
            console3.appendText("Group " + group + " allready exist!" + '\n');
            console1.appendText("Group creation aborted." + '\n');
            console2.appendText("Group creation aborted." + '\n');
            console3.appendText("Group creation aborted." + '\n');
        }
        else{
            console1.appendText("Group " + group + " does not exist" + '\n');
            console2.appendText("Group " + group + " does not exist" + '\n');
            console3.appendText("Group " + group + " does not exist" + '\n');
            console1.appendText("Creating group " + group + '\n');
            console2.appendText("Creating group " + group + '\n');
            console3.appendText("Creating group " + group + '\n');
            if (OS.createGroup(group)) {
                console1.appendText("Group " + group + " successfully created!" + '\n');
                console2.appendText("Group " + group + " successfully created!" + '\n');
                console3.appendText("Group " + group + " successfully created!" + '\n');
            } else {
                console1.appendText("Error creating " + group + " group" + '\n');
                console2.appendText("Error creating " + group + " group" + '\n');
                console3.appendText("Error creating " + group + " group" + '\n');
            }
            
        }
    }

}