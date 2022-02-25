package ru.vtb.study.premieres.interfaces;

public interface IPremiere {
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    int getAgeCategory();
    void setAgeCategory(int ageCategory);
    int getAvailableSeats();
    void setAvailableSeats(int availableSeats);
}
