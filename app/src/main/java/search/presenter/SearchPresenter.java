package search.presenter;


public interface SearchPresenter {
    void  filterByMainIngrediant(String Ingrediant);
    void filterByCategory(String category);
    void filterByArea(String area);
    void getAllCategories();
    void getAllAreas();
    void getAllIngrediants();
    void getFetchedCategories(String category);
    void getFetchedCountries(String country);
    void getFetchedMealsByFirstLetter(String mealText);

    void getFetchedIngrediants(String ingrediant);


}
