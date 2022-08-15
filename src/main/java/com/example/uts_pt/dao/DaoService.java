package com.example.uts_pt.dao;

import java.util.List;

public interface DaoService<T> {
    List<T>getdata();
    void addData(T data);
    void deleteData(T data);
    void updateData(T data);

}
