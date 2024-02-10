package network;

import model.Categories;
import model.Meals;
import retrofit2.Call;
import retrofit2.http.GET;

public interface FoodService {
    @GET("api/json/v1/1/categories.php")
    Call<Categories> getAllCategories();

    @GET("api/json/v1/1/filter.php?c=Vegetarian")
    Call<Meals> getVegetarianMeals();

    @GET("api/json/v1/1/filter.php?c=Beef")
    Call<Meals> getBeefMeals();

    @GET("api/json/v1/1/filter.php?c=Chicken")
    Call<Meals> getChickenMeals();

    @GET("api/json/v1/1/filter.php?c=Dessert")
    Call<Meals> getDessertMeals();

    @GET("api/json/v1/1/filter.php?c=Lamb")
    Call<Meals> getLambMeals();


    @GET("api/json/v1/1/filter.php?c=Miscellaneous")
    Call<Meals> getMiscellaneousMeals();

    @GET("api/json/v1/1/filter.php?c=Pasta")
    Call<Meals> getPastaMeals();

    @GET("api/json/v1/1/filter.php?c=Pork")
    Call<Meals> getPorkMeals();

    @GET("api/json/v1/1/filter.php?c=Seafood")
    Call<Meals> getSeafoodMeals();

    @GET("api/json/v1/1/filter.php?c=Side")
    Call<Meals> getSideMeals();

    @GET("api/json/v1/1/filter.php?c=Starter")
    Call<Meals> getStarterMeals();

    @GET("api/json/v1/1/filter.php?c=Vegan")
    Call<Meals> getVeganMeals();

    @GET("api/json/v1/1/filter.php?c=Breakfast")
    Call<Meals> getBreakfastMeals();

    @GET("api/json/v1/1/filter.php?c=Goat")
    Call<Meals> getGoatMeals();



}
