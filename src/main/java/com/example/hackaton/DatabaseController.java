package com.example.hackaton;

import java.sql.*;
import java.util.*;
import java.util.stream.Collectors;

public class DatabaseController {
    static Connection connection;
    public static void connectToDatabase() {
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection("jdbc:sqlite:application.db");
        } catch ( Exception e ) {
            System.err.println( e.getClass().getName() + ": " + e.getMessage() );
            System.exit(0);
        }
    }

    public static Map<Animal, Double> getMatchingAnimals(Animal idealAnimal) {
        Map<Animal, Double> animals = new HashMap<>();
        String sql = "SELECT * FROM animals";

        try (
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql)){

            // Loop through the item names in inventory, checking if any matches
            int index = 0;
            while (result.next()) {
                Animal animal = new Animal(
                        result.getBoolean("fur"),
                        result.getString("name"),
                        result.getString("species"),
                        result.getInt("age"),
                        result.getInt("sex"),
                        result.getString("pathToImage"),
                        result.getInt("housemateCount"),
                        result.getInt("qustionareeAge"),
                        result.getInt("currentAnimals"),
                        result.getInt("children"),
                        result.getInt("careTimeNeeded"),
                        result.getInt("resourcefulness"),
                        result.getInt("competentWithAnimals"),
                        result.getInt("impulsiveness"),
                        result.getInt("income"),
                        result.getInt("gardenSize"),
                        result.getInt("freeTime"),
                        result.getInt("activeLifestyle"),
                        result.getInt("livingArea"),
                        result.getInt("houseType"),
                        result.getInt("animalsActivity"),
                        result.getString("ownerTraits")
                );

                double similarity = Algorithms.calculateSimilarity(idealAnimal, animal);
                boolean isPreferredSex;
                if (idealAnimal.getSex() > 4) {
                    isPreferredSex = true;
                } else {
                    isPreferredSex = idealAnimal.getSex() == animal.getSex() ||(idealAnimal.getSex() == 3 && animal.getSex() == 1);
                }
                boolean isAllergyCompatible = idealAnimal.fur || !animal.fur;


                isAllergyCompatible = true;
                isPreferredSex = true;

                System.out.println((similarity < 17000.5) + " " + !HelloApplication.isBlackListed.get(index));
                if (similarity < 17000.5 && isPreferredSex && isAllergyCompatible && !HelloApplication.isBlackListed.get(index)) {
                    animals.put(animal, similarity);
                }
                index++;
            }
            double min=1000;
            double max=0;
            for(double num: animals.values()){
                if(num<min){
                    min = num;
                }
                if(num>max){
                    max = num;
                }
            }
            double finalMin = min;
            double finalMax = max;
            //animals.values().removeIf(v -> v>((finalMin + finalMax)/2));

            return animals.entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue())
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            Map.Entry::getValue,
                            (oldValue, _) -> oldValue,
                            LinkedHashMap::new
                    )).reversed();

        } catch (SQLException e) {
            System.out.println(e.getMessage() +  e.getCause()+" XDDD");
        }

        return animals;
    }
}