package allcategories.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import allcategories.presenter.AllCategoriesPresenter;
import allcategories.presenter.AllCategoriesPresenterImpl;
import allmeals.view.AllMealsActivity;
import model.FoodRepositoryImpl;
import model.Category;
import network.FoodRemoteDataSourceImpl;

public class AllCategoriesActivity extends AppCompatActivity implements OnCategoryClickListener, AllCategoriesView{
    RecyclerView allRecyclerView;
    AllCategoriesAdapter categoriesAdapter;
    AllCategoriesPresenter allPresenter;
    LinearLayoutManager linearLayoutManager;
    OnCategoryClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_categories);
        allRecyclerView=findViewById(R.id.allCategoriesRv);
        allRecyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(this);
        categoriesAdapter=new AllCategoriesAdapter(this,new ArrayList<>(),this);
        allPresenter = new AllCategoriesPresenterImpl(this , FoodRepositoryImpl.getInstance(FoodRemoteDataSourceImpl.getInstance()));
        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);
        allRecyclerView.setLayoutManager(linearLayoutManager);
        allRecyclerView.setAdapter(categoriesAdapter);
        allPresenter.getAllCategories();
    }



    @Override
    public void showData(List<Category> categories) {
        categoriesAdapter.setCategories( categories);
        categoriesAdapter.notifyDataSetChanged();

    }

    @Override
    public void showErrMsg(String error) {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setMessage(error).setTitle("An error occured");
        AlertDialog dialog=builder.create();
        dialog.show();

    }

    @Override
    public void onCategorySelected(Category category) {
        Intent intent = new Intent(this, AllMealsActivity.class);
        intent.putExtra("CATEGORY_Name", category.getStrCategory());
        startActivity(intent);
    }



}