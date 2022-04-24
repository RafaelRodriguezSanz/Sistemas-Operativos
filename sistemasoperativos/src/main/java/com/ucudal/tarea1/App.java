package com.ucudal.tarea1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

import com.ucudal.tarea1.OS.OS;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    public static Scene getScene() {
        return scene;
    }

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        // scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setTitle("OS Manager");
        stage.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
        ProgressIndicator progress1 = (ProgressIndicator) scene.lookup("#ProgressIndicator1");
        ProgressIndicator progress2 = (ProgressIndicator) scene.lookup("#ProgressIndicator2");
        progress1.setVisible(false);
        progress2.setVisible(false);
        stage.show();
        TextArea console1 = (TextArea) scene.lookup("#Console1");
        TextArea console2 = (TextArea) scene.lookup("#Console2");
        console1.setText("Console>>\n");
        console2.setText("Console>>\n");

    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        
        launch();
    }

}