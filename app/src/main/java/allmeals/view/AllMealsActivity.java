package allmeals.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import allcategories.presenter.AllCategoriesPresenterImpl;
import allcategories.view.AllCategoriesAdapter;
import allmeals.presenter.AllMealsPresenterImpl;
import mealdetail.view.MealDetailActivity;
import model.Category;
import model.FoodRepositoryImpl;
import model.Meal;
import network.FoodRemoteDataSourceImpl;

public class AllMealsActivity extends AppCompatActivity implements AllMealsView,OnMealClickListener{
    RecyclerView mealsRecyclerView;
    AllMealsAdapter allMealsAdapter;
    LinearLayoutManager layoutManager;
    AllMealsPresenterImpl allMealsPresenter;
    private static final String TAG = "allmealsActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_meals);
        mealsRecyclerView=findViewById(R.id.allMealsRV);
        mealsRecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(this);
        allMealsAdapter=new AllMealsAdapter(this,new ArrayList<>(),this);
        allMealsPresenter = new AllMealsPresenterImpl(this , FoodRepositoryImpl.getInstance(FoodRemoteDataSourceImpl.getInstance()));
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mealsRecyclerView.setLayoutManager(layoutManager);
        mealsRecyclerView.setAdapter(allMealsAdapter);
        String categoryName = getIntent().getStringExtra("CATEGORY_Name");
        allMealsPresenter.getMealsByCategoryId(categoryName);



    }

    @Override
    public void showData(List<Meal> meals) {
        allMealsAdapter.setMeals( meals);
        allMealsAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An error occured");
        AlertDialog dialog=builder.create();
        dialog.show();

    }


    @Override
    public void onMealSelected(Meal meal) {
        Intent intent = new Intent(this, MealDetailActivity.class);
        intent.putExtra("Meal_Id", meal.getIdMeal());
        startActivity(intent);

    }
}