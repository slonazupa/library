package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import model.Book;


import java.io.*;
import java.net.URL;

import java.util.ResourceBundle;

public class Controller implements Initializable{

    public TextField authorTF;
    public TextField titleTF;
    public TextField cityTF;
    public TextField publisherTF;
    public TextField publicationDateTF;
    public TextField issueTF;
    public TextField conditionTF;


    private ObservableList<Book> books = FXCollections.observableArrayList();
    private String filePathString = "database.bin";
    private DBController conn = new DBController();

    @FXML private TableView <Book> tableView;
    @FXML private TableColumn <Book, String> authorTC;
    @FXML private TableColumn <Book, String> titleTC;
    @FXML private TableColumn <Book, String> cityTC;
    @FXML private TableColumn <Book, String> publisherTC;
    @FXML private TableColumn <Book, String> publicationDateTC;
    @FXML private TableColumn <Book, Integer> issueTC;
    @FXML private TableColumn <Book, String> conditionTC;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        authorTC.setCellValueFactory(new PropertyValueFactory<>("author"));
        titleTC.setCellValueFactory(new PropertyValueFactory<>("title"));
        cityTC.setCellValueFactory(new PropertyValueFactory<>("city"));
        publisherTC.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        publicationDateTC.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        issueTC.setCellValueFactory(new PropertyValueFactory<>("issue"));
        conditionTC.setCellValueFactory(new PropertyValueFactory<>("condition"));
        tableView.setItems(getBooks());
        loadData();
    }

    private void loadData() {

        ObservableList<Book> data = FXCollections.observableArrayList(conn.selectBooks());
        tableView.setItems(data);

    }

    private ObservableList<Book> getBooks() {
        return books;
    }

    public void addButtonClicked(MouseEvent mouseEvent) {
        String author = authorTF.getText();
        String title = titleTF.getText();
        String city = cityTF.getText();
        String publisher = publisherTF.getText();
        String publicationDateString = publicationDateTF.getText();
        int publicationDate = Integer.parseInt(publicationDateString);
        String conditionString = conditionTF.getText();
        int condition =  Integer.parseInt(conditionString);
        String issueString = issueTF.getText();
        int issue =  Integer.parseInt(issueString);

        //conn = new DBController();
        conn.insertBook(author, title, city, publisher, publicationDate, issue, condition);
        System.out.println(conn.selectBooks());
        loadData();
        //String author, String title, String city, String publisher, String publicationDate, int issueNumber, String condition
        //books.add(new Book(author, title, city, publisher, publicationDate, issue, condition));



        authorTF.clear();
        titleTF.clear();
        cityTF.clear();
        publisherTF.clear();
        publicationDateTF.clear();
        conditionTF.clear();
        issueTF.clear();
    }

    public void deleteButtonClicked(){
        ObservableList<Book> bookSelected, allBooks;
        allBooks = tableView.getItems();
        bookSelected = tableView.getSelectionModel().getSelectedItems();
        Book book = (Book)tableView.getSelectionModel().getSelectedItems();
        book.getId();

        bookSelected.forEach(getBooks()::remove);


    }
}
