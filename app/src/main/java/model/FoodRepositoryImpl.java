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
    public void getAllIngrediants(NetworkDeligate networkDeligate) {
        remoteSource.getAllIngrediants(networkDeligate);
    }

    @Override
    public void getMealsByCategoryName(NetworkDeligate networkDeligate, String categoryName) {
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



    @Override
    public Flowable<List<String>> getPlannedDays() {
        return  localSource.getPlannedDays();
    }


    @Override
    public void insertPlannedMeal(MealDetail meal,String date) {
        localSource.insertPlannedMeal(meal, date);

    }

    @Override
    public void deletePlannedMeal(PlannedMeal meal) {
        localSource.deletePlannedMeal(meal);

    }

    @Override
    public Flowable<List<PlannedMeal>> getPlannedMealsForDate(String selectedDate) {
        return localSource.getPlannedMealsForDate(selectedDate);
    }

    @Override
    public void getMealsByMainIngrediants(NetworkDeligate networkDeligate,String ingrediant) {
        remoteSource.getMealsByMainIngrediant(networkDeligate,ingrediant);

    }

    @Override
    public void getMealsByArea(NetworkDeligate networkDeligate,String area) {
        remoteSource.getMealsByArea(networkDeligate,area);

    }

    @Override
    public void getAllAreas(NetworkDeligate networkDeligate) {
        remoteSource.getAllAreas(networkDeligate);
    }

    @Override
    public void getFetchedCategories(NetworkDeligate networkDeligate,String category) {
        remoteSource.fetchCategories(networkDeligate,category);
    }

    @Override
    public void getFetchedAreas(NetworkDeligate networkDeligate, String area) {
        remoteSource.fetchAreas(networkDeligate,area);
    }

    @Override
    public void getFetchedIngrediants(NetworkDeligate networkDeligate, String ingrediant) {
        remoteSource.fetchIngrediants( networkDeligate,  ingrediant);
    }

    @Override
    public void filterMealsByCategoryName(NetworkDeligate networkDeligate, String categoryName) {
        remoteSource.filterMealsByCategoryName(networkDeligate,categoryName);
    }

    @Override
    public void filterMealsByFirstLetter(NetworkDeligate networkDeligate, String mealName) {
        remoteSource.filterMealByFirstLetter(networkDeligate,mealName);
    }

    @Override
    public void getRandomMeal(NetworkDeligate networkDeligate) {
        remoteSource.getRandomMeal(networkDeligate);
    }


}
