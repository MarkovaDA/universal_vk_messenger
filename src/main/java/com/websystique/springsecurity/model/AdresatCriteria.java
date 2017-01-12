package com.websystique.springsecurity.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;


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
        Map<String,String> params = new HashMap<>();
        params.put("university", Integer.toString(university));
        params.put("university_faculty", Integer.toString(university_faculty));
        params.put("university_year", Integer.toString(university_year));
        params.put("age_from", Integer.toString(age_from));
        params.put("age_to", Integer.toString(age_to));
        params.put("position", position);
        String criteria = "";
        Iterator iterator = params.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry pair = (Map.Entry)iterator.next();
            if (pair.getValue() != null) //не учитываем неустановленные параметры
            criteria+=pair.getKey() + " = " + pair.getValue();
        }
        return criteria;
        //return  "university=" + university + "&university_faculty=" + university_faculty + "&university_year=" + university_year + "&age_from=" + age_from + "&age_to=" + age_to + "&position=" + position;
    }
    
    
}
