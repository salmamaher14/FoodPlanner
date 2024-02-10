package network;

import android.util.Log;

import java.util.List;

import model.Categories;
import model.Meal;
import model.Meals;
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
    public void getVegetarianMeals(NetworkDeligate networkDeligate) {
        mealsCall = foodService.getVegetarianMeals();
        mealsCall.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Vegeterian", "Succeeded to get vegetarian meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch vegetarian meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch vegetarian meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });
    }

    @Override
    public void getBeefMeals(NetworkDeligate networkDeligate) {
        mealsCall= foodService.getBeefMeals();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Beef", "Succeeded to get beef meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch beef meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch beef meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getChickenMeals(NetworkDeligate networkDeligate) {
        mealsCall = foodService.getChickenMeals();
        mealsCall.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Chicken", "Succeeded to get Chicken meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch Chicken meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch Chicken meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getDessertMeals(NetworkDeligate networkDeligate) {
        mealsCall = foodService.getDessertMeals();
        mealsCall.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Dessert", "Succeeded to get dessert meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch dessert meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch dessert meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getLambMeals(NetworkDeligate networkDeligate) {
        mealsCall = foodService.getLambMeals();
       mealsCall.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Lamb", "Succeeded to get Lamb meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch Lamb meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch Lamb meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getMiscellaneousMeals(NetworkDeligate networkDeligate) {
        mealsCall = foodService.getMiscellaneousMeals();
        mealsCall.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Miscellaneous", "Succeeded to get miscellaneous meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch miscellaneous meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch miscellaneous meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getPastaMeals(NetworkDeligate networkDeligate) {
        mealsCall = foodService.getPastaMeals();
        mealsCall.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Pasta", "Succeeded to get pasta meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch pasta meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch pasta meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getPorkMeals(NetworkDeligate networkDeligate) {
        mealsCall= foodService.getPorkMeals();
        mealsCall.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Pork", "Succeeded to get pork meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch pork meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch pork meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getSeafoodMeals(NetworkDeligate networkDeligate) {
        mealsCall= foodService.getSeafoodMeals();
       mealsCall.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Seafood", "Succeeded to get Seafood meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch Seafood meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch Seafood meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getSideMeals(NetworkDeligate networkDeligate) {
        mealsCall= foodService.getSideMeals();
        mealsCall.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("SideMeals", "Succeeded to get SideMeals meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch SideMeals meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch SideMeals meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getStarterMeals(NetworkDeligate networkDeligate) {
        Call<Meals> call = foodService.getStarterMeals();
        call.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("StarterMeals", "Succeeded to get StarterMeals meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch StarterMeals meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch StarterMeals meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getVeganMeals(NetworkDeligate networkDeligate) {
        Call<Meals> call = foodService.getVeganMeals();
        call.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Vegan", "Succeeded to get VeganMeals meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch VeganMeals meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch VeganMeals meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getBreakfastMeals(NetworkDeligate networkDeligate) {
        Call<Meals> call = foodService.getBreakfastMeals();
        call.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Breakfast", "Succeeded to get Breakfast meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch Breakfast meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch Breakfast meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

    @Override
    public void getGoatMeals(NetworkDeligate networkDeligate) {
        Call<Meals> call = foodService.getGoatMeals();
        call.enqueue(new Callback<Meals>() {

            @Override
            public void onResponse(Call<Meals> call, Response<Meals> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Meal> meals = response.body().getMeals();
                    Log.e("Goat", "Succeeded to get Goat meals: " + response.body()+meals.get(0).getStrMeal());
                    networkDeligate.onSuccessResult(response.body().getMeals());
                } else {
                    Log.e(TAG, "Failed to fetch Goat meals: " + response.message());
                    networkDeligate.onFailureResult("Failed to fetch Goat meals: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<Meals> call, Throwable t) {
                Log.e(TAG, "Retrofit onFailure: " + t.getMessage());
                networkDeligate.onFailureResult("Retrofit onFailure: " + t.getMessage());
            }
        });

    }

}






