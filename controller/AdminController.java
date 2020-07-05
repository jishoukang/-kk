package com.lib.mangement.controller;


import com.lib.mangement.dao.impl.AdminDaoImpl;
import com.lib.mangement.lunchs.AddRebelMain;
import com.lib.mangement.lunchs.AddStudentMain;
import com.lib.mangement.Common.dao.CommonDao;
import com.lib.mangement.Common.entity.Admin;
import com.lib.mangement.Common.entity.Broken;
import com.lib.mangement.Common.entity.User;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.stage.Stage;
import javafx.util.converter.DateTimeStringConverter;
import javafx.util.converter.IntegerStringConverter;
import jdk.nashorn.internal.runtime.regexp.joni.constants.TargetInfo;


import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;


/**
 * 这是针对管理员进行操作的工具类
 * @author zz
 * @version 1.0
 */

public class AdminController {

     @FXML
    private TableColumn classname;
     @FXML
    private TableColumn name;
     @FXML
    private TableColumn rid;

    @FXML
    private TableColumn icontent;
    @FXML
    private TableColumn idate;

     @FXML
    private TableColumn sid;

     @FXML
    private TableColumn sname;
    @FXML
    private TableColumn pass;
    @FXML
    private TableColumn department;


    @FXML
    private TableView tableinfo;

    @FXML
    private TableView stutable;

    @FXML
    private TextField searchcontent;
    @FXML
    private TextField searchcontent1;
    @FXML
    private TextField aoldpass;
    @FXML
    private TextField anewpass;
    @FXML
    private TextField arepass;

    @FXML
    private TextField asno;

    @FXML
    private TextField aname;

    @FXML
    private TextField apass;

    @FXML
    private TextField aclass;
    @FXML
    private TextField rsid;
    @FXML
    private TextField rname;
    @FXML
    private TextField rcontent;
    @FXML
    private DatePicker rdate;
    @FXML
    private DatePicker rhdate;


    @FXML
    private TextField adepart;


    private AdminDaoImpl adminDao=new AdminDaoImpl();


    /**
     * 这是增加违规记录的方法
     * @param actionEvent 监听对象
     * */
    public void addRebelRecord(ActionEvent actionEvent) {
         String rsidstr=rsid.getText();
         String rnamestr=rname.getText();
         String rcontentstr=rcontent.getText();

         LocalDate value = rdate.getValue();
         LocalDate valueh = rhdate.getValue();
         ZoneId zoneId = ZoneId.systemDefault();
         LocalDateTime now = LocalDateTime.now();
         Instant instant = value.atStartOfDay().atZone(zoneId).toInstant();
         Instant valuehinstan = valueh.atStartOfDay().atZone(zoneId).toInstant();
         Broken broken=new Broken();
         try {
             broken.setU_name(rnamestr);
             broken.setU_num(Integer.valueOf(rsidstr));
             broken.setV_date(Date.from(instant));
             broken.setDw_date(Date.from(valuehinstan));
             broken.setContent(rcontentstr);
             adminDao.addBroken(broken);
             Alert alert=new Alert(Alert.AlertType.INFORMATION);
             alert.titleProperty().set("消息");
             alert.headerTextProperty().set("操作成功,点击刷新");
             alert.showAndWait();
             Stage window = (Stage) rsid.getParent().getScene().getWindow();
             window.close();

         }catch (Exception e){
             Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("消息");
            alert.headerTextProperty().set("信息有误");
            alert.showAndWait();
         }


    }

    public void stumanagement(Event event) {
        List<User> users = adminDao.getAllStudent();
        ObservableList<User> userdata= FXCollections.observableArrayList(users);
        stutable.setEditable(true);
        sid.setCellValueFactory(new PropertyValueFactory<>("id"));
        classname.setCellValueFactory(new PropertyValueFactory<>("class_name"));
        sname.setCellValueFactory(new PropertyValueFactory<>("name"));
        pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        stumemgmtListner();
        stutable.setItems(userdata);
    }
    public void stumemgmtListner(){
        sid.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        classname.setCellFactory(TextFieldTableCell.forTableColumn());
        sname.setCellFactory(TextFieldTableCell.forTableColumn());
        pass.setCellFactory(TextFieldTableCell.forTableColumn());
        department.setCellFactory(TextFieldTableCell.forTableColumn());
        sid.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Object newValue = event.getNewValue();
                TablePosition tablePosition = event.getTablePosition();
                TableView tableView = tablePosition.getTableView();
                User user = (User) tableView.getSelectionModel().getSelectedItem();
                adminDao.updateUser("id",newValue,user.getId());
            }
        });  sid.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Object newValue = event.getNewValue();
                TablePosition tablePosition = event.getTablePosition();
                TableView tableView = tablePosition.getTableView();
                User user = (User) tableView.getSelectionModel().getSelectedItem();
                adminDao.updateUser("id",newValue,user.getId());
            }
        });
        classname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Object newValue = event.getNewValue();
                TablePosition tablePosition = event.getTablePosition();
                TableView tableView = tablePosition.getTableView();
                User user = (User) tableView.getSelectionModel().getSelectedItem();
                adminDao.updateUser("class_name",newValue,user.getId());
            }
        });
        sname.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Object newValue = event.getNewValue();
                TablePosition tablePosition = event.getTablePosition();
                TableView tableView = tablePosition.getTableView();
                User user = (User) tableView.getSelectionModel().getSelectedItem();
                adminDao.updateUser("name",newValue,user.getId());
            }
        });
        pass.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Object newValue = event.getNewValue();
                TablePosition tablePosition = event.getTablePosition();
                TableView tableView = tablePosition.getTableView();
                User user = (User) tableView.getSelectionModel().getSelectedItem();
                adminDao.updateUser("password",newValue,user.getId());
            }
        });
        department.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
                    @Override
                    public void handle(TableColumn.CellEditEvent event) {
                        Object newValue = event.getNewValue();
                        TablePosition tablePosition = event.getTablePosition();
                        TableView tableView = tablePosition.getTableView();
                        User user = (User) tableView.getSelectionModel().getSelectedItem();
                        adminDao.updateUser("department",newValue,user.getId());
                    }
                });

    }
    
    public void newbroken(ActionEvent actionEvent) throws Exception {
        AddRebelMain main=new AddRebelMain();
        main.start(new Stage());
    }
    /**
     * 这是搜索学生信息的方法
     * @param actionEvent 学生信息
     * */
    public void seachstu(ActionEvent actionEvent) {
        String text = searchcontent1.getText();
        List<User> users = adminDao.getStudentBySid(text);
        ObservableList<User> userdata= FXCollections.observableArrayList(users);
        stutable.setEditable(true);
        sid.setCellValueFactory(new PropertyValueFactory<>("id"));
        classname.setCellValueFactory(new PropertyValueFactory<>("class_name"));
        sname.setCellValueFactory(new PropertyValueFactory<>("name"));
        pass.setCellValueFactory(new PropertyValueFactory<>("password"));
        department.setCellValueFactory(new PropertyValueFactory<>("department"));
        stumemgmtListner();
        stutable.setItems(userdata);
    }


    /**
     * 这是更换管理员密码的方法
     * @param actionEvent 监听对象
     * */
    public void changepass(ActionEvent actionEvent) {
        Admin admin= CommonDao.getAdminFromFile();
        String aoldpassstr=aoldpass.getText();
        String anewpassstr=anewpass.getText();
        String arepassstr=arepass.getText();
        if(!admin.getPassword().equals(aoldpassstr))  {
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("消息");
            alert.headerTextProperty().set("原密码不正确");
            alert.showAndWait();
            return ;
        }
        if(!anewpassstr.equals(arepassstr)){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("消息");
            alert.headerTextProperty().set("两次密码不一致");
            alert.showAndWait();
            return ;
        }
        admin.setPassword(arepassstr);
        adminDao.updatePassword(admin);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("消息");
        alert.headerTextProperty().set("修改成功");
        alert.showAndWait();

    }
    
    
    
    public void addstu(ActionEvent actionEvent) {
        AddStudentMain main=new AddStudentMain() ;
        try {
            main.start(new Stage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadrebelinfo(Event event) {
        List<Broken> brokens = adminDao.getBrokens();
        ObservableList<Broken> data= FXCollections.observableArrayList(brokens);
        tableinfo.setEditable(true);
        rid.setCellValueFactory(new PropertyValueFactory<>("u_num"));
        name.setCellValueFactory(new PropertyValueFactory<>("u_name"));
        icontent.setCellValueFactory(new PropertyValueFactory<>("content"));
        idate.setCellValueFactory(new PropertyValueFactory<>("v_date"));
        setborkenListender();
        tableinfo.setItems(data);
    }

    /**
     * 这是搜索违规记录的方法
     * @param actionEvent 监听对象
     * */
    public void searchbroken(ActionEvent actionEvent) {
        String key=searchcontent.getText();
        List<Broken> brokens = adminDao.getBrokensBySearch(key);
        ObservableList<Broken> data= FXCollections.observableArrayList(brokens);
        tableinfo.setEditable(true);
        rid.setCellValueFactory(new PropertyValueFactory<>("u_num"));
        name.setCellValueFactory(new PropertyValueFactory<>("u_name"));
        icontent.setCellValueFactory(new PropertyValueFactory<>("content"));
        idate.setCellValueFactory(new PropertyValueFactory<>("v_date"));
        setborkenListender();
        tableinfo.setItems(data);
    }

    public void setborkenListender(){
        rid.setCellFactory(TextFieldTableCell.forTableColumn(new IntegerStringConverter()));
        name.setCellFactory(TextFieldTableCell.forTableColumn());
        icontent.setCellFactory(TextFieldTableCell.forTableColumn());
        idate.setCellFactory(TextFieldTableCell.forTableColumn(new DateTimeStringConverter()));
        name.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Object newValue = event.getNewValue();
                TablePosition tablePosition = event.getTablePosition();
                TableView tableView = tablePosition.getTableView();
                Broken selectedItem = (Broken) tableView.getSelectionModel().getSelectedItem();
                adminDao.updateBroken("u_name",newValue,selectedItem.getId());
            }
        });
        idate.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Object newValue = event.getNewValue();
                TablePosition tablePosition = event.getTablePosition();
                TableView tableView = tablePosition.getTableView();
                Broken selectedItem = (Broken) tableView.getSelectionModel().getSelectedItem();
                adminDao.updateBroken("v_date",newValue,selectedItem.getId());
            }
        });
        icontent.setOnEditCommit(new EventHandler<TableColumn.CellEditEvent>() {
            @Override
            public void handle(TableColumn.CellEditEvent event) {
                Object newValue = event.getNewValue();
                TablePosition tablePosition = event.getTablePosition();
                TableView tableView = tablePosition.getTableView();
                Broken selectedItem = (Broken) tableView.getSelectionModel().getSelectedItem();
                adminDao.updateBroken("content",newValue,selectedItem.getId());
            }
        });
    }

    /**
     * 这是增加学生信息方法
     * @param actionEvent 监听对象
     * */
    public void addStudentInfo(ActionEvent actionEvent) {
         String asnostr=asno.getText();
         String anamestr=aname.getText();
         String apassstr=apass.getText();
         String aclassstr=aclass.getText();
         String adepartstr=adepart.getText();

        List<User> studentBySid = adminDao.getStudentBySid(asnostr);
        if(studentBySid.size()>=1){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("消息");
            alert.headerTextProperty().set("学号已存在");
            alert.showAndWait();
            return ;
        }
         try {
             User user=new User();
             user.setPassword(apassstr);
             user.setClass_name(aclassstr);
             user.setDepartment(adepartstr);
             user.setName(anamestr);
             adminDao.addStudent(user);

         }catch(Exception e){
             e.printStackTrace();
           }
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.titleProperty().set("消息");
        alert.headerTextProperty().set("添加成功");
        alert.showAndWait();
        stumanagement(actionEvent);
        Stage window = (Stage) asno.getParent().getScene().getWindow();
        window.close();

    }

    /**
     * 这是删除违规记录的方法
     * @param actionEvent 监听对象
     * */
    public void deleteinfo(ActionEvent actionEvent) {
        Broken selectedItem = (Broken) tableinfo.getSelectionModel().getSelectedItem();
        if(selectedItem==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("消息");
            alert.headerTextProperty().set("请选择一行");
            alert.showAndWait();
        }
        adminDao.deleteBroken(selectedItem);
        loadrebelinfo(actionEvent);
    }

    public void refresh(ActionEvent actionEvent) {
        loadrebelinfo(actionEvent);
    }

    /**
     * 这是删除学生信息方法
     * @param actionEvent 监听对象
     * */
    public void deletestu(ActionEvent actionEvent) {
        User selectedItem = (User)stutable.getSelectionModel().getSelectedItem();
        if(selectedItem==null){
            Alert alert=new Alert(Alert.AlertType.ERROR);
            alert.titleProperty().set("消息");
            alert.headerTextProperty().set("请选择一行");
            alert.showAndWait();
        }
        adminDao.deleteUser(selectedItem);
        stumanagement(actionEvent);
    }
}