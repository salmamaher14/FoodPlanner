package network;

public interface FoodRemoteDataSource {
    void getAllCategories(NetworkDeligate networkDeligate);
    void getMealsByCategoryId(NetworkDeligate networkDeligate,String categoryName);
    void getMealsById(NetworkDeligate networkDeligate,String id);


}

