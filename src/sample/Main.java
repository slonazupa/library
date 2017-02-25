package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Aukcjoner");
        primaryStage.setFullScreen(true);
        primaryStage.setScene(new Scene(root, 1000, 600));
        primaryStage.show();
    }


    public static void main(String[] args) {
        //String dbName = "libdb";
        //Connection connect = connect(dbName);
        DBController conn = new DBController();
        conn.createTables();
        conn.insertBook();
        System.out.println(conn.selectBooks());
        //conn.closeConnection();
        launch(args);
    }
}
