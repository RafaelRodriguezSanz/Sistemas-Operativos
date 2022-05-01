package com.ucudal.tarea1;

import static org.junit.Assert.*;

import java.io.IOException;

import com.ucudal.tarea1.OS.OS;

import static com.ucudal.tarea1.App.loadFXML;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.isEnabled;
import static org.testfx.matcher.base.NodeMatchers.isInvisible;
import static org.testfx.matcher.base.NodeMatchers.isNotNull;
import static org.testfx.matcher.base.NodeMatchers.isVisible;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import org.testfx.framework.junit.ApplicationTest;
import org.testfx.osgi.service.TestFx;

import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class AppUITest extends ApplicationTest {
    @Override
    public void start(Stage stage){
        Scene scene;
        try {
            scene = new Scene(loadFXML("main"));
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("OS Manager");
            stage.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            stage.show();
            TextArea console1 = (TextArea) scene.lookup("#Console1");
            TextArea console2 = (TextArea) scene.lookup("#Console2");
            console1.setText("Console>>\n");
            console2.setText("Console>>\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void smoke() {
        //given:
        clickOn("#Banner1");

        // when:

        //nothing

        // then:
        verifyThat("#Banner1", isVisible());
    }
}