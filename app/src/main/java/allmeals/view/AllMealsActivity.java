//package allmeals.view;
//
//import android.os.Bundle;
//
//import androidx.appcompat.app.AlertDialog;
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.foodplanner.R;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import allmeals.presenter.AllMealsPresenter;
//
//import model.Category;
//import model.FoodRepositoryImpl;
//import network.FoodRemoteDataSourceImpl;
//
//public class AllMealsActivity extends AppCompatActivity implements  AllMealsView {
//    RecyclerView allRecyclerView;
//    AllMealsAdapter categoriesAdapter;
//    AllMealsPresenter allPresenter;
//    LinearLayoutManager linearLayoutManager;
//    OnCategoryClickListener listener;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_all_categories);
//        allRecyclerView=findViewById(R.id.allCategoriesRv);
//        allRecyclerView.setHasFixedSize(true);
//        linearLayoutManager=new LinearLayoutManager(this);
//        categoriesAdapter=new AllMealsAdapter(this,new ArrayList<>(),this);
//        allPresenter = new presenter.AllMealsPresenterImpl(this , FoodRepositoryImpl.getInstance(FoodRemoteDataSourceImpl.getInstance()));
//        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        allRecyclerView.setLayoutManager(linearLayoutManager);
//        allRecyclerView.setAdapter(categoriesAdapter);
//        allPresenter.getAllCategories();
//    }
//
//
//
//    @Override
//    public void showData(List<Category> categories) {
//        categoriesAdapter.setCategories( categories);
//        categoriesAdapter.notifyDataSetChanged();
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
//    public void onCategoryListener(Category category) {
//
//    }
//}