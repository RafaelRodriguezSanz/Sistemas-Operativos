package com.ucudal.tarea1;

import java.io.IOException;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class mainController {
    @FXML
    private ImageView Banner1;

    @FXML
    private ImageView Banner2;

    @FXML
    private ImageView Banner3;

    @FXML
    private Button ClearConsole1;

    @FXML
    private static TextArea Console1;

    @FXML
    private TextArea Console2;

    @FXML
    private TextArea Console3;

    @FXML
    private StackPane GroupViewer1;

    @FXML
    private StackPane GroupViewer2;

    @FXML
    private StackPane GroupViewer3;

    @FXML
    private Button addGroupButton;

    @FXML
    private Button addUserButton;

    @FXML
    private TabPane all;

    @FXML
    private Button clearConsoleButton2;

    @FXML
    private Button clearConsoleButton3;

    @FXML
    private ChoiceBox<String> component;

    @FXML
    private Button existGroupButton;

    @FXML
    private Button existUserButton;

    @FXML
    private LineChart<?, ?> graph;

    @FXML
    private static  PieChart pie1 = new PieChart();

    @FXML
    private  PieChart pie2;

    @FXML
    private  PieChart pie3;

    @FXML
    private  PieChart pie4;

    @FXML
    private  PieChart pie5;

    @FXML
    private PieChart pie6;

    @FXML
    private Button removeGroupButton;

    @FXML
    private Button removeUserButton;

    @FXML
    private Button showGroupButton;

    @FXML
    private Button showUserButton;

    // #region
    public static PieChart getPie1() {
        return pie1;
    }
    public PieChart getPie2() {
        return this.pie2;
    }
    public PieChart getPie3() {
        return this.pie3;
    }
    public PieChart getPie4() {
        return this.pie4;
    }
    public PieChart getPie5() {
        return this.pie5;
    }
    public PieChart getPie6() {
        return this.pie6;
    }
    public Button getAddGroupButton() {
        return addGroupButton;
    }

    public Button getAddUserButton() {
        return addUserButton;
    }

    public ImageView getBanner1() {
        return Banner1;
    }

    public ImageView getBanner2() {
        return Banner2;
    }

    public Button getClearConsole1() {
        return ClearConsole1;
    }

    public Button getClearConsoleButton2() {
        return clearConsoleButton2;
    }

    public static TextArea getConsole1() {
        return Console1;
    }

    public TextArea getConsole2() {
        return Console2;
    }

    public Button getExistGroupButton() {
        return existGroupButton;
    }

    public Button getExistUserButton() {
        return existUserButton;
    }

    public StackPane getGroupViewer2() {
        return GroupViewer2;
    }

    public StackPane getGroupViewer1() {
        return GroupViewer1;
    }

    public Button getRemoveGroupButton() {
        return removeGroupButton;
    }

    public Button getRemoveUserButton() {
        return removeUserButton;
    }

    public Button getShowGroupButton() {
        return showGroupButton;
    }

    public Button getShowUserButton() {
        return showUserButton;
    }

    public void setAddGroupButton(Button addGroupButton) {
        this.addGroupButton = addGroupButton;
    }

    public void setAddUserButton(Button addUserButton) {
        this.addUserButton = addUserButton;
    }

    public void setBanner(Image banner) {
        Banner1.setImage(banner);
        Banner2.setImage(banner);
    }

    public void setConsole(String text) {
        Console1.appendText("\n" + text);
        Console2.appendText("\n" + text);
    }

    public void setGroupViewer2(StackPane groupViewer2) {
        GroupViewer2 = groupViewer2;
    }

    public void setGroupViewer1(StackPane groupViewer1) {
        GroupViewer1 = groupViewer1;
    }

    // #endregion

    @FXML
    void AddGroup(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("addGroupPrompt"));
            prompt.setTitle("Add Group");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

    @FXML
    void BackupUser(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("backupUserPrompt"));
            prompt.setTitle("Backup User");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

    @FXML
    void ClearConsoleButton(ActionEvent event) {
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        console1.setText("Console>>" + '\n');
        console2.setText("Console>>" + '\n');
    }

    @FXML
    void ExistGroup(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("existGroupPrompt"));
            prompt.setTitle("Search Input");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

    @FXML
    void ExistUser(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("existUserPrompt"));
            prompt.setTitle("Search Input");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

    @FXML
    void RemoveGroup(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("removeGroupPrompt"));
            prompt.setTitle("Remove Group");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

    @FXML
    void RemoveUser(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("removeUserPrompt"));
            prompt.setTitle("Remove User");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

    @FXML
    void ShowGroup(ActionEvent event) {

        final Image userIcon = new Image(getClass().getResourceAsStream("userIcon.png"));
        final Image groupIcon = new Image(getClass().getResourceAsStream("groupIcon.png"));
        final Image homeIcon = new Image(getClass().getResourceAsStream("homeIcon.png"));

        String[] groups = OS.getGroups();
        TreeItem<String> root = new TreeItem<>("Groups (" + (groups.length - 1) + ")", new ImageView(homeIcon));
        root.setExpanded(true);
        for (String group : groups) {
            if (!group.isBlank()) {
                String[] groupArray = group.split(":");
                if (groupArray.length == 4) {
                    for (String user : (group.split(":")[3]).split(",")) {
                        if (!user.isBlank()) {
                            TreeItem<String> groupTree = new TreeItem<>(group.split(":")[0], new ImageView(groupIcon));
                            groupTree.getChildren().add(new TreeItem<String>(user, new ImageView(userIcon)));
                            root.getChildren().add(groupTree);
                        }
                    }
                } else {
                    TreeItem<String> groupTree = new TreeItem<>(group.split(":")[0], new ImageView(groupIcon));
                    root.getChildren().add(groupTree);
                }
            }
        }
        TreeView<String> tree = new TreeView<>(root);
        // getGroupViewer1().getChildren().add(tree);
        getGroupViewer2().getChildren().add(tree);
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        console1.appendText("Groups and users where displayed successfully!" + '\n');
        console2.appendText("Groups and users where displayed successfully!" + '\n');
    }

    @FXML
    void ShowUsers(ActionEvent event) {
        final Image userIcon = new Image(getClass().getResourceAsStream("userIcon.png"));
        final Image groupIcon = new Image(getClass().getResourceAsStream("groupIcon.png"));

        String[] users = OS.getUsers();
        TreeItem<String> root = new TreeItem<>("Users (" + (users.length - 1) + ")", new ImageView(groupIcon));
        root.setExpanded(true);
        for (String user : users) {
            if (!user.isBlank()) {
                String[] userData = user.split(":");
                TreeItem<String> userTree = new TreeItem<>(userData[0], new ImageView(userIcon));
                for (String data : userData) {
                    userTree.getChildren().add(new TreeItem<String>(data));
                }
                root.getChildren().add(userTree);
            }
        }
        TreeView<String> tree = new TreeView<>(root);
        getGroupViewer1().getChildren().add(tree);
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        console1.appendText("Users where displayed successfully!" + '\n');
        console2.appendText("Users where displayed successfully!" + '\n');
    }

    @FXML
    void addUser(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("addUserPrompt"));
            prompt.setTitle("Add User");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

}