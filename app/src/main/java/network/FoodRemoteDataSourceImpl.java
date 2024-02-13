package network;

import android.util.Log;

import java.util.List;

import model.Categories;
import model.Meal;
import model.MealDetail;
import model.Meals;
import model.AllMealsDetails;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodRemoteDataSourceImpl implements FoodRemoteDataSource {
    private static final String BASE_URL = "https://www.themealdb.com/";
    private static final String TAG = "RemoteDataSourceImpl";
    private static FoodRemoteDataSourceImpl client = null;
    private FoodService foodService;
    Call<Meals> mealsCall;
    Call<Categories> categoriesCall;
    Call<AllMealsDetails>mealsDetailsCall;


    public FoodRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create()).baseUrl(BASE_URL)
                .build();

        foodService = retrofit.create(FoodService.class);

    }

    public static FoodRemoteDataSourceImpl getInstance(){
        if(client==null){
            client=new FoodRemoteDataSourceImpl();
        }
        return client;
    }


    @Override
    public void getAllCategories(NetworkDeligate networkDeligate) {

        categoriesCall = foodService.getAllCategories();
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(Call<Categories> call, Response<Categories> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Log.e(TAG, "onResponse : " + response.body());
                    networkDeligate.onSuccessResult(response.body().getCategories());

                } else {
                    Log.e(TAG, "Failed to fetch data: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<Categories> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult(t.getMessage());

            }
        });

    }

    @Override
    public void getMealsByCategoryId(NetworkDeligate networkDeligate,String categoryName) {
        mealsCall=foodService.getMealsByCategoryId(categoryName);
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Vegeterian", "Succeeded to get  meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch  meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch  meals: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult(t.getMessage());

            }
        });

    }

    @Override
    public void getMealsById(NetworkDeligate networkDeligate, String id) {
        mealsDetailsCall=foodService.getMealById(id);
        mealsDetailsCall.enqueue(new Callback<AllMealsDetails>() {
            @Override
            public void onResponse(Call<AllMealsDetails> call, Response<AllMealsDetails> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<MealDetail> meals = response.body().getMealsDetails();
                    Log.e("mealdetail", "Succeeded to get  meal detail: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMealsDetails());
                } else {
                    Log.e(TAG, "Failed to fetch  meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch  meals: " + response.message());
                }

            }

            @Override
            public void onFailure(Call<AllMealsDetails> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult(t.getMessage());

            }


        });

    }


}






