package com.zybooks.foodorder;

public class Food {
    private String foodTitle;
    private double cost;
    private int drawableId;

    public Food(String foodTitle, double cost, int drawableId) {
        this.foodTitle = foodTitle;
        this.cost = cost;
        this.drawableId = drawableId;
    }

    public String getFoodTitle() {
        return foodTitle;
    }

    public void setFoodTitle(String foodTitle) {
        this.foodTitle = foodTitle;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

}
