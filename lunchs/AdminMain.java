package com.lib.mangement.lunchs;

import com.lib.mangement.Common.entity.User;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.stage.Stage;


public class AdminMain extends Application {

    private final TableView<User> usersview=new TableView<>();
    private final ObservableList<User> userdata= FXCollections.observableArrayList();

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("admin.fxml"));
        primaryStage.setTitle("图书座位预定系统----->管理员端");
        primaryStage.setScene(new Scene(root, 845, 522));

        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
