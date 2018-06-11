package model;

public class Operator {
    private int id;
    private int grade;  //是否是admin
    private String name = null;
    private String password;
    private String sex;
    private int age;
    private String identityCard;
    private String workDate;
    private String tel;

    public int getId() {
        return id;
    }

    public int getGrade() {
        return grade;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getSex() {
        return sex;
    }

    public int getAge() {
        return age;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public String getWorkDate() {
        return workDate;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public void setWorkDate(String workDate) {
        this.workDate = workDate;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGrade(int grade) {
        this.grade = grade;
    }

    public void setId(int id) {

        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
