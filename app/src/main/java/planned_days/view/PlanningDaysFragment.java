package planned_days.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
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

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import model.FoodRepositoryImpl;
import model.MealLocalDataSourceImpl;
import network.FoodRemoteDataSourceImpl;
import planned_days.presenter.PlanningDaysPresenterImpl;



public class PlanningDaysFragment extends Fragment implements PlanningDaysView, OnViewPlannedMealListener {
    RecyclerView planningDaysRv;
   PlanningDaysAdapter planningDaysAdapter;
    LinearLayoutManager layoutManager;
    PlanningDaysPresenterImpl plannedMealsPresenter;
    private static final String TAG = "PlannedMealsFragment";




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_planning_days, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        planningDaysRv=view.findViewById(R.id.daysRv);
        planningDaysRv.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        planningDaysAdapter=new PlanningDaysAdapter(getContext(),new ArrayList<String>(),this);

        plannedMealsPresenter=new PlanningDaysPresenterImpl(this, FoodRepositoryImpl.getInstance(
                FoodRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext())
        ));
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        planningDaysRv.setLayoutManager(layoutManager);
        planningDaysRv.setAdapter(planningDaysAdapter);
        plannedMealsPresenter.getAllPlanningDayes();

    }

    @Override

    public void showPlanningDays(Flowable<List<String>> days) {
        days.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(item -> {
                    Log.i(TAG, "showPlanningDays: subscribe" + item);
                    if (item != null) {
                        planningDaysAdapter.setPlanningDays(item);
                        planningDaysAdapter.notifyDataSetChanged();
                    } else {
                        Log.e(TAG, "Received null list of planning days");
                        // Handle the case when the received list is null
                    }
                }, error -> {
                    Log.e(TAG, "Error fetching planning days: " + error.getMessage(), error);
                    Toast.makeText(getContext(), "Error fetching planning days: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                }, () -> {
                    Log.i(TAG, "showPlanningDays: OnComplete");
                });
    }


    @Override
    public void showErrMsg(String error) {

    }

    @Override
    public void viewPlanningMeals(String date) {
        PlanningDaysFragmentDirections.ActionPlanningDaysFragmentToPlannedMealsFragment
                action=PlanningDaysFragmentDirections.actionPlanningDaysFragmentToPlannedMealsFragment(date);
        Navigation.findNavController(getView()).navigate(action);
//        AllCategoriesFragmentDirections.ActionAllCategoriesFragmentToAllMealsFragment
//                action=AllCategoriesFragmentDirections.actionAllCategoriesFragmentToAllMealsFragment(category.getStrCategory());
//        Navigation.findNavController(getView()).navigate(action);

    }
}