//package com.example.foodplanner;
//
//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//import java.util.List;
//
//import allmeals.presenter.AllMealsPresenterImpl;
//import allmeals.view.AllMealsView;
//import model.FoodRepositoryImpl;
//import model.Meal;
//import network.FoodRemoteDataSourceImpl;
//
//public class ProvaActivity extends AppCompatActivity implements AllMealsView {
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_prova);
//
//        //AllMealsPresenterImpl allPresenter = new AllMealsPresenterImpl(this , FoodRepositoryImpl.getInstance(FoodRemoteDataSourceImpl.getInstance()));
//       // allPresenter.getVeganMeals();
//
//
//
//    }
//
//    @Override
//    public void showData(List<Meal> meals) {
//
//    }
//
//    @Override
//    public void showErrMsg(String error) {
//
//    }
//}