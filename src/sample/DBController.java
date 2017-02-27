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
        String createBook = "CREATE TABLE IF NOT EXISTS book (id_book INTEGER PRIMARY KEY AUTOINCREMENT, author varchar(255), title varchar(255), city varchar(255), publisher varchar(255), publicationDate int, issue int, condition int, sqltime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL)";
        try{
            stat.execute(createBook);
            System.out.println("Stworzono tabele");
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean insertBook(String author, String title, String city, String publisher, int publicationDate, int issue, int condition){
        try {
            //(author, title, city, publisher, publicationDate, issue, condition);
            PreparedStatement prepStmt = conn.prepareStatement("insert into book (id_book, author, title, city, publisher, publicationDate, issue, condition) values (NULL, ?, ?, ?, ?, ?, ?, ?);");
            prepStmt.setString(1, author);
            prepStmt.setString(2, title);
            prepStmt.setString(3, city);
            prepStmt.setString(4, publisher);
            prepStmt.setString(5, Integer.toString(publicationDate));
            prepStmt.setString(6, Integer.toString(issue));
            prepStmt.setString(7, Integer.toString(condition));
            prepStmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            System.err.println("Blad przy wstawianiu ksiazki");
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

    public boolean deleteBook(int id){
        try{
            PreparedStatement prepStmt = conn.prepareStatement("DELETE FROM book WHERE id_book=?");
            prepStmt.setString(1, String.valueOf(id));
            prepStmt.execute();
        }catch (SQLException e){
            System.err.println("Blad przy usuwaniu ksiazki");
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<Book> selectBooks() {
        List<Book> books = new ArrayList<>();
        try {
            //(author, title, city, publisher, publicationDate, issue, condition);

            ResultSet result = stat.executeQuery("SELECT * FROM book");
            int id, publicationDate, issue, condition;
            String author, title, city, publisher, addTime;
            while(result.next()) {
                id = result.getInt("id_book");
                title = result.getString("title");
                author = result.getString("author");
                city = result.getString("city");
                publisher = result.getString("publisher");
                publicationDate = result.getInt("publicationDate");
                issue = result.getInt("issue");
                condition = result.getInt("condition");
                addTime = result.getString("sqltime");

                books.add(new Book(id, author, title, city, publisher, publicationDate, issue, condition, addTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return books;
    }

    public boolean updateBook(int id, String author, String title, String city, String publisher, int publicationDate, int condition, int issue) {
        try {
            //(author, title, city, publisher, publicationDate, issue, condition);
            PreparedStatement prepStmt = conn.prepareStatement("UPDATE book set author = ?, title = ?, city = ?,publisher = ?, publicationDate = ?, issue = ?, condition = ? WHERE id_book = ?;");
            prepStmt.setString(1, author);
            prepStmt.setString(2, title);
            prepStmt.setString(3, city);
            prepStmt.setString(4, publisher);
            prepStmt.setString(5, Integer.toString(publicationDate));
            prepStmt.setString(6, Integer.toString(issue));
            prepStmt.setString(7, Integer.toString(condition));
            prepStmt.setString(8, Integer.toString(id));
            prepStmt.execute();
        } catch (SQLException e) {
            System.err.println("Blad przy modyfikacji ksiazki");
            e.printStackTrace();
            return false;
        }

        return true;
    }

    public List<Book> searchText(String searchText) {
        List<Book> books = new ArrayList<>();
        try {
            //(author, title, city, publisher, publicationDate, issue, condition);
            //PreparedStatement prepStmt = conn.prepareStatement("SELECT * FROM book WHERE ? IN (author, title, city, publisher, publicationDate, issue, condition)");
            //prepStmt.setString(1, searchText);
            //ResultSet result = null;
            //result.execute(prepStmt);
            //ResultSet result = stat.executeQuery("SELECT * FROM book WHERE ? IN (author, title, city, publisher, publicationDate, issue, condition)");

            String sql;
            //sql = "SELECT * FROM book WHERE '" + searchText + "' IN (author, title, city, publisher, publicationDate, issue, condition)";
            sql = "SELECT  * FROM book WHERE author LIKE '%" + searchText + "%' or title LIKE '%" + searchText + "%' or city LIKE '%" + searchText + "%' or publisher LIKE '%" + searchText + "%' or publicationDate LIKE '%" + searchText + "%'or issue LIKE '%" + searchText + "%'or condition LIKE '%" + searchText + "%'";
            //System.out.println(sql);
            ResultSet result = stat.executeQuery(sql);
            int id, publicationDate, issue, condition;
            String author, title, city, publisher, addTime;
            while(result.next()) {
                id = result.getInt("id_book");
                title = result.getString("title");
                author = result.getString("author");
                city = result.getString("city");
                publisher = result.getString("publisher");
                publicationDate = result.getInt("publicationDate");
                issue = result.getInt("issue");
                condition = result.getInt("condition");
                addTime = result.getString("sqltime");

                books.add(new Book(id, author, title, city, publisher, publicationDate, issue, condition, addTime));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return books;
    }

    public boolean addTable(){
        String createBook = "ALTER TABLE book ADD COLUMN sqltime TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL";
        try{
            stat.execute(createBook);
            System.out.println("Dodano tabele");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
