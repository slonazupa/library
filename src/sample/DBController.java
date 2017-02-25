package sample;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Marek on 2017-02-25.
 */
public class DBController {

    private static final String DB_URL = "jdbc:sqlite:libdb.db";
    private static final String DRIVER = "org.sqlite.JDBC";

    private Connection conn;
    private Statement stat;

    public DBController(){
        try {
            Class.forName(DBController.DRIVER);
        } catch (ClassNotFoundException e) {
            System.err.println("Brak sterownika JDBC");
            e.printStackTrace();
        }

        try {
            conn = DriverManager.getConnection(DB_URL);
            stat = conn.createStatement();
        } catch (SQLException e) {
            System.err.println("Problem z otwarciem polaczenia");
            e.printStackTrace();
        }
    }

    public boolean createTables(){
        //(id_czytelnika INTEGER PRIMARY KEY AUTOINCREMENT, imie varchar(255), nazwisko varchar(255), pesel int)";
        //Book(author, title, city, publisher, publicationDate, issue, condition);
        String createBook = "CREATE TABLE IF NOT EXISTS book (id_book INTEGER PRIMARY KEY AUTOINCREMENT, author varchar(255), title varchar(255), city varchar(255), publisher varchar(255), publicationDate int, issue int, condition int)";
        try{
            stat.execute(createBook);
            System.out.println("Stworzono tabele");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertBook(){
        try {
            //(author, title, city, publisher, publicationDate, issue, condition);
            PreparedStatement prepStmt = conn.prepareStatement("insert into book values (NULL, ?, ?, ?, ?, ?, ?, ?);");
            prepStmt.setString(1, "test");
            prepStmt.setString(2, "test");
            prepStmt.setString(3, "test");
            prepStmt.setString(4, "test");
            prepStmt.setString(5, "1");
            prepStmt.setString(6, "1");
            prepStmt.setString(7, "1");
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy wstawianiu czytelnika");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException e) {
            System.err.println("Problem z zamknieciem polaczenia");
            e.printStackTrace();
        }
    }


    public List<Book> selectBooks() {
        List<Book> books = new ArrayList<>();
        try {
            //(author, title, city, publisher, publicationDate, issue, condition);
            ResultSet result = stat.executeQuery("SELECT * FROM book");
            int id, publicationDate, issue, condition;
            String author, title, city, publisher;
            while(result.next()) {
                id = result.getInt("id_book");
                title = result.getString("title");
                author = result.getString("author");
                city = result.getString("city");
                publisher = result.getString("publisher");
                publicationDate = result.getInt("publicationDate");
                issue = result.getInt("issue");
                condition = result.getInt("condition");

                books.add(new Book(author, title, city, publisher, publicationDate, issue, condition));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return books;
    }
}
