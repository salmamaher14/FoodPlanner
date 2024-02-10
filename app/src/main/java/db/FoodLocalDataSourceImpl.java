package db;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import model.Meal;

public class FoodLocalDataSourceImpl implements MealDAO {
    private MealDAO mealDAO;
    private static FoodLocalDataSourceImpl localDataSource=null;
    private LiveData<List<Meal>>storedMeals;


    public FoodLocalDataSourceImpl(Context context) {
        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO=db.getMealDAO();
        storedMeals=mealDAO.getSavedMeals();
    }


    public static FoodLocalDataSourceImpl getInstance(Context context){
        if(localDataSource==null){
            localDataSource=new FoodLocalDataSourceImpl(context);
        }
        return localDataSource;
    }

    @Override
    public LiveData<List<Meal>> getSavedMeals() {
        return storedMeals;
    }

    @Override
    public void insert(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insert(meal);
            }
        }).start();

    }

    @Override
    public void delete(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.delete(meal);
            }
        }).start();

    }
}

