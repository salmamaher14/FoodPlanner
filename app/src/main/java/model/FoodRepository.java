package model;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import network.NetworkDeligate;

public interface FoodRepository {

    public void getAllCategories(NetworkDeligate networkDeligate);
    public void getAllIngrediants(NetworkDeligate networkDeligate);
    void getMealsByCategoryName(NetworkDeligate networkDeligate,String categoryName);
    void getMealsById(NetworkDeligate networkDeligate,String id);

    Flowable<List<Meal>> getStoredFavMeals();
    public void insertMeal(MealDetail meal);
    public void deleteMeal(Meal meal);


    public Flowable<List<String>>getPlannedDays();

    public void insertPlannedMeal(MealDetail meal ,String date);

    public void deletePlannedMeal(PlannedMeal meal);

    Flowable<List<PlannedMeal>> getPlannedMealsForDate(String selectedDate);
    public void getMealsByMainIngrediants(NetworkDeligate networkDeligate,String ingrediant);
    public void getMealsByArea(NetworkDeligate networkDeligate,String area);

    void getAllAreas(NetworkDeligate networkDeligate);

    public void getFetchedCategories(NetworkDeligate networkDeligate,String category);
    public void getFetchedAreas(NetworkDeligate networkDeligate,String area);
    public void getFetchedIngrediants(NetworkDeligate networkDeligate,String ingrediant);
    public  void filterMealsByCategoryName(NetworkDeligate networkDeligate,String categoryName);
    public  void filterMealsByFirstLetter(NetworkDeligate networkDeligate,String mealName);
    public void getRandomMeal(NetworkDeligate networkDeligate);





}
