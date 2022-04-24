package com.ucudal.tarea1;

import java.io.IOException;

import com.ucudal.tarea1.OS.OS;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;
import javafx.scene.control.TreeView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class PrimaryController {

    @FXML
    private ImageView Banner1;

    @FXML
    private ImageView Banner2;

    @FXML
    private Button ClearConsole1;

    @FXML
    private static TextArea Console1;

    @FXML
    private TextArea Console2;

    @FXML
    private TreeView<?> GroupViewer2;

    @FXML
    private TreeView<?> GroupViewer1;

    @FXML
    private ProgressIndicator ProgressIndicator1;

    @FXML
    private ProgressIndicator ProgressIndicator2;

    @FXML
    private Button addGroupButton;

    @FXML
    private Button addUserButton;

    @FXML
    private Button clearConsoleButton2;

    @FXML
    private Button existGroupButton;

    @FXML
    private Button existUserButton;

    @FXML
    private Button removeGroupButton;

    @FXML
    private Button removeUserButton;

    @FXML
    private Button showGroupButton;

    @FXML
    private Button showUserButton;

    // #region
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
        return PrimaryController.Console1;
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

    public TreeView<?> getGroupViewer2() {
        return GroupViewer2;
    }

    public TreeView<?> getGroupViewer1() {
        return GroupViewer1;
    }

    public ProgressIndicator getProgressIndicator1() {
        return ProgressIndicator1;
    }

    public ProgressIndicator getProgressIndicator2() {
        return ProgressIndicator2;
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

    public void setGroupViewer(TreeView<?> groupViewer2) {
        GroupViewer2 = groupViewer2;
    }

    public void setGroupViewer1(TreeView<?> groupViewer1) {
        GroupViewer1 = groupViewer1;
    }

    public void setProgressIndicator(Double progress) {
        ProgressIndicator1.setProgress(progress);
        ProgressIndicator2.setProgress(progress);
    }

    // #endregion

    @FXML
    void AddGroup(ActionEvent event) {
        setConsole("Group is been added.");
        setConsole("Group was added succesfully!");
    }

    @FXML
    void BackupUser(ActionEvent event) {

    }

    @FXML
    void ClearConsoleButton(ActionEvent event) {
        Console1.setText("Console>>");
        Console2.setText("Console>>");
    }

    @FXML
    void ExistGroup(ActionEvent event) {

    }

    @FXML
    void ExistUser(ActionEvent event) {
        Stage prompt = new Stage();
        try {
            Scene scene = new Scene(App.loadFXML("SearchPrompt"));
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

    }

    @FXML
    void RemoveUser(ActionEvent event) {

    }

    @FXML
    void ShowGroup(ActionEvent event) {

    }

    @FXML
    void ShowUsers(ActionEvent event) {

    }

    @FXML
    void addUser(ActionEvent event) {

    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("main");
    }

}