package com.ucudal.tarea1;

import java.io.IOException;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

/**
* Controller for backuping a User prompt
* 
* Please see the {@link com.ucudal.tarea1.OS.backupUser} to see the command
* @author Rafael Rodriguez
* @since 1.0
* @version %I%, %G%
*/
public class backupUserPrompt {

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
    void CancelBackup(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
    }

    
    /** 
     * @param event
     */
    @FXML
    void backup(ActionEvent event) {
        Node prompt = (Node) event.getSource();
        prompt.getScene().getWindow().hide();
        Scene main = App.getScene();
        overwritePrompt.setUserNameID(userNameID.getText());       
        Stage stage = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("overwritePrompt"));
            stage.setTitle("Overwrite?");
            stage.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        stage.show();
    }

}