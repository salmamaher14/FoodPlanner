//package favouritemeals.view;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.widget.Toast;
//
//import com.example.foodplanner.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import allmeals.presenter.AllMealsPresenterImpl;
//import allmeals.view.AllMealsAdapter;
//import favouritemeals.presenter.FavMealPresenter;
//import favouritemeals.presenter.FavMealPresenterImpl;
//import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
//import io.reactivex.rxjava3.core.Flowable;
//import io.reactivex.rxjava3.schedulers.Schedulers;
//import mealdetail.view.MealDetailActivity;
//import model.FoodRepositoryImpl;
//import model.Meal;
//import model.MealDetail;
//import model.MealLocalDataSourceImpl;
//import network.FoodRemoteDataSourceImpl;
//
//public class FavMealActivity extends AppCompatActivity implements FavMealView,OnFavMealClickListener {
//
//    RecyclerView favMealsRv;
//    FavMealAdapter favMealAdapter;
//    LinearLayoutManager layoutManager;
//    FavMealPresenterImpl favMealPresenter;
//    private static final String TAG = "FavMealActivity";
//
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_fav_meal);
//        favMealsRv=findViewById(R.id.favMealsRv);
//        favMealsRv.setHasFixedSize(true);
//        layoutManager=new LinearLayoutManager(this);
//        favMealAdapter=new FavMealAdapter(this,new ArrayList<>(),this);
//        favMealPresenter = new FavMealPresenterImpl(this, FoodRepositoryImpl.getInstance(
//                FoodRemoteDataSourceImpl.getInstance(),
//                MealLocalDataSourceImpl.getInstance(this)));
//
//        layoutManager.setOrientation(RecyclerView.VERTICAL);
//        favMealsRv.setLayoutManager(layoutManager);
//        favMealsRv.setAdapter(favMealAdapter);
//        favMealPresenter.getFavMeals();
//
//
//    }
//
//    @Override
//    public void showFavMeals(Flowable<List<MealDetail>>meals) {
//        meals.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(item -> {
//                    Log.i("TAG", "setProductsList: subscribe" + item);
//                    favMealAdapter.setMeals(item);
//                    favMealAdapter.notifyDataSetChanged();
//                }, error -> {
//                    Log.e("TAG", "setProductsList: Error", error);
//                    Toast.makeText(FavMealActivity.this, "Error fetching products: " + error.getMessage(), Toast.LENGTH_SHORT).show();
//                }, () -> {
//                    Log.i("TAG", "setProductsList: OnComplete");
//                });
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
//
//    @Override
//    public void removeMealSelected(MealDetail meal) {
//        favMealPresenter.removeFromFav(meal);
////        Intent intent = new Intent(this, MealDetailActivity.class);
////        intent.putExtra("Meal_Id", meal.getIdMeal());
////        startActivity(intent);
//
//    }
//}