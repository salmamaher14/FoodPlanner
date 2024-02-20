package allcategories.view;

import java.util.List;

import model.Category;
import model.MealDetail;

public interface AllCategoriesView {
    public void showData(List<Category> categories);
    public void showRandomMeal(List<MealDetail>randomMeal);
    public void showErrMsg(String error);

}
