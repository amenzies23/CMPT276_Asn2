package com.asn2.demo.models;

import jakarta.persistence.*;

@Entity
@Table(name="Students")
public class Student{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private int weight;
    private int height;
    private String hair_color;
    private String gpa;
    private String major;
    public Student() {
    }
    public Student(String name, String major, String gpa, int height, int weight, String hair_color) {
        this.name = name;
        this.weight = weight;
        this.height = height;
        this.hair_color = hair_color;
        this.gpa = gpa;
        this.major = major;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getWeight() {
        return weight;
    }
    public void setWeight(int weight) {
        this.weight = weight;
    }
    public int getHeight() {
        return height;
    }
    public void setHeight(int height) {
        this.height = height;
    }
    public String getHair_color() {
        return hair_color;
    }
    public void setHair_color(String hair_color) {
        this.hair_color = hair_color;
    }
    public String getGpa() {
        return gpa;
    }
    public void setGpa(String gpa) {
        this.gpa = gpa;
    }
    public String getMajor() {
        return major;
    }
    public void setMajor(String major) {
        this.major = major;
    }
    
}