package com.example.utilities;
enum Hhcategory {
    Heartrate, water, calorieintake, steps, sleep

}
public class HealthStatus {
    private int weight;
    private int height;
    private String date;
    private String birthdate;
    private String name;
    private Hhcategory Category;

    public HealthStatus(int weight, int height, String date, String birthdate, String name, Hhcategory category) {
        this.weight = weight;
        this.height = height;
        this.date = date;
        this.birthdate = birthdate;
        this.name = name;
        Category = category;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(String birthdate) {
        this.birthdate = birthdate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hhcategory getCategory() {
        return Category;
    }

    public void setCategory(Hhcategory category) {
        Category = category;
    }

    @Override
    public String toString() {
        return "Hhcategories{" +
                "weight=" + weight +
                ", height=" + height +
                ", date='" + date + '\'' +
                ", birthdate='" + birthdate + '\'' +
                ", name='" + name + '\'' +
                ", Category=" + Category +
                '}';
    }
}
