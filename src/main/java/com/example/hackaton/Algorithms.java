package com.example.hackaton;

public class Algorithms {
    public static double calculateSimilarity(Animal idealAnimal, Animal currentAnimal) {
        if (!idealAnimal.fur && currentAnimal.fur) return 0;
        double similarity = 0;
        similarity += 1*Math.pow(idealAnimal.caretTimeNeeded - currentAnimal.caretTimeNeeded, 2);
        similarity += 1*Math.pow(idealAnimal.resourcefulness - currentAnimal.resourcefulness, 2);
        similarity += 1*Math.pow(idealAnimal.competentWithAnimals - currentAnimal.competentWithAnimals, 2);
        similarity += 1*Math.pow(idealAnimal.impulsiveness - currentAnimal.impulsiveness, 2);
        similarity += 1*Math.pow(idealAnimal.income - currentAnimal.income, 2);
        similarity += 1*Math.pow(idealAnimal.dedication - currentAnimal.dedication, 2);
        similarity += 1*Math.pow(idealAnimal.gardenSize - currentAnimal.gardenSize, 2);
        similarity += 1*Math.pow(idealAnimal.freeTime - currentAnimal.freeTime, 2);
        similarity += 1*Math.pow(idealAnimal.activeLifestyle - currentAnimal.activeLifestyle, 2);
        similarity += 1*Math.pow(idealAnimal.livingArea - currentAnimal.livingArea, 2);
        similarity += 1*Math.pow(idealAnimal.currentAnimals - currentAnimal.currentAnimals, 2);
        similarity += 1*Math.pow(idealAnimal.houseType - currentAnimal.houseType, 2);
        similarity += 1*Math.pow(idealAnimal.housemateCount - currentAnimal.housemateCount, 2);
        similarity += 1*Math.pow(idealAnimal.qustionareeAge - currentAnimal.qustionareeAge, 2);
        similarity += 1*Math.pow(idealAnimal.children - currentAnimal.children, 2);
        similarity += 1*Math.pow(idealAnimal.animalsActivity - currentAnimal.animalsActivity, 2);
        similarity += 1*Math.pow(idealAnimal.responsibility - currentAnimal.responsibility, 2);
        similarity += 1*Math.pow(idealAnimal.forgetfulness - currentAnimal.forgetfulness, 2);
        similarity += 1*Math.pow(idealAnimal.shortLivedEnthusiasm - currentAnimal.shortLivedEnthusiasm, 2);
        similarity += 1*Math.pow(idealAnimal.determined - currentAnimal.determined, 2);
        similarity += 1*Math.pow(idealAnimal.optimistic - currentAnimal.optimistic, 2);

        return Math.sqrt(similarity);
    }
}
