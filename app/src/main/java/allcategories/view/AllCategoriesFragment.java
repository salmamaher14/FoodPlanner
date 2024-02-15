package allcategories.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import allcategories.presenter.AllCategoriesPresenter;
import allcategories.presenter.AllCategoriesPresenterImpl;
import model.Category;
import model.FoodRepositoryImpl;
import model.MealLocalDataSourceImpl;
import network.FoodRemoteDataSourceImpl;

public class AllCategoriesFragment extends Fragment implements AllCategoriesView,OnCategoryClickListener {

    RecyclerView allRecyclerView;
    AllCategoriesAdapter categoriesAdapter;
    AllCategoriesPresenter allPresenter;
    LinearLayoutManager linearLayoutManager;
    OnCategoryClickListener listener;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allRecyclerView=view.findViewById(R.id.allCategoriesRv);
        allRecyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(getContext());
        categoriesAdapter=new AllCategoriesAdapter(getContext(),new ArrayList<>(),this);
        allPresenter = new AllCategoriesPresenterImpl(this , FoodRepositoryImpl.getInstance(
                FoodRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext())));

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
        AlertDialog.Builder builder=new AlertDialog.Builder(getContext());
        builder.setMessage(error).setTitle("An error occured");
        AlertDialog dialog=builder.create();
        dialog.show();

    }

    @Override
    public void onCategorySelected(Category category) {

        AllCategoriesFragmentDirections.ActionAllCategoriesFragmentToAllMealsFragment
                action=AllCategoriesFragmentDirections.actionAllCategoriesFragmentToAllMealsFragment(category.getStrCategory());
        Navigation.findNavController(getView()).navigate(action);

//        Intent intent = new Intent(this, AllMealsActivity.class);
//        intent.putExtra("CATEGORY_Name", category.getStrCategory());
//        startActivity(intent);
    }

}
