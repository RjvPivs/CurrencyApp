module com.example.javathesecondtask {
    requires java.sql;
    requires commons.csv;
    requires javafx.base;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.controls;
    requires retrofit;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;
    requires org.slf4j;

    opens ru.Savenko.view to javafx.fxml;
    opens ru.Savenko.DTO to com.fasterxml.jackson.databind;
    opens ru.Savenko.utils to com.fasterxml.jackson.databind;
    opens ru.Savenko.model to javafx.base;
    exports ru.Savenko.model to com.fasterxml.jackson.databind;
    exports ru.Savenko.view;
    opens ru.Savenko.cbr to com.fasterxml.jackson.databind;
}