//package mealdetail.view;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//
//import com.example.foodplanner.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import allmeals.presenter.AllMealsPresenterImpl;
//import allmeals.view.AllMealsAdapter;
//import favouritemeals.view.FavMealActivity;
//import mealdetail.presenter.MealDetailPresenterImpl;
//import model.FoodRepositoryImpl;
//import model.Meal;
//import model.MealDetail;
//import model.MealLocalDataSourceImpl;
//import network.FoodRemoteDataSourceImpl;
//
//public class MealDetailActivity extends AppCompatActivity implements MealDetailView,OnFavButtonListener{
//    RecyclerView mealDetailRecyclerView;
//    MealDetailAdapter mealDetailAdapter;
//    LinearLayoutManager layoutManager;
//    MealDetailPresenterImpl mealDetailPresenter;
//    private static final String TAG = "MealDetailActivity";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_meal_detail);
//        mealDetailRecyclerView=findViewById(R.id.mealDetailRv);
//        mealDetailRecyclerView.setHasFixedSize(true);
//        layoutManager=new LinearLayoutManager(this);
//        mealDetailAdapter=new MealDetailAdapter(this,new ArrayList<MealDetail>(),this);
//
//        mealDetailPresenter=new MealDetailPresenterImpl(this,FoodRepositoryImpl.getInstance(
//                FoodRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(this)
//        ));
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        mealDetailRecyclerView.setLayoutManager(layoutManager);
//        mealDetailRecyclerView.setAdapter(mealDetailAdapter);
//        String id = getIntent().getStringExtra("Meal_Id");
//        mealDetailPresenter.getMealsById(id);
//
//
//    }
//
//
//    @Override
//    public void showMealDetail(List<MealDetail> meals) {
//        mealDetailAdapter.setMealList( meals);
//        mealDetailAdapter.notifyDataSetChanged();
//
//    }
//
//    @Override
//    public void showErrMsg(String error) {
//        AlertDialog.Builder builder=new AlertDialog.Builder(this);
//        builder.setMessage(error).setTitle("An error occured");
//        AlertDialog dialog=builder.create();
//        dialog.show();
//
//    }
//
//    @Override
//    public void onFavButtonClick(MealDetail meal) {
//       mealDetailPresenter.addMealToFav(meal);
//       Intent intent=new Intent(this, FavMealActivity.class);
//       startActivity(intent);
//
//
//    }
//    //        Intent intent = new Intent(this, MealDetailActivity.class);
////        intent.putExtra("Meal_Id", meal.getIdMeal());
////        startActivity(intent);
//}