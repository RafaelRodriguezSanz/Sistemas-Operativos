package com.ucudal.tarea1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TabPane;
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
        stage.setResizable(false);
        stage.setTitle("OS Manager");
        stage.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
        stage.show();
        TextArea console1 = (TextArea) scene.lookup("#Console1");
        TextArea console2 = (TextArea) scene.lookup("#Console2");
        console1.setText("Console>>\n");
        console2.setText("Console>>\n");
        TabPane all = (TabPane) scene.lookup("#all");
        all.setDisable(true);
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("passwordPrompt"));
            prompt.setTitle("Login SUDO user");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
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