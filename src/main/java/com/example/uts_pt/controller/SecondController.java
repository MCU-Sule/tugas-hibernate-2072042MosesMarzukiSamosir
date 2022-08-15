package com.example.uts_pt.controller;

import com.example.uts_pt.dao.UserDao;
import com.example.uts_pt.model.UserEntity;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;

public class SecondController {
    public TextField txtUserName;
    public PasswordField txtPassword;
    public Button btnSubmit;

    ObservableList<UserEntity>userObservableList;

    public void clear (){
        txtUserName.clear();
        txtPassword.clear();
    }
    public void submit(ActionEvent event) {
        UserDao userDao = new UserDao();
        userObservableList = FXCollections.observableArrayList(userDao.getdata());
        if (txtUserName.getText().isEmpty()|| txtPassword.getText().isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR, "please fill in all the field", ButtonType.OK);
            alert.showAndWait();
        }else {
            UserEntity user = new UserEntity();
            user.setUserName(txtUserName.getText());
            user.setUserPassword(txtPassword.getText());
            userDao.addData(user);
            userObservableList = FXCollections.observableArrayList(userDao.getdata());
            clear();
            btnSubmit.getScene().getWindow().hide();
        }
    }
}
