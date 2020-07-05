package com.lib.mangement.controller;


import com.lib.mangement.lunchs.BRecordMain;
import com.lib.mangement.lunchs.ChangePasswordMain;
import com.lib.mangement.Common.dao.CommonDao;
import com.lib.mangement.Common.entity.BookInfo;
import com.lib.mangement.Common.entity.Seat;
import com.lib.mangement.Common.entity.User;
import com.lib.mangement.dao.impl.AdminDaoImpl;
import com.lib.mangement.dao.impl.UserDaoImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 * 用户接口实现类
 * @author zz
 * @version 1.0
 * */

public class UserController {


    @FXML
    private TextField oldpass;     //旧密码
    @FXML 
    private TextField pass;        //输入新密码

     @FXML
    private TextField repass;      //再次输入新密码

    @FXML
    private TableView seatTable;   //



    @FXML
    private TextField searchcontent;

 @FXML
    private TextField searchcontent1;


    @FXML
    private TableColumn sname;

    @FXML
    private TableColumn sid;
    @FXML
    private TableColumn sname1;
    @FXML
    private TableColumn sstatus1;


    @FXML
    private TableView booktable;


    @FXML
    private TableColumn sno;
    @FXML
    private TableColumn s_id;        //座位号
    @FXML
    private TableColumn status;      //座位状态

    @FXML
    private TableColumn booktime;    //预约时间

    private UserDaoImpl userDao=new UserDaoImpl();

    /**
     * 
     * */
    public void loadSeattable(){
        List<Seat> seats = userDao.getSeatInfo();
        ObservableList<Seat> userdata= FXCollections.observableArrayList(seats);
        seatTable.setEditable(true);
        sname1.setCellValueFactory(new PropertyValueFactory<>("id"));
        sstatus1.setCellValueFactory(new PropertyValueFactory<>("status"));
        seatTable.setItems(userdata);
    }

    /**
     * 这是申请预约座位的方法
     * @param actionEvent 监听对象
     * */
    public void applyseat(ActionEvent actionEvent) {
        Seat selectedItem = (Seat) seatTable.getSelectionModel().getSelectedItem();
       
        
        if(selectedItem==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("消息");
            alert.headerTextProperty().set("请选择");
            alert.showAndWait();

            return;
        }

        User user= CommonDao.getUserFromFile();
        
        boolean isExist=userDao.getSeatByUid(user.getId());
        if(isExist) {
        	 Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.titleProperty().set("消息");
             alert.headerTextProperty().set("你已经预约过了，请不要重复预约");
             alert.showAndWait();
             return;
        	
        }
        
        
         
        BookInfo bookInfo=new BookInfo();
        bookInfo.setBooktime(new Date());
        bookInfo.setS_num(selectedItem.getId());
        bookInfo.setU_num(user.getId());
        bookInfo.setU_name(user.getName());
        bookInfo.setStatus("已预订");
        //创建bookinfo将作为置为已预订
        try {
            userDao.applySeat(bookInfo);
        } catch (SQLException e) {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("消息");
            alert.headerTextProperty().set("预定失败");
            alert.showAndWait();
            return;
        }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("消息");
        alert.headerTextProperty().set("预定成功");
        alert.showAndWait();

        loadSeattable();
    }

    /**
     * 这是搜索预定信息的方法
     * @param actionEvent 监听对象
     * */
    public void searchBookinfo(ActionEvent actionEvent) {
        String text=searchcontent.getText();
        User user=CommonDao.getUserFromFile();
        List<BookInfo> bookInfos = userDao.getBookInfo(text,String.valueOf(user.getId()));
        ObservableList<BookInfo> userdata= FXCollections.observableArrayList(bookInfos);
        booktable.setEditable(true);
        sname.setCellValueFactory(new PropertyValueFactory<>("u_name"));
        sno.setCellValueFactory(new PropertyValueFactory<>("u_num"));
        s_id.setCellValueFactory(new PropertyValueFactory<>("s_num"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        booktime.setCellValueFactory(new PropertyValueFactory<>("booktime"));
        booktable.setItems(userdata);

    }
    
    /**
     * 这是搜索座位信息的方法
     * @param actionEvent 监听对象
     * */
    public void searchSeatInfo(ActionEvent actionEvent) {
        String text=searchcontent1.getText();
        List<Seat> seats = userDao.getSeatInfoBySeatId(text);
        ObservableList<Seat> userdata= FXCollections.observableArrayList(seats);
        seatTable.setEditable(true);
        sname1.setCellValueFactory(new PropertyValueFactory<>("id"));
        sstatus1.setCellValueFactory(new PropertyValueFactory<>("status"));
        seatTable.setItems(userdata);

    }


    /**
     * 这是学生对座位进行暂时离座操作的方法
     * @param actionEvent 监听对象
     * */
    public void tempstatus(ActionEvent actionEvent) {
        BookInfo bookInfo = (BookInfo) booktable.getSelectionModel().getSelectedItem();
        if(bookInfo==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("消息");
            alert.headerTextProperty().set("请选择一行");
            alert.showAndWait();
            return;
        }
        userDao.tempStatus(bookInfo.getId(),"暂时离座");
        userDao.chageSeatStatus("临时离座",bookInfo.getS_num());
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("消息");
        alert.headerTextProperty().set("操作成功");
        alert.showAndWait();
        loadpersonbook(actionEvent);

    }

    
    /**
     * 这是学生归还座位的方法
     * @param actionEvent 监听对象
     * */
    public void backstatus(ActionEvent actionEvent) {
        BookInfo bookInfo = (BookInfo) booktable.getSelectionModel().getSelectedItem();
        if(bookInfo==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("消息");
            alert.headerTextProperty().set("请选择一行");
            alert.showAndWait();
            return;
        }
        userDao.tempStatus(bookInfo.getId(),"空");
        userDao.chageSeatStatus("空",bookInfo.getS_num());
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("消息");
        alert.headerTextProperty().set("操作成功");
        alert.showAndWait();
        loadpersonbook(actionEvent);
    }

    public void openchange(ActionEvent actionEvent) throws Exception {
        ChangePasswordMain main=new ChangePasswordMain();
        main.start(new Stage());
    }

    /**
     * 这是学生进行密码修改的方法
     * @param actionEvent 监听对象
     * */
    public void dochangePass(ActionEvent actionEvent) {
      String oldpassstr=oldpass.getText();
      String passstr=pass.getText();
      String repassstr=repass.getText();
      User userFromFile = CommonDao.getUserFromFile();
       if(!oldpassstr.equals(userFromFile.getPassword())){
           Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.titleProperty().set("消息");
           alert.headerTextProperty().set("原密码不正确");
           alert.showAndWait();
           return;
       }
       if(!passstr.equals(repassstr)){
           Alert alert=new Alert(Alert.AlertType.ERROR);
           alert.titleProperty().set("消息");
           alert.headerTextProperty().set("两次密码不一致");
           alert.showAndWait();
           return;
       }
        userFromFile.setPassword(passstr);
        userDao.updateUserPass(userFromFile);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("消息");
        alert.headerTextProperty().set("修改成功");
        alert.showAndWait();
        Stage window = (Stage) oldpass.getParent().getScene().getWindow();
        window.close();

    }

    public void loadpersonbook(Event event) {
        String text=searchcontent.getText();
        User user=CommonDao.getUserFromFile();
        List<BookInfo> bookInfos = userDao.getpersonalbook(user.getId());
        ObservableList<BookInfo> userdata= FXCollections.observableArrayList(bookInfos);
        booktable.setEditable(true);
        sname.setCellValueFactory(new PropertyValueFactory<>("u_name"));
        sno.setCellValueFactory(new PropertyValueFactory<>("u_num"));
        s_id.setCellValueFactory(new PropertyValueFactory<>("s_num"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
        booktime.setCellValueFactory(new PropertyValueFactory<>("booktime"));
        booktable.setItems(userdata);
    }

    public void openrebelifo(ActionEvent actionEvent) {
        BRecordMain main=new BRecordMain();
        try {
            main.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
