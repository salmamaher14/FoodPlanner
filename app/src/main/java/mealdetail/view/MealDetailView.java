package mealdetail.view;

import java.util.List;

import model.Meal;
import model.MealDetail;

public interface MealDetailView {
    public void showData(List<MealDetail> meals);
    public void showErrMsg(String error);
}
