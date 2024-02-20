package search.view;

import model.Category;

public interface SearchClickListener {
    public void getAllMealsOfCategory(String category);
    public void getAllMealsOfArea(String area);
    public void getAllMealsOfIngrediant(String ingrediant);
    public void OnClickMeal(String id);
//    public void onClickArea(String id);
}
