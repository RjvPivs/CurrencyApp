package ru.Savenko.view;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.Savenko.database.Database;

import java.io.IOException;

public class CurrencyApp extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(CurrencyApp.class.getResource("currency-app.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 750, 500);
        stage.setTitle("Currency app");
        stage.setScene(scene);
        st = stage;
        stage.show();
        log.debug("App started.");
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                log.info("Connection closed.");
                Database.getInstance().closeConnection();
                log.info("App closed.");
            }
        });
    }

    private static final Logger log = LoggerFactory.getLogger(CurrencyApp.class);

    public static Stage getSt() {
        return st;
    }

    private static Stage st;

    public static void main(String[] args) {
        launch();
    }
}