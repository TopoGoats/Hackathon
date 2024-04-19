package com.example.hackaton;

import javafx.scene.image.Image;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Animal {
    private Image image;

    private String name;
    private int age;
    private String breed;
    private String sex;
    private String desc;

    public Animal(String name, int age, String breed, String sex, String desc) {
        try {
            this.image = new Image(new FileInputStream("img.png"));
        } catch (FileNotFoundException e) {
            System.out.println("fuck");
            throw new RuntimeException(e);
        }
        this.name = name;
        this.age = age;
        this.breed = breed;
        this.sex = sex;
        this.desc = desc;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
