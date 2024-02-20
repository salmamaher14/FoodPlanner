package model;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;

import java.util.List;

import db.AppDataBase;
import db.MealDAO;
import db.PlannedMealDao;
import io.reactivex.rxjava3.core.Flowable;

public class MealLocalDataSourceImpl implements MealLocalDataSource {
    private MealDAO mealDAO;
    private PlannedMealDao plannedMealDao;
    private static MealLocalDataSourceImpl localDataSource=null;
    private Flowable<List<Meal>> storedMeals;
    private Flowable<List<PlannedMeal>> storedPlannedMeals;
      private Flowable<List<String>> storedPlannedDays;


    public MealLocalDataSourceImpl(Context context) {
        AppDataBase db=AppDataBase.getInstance(context.getApplicationContext());
        mealDAO=db.getMealDAO();
        plannedMealDao=db.getPlannedMealDao();
        storedMeals=mealDAO.getFavMeals();
        storedPlannedDays=plannedMealDao.getDistinctPlanningDates();
        storedPlannedMeals=plannedMealDao.getPlannedMealsForDate("");

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

    @Override
    public Flowable<List<PlannedMeal>> getPlannedMeals() {
       return  storedPlannedMeals;
    }

    @Override
    public Flowable<List<String>> getPlannedDays() {
        return storedPlannedDays;
    }

    @Override
    public void insertPlannedMeal(MealDetail mealDetail, String date) {
        PlannedMeal plannedMeal = new PlannedMeal();
        plannedMeal.setIdPlannedMeal(mealDetail.getIdMeal());
        plannedMeal.setStrMeal(mealDetail.getStrMeal());
        plannedMeal.setStrMealThumb(mealDetail.getStrMealThumb());
        plannedMeal.setDate(date);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Log.i("MealLocalDataSource", "Inserting planned meal: " + plannedMeal.getIdPlannedMeal());
                    plannedMealDao.insertPlannedMeal(plannedMeal);
                } catch (Exception e) {
                    Log.e("MealLocalDataSource", "Error inserting planned meal: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        }).start();
    }

    @Override
    public void deletePlannedMeal(PlannedMeal meal) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                plannedMealDao.deletePlannedMeal(meal);
            }
        }).start();

    }

    @Override
    public Flowable<List<PlannedMeal>> getPlannedMealsForDate(String selectedDate) {
        storedPlannedMeals=plannedMealDao.getPlannedMealsForDate(selectedDate);
        Log.i("storedPlannedMeals", "getPlannedMealsForDate: "+storedPlannedMeals);
        return storedPlannedMeals;
    }


}

