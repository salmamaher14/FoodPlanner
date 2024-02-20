package mealdetail.view;

import model.MealDetail;

public interface OnMealDetailListener {
    void onFavButtonClick(MealDetail meal);
    void onPlannedButtonListener(MealDetail mealDetail,String date);
}
