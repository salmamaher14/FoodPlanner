package favouritemeals.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import favouritemeals.presenter.FavMealPresenterImpl;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.FoodRepositoryImpl;
import model.Meal;
import model.MealLocalDataSourceImpl;
import network.FoodRemoteDataSourceImpl;


public class FavMealFragment extends Fragment implements FavMealView, OnRemoveMealClickListener {
    RecyclerView favMealsRv;
    FavMealAdapter favMealAdapter;
    LinearLayoutManager layoutManager;
    FavMealPresenterImpl favMealPresenter;
    private static final String TAG = "FavMealFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fav_meal, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        favMealsRv=view.findViewById(R.id.favMealsRv);
        favMealsRv.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        favMealAdapter=new FavMealAdapter(getContext(),new ArrayList<>(),this);
        favMealPresenter = new FavMealPresenterImpl(this, FoodRepositoryImpl.getInstance(
                FoodRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(getContext())));

        layoutManager.setOrientation(RecyclerView.VERTICAL);
        favMealsRv.setLayoutManager(layoutManager);
        favMealsRv.setAdapter(favMealAdapter);
        favMealPresenter.getFavMeals();
    }

    @Override
    public void showFavMeals(Flowable<List<Meal>>meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    Log.i("TAG", "setProductsList: subscribe" + item);
                    favMealAdapter.setMeals(item);
                    favMealAdapter.notifyDataSetChanged();
                }, error -> {
                    Log.e("TAG", "setProductsList: Error", error);
                    Toast.makeText(getContext(), "Error fetching products: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }, () -> {
                    Log.i("TAG", "setProductsList: OnComplete");
                });

    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An error occured");
        AlertDialog dialog=builder.create();
        dialog.show();

    }


    @Override
    public void removeMealSelected(Meal meal) {
        favMealPresenter.removeFromFav(meal);
//        Intent intent = new Intent(this, MealDetailActivity.class);
//        intent.putExtra("Meal_Id", meal.getIdMeal());
//        startActivity(intent);

    }
}