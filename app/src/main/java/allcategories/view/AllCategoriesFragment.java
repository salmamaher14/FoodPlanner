package allcategories.view;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;

import java.util.ArrayList;
import java.util.List;

import allcategories.presenter.AllCategoriesPresenter;
import allcategories.presenter.AllCategoriesPresenterImpl;
import allmeals.view.AllMealsFragmentDirections;
import model.Category;
import model.FoodRepositoryImpl;
import model.MealDetail;
import model.MealLocalDataSourceImpl;
import network.FoodRemoteDataSourceImpl;

public class AllCategoriesFragment extends Fragment implements AllCategoriesView,OnCategoryClickListener {

    RecyclerView allRecyclerView;
    CardView randomMealCard;
    ImageView imageViewRandomMeal;
    TextView randomMealName;
    AllCategoriesAdapter categoriesAdapter;
    AllCategoriesPresenter allPresenter;
    LinearLayoutManager linearLayoutManager;
    OnCategoryClickListener listener;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_all_categories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        allRecyclerView=view.findViewById(R.id.allCategoriesRv);
        randomMealCard=view.findViewById(R.id.cardViewRandomMeal);
        imageViewRandomMeal=view.findViewById(R.id.randomMealImageView);
        randomMealName=view.findViewById(R.id.randomMealName);

        allRecyclerView.setHasFixedSize(true);
        linearLayoutManager=new LinearLayoutManager(getContext());
        categoriesAdapter=new AllCategoriesAdapter(getContext(),new ArrayList<>(),this);
        allPresenter = new AllCategoriesPresenterImpl(this , FoodRepositoryImpl.getInstance(
                FoodRemoteDataSourceImpl.getInstance(), MealLocalDataSourceImpl.getInstance(getContext())));

        linearLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        allRecyclerView.setLayoutManager(linearLayoutManager);
        allRecyclerView.setAdapter(categoriesAdapter);
        allPresenter.getAllCategories();
        allPresenter.getRandomMeal();

    }

    @Override
    public void showData(List<Category> categories) {
        categoriesAdapter.setCategories( categories);
        categoriesAdapter.notifyDataSetChanged();

    }

    @Override
    public void showRandomMeal(List<MealDetail> randomMeal) {
        Glide.with(this)
                .load(randomMeal.get(0).getStrMealThumb())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(imageViewRandomMeal);
        randomMealName.setText(randomMeal.get(0).getStrMeal());
        randomMealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AllCategoriesFragmentDirections.ActionAllCategoriesFragmentToMealDetailFragment action=
                        AllCategoriesFragmentDirections.actionAllCategoriesFragmentToMealDetailFragment(randomMeal.get(0).getIdMeal());
                Navigation.findNavController(getView()).navigate(action);
            }
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
    public void onCategorySelected(Category category) {

        AllCategoriesFragmentDirections.ActionAllCategoriesFragmentToAllMealsFragment
                action=AllCategoriesFragmentDirections.actionAllCategoriesFragmentToAllMealsFragment(category.getStrCategory());
        Navigation.findNavController(getView()).navigate(action);

//        Intent intent = new Intent(this, AllMealsActivity.class);
//        intent.putExtra("CATEGORY_Name", category.getStrCategory());
//        startActivity(intent);
    }

}
