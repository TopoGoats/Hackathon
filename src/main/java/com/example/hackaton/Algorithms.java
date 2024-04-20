package com.example.hackaton;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Algorithms {
    public static double calculateSimilarity(Animal idealAnimal, Animal currentAnimal) {
        if (!idealAnimal.fur && currentAnimal.fur) return 0;
        double similarity = 0;
        similarity += Math.pow(idealAnimal.careTimeNeeded - currentAnimal.careTimeNeeded, 2)*0.7;
        similarity += Math.pow(idealAnimal.resourcefulness - currentAnimal.resourcefulness, 2)*0.3;
        similarity += Math.pow(idealAnimal.competentWithAnimals - currentAnimal.competentWithAnimals, 2)*0.5;
        similarity += Math.pow(idealAnimal.impulsiveness - currentAnimal.impulsiveness, 2)*0.5;
        similarity += Math.pow(idealAnimal.income - currentAnimal.income, 2)*0.4;
        similarity += Math.pow(idealAnimal.sex - currentAnimal.sex, 2);
        similarity += Math.pow(idealAnimal.gardenSize - currentAnimal.gardenSize, 2)*0.4;
        similarity += Math.pow(idealAnimal.freeTime - currentAnimal.freeTime, 2)*0.7;
        similarity += Math.pow(idealAnimal.activeLifestyle - currentAnimal.activeLifestyle, 2)*0.8;
        similarity += Math.pow(idealAnimal.livingArea - currentAnimal.livingArea, 2)*0.6;
        similarity += Math.pow(idealAnimal.currentAnimals - currentAnimal.currentAnimals, 2)*0.6;
        similarity += Math.pow(idealAnimal.houseType - currentAnimal.houseType, 2)*0.7;
        similarity += Math.pow(idealAnimal.housemateCount - currentAnimal.housemateCount, 2)*0.5;
        similarity += Math.pow(idealAnimal.qustionareeAge - currentAnimal.qustionareeAge, 2)*0.5;
        similarity += Math.pow(idealAnimal.children - currentAnimal.children, 2)*0.4;
        similarity += Math.pow(idealAnimal.animalsActivity - currentAnimal.animalsActivity, 2)*0.7;
        similarity += 2*calculateTraitsSimilarity(idealAnimal.ownerTraits, currentAnimal.ownerTraits);
        return Math.sqrt(similarity);
    }

    public static int calculateTraitsSimilarity(String idealTraits, String currentTraits) {
        List<String> idealTraitsList = Arrays.asList(idealTraits.split(", "));
        //List<String> currentTraitsList = Arrays.asList(currentTraits.split(", "));
        Set idealSet = new HashSet<>(idealTraitsList);
        //Set currentSet = new HashSet<>(currentTraitsList);
        int idealSetLength = idealSet.size();
        //idealSet.removeAll(currentTraitsList);
        //currentSet.removeAll(idealTraitsList);
        return (idealSet.size()/idealSetLength*5 ) * 2;
    }

}
