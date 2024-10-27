package com.example.demo1;

import javafx.scene.Node;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Database {
    Connection connect = null;

    private Connection connect() throws SQLException
    {
        String url = "jdbc:sqlite:/Users/ubeydgur/Projects/Java/ChefDecode/src/database/recipes.db";
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
        String deleteRelationQuery = "DELETE FROM relation WHERE RecipeID = ?";
        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(deleteRelationQuery))
        {
            preparedStatement.setInt(1, recipeID);
            preparedStatement.executeUpdate();
            System.out.println("İlişkilendirilmiş malzemeler silindi. RecipeID: " + recipeID);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

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
        String query = "INSERT INTO ingredients (IngredientID, IngredientName, StockQuantity, Unit, UnitPrice) VALUES (?, ?, ?, ?, ?)";

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
        String query = "DELETE FROM ingredients WHERE IngredientID = ?";

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

    public void updateRecipe(Recipe recipe)
    {
        String query = "UPDATE recipes SET RecipeName = ?, Category = ?, PreparationTime = ?, Instruction = ?, NumberPoints = ?, TotalPoints = ? WHERE RecipeID = ?";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1, recipe.getName());
            preparedStatement.setString(2, recipe.getCategory());
            preparedStatement.setInt(3, recipe.getPreparationTime());
            preparedStatement.setString(4, recipe.getInstruction());
            preparedStatement.setInt(5, recipe.getNumberPoints());
            preparedStatement.setFloat(6, recipe.getTotalPoints());
            preparedStatement.setInt(7, recipe.getID());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("Tarif başarıyla güncellendi.");
            }
            else
            {
                System.out.println("Güncellenecek tarif bulunamadı.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateIngredient(Ingredient ingredient)
    {
        String query = "UPDATE ingredients SET IngredientName = ?, StockQuantity = ?, Unit = ?, UnitPrice = ? WHERE IngredientID = ?";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1, ingredient.getIngredientName());
            preparedStatement.setString(2, ingredient.getQuantity());
            preparedStatement.setString(3, ingredient.getUnit());
            preparedStatement.setInt(4, ingredient.getUnitPrice());
            preparedStatement.setInt(5, ingredient.getIngredientID());

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("Malzeme başarıyla güncellendi.");
            }
            else
            {
                System.out.println("Güncellenecek malzeme bulunamadı.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateRelation(int recipeID, int ingredientID, float ingredientQuantity)
    {
        String query = "UPDATE relation SET IngredientQuantity = ? WHERE RecipeID = ? AND IngredientID = ?";

        try (Connection connection = this.connect(); PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setFloat(1, ingredientQuantity);
            preparedStatement.setInt(2, recipeID);
            preparedStatement.setInt(3, ingredientID);

            int rowsAffected = preparedStatement.executeUpdate();
            if (rowsAffected > 0)
            {
                System.out.println("İlişki başarıyla güncellendi.");
            }
            else
            {
                System.out.println("Güncellenecek ilişki bulunamadı.");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public List<Ingredient> getAllIngredients()
    {
        List<Ingredient> ingredientList = new ArrayList<>();
        String query = "SELECT * FROM ingredients";

        try (Connection connection = this.connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {
            while (resultSet.next())
            {
                int ingredientID = resultSet.getInt("IngredientID");
                String ingredientName = resultSet.getString("IngredientName");
                String quantity = resultSet.getString("StockQuantity");
                String unit = resultSet.getString("Unit");
                int unitPrice = resultSet.getInt("UnitPrice");

                Ingredient ingredient = new Ingredient(ingredientID, ingredientName, quantity, unit, unitPrice);
                ingredientList.add(ingredient);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ingredientList;
    }

    public Ingredient getIngredientByName(String ingredientName)
    {
        Ingredient ingredient = null;
        String query = "SELECT * FROM ingredients WHERE IngredientName = ?";

        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setString(1, ingredientName);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                int ingredientID = resultSet.getInt("IngredientID");
                String quantity = resultSet.getString("StockQuantity");
                String unit = resultSet.getString("Unit");
                int unitPrice = resultSet.getInt("UnitPrice");

                ingredient = new Ingredient(ingredientID, ingredientName, quantity, unit, unitPrice);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ingredient;
    }

    public Ingredient getIngredientByID(int ingredientID)
    {
        Ingredient ingredient = null;
        String query = "SELECT * FROM ingredients WHERE IngredientID = ?";

        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {
            preparedStatement.setInt(1, ingredientID);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next())
            {
                String ingredientName = resultSet.getString("IngredientName");
                String quantity = resultSet.getString("StockQuantity");
                String unit = resultSet.getString("Unit");
                int unitPrice = resultSet.getInt("UnitPrice");

                ingredient = new Ingredient(ingredientID, ingredientName, quantity, unit, unitPrice);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return ingredient;
    }

    public List<String> getUnitListByRecipeId(int recipeId)
    {
        List<String> unitList = new ArrayList<>();
        String query = "SELECT Unit FROM ingredients m " +
                "JOIN relation t ON m.IngredientID = t.IngredientID " +
                "WHERE t.RecipeID = ?";

        try (Connection conn = connect(); PreparedStatement preparedStatement = conn.prepareStatement(query))
        {
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                unitList.add(resultSet.getString("Unit"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return unitList;
    }

    public List<String> getIngredientNameListByRecipeId(int recipeId)
    {
        List<String> ingredientNameList = new ArrayList<>();
        String query = "SELECT IngredientName FROM ingredients m " + "JOIN relation t ON m.IngredientID = t.IngredientID " + "WHERE t.RecipeID = ?";

        try (Connection conn = connect(); PreparedStatement preparedStatement = conn.prepareStatement(query))
        {
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                ingredientNameList.add(resultSet.getString("IngredientName"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return ingredientNameList;
    }

    public List<String> getIngredientQuantityListByRecipeId(int recipeId)
    {
        List<String> quantityList = new ArrayList<>();
        String query = "SELECT IngredientQuantity FROM relation " + "WHERE RecipeID = ?";

        try (Connection conn = connect(); PreparedStatement preparedStatement = conn.prepareStatement(query))
        {
            preparedStatement.setInt(1, recipeId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next())
            {
                quantityList.add(resultSet.getString("IngredientQuantity"));
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return quantityList;
    }

    public List<String> getIngredientsByRecipeId(int recipeId)
    {
        List<String> ingredientList = new ArrayList<>();

        String query = "SELECT i.IngredientName, r.IngredientQuantity, i.Unit " + "FROM relation r " + "JOIN ingredients i ON r.IngredientID = i.IngredientID " + "WHERE r.RecipeID = ?";

        try (Connection conn = connect(); PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setInt(1, recipeId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                String ingredientName = rs.getString("IngredientName");
                float ingredientQuantity = rs.getFloat("IngredientQuantity");
                String unit = rs.getString("Unit");

                String ingredient = ingredientName + ": " + ingredientQuantity + " " + unit;
                ingredientList.add(ingredient);
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return ingredientList;
    }

    public String getIngredientUnitPrice(String ingredientName)
    {
        int unitPrice = -1;
        String query = "SELECT UnitPrice FROM ingredients WHERE IngredientName = ?";

        try (Connection connection = connect(); PreparedStatement pstmt = connection.prepareStatement(query))
        {
            pstmt.setString(1, ingredientName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next())
            {
                unitPrice = rs.getInt("UnitPrice");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return Integer.toString(unitPrice);
    }

    public int getMaxRecipeIdFromDatabase()
    {
        int maxRecipeId = 0;
        String query = "SELECT MAX(RecipeID) AS maxID FROM recipes";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {

            if (resultSet.next())
            {
                maxRecipeId = resultSet.getInt("maxID");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return maxRecipeId;
    }

    public int getMaxIngredientIdFromDatabase()
    {
        int maxIngredientId = 0;
        String query = "SELECT MAX(IngredientID) AS maxID FROM ingredients";

        try (Connection connection = connect();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query))
        {

            if (resultSet.next())
            {
                maxIngredientId = resultSet.getInt("maxID");
            }
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }

        return maxIngredientId;
    }

    public String getIngredientStockWithUnit(String ingredientName)
    {
        String stockWithUnit = "";
        String query = "SELECT StockQuantity, Unit FROM ingredients WHERE IngredientName = ?";

        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, ingredientName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                String stockQuantity = rs.getString("StockQuantity");
                String unit = rs.getString("Unit");
                stockWithUnit = stockQuantity + " " + unit + " is available for usage";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return stockWithUnit;
    }

    public String getIngredientUnitTypeByName(String ingredientName)
    {
        String unit = "";
        String query = "SELECT Unit FROM ingredients WHERE IngredientName = ?";

        try (Connection connection = connect();
             PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, ingredientName);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                unit = rs.getString("Unit");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return unit;
    }

    public void updatePreparationTime(int recipeID, int newPreparationTime)
    {
        String query = "UPDATE recipes SET PreparationTime = ? WHERE RecipeID = ?";

        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {

            preparedStatement.setInt(1, newPreparationTime);
            preparedStatement.setInt(2, recipeID);

            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("PreparationTime başarıyla güncellendi. RecipeID: " + recipeID);
            } else {
                System.out.println("Güncellenmek istenen RecipeID bulunamadı: " + recipeID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateInstructions(int recipeId, String instructions)
    {
        String query = "UPDATE recipes SET Instruction = ? WHERE RecipeID = ?";

        try (Connection connection = this.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(query))
        {

            preparedStatement.setString(1, instructions);
            preparedStatement.setInt(2, recipeId);

            preparedStatement.executeUpdate();
            System.out.println("Talimatlar başarıyla güncellendi.");
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

    public void updateRecipeIngredients(int recipeId, VBox nameBox, VBox quantityBox)
    {
        String deleteQuery = "DELETE FROM relation WHERE RecipeID = ?";
        try (Connection conn = connect();
             PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
            deleteStmt.setInt(1, recipeId);
            deleteStmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        List<String> ingredientNames = new ArrayList<>();
        List<String> ingredientQuantities = new ArrayList<>();

        for (Node node : nameBox.getChildren()) {
            if (node instanceof Label) {
                ingredientNames.add(((Label) node).getText());

            }
        }

        for (Node node : quantityBox.getChildren()) {
            if (node instanceof TextField) {
                ingredientQuantities.add(((TextField) node).getText());
            }
        }

        System.out.println("Size : " + ingredientNames.size());
        System.out.println("Size : " + ingredientQuantities.size());

        String insertQuery = "INSERT INTO relation (RecipeID, IngredientID, IngredientQuantity) " +
                "VALUES (?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement insertStmt = conn.prepareStatement(insertQuery)) {
            for (int i = 0; i < ingredientNames.size(); i++) {
                int ingredientId = getIngredientIdByName(ingredientNames.get(i));

                insertStmt.setInt(1, recipeId);
                insertStmt.setInt(2, ingredientId);
                insertStmt.setString(3, ingredientQuantities.get(i));
                insertStmt.addBatch();
            }
            insertStmt.executeBatch();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private int getIngredientIdByName(String ingredientName) {
        // Ingredient ID'sini almak için SQL sorgusu
        String query = "SELECT IngredientID FROM ingredients WHERE IngredientName = ?";
        try (Connection conn = connect();
             PreparedStatement preparedStatement = conn.prepareStatement(query)) {
            preparedStatement.setString(1, ingredientName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("IngredientID");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("NULLL");
        return -1; // Eğer bulunamazsa
    }

    public void printAllRelations() {
        String query = "SELECT * FROM relation"; // relation tablosundaki tüm kayıtları al

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int recipeId = rs.getInt("RecipeID");
                int ingredientId = rs.getInt("IngredientID");
                float ingredientQuantity = rs.getFloat("IngredientQuantity");

                // İlişki bilgilerini yazdır
                System.out.println("Recipe ID: " + recipeId + ", Ingredient ID: " + ingredientId + ", Quantity: " + ingredientQuantity);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Hata durumunda istisnayı yazdır
        }
    }
}
