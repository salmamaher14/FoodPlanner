package favouritemeals.presenter;

import model.Meal;
import model.MealDetail;

public interface FavMealPresenter {
   void getFavMeals();
    public void  removeFromFav(Meal meal);

}
