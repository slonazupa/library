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


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
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
    private String filePathString = "database.txt";

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
        openFile();
    }

    private ObservableList<Book> getBooks() {
        return books;
    }

    private void addBook(Book book){
        books.add(book);
    }

    public void addButtonClicked(MouseEvent mouseEvent) {
        String author = authorTF.getText();
        String title = titleTF.getText();
        String city = cityTF.getText();
        String publisher = publisherTF.getText();
        String publicationDate = publicationDateTF.getText();
        String condition = conditionTF.getText();
        String issueString = issueTF.getText();
        int issue =  Integer.parseInt(issueString);

        //String author, String title, String city, String publisher, String publicationDate, int issueNumber, String condition
        books.add(new Book(author, title, city, publisher, publicationDate, issue, condition));
        Path path = Paths.get(filePathString);

        //new line
        String newLine = System.lineSeparator();

        try {
            Files.write(path, books.get(books.size()-1).toString().getBytes(), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
            Files.write(path, newLine.getBytes(),StandardOpenOption.APPEND);
        } catch (IOException e) {
            e.printStackTrace();
        }


        authorTF.clear();
        titleTF.clear();
        cityTF.clear();
        publisherTF.clear();
        publicationDateTF.clear();
        conditionTF.clear();
        issueTF.clear();
    }

    //opens the file where you can save information about books. If the file does not exist create it
    private void openFile(){

        Path path = Paths.get(filePathString);
        try (InputStream in = Files.newInputStream(path);
             BufferedReader reader =
                     new BufferedReader(new InputStreamReader(in))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException x) {
            System.err.println(x);
        }
    }
}
