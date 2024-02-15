package model;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDataSource {
    public void insertMeal(MealDetail meal);
    public void deleteMeal(Meal meal);
    public Flowable<List<Meal>> getFavMeals();

}
