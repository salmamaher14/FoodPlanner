package network;
import java.util.List;
import model.Category;
public interface NetworkDeligate<T> {
    void onSuccessResult(List<T> data);
    void onFailureResult(String errorMsg);

    void onSuccessFetchedCategories(List<T> fetchedCategories);
    void onSuccessGetMealsByCategoryName(List<T> meals);
    void onSuccessGetMealsByAreaName(List<T> meals);
    void onSuccessGetMealsByMainIngrediant(List<T> meals);
    void onSuccessGetAllIngrediants(List<T> ingrediants);

    void onSuccessGetAllArea(List<T> areas);
    void OnSuccessFetchedAreas(List<T>fetchedAreas);
    void OnSuccessFetchedIngrediants(List<T>fetchedIngrediants);
    void OnSuccessgetMealsByFirstLetter(List<T>fetchedMeals);
}

