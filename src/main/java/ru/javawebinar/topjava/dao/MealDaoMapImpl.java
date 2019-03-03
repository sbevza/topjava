package ru.javawebinar.topjava.dao;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MealDaoMapImpl implements MealDao {
    private static AtomicInteger id = new AtomicInteger(0);
    private static Map<Integer, Meal> meals = new ConcurrentHashMap<>();

    @Override
    public void add(Meal meal) {
        int id = MealDaoMapImpl.id.incrementAndGet();
        meal.setId(id);
        meals.put(id, meal);
    }

    @Override
    public void addAll(List<Meal> meals) {
        for (Meal meal : meals) {
            add(meal);
        }
    }

    @Override
    public void update(Meal meal) {
        meals.put(meal.getId(), meal);
    }

    @Override
    public void remove(int id) {
        meals.remove(id);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }
}
