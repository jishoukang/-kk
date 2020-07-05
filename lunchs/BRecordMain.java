package com.lib.mangement.lunchs;

import com.lib.mangement.Common.dao.CommonDao;
import com.lib.mangement.Common.entity.Broken;
import com.lib.mangement.Common.entity.User;
import com.lib.mangement.dao.UserDao;
import com.lib.mangement.dao.impl.UserDaoImpl;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;


import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.ContextMenuEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.util.List;

public class BRecordMain extends Application {


    @FXML
    private TableColumn bsno;
    @FXML
    private TableColumn bid;
    @FXML
    private TableColumn bname;
    @FXML
    private TableColumn bdate;
    @FXML
    private TableColumn bhdate;
    @FXML
    private TableColumn bcontent;

    @FXML
    private TableView brokentable;

    private UserDao userDao=new UserDaoImpl();
    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("brokenrecoreds.fxml"));
        primaryStage.setTitle("违规记录");
        primaryStage.setScene(new Scene(root, 776, 440));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void loadinfo(MouseEvent mouseEvent) {
        User user= CommonDao.getUserFromFile();
        List<Broken> brokens = userDao.getBrokenByUserid(user.getId());
        ObservableList<Broken> userdata= FXCollections.observableArrayList(brokens);
        brokentable.setEditable(true);
        bid.setCellValueFactory(new PropertyValueFactory<>("id"));
        bsno.setCellValueFactory(new PropertyValueFactory<>("u_num"));
        bname.setCellValueFactory(new PropertyValueFactory<>("u_name"));
        bdate.setCellValueFactory(new PropertyValueFactory<>("v_date"));
        bhdate.setCellValueFactory(new PropertyValueFactory<>("dw_date"));
        bcontent.setCellValueFactory(new PropertyValueFactory<>("content"));
        brokentable.setItems(userdata);
    }
}
