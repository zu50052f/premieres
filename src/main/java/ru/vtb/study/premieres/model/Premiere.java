package ru.vtb.study.premieres.model;

public class Premiere {
    private String name;
    private String description;
    private int ageCategory;
    private int availableSeats;

    public Premiere(String name, String description, int ageCategory, int availableSeats) {
        this.name = name;
        this.description = description;
        this.ageCategory = ageCategory;
        this.availableSeats = availableSeats;
    }

    public Premiere(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Premiere{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", ageCategory=" + ageCategory +
                ", availableSeats=" + availableSeats +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgeCategory() {
        return ageCategory;
    }

    public void setAgeCategory(int ageCategory) {
        this.ageCategory = ageCategory;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
