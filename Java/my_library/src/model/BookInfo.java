package model;

import dao.Dao;

public class BookInfo {
    private String ISBN;
    private Integer typeId = null;
    private String bookName;
    private BookType bookType = null;
    private String author;
    private String translator;
    private String publisher;
    private String publicationDate;
    private double price;

    public static BookInfo getBookInfo(String isbn) {
        return Dao.selectBookInfo(isbn);
    }

    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public void setTypeId(int typeId) {
        this.typeId = typeId;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTranslator(String translator) {
        this.translator = translator;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public void setBookType(final BookType bookType) {
        this.bookType = bookType;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getISBN() {
        return ISBN;
    }

    public int getTypeId() {
        if (typeId == null) {
            return bookType.getId();
        }
        return typeId;
    }

    public String getBookName() {
        return bookName;
    }

    public String getAuthor() {
        return author;
    }

    public String getTranslator() {
        return translator;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public double getPrice() {
        return price;
    }

    public BookType getBookType() {
        return bookType;
    }
}
