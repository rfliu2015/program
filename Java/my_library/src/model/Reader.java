package model;

import java.util.Date;

public class Reader {
    private String name;
    private String sex;
    private int age;
    private String identityCard;
    private String startDate;
    private String validDate;  //会员证有效日期
    private int maxNum;
    private String telNumber;
    private double keepMoney;  //押金
    private int identityCardType;
    private String occupation;
    private String number;
    private Date loginTime;

    public void setName(String name) {
        this.name = name;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public void setValidDate(String  validDate) {
        this.validDate = validDate;
    }

    public void setMaxNum(int maxNum) {
        this.maxNum = maxNum;
    }

    public void setTelNumber(String telNumber) {
        this.telNumber = telNumber;
    }

    public void setKeepMoney(double keepMoney) {
        this.keepMoney = keepMoney;
    }

    public void setIdentityCardType(int identityCardType) {
        this.identityCardType = identityCardType;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public void setNumber(String ISBN) {
        this.number = ISBN;
    }

    public void setLoginTime(Date loginTime) {
        this.loginTime = loginTime;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getName() {
        return name;
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

    public String  getValidDate() {
        return validDate;
    }

    public int getMaxNum() {
        return maxNum;
    }

    public String getTelNumber() {
        return telNumber;
    }

    public double getKeepMoney() {
        return keepMoney;
    }

    public int getIdentityCardType() {
        return identityCardType;
    }

    public String getOccupation() {
        return occupation;
    }

    public String getNumber() {
        return number;
    }

    public Date getLoginTime() {
        return loginTime;
    }
}
