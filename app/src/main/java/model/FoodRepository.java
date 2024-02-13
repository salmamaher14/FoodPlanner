package model;

import network.NetworkDeligate;

public interface FoodRepository {

    public void getAllCategories(NetworkDeligate networkDeligate);
    void getMealsByCategoryId(NetworkDeligate networkDeligate,String categoryName);
    void getMealsById(NetworkDeligate networkDeligate,String id);




}
