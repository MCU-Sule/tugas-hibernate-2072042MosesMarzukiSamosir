package com.example.uts_pt.dao;

import javafx.collections.ObservableList;

public interface DaoInterface<T> {
    ObservableList<T> getData();
    void addData(T data);
    boolean delData(T data);
    int updateData(T data);

}
