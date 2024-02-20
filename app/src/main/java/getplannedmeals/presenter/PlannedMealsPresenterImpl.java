package getplannedmeals.presenter;

import java.util.List;

import favouritemeals.view.FavMealView;
import getplannedmeals.view.PlannedMealsView;
import io.reactivex.rxjava3.core.Flowable;
import model.FoodRepositoryImpl;
import model.PlannedMeal;

public class PlannedMealsPresenterImpl implements PlannedMealsPresenter{
    PlannedMealsView _view;
    FoodRepositoryImpl _repo;

    public PlannedMealsPresenterImpl(PlannedMealsView _view, FoodRepositoryImpl _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getPlannedMeals(String date) {
      Flowable<List<PlannedMeal>>plannedMeals= _repo.getPlannedMealsForDate(date);
      _view.showPlannedMeals(plannedMeals);

    }

    @Override
    public void removePlannedMeal(PlannedMeal meal) {
        _repo.deletePlannedMeal(meal);

    }
}
