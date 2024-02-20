package mealdetail.view;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.foodplanner.R;
import java.util.ArrayList;
import java.util.List;
import mealdetail.presenter.MealDetailPresenterImpl;
import model.FoodRepositoryImpl;
import model.MealDetail;
import model.MealLocalDataSourceImpl;
import network.FoodRemoteDataSourceImpl;



public class MealDetailFragment extends Fragment implements MealDetailView, OnMealDetailListener {

    RecyclerView mealDetailRecyclerView;
    MealDetailAdapter mealDetailAdapter;
    LinearLayoutManager layoutManager;
    MealDetailPresenterImpl mealDetailPresenter;
    private static final String TAG = "MealDetailFragment";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_meal_detail, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mealDetailRecyclerView=view.findViewById(R.id.mealDetailRv);
        mealDetailRecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        mealDetailAdapter=new MealDetailAdapter(getContext(),new ArrayList<MealDetail>(),this);

        mealDetailPresenter=new MealDetailPresenterImpl(this, FoodRepositoryImpl.getInstance(
                FoodRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext())
        ));
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        mealDetailRecyclerView.setLayoutManager(layoutManager);
        mealDetailRecyclerView.setAdapter(mealDetailAdapter);
        String id=MealDetailFragmentArgs.fromBundle(getArguments()).getMealId();
        mealDetailPresenter.getMealsById(id);
//        String id = getIntent().getStringExtra("Meal_Id");
//        mealDetailPresenter.getMealsById(id);
    }

    @Override
    public void showMealDetail(List<MealDetail> meals) {
        mealDetailAdapter.setMealList( meals);
        mealDetailAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An error occured");
        AlertDialog dialog=builder.create();
        dialog.show();

    }





    @Override
    public void onFavButtonClick(MealDetail meal) {
        mealDetailPresenter.addMealToFav(meal);
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(MealDetailFragmentDirections.actionMealDetailFragmentToFavMealFragment());

    }

    @Override
    public void onPlannedButtonListener(MealDetail mealDetail , String date) {
        mealDetailPresenter.addMealToPlanning(mealDetail,date);
        NavController navController = Navigation.findNavController(getView());
        navController.navigate(MealDetailFragmentDirections.actionMealDetailFragmentToPlanningDaysFragment());

    }

}