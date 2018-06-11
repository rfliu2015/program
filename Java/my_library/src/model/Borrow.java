package model;

public class Borrow {
    private int id;  //借阅编号
    private Operator operator;
    private Reader reader;
    private String borrowDate;
    private String returnDate;
    private BookInfo bookInfo;
    private int num;  //借书数量

    public int getId() {
        return id;
    }

    public Operator getOperator() {
        return operator;
    }

    public Reader getReader() {
        return reader;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public BookInfo getBookInfo() {
        return bookInfo;
    }

    public int getNum() {
        return num;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public void setReader(Reader reader) {
        this.reader = reader;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setBookInfo(BookInfo bookInfo) {
        this.bookInfo = bookInfo;
    }

    public void setNum(int num) {
        this.num = num;
    }
}
