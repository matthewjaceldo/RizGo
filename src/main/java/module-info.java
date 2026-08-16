module com.rizgo {

    requires javafx.controls;
    requires javafx.fxml;

    requires java.sql;
    requires org.xerial.sqlitejdbc;
    requires org.slf4j;

    opens com.rizgo.controllers to javafx.fxml;

    exports com.rizgo;
    exports com.rizgo.controllers;
}