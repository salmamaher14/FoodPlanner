package network;
import android.util.Log;

import java.util.List;
import java.util.stream.Collectors;

import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.Area;
import model.Areas;
import model.Categories;
import model.Category;
import model.Ingrediant;
import model.Ingrediants;
import model.Meal;
import model.MealDetail;
import model.Meals;
import model.AllMealsDetails;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FoodRemoteDataSourceImpl implements FoodRemoteDataSource {
    private static final String BASE_URL = "https://www.themealdb.com/";
    private static final String TAG = "RemoteDataSourceImpl";
    private static FoodRemoteDataSourceImpl client = null;
    private FoodService foodService;
    Single<Meals> mealsCall;
    Single<Categories> categoriesCall;
    Single<AllMealsDetails> mealsDetailsCall;

    Single<Areas> areasCall;
    Single<Ingrediants> ingrediantsCall;


    public FoodRemoteDataSourceImpl() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        foodService = retrofit.create(FoodService.class);

    }

    public static FoodRemoteDataSourceImpl getInstance() {
        if (client == null) {
            client = new FoodRemoteDataSourceImpl();
        }
        return client;
    }


    @Override
    public void getAllCategories(NetworkDeligate networkDeligate) {

        categoriesCall = foodService.getAllCategories();
        categoriesCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            //if (response.isSuccessful()) {
                            List<Category> categories = response.getCategories();
                            Log.i(TAG, "onResponse: Callback : " + " " + categories);
                            networkDeligate.onSuccessResult(categories);
                        },
                        throwable -> {
                            Log.i(TAG, "onFailure: " + throwable.getMessage());
                            networkDeligate.onFailureResult(throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );


    }

    @Override
    public void getMealsByCategoryName(NetworkDeligate networkDeligate, String categoryName) {
        mealsCall = foodService.getMealsByCategoryName(categoryName);
        mealsCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            List<Meal> meals = response.getMeals();
                            networkDeligate.onSuccessResult(meals);
                        },
                        throwable -> {
                            networkDeligate.onFailureResult(throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );


    }

    @Override
    public void getMealsById(NetworkDeligate networkDeligate, String id) {
        mealsDetailsCall = foodService.getMealById(id);
        mealsDetailsCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()
        ).subscribe(
                response -> {
                    List<MealDetail> mealDetailList = response.getMealsDetails();
                    networkDeligate.onSuccessResult(mealDetailList);
                },
                throwable -> {
                    networkDeligate.onFailureResult(throwable.getMessage());
                    throwable.printStackTrace();
                }
        );


    }

    @Override
    public void getAllAreas(NetworkDeligate networkDeligate) {
        Single<Areas> areaCall = foodService.getAllAreas();
        areaCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()
        ).subscribe(
                response -> {
                    List<Area> areasList = response.getAreas();

                    networkDeligate.onSuccessGetAllArea(areasList);

                },

                throwable -> {
                    Log.i("getAllAreasfail", "onFailure: " + throwable.getMessage());
                    networkDeligate.onFailureResult(throwable.getMessage());
                    throwable.printStackTrace();
                }
        );
    }

    @Override
    public void getAllIngrediants(NetworkDeligate networkDeligate) {
        Single<Ingrediants> ingrediantCall = foodService.getAllIngrediants();
        ingrediantCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()
        ).subscribe(
                response -> {
                    List<Ingrediant> ingrediantsList = response.getIngrediants();
                    Log.i("getAllIngrediants", "getAllIngrediants: "+ingrediantsList.get(0).getStrIngredient());

                    networkDeligate.onSuccessGetAllIngrediants(ingrediantsList);


                },

                throwable -> {
                    Log.i("getAllAreasfail", "onFailure: " + throwable.getMessage());
                    networkDeligate.onFailureResult(throwable.getMessage());
                    throwable.printStackTrace();
                }
        );

    }


    @Override
    public void getMealsByArea(NetworkDeligate networkDeligate, String areaName) {
        mealsCall = foodService.getMealsByArea(areaName);
        mealsCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()
        ).subscribe(
                response -> {
                    List<Meal> mealsList = response.getMeals();
                    networkDeligate.onSuccessGetMealsByAreaName(mealsList);
                },
                throwable -> {
                    networkDeligate.onFailureResult(throwable.getMessage());
                    throwable.printStackTrace();
                }
        );

    }

    @Override
    public void getMealsByMainIngrediant(NetworkDeligate networkDeligate, String mainIngrediant) {
        mealsCall = foodService.getMealsByMainIngrediant(mainIngrediant);
        mealsCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()
        ).subscribe(
                response -> {
                    List<Meal> mealsList = response.getMeals();
                    networkDeligate.onSuccessGetMealsByMainIngrediant(mealsList);
                },
                throwable -> {
                    networkDeligate.onFailureResult(throwable.getMessage());
                    throwable.printStackTrace();
                }
        );

    }

    @Override
    public void fetchCategories(NetworkDeligate networkDeligate, String categoryItem) {
        categoriesCall = foodService.getAllCategories();
        categoriesCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            List<Category> categories = response.getCategories();
                            Log.i(TAG, "onResponse: Callback : " + " " + categories);
                            // Apply filtering here
                            List<Category> filteredCategories = categories.stream()
                                    .filter(category -> category.getStrCategory().toLowerCase().contains(categoryItem.toLowerCase()))
                                    .collect(Collectors.toList());
                            networkDeligate.onSuccessFetchedCategories(filteredCategories);
                        },
                        throwable -> {
                            Log.i(TAG, "onFailure: " + throwable.getMessage());
                            networkDeligate.onFailureResult(throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );
    }

    @Override
    public void fetchAreas(NetworkDeligate networkDeligate, String area) {
        areasCall = foodService.getAllAreas();
        areasCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            List<Area> areas = response.getAreas();
                            Log.i(TAG, "onResponse: Callback : " + " " + areas);
                            // Apply filtering here
                            List<Area> filteredAreas = areas.stream()
                                    .filter(areaItem -> areaItem.getStrArea().toLowerCase().contains(area.toLowerCase()))
                                    .collect(Collectors.toList());
                            networkDeligate.OnSuccessFetchedAreas(filteredAreas);
                        },
                        throwable -> {
                            Log.i(TAG, "onFailure: " + throwable.getMessage());
                            networkDeligate.onFailureResult(throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );

    }

    @Override
    public void fetchIngrediants(NetworkDeligate networkDeligate, String ingrediant) {
        ingrediantsCall = foodService.getAllIngrediants();
        ingrediantsCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            List<Ingrediant> ingrediants = response.getIngrediants();
                            Log.i(TAG, "onResponse: Callback : " + " " + ingrediants);
                            // Apply filtering here
                            List<Ingrediant> filteredIngrediant = ingrediants.stream()
                                    .filter(ingrediantItem -> ingrediantItem.getStrIngredient().toLowerCase().contains(ingrediant.toLowerCase()))
                                    .collect(Collectors.toList());
                            networkDeligate.OnSuccessFetchedIngrediants(filteredIngrediant);
                        },
                        throwable -> {
                            Log.i(TAG, "onFailure: " + throwable.getMessage());
                            networkDeligate.onFailureResult(throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );


    }

    @Override
    public void filterMealsByCategoryName(NetworkDeligate networkDeligate, String categoryName) {
        mealsCall = foodService.getMealsByCategoryName(categoryName);
        mealsCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            List<Meal> meals = response.getMeals();
                            networkDeligate.onSuccessGetMealsByCategoryName(meals);
                        },
                        throwable -> {
                            networkDeligate.onFailureResult(throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );

    }

    @Override
    public void filterMealByFirstLetter(NetworkDeligate networkDeligate, String mealName) {
        mealsCall = foodService.getMealByFirstLetter(mealName);
        mealsCall.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        response -> {
                            List<Meal> meals = response.getMeals();
                            Log.i(TAG, "onResponse: Callback : " + " " + meals);
                            // Apply filtering here
                            List<Meal> filteredMeals = meals.stream()
                                    .filter(mealItem -> mealItem.getStrMeal().toLowerCase().contains(mealName.toLowerCase()))
                                    .collect(Collectors.toList());
                            networkDeligate.OnSuccessgetMealsByFirstLetter(filteredMeals);
                        },
                        throwable -> {
                            Log.i(TAG, "onFailure: " + throwable.getMessage());
                            networkDeligate.onFailureResult(throwable.getMessage());
                            throwable.printStackTrace();
                        }
                );
    }


}