package search.presenter;
import java.util.List;
import model.FoodRepository;
import model.Meal;
import network.NetworkDeligate;
import search.view.SearchFeatureFragment;

public class SearchPresenterImpl implements SearchPresenter,NetworkDeligate {
    private SearchFeatureFragment _view;
    private FoodRepository _repo;
    private static final String TAG = "SearchFragment";


    public SearchPresenterImpl(SearchFeatureFragment _view, FoodRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void onSuccessResult(List Category) {
        _view.showCategories(Category);

    }

    @Override
    public void onFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);
    }

    @Override
    public void onSuccessFetchedCategories(List Category) {
        _view.showFetchedCategoris(Category);

    }

    @Override
    public void onSuccessGetMealsByCategoryName(List Meal) {
        _view.showMealsOfCategoryName(Meal);
    }

    @Override
    public void onSuccessGetMealsByAreaName(List Meal) {
        _view.showMealsOfAreaName(Meal);
    }

    @Override
    public void onSuccessGetMealsByMainIngrediant(List Meal) {
        _view.showMealsOfIngrediant(Meal);
    }

    @Override
    public void onSuccessGetAllIngrediants(List Ingrediant) {
        _view.showAllIngrediants(Ingrediant);
    }

    @Override
    public void onSuccessGetAllArea(List Area) {
        _view.showAllAreas(Area);
    }

    @Override
    public void OnSuccessFetchedAreas(List Area) {
        _view.showFetchedAreas(Area);
    }

    @Override
    public void OnSuccessFetchedIngrediants(List Ingrediant) {
        _view.showFetchedIngrediants(Ingrediant);

    }

    @Override
    public void OnSuccessgetMealsByFirstLetter(List Meal) {
        _view.showMealsByFirstLetter(Meal);
    }

    @Override
    public void OnSuccessgetRandomMeal(List randomMeal) {

    }


    @Override
    public void filterByMainIngrediant(String ingrediant) {
        _repo.getMealsByMainIngrediants(this,ingrediant);

    }

    @Override
    public void filterByCategory(String category) {
        _repo.filterMealsByCategoryName(this,category);

    }

    @Override
    public void filterByArea(String area) {
        _repo.getMealsByArea(this,area);

    }

    @Override
    public void getAllCategories() {
        _repo.getAllCategories(this);
    }


    @Override
    public void getAllAreas() {
        _repo.getAllAreas(this);

    }

    @Override
    public void getAllIngrediants() {
        _repo.getAllIngrediants(this);

    }

    @Override
    public void getFetchedCategories(String category) {
        _repo.getFetchedCategories(this,category);
    }

    @Override
    public void getFetchedCountries(String country) {
        _repo.getFetchedAreas(this,country);
    }

    @Override
    public void getFetchedMealsByFirstLetter(String mealText) {
        _repo.filterMealsByFirstLetter(this,mealText);

    }

    @Override
    public void getFetchedIngrediants(String ingrediant) {
        _repo.getFetchedIngrediants(this,ingrediant);
    }


}

/*
public class MealDetailPresenterImpl implements MealDetailPresenter , NetworkDeligate {

    private MealDetailFragment _view;
    private FoodRepository _repo;
    private static final String TAG = "MealDetailFragment";

    public MealDetailPresenterImpl(MealDetailFragment _view, FoodRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }



    @Override
    public void onSuccessResult(List MealDetail) {
        _view.showMealDetail(MealDetail);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }


    @Override
    public void getMealsById(String id) {
        _repo.getMealsById(this,id);
    }

    @Override
    public void addMealToFav(MealDetail meal) {
        _repo.insertMeal(meal);
    }

    @Override
    public void addMealToPlanning(MealDetail meal, String date) {
        _repo.insertPlannedMeal(meal,date);
    }
}

 */
