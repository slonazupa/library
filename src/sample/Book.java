package sample;

/**
 * Created by Marek on 2017-02-21.
 */
public class Book {

    private String author;
    private String title;
    private String publicationDate;
    private String city;
    private String publisher;
    private int issue;
    private String condition;

    public Book() {
        this.author = "";
        this.title = "";
        this.city = "";
        this.publisher = "";
        this.publicationDate = "";
        this.issue = 0;
        this.condition = "";
    }

    public Book(String author, String title, String city, String publisher, String publicationDate, int issueNumber, String condition) {
        this.author = author;
        this.title = title;
        this.city = city;
        this.publisher = publisher;
        this.publicationDate = publicationDate;
        this.issue = issueNumber;
        this.condition = condition;
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

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
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

    @Override
    public String toString() {
        return "Book{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", city='" + city + '\'' +
                ", publisher='" + publisher + '\'' +
                ", issueNumber=" + issue +
                ", condition='" + condition + '\'' +
                '}';
    }
}
