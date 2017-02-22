package sample;

/**
 * Created by Marek on 2017-02-21.
 */
public class Book {

    private String title;
    private String author;
    private String isbn;
    private String publicationDate;
    private int issueNumber;

    public Book() {
        this.title = "";
        this.author = "";
        this.isbn = "";
        this.publicationDate = "";
        this.issueNumber = 0;
    }

    public Book(String title, String author, String isbn, String publicationDate, int issueNumber) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.publicationDate = publicationDate;
        this.issueNumber = issueNumber;
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

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public int getIssueNumber() {
        return issueNumber;
    }

    public void setIssueNumber(int issueNumber) {
        this.issueNumber = issueNumber;
    }


    @Override
    public String toString() {
        return "Book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", isbn='" + isbn + '\'' +
                ", publicationDate='" + publicationDate + '\'' +
                ", issueNumber=" + issueNumber +
                '}';
    }
}
