module ism.banque {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.java;
    requires com.fasterxml.jackson.databind;
    
    opens ism.banque.controllers to javafx.fxml;
    exports ism.banque;
    exports ism.banque.entities;
}
