package model;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import db.AppDataBase;
import db.MealDAO;
import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceImpl implements MealLocalDataSource {
    private MealDAO mealDAO;
    private static MealLocalDataSourceImpl localDataSource=null;
    private Flowable<List<Meal>> storedMeals;


    public MealLocalDataSourceImpl(Context context) {
        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO=db.getMealDAO();
        storedMeals=mealDAO.getFavMeals();
    }

        public static MealLocalDataSourceImpl getInstance(Context context){
        if(localDataSource==null){
            localDataSource=new MealLocalDataSourceImpl(context);
        }
        return localDataSource;
    }


    @Override
    public void insertMeal(MealDetail mealDetail) {

        Meal favMeal=new Meal();
        favMeal.setIdMeal(mealDetail.getIdMeal());
        favMeal.setStrMeal(mealDetail.getStrMeal());
        favMeal.setStrMealThumb(mealDetail.getStrMealThumb());

        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.insert(favMeal);
            }
        }).start();

    }

    @Override
    public void deleteMeal(Meal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mealDAO.delete(meal);
            }
        }).start();

    }

    @Override
    public Flowable<List<Meal>> getFavMeals() {
        return storedMeals;
    }




}

