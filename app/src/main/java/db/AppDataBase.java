package db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import model.Meal;
import model.PlannedMeal;

@Database(entities = {Meal.class,PlannedMeal.class}, version =1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {

    private static AppDataBase instance;

    public abstract MealDAO getMealDAO();
    public abstract PlannedMealDao getPlannedMealDao();

    public static synchronized AppDataBase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            AppDataBase.class, "mealsdb")
                    .fallbackToDestructiveMigration() // This ensures that the database is recreated
                    .build();
//            instance= Room.databaseBuilder(context.getApplicationContext(),AppDataBase.class,"mealsdb").build();

        }
        return instance;
    }
}
