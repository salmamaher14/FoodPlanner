package mealdetail.presenter;

import model.Meal;
import model.MealDetail;
import network.NetworkDeligate;

public interface MealDetailPresenter {
    void getMealsById( String id);
    void addMealToFav(MealDetail meal);
}
