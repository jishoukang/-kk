package com.lib.mangement.lunchs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AddStudentMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("addStu.fxml"));
        primaryStage.setTitle("添加学生");
        primaryStage.setScene(new Scene(root, 553, 443));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
