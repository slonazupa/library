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


import java.io.*;
import java.net.URL;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;
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
        String publicationDateString = publicationDateTF.getText();
        int publicationDate = Integer.parseInt(publicationDateString);
        String condition = conditionTF.getText();
        String issueString = issueTF.getText();
        int issue =  Integer.parseInt(issueString);

        //String author, String title, String city, String publisher, String publicationDate, int issueNumber, String condition
        //books.add(new Book(author, title, city, publisher, publicationDate, issue, condition));

        try {

            File file = new File(filePathString);
            file.createNewFile(); // if file already exists will do nothing
            FileOutputStream f = new FileOutputStream(file, true);
            ObjectOutputStream o = new ObjectOutputStream(f);

            Book book = new Book(author, title, city, publisher, publicationDate, issue, condition);
            books.add(book);
            o.writeObject(book);
            o.close();
            f.close();


        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
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

        //Path path = Paths.get(filePathString);
        try {

            FileInputStream fi = new FileInputStream(new File(filePathString));
            //ObjectInputStream oi = new ObjectInputStream(fi);
            ObjectInputStream oi;// = new ObjectInputStream(fi);

            while (fi.available()>0){
                oi = new ObjectInputStream(fi);
                Book book = (Book) oi.readObject();
                books.add(book);
                System.out.println(book);
            }

            //oi.close();
            fi.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}
