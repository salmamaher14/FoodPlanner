package network;

import model.Categories;
import model.Meals;
import model.AllMealsDetails;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface FoodService {
    @GET("api/json/v1/1/categories.php")
    Call<Categories> getAllCategories();

    @GET("api/json/v1/1/filter.php")
    Call<Meals> getMealsByCategoryId(@Query("c") String categoryName);

    @GET("api/json/v1/1/lookup.php")
    Call<AllMealsDetails> getMealById(@Query("i") String mealId);



}
