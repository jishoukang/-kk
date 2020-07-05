package com.lib.mangement.lunchs;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class UserMain extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("stu.fxml"));
        primaryStage.setTitle("图书座位预定系统----->学生端");
        primaryStage.setScene(new Scene(root, 767, 535));
        primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                Platform.exit();
            }
        });
       primaryStage.show();

    }

    public static void main(String[] args) {
        launch(args);
    }
}
