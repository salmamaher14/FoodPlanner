package network;

public interface FoodRemoteDataSource {
    void getAllCategories(NetworkDeligate networkDeligate);

    void getVegetarianMeals(NetworkDeligate networkDeligate);

    void getBeefMeals(NetworkDeligate networkDeligate);

    void getChickenMeals(NetworkDeligate networkDeligate);

    void getDessertMeals(NetworkDeligate networkDeligate);

    void getLambMeals(NetworkDeligate networkDeligate);

    void getMiscellaneousMeals(NetworkDeligate networkDeligate);

    void getPastaMeals(NetworkDeligate networkDeligate);

    void getPorkMeals(NetworkDeligate networkDeligate);

    void getSeafoodMeals(NetworkDeligate networkDeligate);

    void getSideMeals(NetworkDeligate networkDeligate);

    void getStarterMeals(NetworkDeligate networkDeligate);

    void getVeganMeals(NetworkDeligate networkDeligate);

    void getBreakfastMeals(NetworkDeligate networkDeligate);

    void getGoatMeals(NetworkDeligate networkDeligate);

}

