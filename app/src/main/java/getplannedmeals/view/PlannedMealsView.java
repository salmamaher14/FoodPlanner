package getplannedmeals.view;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import model.Meal;
import model.PlannedMeal;

public interface PlannedMealsView {
    public void showPlannedMeals(Flowable<List<PlannedMeal>> meals);
    public void showErrMsg(String error);
}
