package getplannedmeals.view;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import allmeals.view.AllMealsFragmentArgs;
import getplannedmeals.presenter.PlannedMealsPresenterImpl;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.FoodRepositoryImpl;
import model.MealLocalDataSourceImpl;
import model.PlannedMeal;
import network.FoodRemoteDataSourceImpl;


public class PlannedMealsFragment extends Fragment implements PlannedMealsView,OnRemovePlannedMealClickListener {

    RecyclerView plannedMealsRv;
    PlannedMealsAdapter plannedMealsAdapter;
    LinearLayoutManager layoutManager;
    PlannedMealsPresenterImpl plannedMealsPresenter;
    TextView dayNameTextView;

    private static final String TAG = "PlannedMealsFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planned_meals, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        plannedMealsRv=view.findViewById(R.id.plannedMealRv);
//        dayNameTextView=view.findViewById(R.id.dayTextView);
        plannedMealsRv.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        plannedMealsAdapter=new PlannedMealsAdapter(getContext(),new ArrayList<>(),this, Navigation.findNavController(getView()));
        plannedMealsPresenter = new PlannedMealsPresenterImpl(this, FoodRepositoryImpl.getInstance(
                FoodRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(getContext())));

        layoutManager.setOrientation(RecyclerView.VERTICAL);
        plannedMealsRv.setLayoutManager(layoutManager);
        plannedMealsRv.setAdapter(plannedMealsAdapter);
        String date= PlannedMealsFragmentArgs.fromBundle(getArguments()).getDate();
//        dayNameTextView.setText(date);
        plannedMealsPresenter.getPlannedMeals(date);
    }



    @Override
    public void showPlannedMeals(Flowable<List<PlannedMeal>> meals) {
        meals.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    Log.i("showPlannedMeals", "setProductsList: subscribe" + item);
                    plannedMealsAdapter.setMeals(item);
                    plannedMealsAdapter.notifyDataSetChanged();
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
    public void removeMealSelected(PlannedMeal meal) {
        plannedMealsPresenter.removePlannedMeal(meal);

    }
}


