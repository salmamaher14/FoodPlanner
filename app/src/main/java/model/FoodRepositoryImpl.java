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
    public void getVegetarianMeals(NetworkDeligate networkDeligate) {
        remoteSource.getVegetarianMeals(networkDeligate);

    }

    @Override
    public void getBeefMeals(NetworkDeligate networkDeligate) {
        remoteSource.getBeefMeals(networkDeligate);
    }

    @Override
    public void getChickenMeals(NetworkDeligate networkDeligate) {
        remoteSource.getChickenMeals(networkDeligate);

    }

    @Override
    public void getDessertMeals(NetworkDeligate networkDeligate) {
        remoteSource.getDessertMeals(networkDeligate);

    }

    @Override
    public void getLambMeals(NetworkDeligate networkDeligate) {
        remoteSource.getLambMeals(networkDeligate);

    }

    @Override
    public void getMiscellaneousMeals(NetworkDeligate networkDeligate) {
        remoteSource.getMiscellaneousMeals(networkDeligate);

    }

    @Override
    public void getPastaMeals(NetworkDeligate networkDeligate) {
        remoteSource.getPastaMeals(networkDeligate);

    }

    @Override
    public void getPorkMeals(NetworkDeligate networkDeligate) {
        remoteSource.getPorkMeals(networkDeligate);

    }

    @Override
    public void getSeafoodMeals(NetworkDeligate networkDeligate) {
        remoteSource.getSeafoodMeals(networkDeligate);

    }

    @Override
    public void getSideMeals(NetworkDeligate networkDeligate) {
        remoteSource.getSideMeals(networkDeligate);

    }

    @Override
    public void getStarterMeals(NetworkDeligate networkDeligate) {
        remoteSource.getStarterMeals(networkDeligate);

    }

    @Override
    public void getVeganMeals(NetworkDeligate networkDeligate) {
        remoteSource.getVeganMeals(networkDeligate);

    }

    @Override
    public void getBreakfastMeals(NetworkDeligate networkDeligate) {
        remoteSource.getBreakfastMeals(networkDeligate);

    }

    @Override
    public void getGoatMeals(NetworkDeligate networkDeligate) {
        remoteSource.getGoatMeals(networkDeligate);

    }

}
