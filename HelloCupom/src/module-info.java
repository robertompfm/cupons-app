module HelloCupom {

    requires javafx.fxml;
    requires javafx.controls;
    requires javafx.graphics;
    requires junit;
    requires java.sql;
    requires java.base;
    requires java.xml;
    requires fontawesomefx;


    opens com.robertompfm;
    opens com.robertompfm.control;
    opens com.robertompfm.dao;
    opens com.robertompfm.model;
    opens com.robertompfm.tests;
    opens com.robertompfm.view;

}