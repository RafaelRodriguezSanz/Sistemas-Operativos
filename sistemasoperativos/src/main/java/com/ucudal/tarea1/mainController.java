package com.ucudal.tarea1;

import java.io.IOException;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import java.awt.datatransfer.StringSelection;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * Controller for the main scene of the application
 * 
 * @author Rafael Rodriguez
 * @since 1.0
 * @version %I%, %G%
 */
public class mainController {
    @FXML
    private Button removeFromGroupButton;

    @FXML
    private Button addToGroupButton;

    @FXML
    private ImageView Banner1;

    @FXML
    private ImageView Banner2;

    @FXML
    private ImageView Banner3;

    @FXML
    private Button ClearConsole1;

    @FXML
    private Button addSUDOButton;

    @FXML
    private static TextArea Console1;

    @FXML
    private Button modifyButton;

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
    private static TextArea stats;

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
    private Button existGroupButton;

    @FXML
    private Button existUserButton;

    @FXML
    private Button copyButton;

    @FXML
    private Button detailsButton;

    @FXML
    private Button removeGroupButton;

    @FXML
    private Button removeUserButton;

    @FXML
    private Button showGroupButton;

    @FXML
    private Button showUserButton;

    /**
     * @return Button
     */
    // #region
    public Button getAddGroupButton() {
        return addGroupButton;
    }

    /**
     * @return Button
     */
    public Button getAddUserButton() {
        return addUserButton;
    }

    /**
     * @return ImageView
     */
    public ImageView getBanner1() {
        return Banner1;
    }

    /**
     * @return ImageView
     */
    public ImageView getBanner2() {
        return Banner2;
    }

    /**
     * @return Button
     */
    public Button getClearConsole1() {
        return ClearConsole1;
    }

    /**
     * @return Button
     */
    public Button getClearConsoleButton2() {
        return clearConsoleButton2;
    }

    /**
     * @return TextArea
     */
    public static TextArea getConsole1() {
        return Console1;
    }

    /**
     * @return TextArea
     */
    public TextArea getConsole2() {
        return Console2;
    }

    /**
     * @return Button
     */
    public Button getExistGroupButton() {
        return existGroupButton;
    }

    /**
     * @return Button
     */
    public Button getExistUserButton() {
        return existUserButton;
    }

    /**
     * @return StackPane
     */
    public StackPane getGroupViewer2() {
        return GroupViewer2;
    }

    /**
     * @return StackPane
     */
    public StackPane getGroupViewer1() {
        return GroupViewer1;
    }

    /**
     * @return Button
     */
    public Button getRemoveGroupButton() {
        return removeGroupButton;
    }

    /**
     * @return Button
     */
    public Button getRemoveUserButton() {
        return removeUserButton;
    }

    /**
     * @return Button
     */
    public Button getShowGroupButton() {
        return showGroupButton;
    }

    /**
     * @return TextArea
     */
    public static TextArea getStats() {
        return stats;
    }

    /**
     * @return Button
     */
    public Button getShowUserButton() {
        return showUserButton;
    }

    /**
     * @param addGroupButton
     */
    public void setAddGroupButton(Button addGroupButton) {
        this.addGroupButton = addGroupButton;
    }

    /**
     * @param addUserButton
     */
    public void setAddUserButton(Button addUserButton) {
        this.addUserButton = addUserButton;
    }

    /**
     * @param banner
     */
    public void setBanner(Image banner) {
        Banner1.setImage(banner);
        Banner2.setImage(banner);
    }

    /**
     * @param text
     */
    public void setConsole(String text) {
        Console1.appendText("\n" + text);
        Console2.appendText("\n" + text);
    }

    /**
     * @param groupViewer2
     */
    public void setGroupViewer2(StackPane groupViewer2) {
        GroupViewer2 = groupViewer2;
    }

    /**
     * @param groupViewer1
     */
    public void setGroupViewer1(StackPane groupViewer1) {
        GroupViewer1 = groupViewer1;
    }

    /**
     * @param event
     */
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

    /**
     * @param event
     */
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

    /**
     * @param event
     */
    @FXML
    void ClearConsoleButton(ActionEvent event) {
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        TextArea console3 = (TextArea) main.lookup("#Console3");
        console1.setText("Console>>" + '\n');
        console2.setText("Console>>" + '\n');
        console3.setText("Console>>" + '\n');
    }

    /**
     * @param event
     */
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

    /**
     * @param event
     */
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

    /**
     * @param event
     */
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

    /**
     * @param event
     */
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

    /**
     * @param event
     */
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
        TextArea console3 = (TextArea) main.lookup("#Console3");
        console1.appendText("Groups and users where displayed successfully!" + '\n');
        console2.appendText("Groups and users where displayed successfully!" + '\n');
        console3.appendText("Groups and users where displayed successfully!" + '\n');
    }

    /**
     * @param event
     */
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
        TextArea console3 = (TextArea) main.lookup("#Console3");
        console1.appendText("Users where displayed successfully!" + '\n');
        console2.appendText("Users where displayed successfully!" + '\n');
        console3.appendText("Users where displayed successfully!" + '\n');
    }

    /**
     * @param event
     */
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

    /**
     * @param event
     */
    @FXML
    void copy(ActionEvent event) {
        String stats = OS.getStats();
        StringSelection selection = new StringSelection(stats);
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);
    }

    /**
     * @param event
     */
    @FXML
    void details(ActionEvent event) {
        String output = OS.getStats();
        Scene main = App.getScene();
        TextArea console1 = (TextArea) main.lookup("#Console1");
        TextArea console2 = (TextArea) main.lookup("#Console2");
        TextArea console3 = (TextArea) main.lookup("#Console3");
        console1.appendText("Details: " + output + '\n');
        console2.appendText("Details: " + output + '\n');
        console3.appendText("Details: " + output + '\n');
    }

    /**
     * @param event
     */
    @FXML
    void addToGroup(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("addUserToGroupPrompt"));
            prompt.setTitle("Add User to Group");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

    /**
     * @param event
     */
    @FXML
    void removeFromGroup(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("removeUserFromGroupPrompt"));
            prompt.setTitle("Add User to Group");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

    /**
     * @param event
     */
    @FXML
    void modify(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("modifyPrivilegiesPrompt"));
            prompt.setTitle("Modify Privilegies");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

    @FXML
    void AddSUDO(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("addSUDOPrompt"));
            prompt.setTitle("Add SUDO User");
            prompt.getIcons().add(new Image("file:./src/main/resources/com/ucudal/tarea1/icon.png"));
            prompt.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
        prompt.show();
    }

}