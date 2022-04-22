package com.ucudal.tarea1;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class PrimaryController {
    @FXML
    private ProgressIndicator Progress;

    @FXML
    private Button addUserButton;

    @FXML
    private TextField addUserName;

    @FXML
    private TextField addUserPrivilegies;

    @FXML
    private Button removeUserButton;

    @FXML
    private TextField removeUserName;

    @FXML
    private TextField removeUserPrivilegies;

    @FXML
    void addUser(ActionEvent event) {

    }

    @FXML
    void removeUser(ActionEvent event) {

    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }
}
