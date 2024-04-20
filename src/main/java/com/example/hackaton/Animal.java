package com.example.hackaton;


public class Animal {
    // Meta
    String name;
    String species;
    int age;
    String pathToImage;
    boolean fur;

    int sex;

    // Sliders
    int housemateCount;
    int qustionareeAge;
    int currentAnimals;

    int children;

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
    int houseType;
    int animalsActivity;

    // Traits
    String ownerTraits;

    public Animal(boolean fur, String name, String species, int age,int sex, String pathToImage, int housemateCount, int qustionareeAge, int currentAnimals,int children, int careTimeNeeded, int resourcefulness, int competentWithAnimals, int impulsiveness, int income, int gardenSize, int freeTime, int activeLifestyle, int livingArea,  int houseType, int animalsActivity, String ownerTraits) {
        this.housemateCount = housemateCount;
        this.qustionareeAge = qustionareeAge;
        this.children = children;
        this.currentAnimals = currentAnimals;
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
        this.houseType = houseType;
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

    public int getSex() {
        return sex;
    }
}
