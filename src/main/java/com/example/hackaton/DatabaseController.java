package com.example.hackaton;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        String sql = "CREATE TABLE IF NOT EXISTS animals (     id SERIAL PRIMARY KEY,     fur BOOLEAN,     name VARCHAR(255),     species VARCHAR(255),     age INT,     pathToImage VARCHAR(255),     caretTimeNeeded INT,     resourcefulness INT,     competentWithAnimals INT,     impulsiveness INT,     income INT,     dedication INT,     gardenSize INT,     freeTime INT,     activeLifestyle INT,     livingArea INT,     currentAnimals INT,     houseType INT,     housemateCount INT,     qustionareeAge INT,     children INT,     animalsActivity INT,     responsibility INT,     forgetfulness INT,     shortLivedEnthusiasm INT,     determined INT,     optimistic INT ); ";
        try (
            Statement statement = connection.createStatement()) {
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // That's something we use in rendering items in inventory
    public static List<String> getAllItems() {
        List<String> items = new ArrayList<>();
        String sql = "SELECT field_id, filename FROM animals";

        try (
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql)){

            // loop through the result set
            while (result.next()) {
                if (result.getString("filename") == null) continue;
                String imagePath = "file:assets/" + result.getString("filename");
                items.add(imagePath);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return items;
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
                        result.getInt("careTimeNeeded"),
                        result.getInt("resourcefulness"),
                        result.getInt("competentWithAnimals"),
                        result.getInt("impulsiveness"),
                        result.getInt("income"),
                        result.getInt("dedication"),
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
                if (similarity > 7) {
                    animals.put(animal, similarity);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return animals;
    }
}