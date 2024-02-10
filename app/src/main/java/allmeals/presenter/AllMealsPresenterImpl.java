package allmeals.presenter;
import android.content.Context;
import android.util.Log;
import com.example.foodplanner.ProvaActivity;
import java.util.List;

import db.AppDataBase;
import db.FoodLocalDataSourceImpl;
import model.FoodRepository;
import network.NetworkDeligate;


public class AllMealsPresenterImpl implements AllMealsPresenter , NetworkDeligate {

    private ProvaActivity _view;
    private FoodRepository _repo;
    private static final String TAG = "allmeals";

    public AllMealsPresenterImpl(ProvaActivity _view, FoodRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }



    @Override
    public void onSuccessResult(List Meal) {
        _view.showData(Meal);
    }

    @Override
    public void onFailureResult(String errorMsg) {

    }

    @Override
    public void getVegetarianMeals() {
        Log.e(TAG, "getVegetarianMeals: ");
        Context context;

        _repo.getVegetarianMeals(this);
//        FoodLocalDataSourceImpl localDataSource=new FoodLocalDataSourceImpl(context);


    }


    @Override
    public void getBeefMeals() {
        _repo.getBeefMeals(this);

    }

    @Override
    public void getChickenMeals() {
        _repo.getChickenMeals(this);

    }

    @Override
    public void getDessertMeals() {
        _repo.getDessertMeals(this);

    }

    @Override
    public void getLambMeals() {
        _repo.getLambMeals(this);

    }

    @Override
    public void getMiscellaneousMeals() {
        _repo.getMiscellaneousMeals(this);

    }

    @Override
    public void getPastaMeals() {
        _repo.getPastaMeals(this);

    }

    @Override
    public void getPorkMeals() {
        _repo.getPorkMeals(this);

    }

    @Override
    public void getSeafoodMeals() {
        _repo.getSeafoodMeals(this);

    }

    @Override
    public void getSideMeals() {
        _repo.getSideMeals(this);

    }

    @Override
    public void getStarterMeals() {
        _repo.getStarterMeals(this);

    }

    @Override
    public void getVeganMeals() {
        _repo.getVeganMeals(this);

    }

    @Override
    public void getBreakfastMeals() {
        _repo.getBreakfastMeals(this);

    }

    @Override
    public void getGoatMeals() {
        _repo.getGoatMeals(this);

    }
}


