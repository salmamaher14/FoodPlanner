package favouritemeals.view;

import androidx.lifecycle.LiveData;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import model.Meal;
import model.MealDetail;

public interface FavMealView {
    public void showFavMeals(Flowable<List<Meal>> meals);
    public void showErrMsg(String error);
}
