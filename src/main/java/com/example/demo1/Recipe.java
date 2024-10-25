package com.example.demo1;

public class Recipe
{
    private int recipeID;
    private String name;
    private String category;
    private int preparationTime;
    private String instruction;
    private int numberPoints;
    private float totalPoints;
    private float rate;
    private String imgSrc;
    private String starImgSrc;

    private boolean isSaved;

    public Recipe(int id, String name, String category, int preparationTime, String instruction, int numberPoints, float totalPoints) {
        this.recipeID = id;
        this.name = name;
        this.category = category;
        this.preparationTime = preparationTime;
        this.instruction = instruction;
        this.numberPoints = numberPoints;
        this.totalPoints = totalPoints;
        this.rate = numberPoints == 0 ? 0 : totalPoints / numberPoints;
    }


    public String getName() {return name;}

    public void setName(String name) {this.name = name;}

    public int getID() {return recipeID;}
    public void setID(int id) {this.recipeID = id;}

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    public int getPreparationTime() {return preparationTime;}

    public void setPreparationTime(int preparationTime) {this.preparationTime = preparationTime;}

    public String getInstruction() {return instruction;}

    public void setInstruction(String instruction) {this.instruction = instruction;}

    public int getNumberPoints() {return numberPoints;}

    public void setNumberPoints(int numberPoints) {this.numberPoints = numberPoints;}

    public float getTotalPoints() {return totalPoints;}

    public void setTotalPoints(float totalPoints) {this.totalPoints = totalPoints;}

    public float getRate() {return rate;}

    public void setRate(float rate) {this.rate = rate;}

    public String getImgSrc() {return imgSrc;}

    public void setImgSrc(String imgSrc) {this.imgSrc = imgSrc;}

    public void setStarImgSrc(String starImgSrc) {this.starImgSrc = starImgSrc;}

    public String getStarImgSrc() {return starImgSrc;}

    public void setIsSaved(boolean isSaved) { this.isSaved = isSaved; }
    public boolean isSaved() { return isSaved; }
}
