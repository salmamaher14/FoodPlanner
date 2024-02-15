package allmeals.presenter;
import android.content.Context;
import android.util.Log;
import java.util.List;
import allmeals.view.AllMealsFragment;
import allmeals.view.AllMealsView;
import model.FoodRepository;
import network.NetworkDeligate;


public class AllMealsPresenterImpl implements AllMealsPresenter , NetworkDeligate {

    private AllMealsView _view;
    private FoodRepository _repo;
    private static final String TAG = "allmeals";

    public AllMealsPresenterImpl(AllMealsFragment _view, FoodRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }



    @Override
    public void onSuccessResult(List Meal) {
        _view.showData(Meal);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }

    @Override
    public void getMealsByCategoryName(String categoryName) {
        _repo.getMealsByCategoryId(this,categoryName);
    }




}


