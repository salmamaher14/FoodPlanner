package search.view;

import java.util.List;

import model.Area;
import model.Category;
import model.Ingrediant;
import model.Meal;
import model.MealDetail;

public interface SearchView{
    public void showCategories(List<Category> categories);
    public void showFetchedCategoris(List<Category>fetchedCategories);
    public void showFetchedAreas(List<Area>fetchedAreas);
    public void showMealsOfCategoryName(List<Meal> meals);
    public void showMealsOfAreaName(List<Meal> meals);
    public void showMealsOfIngrediant(List<Meal> meals);
    public void showAllAreas(List<Area>areas);
    public void showAllIngrediants(List<Ingrediant>ingrediants);
    public void showFetchedIngrediants(List<Ingrediant>ingrediants);
    public void  showMealsByFirstLetter(List<Meal>meals);

    public void showErrMsg(String error);
}
