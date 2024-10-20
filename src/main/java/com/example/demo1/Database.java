package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connect() throws SQLException {
        Connection connect = null;


        // String url = "jdbc:sqlite:/Users/ubeydgur/Projects/Java/ChefDecode/src/database/recipes.db";
        // String url = "jdbc:sqlite:/Users/ozgur/Github/ChefDecode/src/database/recipes.db";
        String url = "jdbc:sqlite:/Users/ubeydgur/Projects/Java/ChefDecode/src/database/recipes.db";
        connect = DriverManager.getConnection(url);

        if (connect != null) {
            System.out.println("Veritabanına başarıyla bağlandınız.");
        }
        return connect;
    }


    public List<Recipe> getAllRecipes() {
        List<Recipe> recipesList = new ArrayList<>();
        String query = "SELECT * FROM recipes";

        try (Connection connection = this.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {

            while (resultSet.next()) {
                String name = resultSet.getString("RecipeName");
                String category = resultSet.getString("Category");
                int preparationTime = resultSet.getInt("PreparationTime");
                String instruction = resultSet.getString("Instruction");
                int numberPoints = resultSet.getInt("NumberPoints");
                float totalPoints = resultSet.getFloat("TotalPoints");

                Recipe recipe = new Recipe(name, category, preparationTime, instruction, numberPoints, totalPoints);
                recipesList.add(recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipesList;
    }

}
