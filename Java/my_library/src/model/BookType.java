package model;

public class BookType {
    private int id;
    private String typeName;
    private int daysAvailable;  //可借天数
    private double finePerDay;

    public int getId() {
        return id;
    }

    public String getTypeName() {
        return typeName;
    }

    public int getDaysAvailable() {
        return daysAvailable;
    }

    public double getFinePerDay() {
        return finePerDay;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public void setDaysAvailable(int daysAvailable) {
        this.daysAvailable = daysAvailable;
    }

    public void setFinePerDay(double finePerDay) {
        this.finePerDay = finePerDay;
    }

    @Override
    public String toString() {
        return getTypeName();
    }
}
