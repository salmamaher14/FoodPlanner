package network;

import io.reactivex.rxjava3.core.Single;
import model.Areas;
import model.Ingrediants;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodRemoteDataSource {
    void getAllCategories(NetworkDeligate networkDeligate);
    void getMealsByCategoryName(NetworkDeligate networkDeligate,String categoryName);
    void getMealsById(NetworkDeligate networkDeligate,String id);
    void getAllAreas(NetworkDeligate networkDeligate);
    void getAllIngrediants(NetworkDeligate networkDeligate);

    void  getMealsByArea(NetworkDeligate networkDeligate,String areaName);
    void getMealsByMainIngrediant(NetworkDeligate networkDeligate,String mainIngrediant);
    void fetchCategories(NetworkDeligate networkDeligate,String category);
    void fetchAreas(NetworkDeligate networkDeligate,String area);
    void fetchIngrediants(NetworkDeligate networkDeligate,String ingrediant);
    void filterMealsByCategoryName(NetworkDeligate networkDeligate,String categoryName);
    void filterMealByFirstLetter(NetworkDeligate networkDeligate,String mealName);
    void getRandomMeal(NetworkDeligate networkDeligate);







}

