module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    
    opens com.example to javafx.fxml, org.hibernate.orm.core;
    exports com.example;
}
