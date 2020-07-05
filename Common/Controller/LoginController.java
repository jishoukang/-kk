package com.lib.mangement.Common.Controller;

import com.lib.mangement.lunchs.AdminMain;

import com.lib.mangement.Common.entity.Admin;
import com.lib.mangement.Common.entity.User;
import com.lib.mangement.lunchs.UserMain;
import com.lib.mangement.dao.impl.UserDaoImpl;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;


import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class LoginController {
    @FXML
    private ToggleGroup   ff;

    @FXML
    private TextField name;
    @FXML
    private PasswordField password;

    //登录事件响应
    /**
     * 这是用户登录的方法
     * */
    public void toLogin(){

        Toggle selectedToggle = ff.getSelectedToggle();
        RadioButton button= (RadioButton) selectedToggle;
        String text1 = button.getText();
        UserDaoImpl userDao=new UserDaoImpl();
        User getUserinfo=null;
        Admin getadmin=null;
        if(text1.equals("学生")){
              getUserinfo = userDao.getUserByNaPwdType(name.getText(), password.getText());
            if(getUserinfo==null)
            {
                Alert  alert=new Alert(Alert.AlertType.ERROR);
                alert.titleProperty().set("错误");
                alert.headerTextProperty().set("账号或者密码错误");
                alert.showAndWait();

            }else{
                try {
                    FileOutputStream outputStream=new FileOutputStream(new
                          File("user.txt"));
                    ObjectOutputStream out=new ObjectOutputStream(outputStream);
                    out.writeObject(getUserinfo);
                    out.flush();
                    out.close();
                    outputStream.close();
                    UserMain userm=new UserMain();
                    userm.start(new Stage());

                } catch (Exception e) {
                    e.printStackTrace();
                }


            }
        }else{
              getadmin=userDao.getAdmin(name.getText(), password.getText());
            if(getadmin==null)
            {
                Alert  alert=new Alert(Alert.AlertType.ERROR);
                alert.titleProperty().set("错误");
                alert.headerTextProperty().set("账号或者密码错误");
                alert.showAndWait();
            }else{
                try {
                    FileOutputStream outputStream=new FileOutputStream(new
                          File("admin.txt"));
                    ObjectOutputStream out=new ObjectOutputStream(outputStream);
                    out.writeObject(getadmin);
                    out.flush();
                    out.close();
                    outputStream.close();
                    //打开管理员窗体
                    AdminMain adminMain=new AdminMain() ;
                    adminMain.start(new Stage());

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }

     }

}
