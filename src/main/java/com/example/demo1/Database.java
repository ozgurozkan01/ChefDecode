package com.example.demo1;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    private Connection connect() throws SQLException {
        Connection connect = null;


        String url = "jdbc:sqlite:/Users/ubeydgur/Projects/Java/ChefDecode/src/database/recipes.db";
        //String url = "jdbc:sqlite:/Users/ozgur/Github/ChefDecode/src/database/recipes.db";
        connect = DriverManager.getConnection(url);

        if (connect != null) {
            System.out.println("Veritabanına başarıyla bağlandınız.");
        }
        return connect;
    }


    public List<Recipe> getAllRecipes()
    {
        List<Recipe> recipesList = new ArrayList<>();
        String query = "SELECT * FROM recipes";

        try (Connection connection = this.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {

            while (resultSet.next())
            {
                int recipeID = resultSet.getInt("RecipeID");
                String name = resultSet.getString("RecipeName");
                String category = resultSet.getString("Category");
                int preparationTime = resultSet.getInt("PreparationTime");
                String instruction = resultSet.getString("Instruction");
                int numberPoints = resultSet.getInt("NumberPoints");
                float totalPoints = resultSet.getFloat("TotalPoints");

                Recipe recipe = new Recipe(recipeID, name, category, preparationTime, instruction, numberPoints, totalPoints);
                recipesList.add(recipe);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return recipesList;
    }

    public void insertRecipe(Recipe recipe)
    {
        String query = "INSERT INTO recipes (RecipeID, RecipeName, Category, PreparationTime, Instruction, NumberPoints, TotalPoints) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = this.connect();  PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, recipe.getID());
            preparedStatement.setString(2, recipe.getName());
            preparedStatement.setString(3, recipe.getCategory());
            preparedStatement.setInt(4, recipe.getPreparationTime());
            preparedStatement.setString(5, recipe.getInstruction());
            preparedStatement.setInt(6, recipe.getNumberPoints());
            preparedStatement.setFloat(7, recipe.getTotalPoints());

            preparedStatement.executeUpdate();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public Recipe getRecipe(int recipeID)
    {
        Recipe recipe = null; // Başlangıçta null olarak tanımlıyoruz.
        String query = "SELECT * FROM recipes WHERE RecipeID = ?"; // Belirli bir tarif için sorgu

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, recipeID); // Sorguya recipeID'yi ekle
            ResultSet resultSet = preparedStatement.executeQuery(); // Sorguyu çalıştır

            if (resultSet.next())
            {
                String name = resultSet.getString("RecipeName");
                String category = resultSet.getString("Category");
                int preparationTime = resultSet.getInt("PreparationTime");
                String instruction = resultSet.getString("Instruction");
                int numberPoints = resultSet.getInt("NumberPoints");
                float totalPoints = resultSet.getFloat("TotalPoints");

                recipe = new Recipe(recipeID, name, category, preparationTime, instruction, numberPoints, totalPoints);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return recipe;
    }

    public void deleteRecipe(int recipeID)
    {
        String query = "DELETE FROM recipes WHERE RecipeID = ?";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, recipeID);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                System.out.println("Tarif başarıyla silindi. ID: " + recipeID);
            }
            else
            {
                System.out.println("Silinecek tarif bulunamadı. ID: " + recipeID);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void insertIngredient(Ingredient ingredient)
    {
        String query = "INSERT INTO ingredient (IngredientID, IngredientName, Quantity, Unit, UnitPrice) VALUES (?, ?, ?, ?, ?)";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, ingredient.getIngredientID());
            preparedStatement.setString(2, ingredient.getIngredientName());
            preparedStatement.setString(3, ingredient.getQuantity());
            preparedStatement.setString(4, ingredient.getUnit());
            preparedStatement.setInt(5, ingredient.getUnitPrice());

            preparedStatement.executeUpdate();
            System.out.println("Malzeme başarıyla eklendi.");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteIngredient(int ingredientID)
    {
        String query = "DELETE FROM ingredient WHERE IngredientID = ?";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, ingredientID);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                System.out.println("Malzeme başarıyla silindi. ID: " + ingredientID);
            }
            else
            {
                System.out.println("Silinecek malzeme bulunamadı. ID: " + ingredientID);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void insertRelation(int recipeID, int ingredientID, float ingredientQuantity)
    {
        String query = "INSERT INTO relation (RecipeID, IngredientID, IngredientQuantity) VALUES (?, ?, ?)";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, recipeID);
            preparedStatement.setInt(2, ingredientID);
            preparedStatement.setFloat(3, ingredientQuantity);

            preparedStatement.executeUpdate();
            System.out.println("İlişki başarıyla eklendi.");

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void deleteRelation(int recipeID, int ingredientID)
    {
        String query = "DELETE FROM relation WHERE RecipeID = ? AND IngredientID = ?";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, recipeID);
            preparedStatement.setInt(2, ingredientID);
            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0)
            {
                System.out.println("İlişki başarıyla silindi. RecipeID: " + recipeID + ", IngredientID: " + ingredientID);
            }
            else
            {
                System.out.println("Silinecek ilişki bulunamadı.");
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }
}
