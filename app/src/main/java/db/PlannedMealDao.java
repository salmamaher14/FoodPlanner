package db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import model.Meal;
import model.MealDetail;
import model.PlannedMeal;

@Dao
public interface PlannedMealDao {

    @Query("SELECT * FROM plannedMeals_table WHERE date = :selectedDate")
    Flowable<List<PlannedMeal>> getPlannedMealsForDate(String selectedDate);

//    @Query("SELECT * FROM plannedMeals_table")
//    Flowable<List<PlannedMeal>> getPlannedMeals();
    @Query("SELECT DISTINCT date FROM plannedMeals_table")
    Flowable<List<String>> getDistinctPlanningDates();

    @Insert(onConflict= OnConflictStrategy.IGNORE)
    void insertPlannedMeal(PlannedMeal meal);

    @Delete
    void deletePlannedMeal(PlannedMeal meal);
}

