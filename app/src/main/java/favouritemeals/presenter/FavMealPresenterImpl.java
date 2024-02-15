package favouritemeals.presenter;

import android.util.Log;

import java.util.List;

import favouritemeals.view.FavMealView;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.FoodRepositoryImpl;
import model.Meal;
import model.MealDetail;

public class FavMealPresenterImpl implements FavMealPresenter{
    FavMealView _view;
    FoodRepositoryImpl _repo;

    public FavMealPresenterImpl(FavMealView _view, FoodRepositoryImpl _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void getFavMeals() {
         Flowable<List<Meal>> favMeals =_repo.getStoredFavMeals();
//        Log.i("favmealsdetails", "getFaMeals: "+favMeals.subscribeOn(Schedulers.newThread()).observeOn(
//                AndroidSchedulers.mainThread()).subscribe(item->item.get(0).getStrInstructions())
//        );
        _view.showFavMeals(favMeals);

    }

    @Override
    public void removeFromFav(Meal meal) {
        _repo.deleteMeal(meal);

    }
}
