module com.example {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires jakarta.persistence;
    requires java.sql;
    requires org.hibernate.orm.core;
    requires javafx.base;
    
    opens com.example to javafx.fxml, javafx.graphics, org.hibernate.orm.core;
    exports com.example;
}
