package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.List;

public interface MealDao {
    void add(Meal meal);

    void addAll(List<Meal> meals);

    void update(Meal meal);

    void remove(int id);

    Meal getById(int id);

    List<Meal> getAll();
}
