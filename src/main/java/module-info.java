module com.example.uts_pt {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires jasperreports;
    requires org.hibernate.orm.core;
    requires java.naming;
    requires java.prefs;
    requires java.persistence;

    opens com.example.uts_pt.model;
    opens com.example.uts_pt to javafx.fxml;
    exports com.example.uts_pt;
    exports com.example.uts_pt.controller;
    exports com.example.uts_pt.model;

}