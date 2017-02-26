package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import model.Book;


import java.io.*;
import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable{

    public TextField authorTF;
    public TextField titleTF;
    public TextField cityTF;
    public TextField publisherTF;
    public TextField publicationDateTF;
    public TextField issueTF;
    public TextField conditionTF;

    public TextField idEditTF;
    public TextField authorEditTF;
    public TextField titleEditTF;
    public TextField cityEditTF;
    public TextField publisherEditTF;
    public TextField publicationDateEditTF;
    public TextField issueEditTF;
    public TextField conditionEditTF;
    public TextField searchTF;

    private ObservableList<Book> books = FXCollections.observableArrayList();
    private String filePathString = "database.bin";
    private DBController conn = new DBController();

    @FXML private TableView <Book> tableView;
    @FXML private  TableColumn <Book, Integer> idTC;
    @FXML private TableColumn <Book, String> authorTC;
    @FXML private TableColumn <Book, String> titleTC;
    @FXML private TableColumn <Book, String> cityTC;
    @FXML private TableColumn <Book, String> publisherTC;
    @FXML private TableColumn <Book, String> publicationDateTC;
    @FXML private TableColumn <Book, Integer> issueTC;
    @FXML private TableColumn <Book, String> conditionTC;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idTC.setCellValueFactory(new PropertyValueFactory<>("id"));
        authorTC.setCellValueFactory(new PropertyValueFactory<>("author"));
        titleTC.setCellValueFactory(new PropertyValueFactory<>("title"));
        cityTC.setCellValueFactory(new PropertyValueFactory<>("city"));
        publisherTC.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        publicationDateTC.setCellValueFactory(new PropertyValueFactory<>("publicationDate"));
        issueTC.setCellValueFactory(new PropertyValueFactory<>("issue"));
        conditionTC.setCellValueFactory(new PropertyValueFactory<>("condition"));
        tableView.setItems(getBooks());
        tableView.setRowFactory(tv -> {
            TableRow<Book> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                if (! row.isEmpty() && event.getButton()== MouseButton.PRIMARY
                        && event.getClickCount() == 2) {

                    Book clickedRow = row.getItem();
                    System.out.println(clickedRow);
                    printRow(clickedRow);
                }
            });
            return row ;
        });
        loadData();
    }

    private void printRow(Book book){
        idEditTF.setText(String.valueOf(book.getId()));
        authorEditTF.setText(book.getAuthor());
        titleEditTF.setText(book.getTitle());
        cityEditTF.setText(book.getCity());
        publisherEditTF.setText(book.getPublisher());
        publicationDateEditTF.setText(String.valueOf(book.getPublicationDate()));
        issueEditTF.setText(String.valueOf(book.getIssue()));
        conditionEditTF.setText(book.getCondition());
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

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Potwierdzenie");
        alert.setHeaderText("Potwierdź swój wybór");
        alert.setContentText("Czy na pewno usunąć?");

        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == ButtonType.OK){
            bookSelected = tableView.getSelectionModel().getSelectedItems();
            Book book = bookSelected.get(0);
            conn.deleteBook(book.getId());
            loadData();
        }

        //allBooks = tableView.getItems();
    }

    public void addEditButtonClicked(MouseEvent mouseEvent) {
        String idString = idEditTF.getText();
        int id = Integer.parseInt(idString);
        String author = authorEditTF.getText();
        String title = titleEditTF.getText();
        String city = cityEditTF.getText();
        String publisher = publisherEditTF.getText();
        String publicationDateString = publicationDateEditTF.getText();
        int publicationDate = Integer.parseInt(publicationDateString);
        String conditionString = conditionEditTF.getText();
        int condition =  Integer.parseInt(conditionString);
        String issueString = issueEditTF.getText();
        int issue =  Integer.parseInt(issueString);

        conn.updateBook(id, author, title, city, publisher, publicationDate, condition, issue);
        loadData();
    }

    public void searchButtonClicked(MouseEvent mouseEvent) {
        String searchText = searchTF.getText();

        ObservableList<Book> data = FXCollections.observableArrayList(conn.searchText(searchText));
        tableView.setItems(data);
    }

    public void allBooksButtonClicked(MouseEvent mouseEvent) {
        loadData();
    }
}
