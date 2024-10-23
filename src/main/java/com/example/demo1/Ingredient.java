package com.example.demo1;

public class Ingredient
{
    private int ingredientID;
    private String ingredientName;
    private String quantity;       // "2", "1.5")
    private String unit;           // "kg", "adet")
    private int unitPrice;         // 10 TL)

    public Ingredient(int ingredientID, String ingredientName, String quantity, String unit, int unitPrice) {
        this.ingredientID = ingredientID;
        this.ingredientName = ingredientName;
        this.quantity = quantity;
        this.unit = unit;
        this.unitPrice = unitPrice;
    }

    public int getIngredientID() { return ingredientID; }
    public void setIngredientID(int ingredientID) { this.ingredientID = ingredientID; }

    public String getIngredientName() { return ingredientName; }
    public void setIngredientName(String ingredientName) { this.ingredientName = ingredientName; }

    public String getQuantity() { return quantity; }
    public void setQuantity(String quantity) { this.quantity = quantity; }

    public String getUnit() { return unit; }
    public void setUnit(String unit) { this.unit = unit; }

    public int getUnitPrice() { return unitPrice; }
    public void setUnitPrice(int unitPrice) { this.unitPrice = unitPrice; }

    public String toString()
    {
        return "Ingredient{" +
                "ingredientID=" + ingredientID +
                ", ingredientName='" + ingredientName + '\'' +
                ", quantity='" + quantity + '\'' +
                ", unit='" + unit + '\'' +
                ", unitPrice=" + unitPrice +
                '}';
    }
}
