package allmeals.presenter;
import android.content.Context;
import android.util.Log;
import java.util.List;
import allmeals.view.AllMealsActivity;
import model.FoodRepository;
import network.NetworkDeligate;


public class AllMealsPresenterImpl implements AllMealsPresenter , NetworkDeligate {

    private AllMealsActivity _view;
    private FoodRepository _repo;
    private static final String TAG = "allmeals";

    public AllMealsPresenterImpl(AllMealsActivity _view, FoodRepository _repo) {
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
    public void getMealsByCategoryId(String categoryName) {
        _repo.getMealsByCategoryId(this,categoryName);
    }




}


