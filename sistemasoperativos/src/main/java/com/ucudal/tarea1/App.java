package com.ucudal.tarea1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"));
        //scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("OS Manager");
        ProgressIndicator progress = (ProgressIndicator) scene.lookup("#Progress");
        progress.setVisible(false);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        //CommandExecutor cmd = CommandExecutor.getInstance();
        //cmd.addCommand("whoami");
        //String currentUser = OS.currentUser();
        //OS.userExists(currentUser);
        //OS.userID(currentUser);
        // OS.cleanAll();
        // OS.createGroup("SO");
        // OS.createUser("SO_User", "r--");
        // OS.backup("SO_User");
        // OS.addPrivileges("SO_User", "r--");
        // OS.removePrivileges("SO_User", "r--");
        // OS.getUserInfo("SO_User");
        // OS.getUsers();
        // OS.getGroups();
        
        //OS.cleanAllBackups();
        //OS.cleanUserBackups("rafael");
        // OS.backupUser("rafael");

        launch();
    }

}