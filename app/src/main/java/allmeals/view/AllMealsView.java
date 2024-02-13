package allmeals.view;

import java.util.List;

import model.Category;
import model.Meal;

public interface AllMealsView {
    public void showData(List<Meal> meals);
    public void showErrMsg(String error);


}
