package network;

import io.reactivex.rxjava3.core.Single;
import model.Areas;
import model.Categories;
import model.Ingrediants;
import model.Meals;
import model.AllMealsDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodService {
//    @GET("api/json/v1/1/categories.php")
//    Call<Categories> getAllCategories();

    @GET("api/json/v1/1/categories.php")
    Single<Categories> getAllCategories();

    @GET("api/json/v1/1/filter.php")
    Single<Meals> getMealsByCategoryName(@Query("c") String categoryName); //filter by categoryName

    @GET("api/json/v1/1/lookup.php")
    Single<AllMealsDetails> getMealById(@Query("i") String mealId);

    @GET("api/json/v1/1/list.php?a=list")
    Single<Areas> getAllAreas();


    @GET("api/json/v1/1/list.php?i=list")
    Single<Ingrediants> getAllIngrediants();

    @GET("api/json/v1/1/filter.php")
    Single<Meals> getMealsByArea(@Query("a") String areaName);

    @GET("api/json/v1/1/filter.php")
    Single<Meals>getMealsByMainIngrediant(@Query("i") String mainIngrediant);

    @GET("api/json/v1/1/search.php")
    Single<Meals>getMealByFirstLetter(@Query("f") String meal);

//    @GET("images/ingredients")






}
