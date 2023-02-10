package ru.Savenko.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Database {
    private static final Logger log = LoggerFactory.getLogger(Database.class);

    private static final String create = """
            CREATE TABLE IF NOT EXISTS currency (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                "value" REAL NOT NULL,
                nominal INTEGER NOT NULL,
                currency_name VARCHAR(100) NOT NULL,
                currency_code VARCHAR(3) NOT NULL,
                "date" DATE NOT NULL
            );""";

    private Database() {

    }

    public static Database getInstance() {
        if (instance == null) {
            log.debug("Initializing db.");
            instance = new Database();
        }
        log.debug("The instance was returned.");
        return instance;
    }

    private static final String url = "jdbc:sqlite:db/currency.db";
    private static Database instance;
    private Connection connection;

    public Connection getConnection() {
        log.info("Starting the connection.");
        if (connection == null) {
            try {
                prepareDirectory();
                connection = DriverManager.getConnection(url);
                connection.setAutoCommit(true);
                initDb();
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        log.info("Connection granted.");
        return connection;
    }

    private void prepareDirectory() {
        try {
            if (!Files.exists(Paths.get("db"))) {
                Files.createDirectory(Paths.get("db"));
                log.error("Cannot create application database directory.");
            }
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    private void initDb() {
        log.info("Starting the initialization.");
        try {
            PreparedStatement st = connection.prepareStatement(create);
            st.execute();
        } catch (Exception e) {
            log.error("Initialization unsuccessful.");
            throw new RuntimeException();
        }
        log.info("Db was initialized.");
    }

    public boolean closeConnection() {
        log.info("Closing started.");
        try {
            if (connection != null) {
                connection.close();
                connection = null;
                return true;
            }
        } catch (Exception e) {
            log.error("Closing error.");
            throw new RuntimeException();
        }
        log.info("Closing finished.");
        return false;
    }
}
