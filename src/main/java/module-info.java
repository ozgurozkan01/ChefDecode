module com.example.chefdecode {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chefdecode to javafx.fxml;
    exports com.example.chefdecode;
}