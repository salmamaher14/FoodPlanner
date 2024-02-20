package model;

import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;

public interface MealLocalDataSource {
    public void insertMeal(MealDetail meal);
    public void deleteMeal(Meal meal);
    public Flowable<List<Meal>> getFavMeals();

    public Flowable<List<PlannedMeal>> getPlannedMeals();
    public Flowable<List<String>> getPlannedDays();

    public void insertPlannedMeal(MealDetail meal,String date);

    public void deletePlannedMeal(PlannedMeal meal);
   public Flowable<List<PlannedMeal>> getPlannedMealsForDate(String selectedDate);

}
