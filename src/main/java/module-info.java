module com.example.chefdecode {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires com.microsoft.sqlserver.jdbc;


    opens com.example.chefdecode to javafx.fxml;
    exports com.example.chefdecode;
}