package db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import model.Meal;

@Dao
public interface MealDAO {
    @Query("SELECT * From meals_table")
    LiveData<List<Meal>> getSavedMeals();

    @Insert (onConflict= OnConflictStrategy.IGNORE)
    void insert(Meal meal);

    @Delete
    void delete(Meal meal);


}
