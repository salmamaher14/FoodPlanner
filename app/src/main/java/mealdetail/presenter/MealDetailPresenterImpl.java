package mealdetail.presenter;
import java.util.List;
import mealdetail.view.MealDetailFragment;
import model.FoodRepository;
import model.Meal;
import model.MealDetail;
import network.NetworkDeligate;

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
}
