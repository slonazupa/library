package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    @FXML private TableView <Book> tableView;
    @FXML private TableColumn <Book, String> titleTableColumn;
    @FXML private TableColumn <Book, String> authorTableColumn;
    @FXML private TableColumn <Book, String> isbnTableColumn;
    @FXML private TableColumn <Book, String> publicationDateTableColumn;
    @FXML private TableColumn <Book, Integer> issueNumberTableColumn;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        titleTableColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorTableColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        isbnTableColumn.setCellValueFactory(new PropertyValueFactory<>("isbn"));
        publicationDateTableColumn.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        issueNumberTableColumn.setCellValueFactory(new PropertyValueFactory<>("issueNumber"));
        tableView.setItems(getBooks());
    }

    private ObservableList<Book> getBooks() {
        ObservableList<Book> books = FXCollections.observableArrayList();
        //String title, String author, String isbn, String publicationDate, int issueNumber
        books.add(new Book("Portret szpiega", "Henryk Piecuch", "83-11-07007-5", "1984", 1));
        return books;
    }
}
