package com.example.hackaton;


public class Animal {
    // Meta
    String name;
    String species;
    int age;
    String pathToImage;
    boolean fur;

    // Sliders
    int caretTimeNeeded;
    int resourcefulness;
    int competentWithAnimals;
    int impulsiveness;
    int income;
    int dedication;
    int gardenSize;

    // Single choice
    int freeTime;
    int activeLifestyle;
    int livingArea;
    int currentAnimals;
    int houseType;
    int housemateCount;
    int qustionareeAge;
    int children;
    int animalsActivity;

    // Traits
    int responsibility;
    int forgetfulness;
    int shortLivedEnthusiasm;
    int determined;
    int optimistic;

    public Animal(boolean fur, String name, String species, int age, String pathToImage, int caretTimeNeeded, int resourcefulness, int competentWithAnimals, int impulsiveness, int income, int dedication, int gardenSize, int freeTime, int activeLifestyle, int livingArea, int currentAnimals, int houseType, int housemateCount, int qustionareeAge, int children, int animalsActivity, int responsibility, int forgetfulness, int shortLivedEnthusiasm, int determined, int optimistic) {
        this.fur = fur;
        this.name = name;
        this.species = species;
        this.age = age;
        this.pathToImage = pathToImage;
        this.caretTimeNeeded = caretTimeNeeded;
        this.resourcefulness = resourcefulness;
        this.competentWithAnimals = competentWithAnimals;
        this.impulsiveness = impulsiveness;
        this.income = income;
        this.dedication = dedication;
        this.gardenSize = gardenSize;
        this.freeTime = freeTime;
        this.activeLifestyle = activeLifestyle;
        this.livingArea = livingArea;
        this.currentAnimals = currentAnimals;
        this.houseType = houseType;
        this.housemateCount = housemateCount;
        this.qustionareeAge = qustionareeAge;
        this.children = children;
        this.animalsActivity = animalsActivity;
        this.responsibility = responsibility;
        this.forgetfulness = forgetfulness;
        this.shortLivedEnthusiasm = shortLivedEnthusiasm;
        this.determined = determined;
        this.optimistic = optimistic;
    }
}
