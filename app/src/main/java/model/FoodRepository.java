package model;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import network.NetworkDeligate;

public interface FoodRepository {

    public void getAllCategories(NetworkDeligate networkDeligate);
    void getMealsByCategoryId(NetworkDeligate networkDeligate,String categoryName);
    void getMealsById(NetworkDeligate networkDeligate,String id);

    Flowable<List<Meal>> getStoredFavMeals();
    public void insertMeal(MealDetail meal);
    public void deleteMeal(Meal meal);




}
