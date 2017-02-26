package model;

import java.io.Serializable;

/**
 * Created by Marek on 2017-02-21.
 */
public class Book implements Serializable{

    private int id;
    private String author;
    private String title;
    private int publicationDate;
    private String city;
    private String publisher;
    private int issue;
    private String condition;

    public Book(String author, String title, String city, String publisher, String publicationDate, int issueNumber, String condition) {
        this.author = "";
        this.title = "";
        this.city = "";
        this.publisher = "";
        this.publicationDate = 0;
        this.issue = 0;
        this.condition = "";
    }

    public Book(String author, String title, String city, String publisher, int publicationDate, int issueNumber, String condition) {
        this.author = author;
        this.title = title;
        this.city = city;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.issue = issueNumber;
        this.condition = condition;
    }

    public Book(int id, String author, String title, String city, String publisher, int publicationDate, int issue, int condition) {
        this.id = id;
        this.author = author;
        this.title = title;
        this.city = city;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.issue = issue;
        this.condition = Integer.toString(condition);
    }

    public Book(String author, String title, String city, String publisher, int publicationDate, int issue, int condition) {
        this.author = author;
        this.title = title;
        this.city = city;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.issue = issue;
        this.condition = Integer.toString(condition);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(int publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getIssue() {
        return issue;
    }

    public void setIssue(int issue) {
        this.issue = issue;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publicationDate=" + publicationDate +
                ", city='" + city + '\'' +
                ", publisher='" + publisher + '\'' +
                ", issue=" + issue +
                ", condition='" + condition + '\'' +
                '}';
    }
}
