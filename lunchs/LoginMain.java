package com.lib.mangement.lunchs;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class LoginMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("login.fxml"));
        primaryStage.setTitle("图书座位预定系统----->登录界面");
        primaryStage.setScene(new Scene(root, 528, 419));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
