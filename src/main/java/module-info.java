module com.self.management.self_management {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires mysql.connector.j;
    requires javafx.web;


    opens com.self.management.self_management to javafx.fxml;
    exports com.self.management.self_management;
    exports com.self.management.self_management.Controller;
    opens com.self.management.self_management.Controller to javafx.fxml;
    exports com.self.management.self_management.Preloader;
    opens com.self.management.self_management.Preloader to javafx.fxml;
}