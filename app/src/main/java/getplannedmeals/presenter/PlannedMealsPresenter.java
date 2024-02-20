package getplannedmeals.presenter;

import model.PlannedMeal;

public interface PlannedMealsPresenter {
    void getPlannedMeals(String date);
    void removePlannedMeal(PlannedMeal meal);
}
