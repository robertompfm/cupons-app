module HelloCupom {

    requires javafx.fxml;
    requires javafx.controls;
    requires junit;
    requires java.sql;



    opens com.robertompfm.control;
    opens com.robertompfm.dao;
    opens com.robertompfm.model;
    opens com.robertompfm.tests;
    opens com.robertompfm.view;

}