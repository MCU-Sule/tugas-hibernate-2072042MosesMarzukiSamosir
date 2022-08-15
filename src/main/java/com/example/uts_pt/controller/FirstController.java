package com.example.uts_pt.controller;

import com.example.uts_pt.UTSApplication;
import com.example.uts_pt.dao.MovieDao;
import com.example.uts_pt.dao.UserDao;
import com.example.uts_pt.dao.WatchlistDao;
import com.example.uts_pt.model.MovieEntity;
import com.example.uts_pt.model.WatchlistEntity;
import com.example.uts_pt.model.UserEntity;
import com.example.uts_pt.util.MyConnection;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;
import org.hibernate.internal.build.AllowSysOut;

import java.io.IOException;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Pattern;

public class FirstController {
    public TableView table1;
    public TableColumn colTitle;
    public TableColumn colGenre;
    public TableColumn colDurasi;
    public ListView<UserEntity> lvUser;
    public TableColumn<WatchlistEntity, String> colMovTit;
    public TableColumn colLastWatch;
    public TableColumn<WatchlistEntity, String> colFav;
    public TableView table2;
    public Stage stage;
    public ComboBox cmbGenre;
    ObservableList<MovieEntity>moviesList;
    ObservableList<UserEntity>userList;
    ObservableList<WatchlistEntity>watchlists;
    ObservableList genreList;

    public void initialize() throws IOException {
        Layout2();
        MovieDao movieDao = new MovieDao();
        moviesList = FXCollections.observableArrayList(movieDao.getdata());
        table1.setItems(moviesList);
        colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
        colGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        colDurasi.setCellValueFactory(new PropertyValueFactory<>("durasi"));

        UserDao userDao = new UserDao();
        userList = FXCollections.observableArrayList(userDao.getdata());
        lvUser.setItems(userList);

        WatchlistDao watchlistDao = new WatchlistDao();
        watchlists = FXCollections.observableArrayList(watchlistDao.getdata());
        table2.setItems(watchlists);
        colMovTit.setCellValueFactory(data-> new SimpleStringProperty(String.valueOf(data.getValue().getMovieByMovieIdMovie().getTitle())));
        colLastWatch.setCellValueFactory(new PropertyValueFactory<>("LastWatch"));
        colFav.setCellValueFactory(data-> new SimpleStringProperty(data.getValue().fav()));

        genreList = FXCollections.observableArrayList("All","Action","Musical","Comedy","Animated","Fantasy","Drama","Mystery","Thriller","Horror");
        cmbGenre.setItems(genreList);
        cmbGenre.getSelectionModel().select(0);
    }
    public void refreshmovie(){
        MovieDao movieDao = new MovieDao();
        moviesList = FXCollections.observableArrayList(movieDao.getdata());
        table1.setItems(moviesList);
    }

    public void userWachlist(MouseEvent event) {
        int selUser = lvUser.getSelectionModel().getSelectedItem().getIdUser();
        System.out.println(selUser);
        ObservableList filterUser = FXCollections.observableArrayList();
        filterUser = watchlists.filtered(data-> data.getUserByUserIdUser().getIdUser() == selUser);
        ObservableList list = FXCollections.observableArrayList();
        list.addAll(filterUser);
        table2.setItems(list);
    }

    public void changeCombo(ActionEvent event) {
        String value = (String) cmbGenre.getValue();
        if (value == "All"){
            refreshmovie();
        }else {
            ObservableList<MovieEntity> filtermovie = FXCollections.observableArrayList();
            filtermovie = moviesList.filtered(data-> data.getGenre().matches(String.valueOf("(.*)"+cmbGenre.getValue()+"(.*)")));
            ObservableList list = FXCollections.observableArrayList();
            list.addAll(filtermovie);
            table1.setItems(list);
            table1.refresh();
        }
    }

    public void Layout2() throws IOException {
        stage = new Stage();
        FXMLLoader loader =new FXMLLoader(UTSApplication.class.getResource("UTSSecondPage.fxml"));
        Scene scene = new Scene(loader.load());

        stage.initModality(Modality.APPLICATION_MODAL);
        stage.setTitle("Tambah Data Penerbangan");
        stage.setScene(scene);

//        sc = loader.getController();
    }
    public void refreshUser(){
        UserDao userDao = new UserDao();
        userList = FXCollections.observableArrayList(userDao.getdata());
        lvUser.setItems(userList);
    }

    public void AddUserAction(ActionEvent event) {
        stage.showAndWait();
        refreshUser();
    }

    public void DelUserAction(ActionEvent event) {
        UserEntity selDel = lvUser.getSelectionModel().getSelectedItem();
//        System.out.println(lvUser.getSelectionModel().getSelectedItem());
        if (selDel == null){
            Alert alert = new Alert(Alert.AlertType.ERROR,"please select data", ButtonType.OK);
            alert.showAndWait();
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION,"are you sure to delete this data ?");
        Optional<ButtonType> answer = alert.showAndWait();
        if (answer.get() == ButtonType.OK){
            UserDao userDao = new UserDao();
            userDao.deleteData(selDel);
            refreshUser();
        }else {
            Alert cancelled = new Alert(Alert.AlertType.INFORMATION,"deleting cancelled");
            cancelled.showAndWait();
        }
    }

    public void printReport(ActionEvent event) {
        JasperPrint jp;
        Connection conn = MyConnection.getConnection();
        Map param = new HashMap<>();
        try {
            jp = JasperFillManager.fillReport("report/uts report.jasper",param,conn);
            JasperViewer viewer = new JasperViewer(jp,false);
            viewer.setTitle("Laporan Item");
            viewer.setVisible(true);
        } catch (JRException e) {
            throw new RuntimeException(e);
        }
    }
}