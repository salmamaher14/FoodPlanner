 package allcategories.presenter;;

 import android.content.Context;
 import android.content.Intent;
 import android.util.Log;

 import java.util.List;

 import allcategories.view.AllCategoriesView;

 import model.FoodRepository;
 import model.Category;
 import network.NetworkDeligate;

public class AllCategoriesPresenterImpl implements AllCategoriesPresenter, NetworkDeligate {
    private AllCategoriesView _view;
    private FoodRepository _repo;
    private Context context;
    private static final String TAG ="allmeals";

    public AllCategoriesPresenterImpl (AllCategoriesView _view , FoodRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void onSuccessResult(List Category ) {
        _view.showData(Category);

    }

    @Override
    public void onFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);

    }

    @Override
    public void onSuccessFetchedCategories(List fetchedCategories) {

    }

    @Override
    public void onSuccessGetMealsByCategoryName(List meals) {

    }

    @Override
    public void onSuccessGetMealsByAreaName(List meals) {

    }

    @Override
    public void onSuccessGetMealsByMainIngrediant(List meals) {

    }

    @Override
    public void onSuccessGetAllIngrediants(List ingrediants) {

    }

    @Override
    public void onSuccessGetAllArea(List areas) {

    }

    @Override
    public void OnSuccessFetchedAreas(List fetchedAreas) {

    }

    @Override
    public void OnSuccessFetchedIngrediants(List fetchedIngrediants) {

    }

    @Override
    public void OnSuccessgetMealsByFirstLetter(List fetchedMeals) {

    }


    @Override
    public void getAllCategories() {
        Log.e(TAG, "getAllCategories: " );
        _repo.getAllCategories(this);

    }




}
