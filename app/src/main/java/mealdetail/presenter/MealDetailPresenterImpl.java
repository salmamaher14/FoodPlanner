package mealdetail.presenter;

import java.util.List;

import allmeals.view.AllMealsActivity;
import mealdetail.view.MealDetailActivity;
import model.FoodRepository;
import network.NetworkDeligate;

public class MealDetailPresenterImpl implements MealDetailPresenter , NetworkDeligate {

    private MealDetailActivity _view;
    private FoodRepository _repo;
    private static final String TAG = "allmeals";

    public MealDetailPresenterImpl(MealDetailActivity _view, FoodRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }



    @Override
    public void onSuccessResult(List MealDetail) {
        _view.showData(MealDetail);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }


    @Override
    public void getMealsById(String id) {
        _repo.getMealsById(this,id);
    }
}
