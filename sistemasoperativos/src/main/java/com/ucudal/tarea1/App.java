package com.ucudal.tarea1;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import com.ucudal.tarea1.OS.OS;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * JavaFX App
 */
public class App extends Application {

    private ScheduledExecutorService scheduledExecutorService;
    private static Scene scene;

    private static TextArea console1;
    private static TextArea console2;
    private static TextArea console3;
    private static TextArea console4;

    /**
     * @return Scene
     */
    public static Scene getScene() {
        return scene;
    }

    /**
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("main"));
        // scene.getStylesheets().add(getClass().getResource("styles.css").toExternalForm());
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("OS Manager");
        stage.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
        stage.show();
        console1 = (TextArea) scene.lookup("#Console1");
        console2 = (TextArea) scene.lookup("#Console2");
        console3 = (TextArea) scene.lookup("#Console3");
        console4 = (TextArea) scene.lookup("#Console4");
        App.setConsole("Console>>\n");
        TabPane all = (TabPane) scene.lookup("#all");
        all.setDisable(true);
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("passwordPrompt"));
            Text message = (Text) scene.lookup("#message");
            message.setVisible(false);
            prompt.setTitle("Login SUDO user");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();

        ////////////////////////////////////////////////////////////////////////////////////////////////////////
        // setup a scheduled executor to periodically put data into the chart
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            // Get Data

            String cpu = OS.getCPUPorcentage();
            String ram = OS.getRAMUsage();
            String disk = OS.getDiskUsage();

            String cpus = OS.getCPUS();
            String ramUsed = OS.getUsedRAM();
            String diskFree = OS.getDiskFreeSpace();

            StringBuilder builder = new StringBuilder();
            builder.append("CPU USAGE: " + cpu + "%\n");
            builder.append("RAM USAGE: " + ram + "%\n");
            builder.append("DISK USAGE: " + disk + "\n");
            builder.append("CPUs: " + cpus + '\n');
            builder.append("RAM USED: " + ramUsed + "MB\n");
            builder.append("DISK FREE SPACE: " + diskFree + '\n');

            TextArea stats = (TextArea) scene.lookup("#stats");
            stats.setText(builder.toString());
            Platform.runLater(() -> {

            });
        }, 0, 1, TimeUnit.SECONDS);
    }

    /**
     * @param fxml
     * @throws IOException
     */
    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    /**
     * @param fxml
     * @return Parent
     * @throws IOException
     */
    static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        launch();
    }

    /**
     * @throws Exception
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        scheduledExecutorService.shutdownNow();
    }

    public static void printConsole(String str) {
        console1.appendText(str);
        console2.appendText(str);
        console3.appendText(str);
        console4.appendText(str);
    }

    public static void setConsole(String str) {
        console1.setText(str);
        console2.setText(str);
        console3.setText(str);
        console4.setText(str);
    }
}