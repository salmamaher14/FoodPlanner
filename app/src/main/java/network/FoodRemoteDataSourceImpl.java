package network;
import android.util.Log;
import java.util.List;
import hu.akarnokd.rxjava3.retrofit.RxJava3CallAdapterFactory;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.Categories;
import model.Category;
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
}






