package model;
import network.FoodRemoteDataSourceImpl;
import network.NetworkDeligate;


public class FoodRepositoryImpl implements FoodRepository {
    FoodRemoteDataSourceImpl remoteSource;
    private static FoodRepositoryImpl repo=null;

    public FoodRepositoryImpl(FoodRemoteDataSourceImpl remoteSource) {
        this.remoteSource = remoteSource;
    }

    public static FoodRepositoryImpl getInstance(FoodRemoteDataSourceImpl remoteSource){
        if(repo==null){
            repo=new FoodRepositoryImpl(remoteSource);
        }
        return repo;
    }


    @Override
    public void getAllCategories(NetworkDeligate networkDeligate) {
        remoteSource.getAllCategories(networkDeligate);

    }

    @Override
    public void getMealsByCategoryId(NetworkDeligate networkDeligate, String categoryName) {
        remoteSource.getMealsByCategoryId(networkDeligate,categoryName);
    }

    @Override
    public void getMealsById(NetworkDeligate networkDeligate, String id) {
        remoteSource.getMealsById(networkDeligate,id);
    }


}
