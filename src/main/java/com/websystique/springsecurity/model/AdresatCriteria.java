package com.websystique.springsecurity.model;


public class AdresatCriteria {
    private int university;
    private int university_faculty;
    private int university_year;
    private int age_from;
    private int age_to;
    private String position; //должность
    private String message; //сообщение

    public int getUniversity() {
        return university;
    }

    public void setUniversity(int university) {
        this.university = university;
    }

    public int getUniversity_faculty() {
        return university_faculty;
    }

    public void setUniversity_faculty(int university_faculty) {
        this.university_faculty = university_faculty;
    }

    public int getUniversity_year() {
        return university_year;
    }

    public void setUniversity_year(int university_year) {
        this.university_year = university_year;
    }

    public int getAge_from() {
        return age_from;
    }

    public void setAge_from(int age_from) {
        this.age_from = age_from;
    }

    public int getAge_to() {
        return age_to;
    }

    public void setAge_to(int age_to) {
        this.age_to = age_to;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return  "university=" + university + "&university_faculty=" + university_faculty + "&university_year=" + university_year + "&age_from=" + age_from + "&age_to=" + age_to + "&position=" + position;
    }
    
    
}
