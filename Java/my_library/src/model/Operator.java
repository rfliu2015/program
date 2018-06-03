package model;

public class Operator {
    private String id = null;
    private String grade;
    private String name = null;
    private String password;

    public String getId() {
        return id;
    }

    public String getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setId(String id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
