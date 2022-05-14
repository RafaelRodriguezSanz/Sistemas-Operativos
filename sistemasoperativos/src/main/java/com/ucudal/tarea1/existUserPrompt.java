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
* Controller for checking is a User exist prompt
* 
* Please see the {@link com.ucudal.tarea1.OS.userExist} to see the command
* @author Rafael Rodriguez
* @since 1.0
* @version %I%, %G%
*/
public class existUserPrompt {

    @FXML
    private Button cancelButton;

    @FXML
    private Button searchButton;

    @FXML
    private TextField userNameID;

    
    /** 
     * @param event
     */
    @FXML
    void CancelSearch(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
    }

    
    /** 
     * @param event
     */
    @FXML
    void search(ActionEvent event) {
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
            console1.appendText("User " + user + " exist!" + '\n');
            console2.appendText("User " + user + " exist!" + '\n');
            console3.appendText("User " + user + " exist!" + '\n');
        }
        else{
            console1.appendText("User " + user + " does not exist" + '\n');
            console2.appendText("User " + user + " does not exist" + '\n');
            console3.appendText("User " + user + " does not exist" + '\n');
        }
    }

}