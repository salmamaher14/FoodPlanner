package allmeals.view;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import allmeals.presenter.AllMealsPresenterImpl;
import mealdetail.view.MealDetailFragment;
import mealdetail.view.MealDetailFragmentDirections;
import model.FoodRepositoryImpl;
import model.Meal;
import model.MealLocalDataSourceImpl;
import network.FoodRemoteDataSourceImpl;

public class AllMealsFragment extends Fragment implements AllMealsView,OnMealClickListener{

    RecyclerView mealsRecyclerView;
    AllMealsAdapter allMealsAdapter;
    LinearLayoutManager layoutManager;
    AllMealsPresenterImpl allMealsPresenter;
    private static final String TAG = "allmealsFragment";



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealsRecyclerView=view.findViewById(R.id.allMealsRV);
        mealsRecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        allMealsAdapter=new AllMealsAdapter(getContext(),new ArrayList<>(),this);
        allMealsPresenter = new AllMealsPresenterImpl(this , FoodRepositoryImpl.getInstance(
                FoodRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext())));
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mealsRecyclerView.setLayoutManager(layoutManager);
        mealsRecyclerView.setAdapter(allMealsAdapter);
        String categoryName=AllMealsFragmentArgs.fromBundle(getArguments()).getCategoryName();
        allMealsPresenter.getMealsByCategoryName(categoryName);

//        String categoryName = getIntent().getStringExtra("CATEGORY_Name");
//        allMealsPresenter.getMealsByCategoryId(categoryName);

    }

    @Override
    public void showData(List<Meal> meals) {
        allMealsAdapter.setMeals( meals);
        allMealsAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An error occured");
        AlertDialog dialog=builder.create();
        dialog.show();

    }


    @Override
    public void onMealSelected(Meal meal) {
        AllMealsFragmentDirections.ActionAllMealsFragmentToMealDetailFragment action=
                AllMealsFragmentDirections.actionAllMealsFragmentToMealDetailFragment(meal.getIdMeal());
        Navigation.findNavController(getView()).navigate(action);


//        Intent intent = new Intent(this, MealDetailActivity.class);
//        intent.putExtra("Meal_Id", meal.getIdMeal());
//        startActivity(intent);

    }
}