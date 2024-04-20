package com.example.hackaton;

import java.sql.*;
import java.util.*;
import java.util.Map.Entry;
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
        Map<Animal, Double> animals = new HashMap();
        String sql = "SELECT * FROM animals";

        try (
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql)){

            // Loop through the item names in inventory, checking if any matches
            while (result.next()) {
                Animal animal = new Animal(
                        result.getBoolean("fur"),
                        result.getString("name"),
                        result.getString("species"),
                        result.getInt("age"),
                        result.getString("pathToImage"),
                        result.getInt("caretTimeNeeded"),
                        result.getInt("resourcefulness"),
                        result.getInt("competentWithAnimals"),
                        result.getInt("impulsiveness"),
                        result.getInt("income"),
                        result.getInt("gardenSize"),
                        result.getInt("freeTime"),
                        result.getInt("activeLifestyle"),
                        result.getInt("livingArea"),
                        result.getInt("currentAnimals"),
                        result.getInt("houseType"),
                        result.getInt("housemateCount"),
                        result.getInt("qustionareeAge"),
                        result.getInt("children"),
                        result.getInt("animalsActivity"),
                        result.getString("ownerTraits")
                );

                double similarity = Algorithms.calculateSimilarity(idealAnimal, animal);
                if (similarity <1000000) {
                    animals.put(animal, similarity);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(animals.toString());
        return animals;
    }
    public static Map<Animal, Double> sortByValue(Map<Animal, Double> hm)
    {
        // Create a list from elements of HashMap
        List<Map.Entry<Animal, Double> > list =
                new LinkedList<Map.Entry<Animal, Double> >(hm.entrySet());

        // Sort the list
        Collections.sort(list, new Comparator<Map.Entry<Animal, Double> >() {
            public int compare(Map.Entry<Animal, Double> o1,
                               Map.Entry<Animal, Double> o2)
            {
                return (o1.getValue()).compareTo(o2.getValue());
            }
        });

        // put data from sorted list to hashmap
        HashMap<Animal, Double> temp = new LinkedHashMap<Animal, Double>();
        for (Map.Entry<Animal, Double> aa : list) {
            temp.put(aa.getKey(), aa.getValue());
        }
        return temp;
    }
}