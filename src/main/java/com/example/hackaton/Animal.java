package com.example.hackaton;


public class Animal {
    // Meta
    String name;
    String species;
    int age;
    String pathToImage;
    boolean fur;

    // Sliders
    int careTimeNeeded;
    int resourcefulness;
    int competentWithAnimals;
    int impulsiveness;
    int income;
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
    int sex;

    // Traits
    String ownerTraits;

    public Animal(boolean fur, String name, String species, int age, String pathToImage, int careTimeNeeded, int resourcefulness, int competentWithAnimals, int impulsiveness, int income, int gardenSize, int freeTime, int activeLifestyle, int livingArea, int currentAnimals, int houseType, int housemateCount, int qustionareeAge, int children, int animalsActivity, int sex, String ownerTraits) {
        this.fur = fur;
        this.name = name;
        this.species = species;
        this.age = age;
        this.pathToImage = pathToImage;
        this.careTimeNeeded = careTimeNeeded;
        this.resourcefulness = resourcefulness;
        this.competentWithAnimals = competentWithAnimals;
        this.impulsiveness = impulsiveness;
        this.income = income;
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
        this.sex = sex; //sex.
        this.ownerTraits = ownerTraits;
    }

    @Override
    public String toString() {
        return "Animal{" +
                "name='" + name + '\'' +
                ", species='" + species + '\'' +
                ", age=" + age +
                ", pathToImage='" + pathToImage + '\'' +
                ", fur=" + fur +
                ", careTimeNeeded=" + careTimeNeeded +
                ", resourcefulness=" + resourcefulness +
                ", competentWithAnimals=" + competentWithAnimals +
                ", impulsiveness=" + impulsiveness +
                ", income=" + income +
                ", gardenSize=" + gardenSize +
                ", freeTime=" + freeTime +
                ", activeLifestyle=" + activeLifestyle +
                ", livingArea=" + livingArea +
                ", currentAnimals=" + currentAnimals +
                ", houseType=" + houseType +
                ", housemateCount=" + housemateCount +
                ", qustionareeAge=" + qustionareeAge +
                ", children=" + children +
                ", animalsActivity=" + animalsActivity +
                ", sex=" + sex +
                ", ownerTraits=" + ownerTraits   +
                '}';
    }
}
