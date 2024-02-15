package model;
import java.util.List;
import io.reactivex.rxjava3.core.Flowable;
import network.FoodRemoteDataSource;
import network.NetworkDeligate;


public class FoodRepositoryImpl implements FoodRepository {
    FoodRemoteDataSource remoteSource;
    MealLocalDataSource localSource;
    private static FoodRepositoryImpl repo=null;

    public FoodRepositoryImpl(FoodRemoteDataSource remoteSource, MealLocalDataSource localSource) {
        this.remoteSource = remoteSource;
        this.localSource = localSource;
    }

    public static FoodRepositoryImpl getInstance(FoodRemoteDataSource remoteSource,MealLocalDataSource localSource){
        if(repo==null){
            repo=new FoodRepositoryImpl(remoteSource,localSource);
        }
        return repo;
    }




    @Override
    public void getAllCategories(NetworkDeligate networkDeligate) {
        remoteSource.getAllCategories(networkDeligate);

    }

    @Override
    public void getMealsByCategoryId(NetworkDeligate networkDeligate, String categoryName) {
        remoteSource.getMealsByCategoryName(networkDeligate,categoryName);
    }

    @Override
    public void getMealsById(NetworkDeligate networkDeligate, String id) {
        remoteSource.getMealsById(networkDeligate,id);
    }

    @Override
    public Flowable<List<Meal>> getStoredFavMeals() {
        return localSource.getFavMeals();
    }

    @Override
    public void insertMeal(MealDetail meal) {
        localSource.insertMeal(meal);

    }

    @Override
    public void deleteMeal(Meal meal) {
        localSource.deleteMeal(meal);

    }


}
