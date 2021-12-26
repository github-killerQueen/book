package com.guo.bean;

public class Student {
    private Integer stuId;
    private String stuName;
    private String gender;
    private String phone;
    private Integer borrowCount;
    private String stuPassword;

    public Student() {}

    public Student(Integer stuId, String stuName, String gender, String phone, Integer borrowCount, String stuPassword) {
        this.stuId = stuId;
        this.stuName = stuName;
        this.gender = gender;
        this.phone = phone;
        this.borrowCount = borrowCount;
        this.stuPassword = stuPassword;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getBorrowCount() {
        return borrowCount;
    }

    public void setBorrowCount(Integer borrowCount) {
        this.borrowCount = borrowCount;
    }

    public String getStuPassword() {
        return stuPassword;
    }

    public void setStuPassword(String stuPassword) {
        this.stuPassword = stuPassword;
    }

    @Override
    public String toString() {
        return "Student{" +
                "stuId='" + stuId + '\'' +
                ", stuName='" + stuName + '\'' +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", borrowCount=" + borrowCount +
                ", stuPassword='" + stuPassword + '\'' +
                '}';
    }
}
