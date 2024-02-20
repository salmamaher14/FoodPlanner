package search.view;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import com.example.foodplanner.R;
import com.google.android.material.chip.Chip;
import java.util.ArrayList;
import java.util.List;
import model.Area;
import model.Category;
import model.FoodRepositoryImpl;
import model.Ingrediant;
import model.Meal;
import model.MealLocalDataSourceImpl;
import network.FoodRemoteDataSourceImpl;
import search.presenter.SearchPresenterImpl;

public class SearchFeatureFragment extends Fragment implements SearchView,SearchClickListener{
    RecyclerView searchRecyclerView;
    private EditText searchEditText;
    LinearLayoutManager layoutManager;
    SearchPresenterImpl searchPresenter;
    AllMealsAdapter allMealsAdapter;
    FilterAreaAdapter filterAreaAdapter;
    FilterCategoryAdapter filterCategoryAdapter;
    FilterIngrediantAdapter filterIngrediantAdapter;

    Chip categoryChip;
    Chip areaChip;
    Chip ingridiantChip;

    private static final String TAG = "SearchFragment";



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_feature, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        searchRecyclerView=view.findViewById(R.id.searchRv);
        categoryChip=view.findViewById(R.id.chipCategory);
        searchEditText = view.findViewById(R.id.searchEditText);
        areaChip=view.findViewById(R.id.chipArea);
        ingridiantChip=view.findViewById(R.id.chipIngredient);
        searchRecyclerView.setHasFixedSize(true);
        layoutManager=new LinearLayoutManager(getContext());
        allMealsAdapter=new AllMealsAdapter(getContext(),new ArrayList<>(),this);
        filterIngrediantAdapter=new FilterIngrediantAdapter(getContext(),new ArrayList<>(),this);
        filterAreaAdapter=new FilterAreaAdapter(getContext(),new ArrayList<>(),this);
        filterCategoryAdapter=new FilterCategoryAdapter(getContext(),new ArrayList<>(),this);

        searchPresenter = new SearchPresenterImpl(this, FoodRepositoryImpl.getInstance(
                FoodRemoteDataSourceImpl.getInstance(),
                MealLocalDataSourceImpl.getInstance(getContext())));
        layoutManager.setOrientation(RecyclerView.VERTICAL);

        searchRecyclerView.setLayoutManager(layoutManager);





        categoryChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryChip.setChecked(true);
                areaChip.setChecked(false);
                ingridiantChip.setChecked(false);
                searchRecyclerView.setAdapter(filterCategoryAdapter);
                searchPresenter.getAllCategories();
                searchEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // Not needed
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        // Not needed
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String category = s.toString();
                        if (!TextUtils.isEmpty(category)) {
                            searchPresenter.getFetchedCategories(category);// set view in presenter

                        }
                    }
                });
            }


        });


        areaChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                categoryChip.setChecked(false);
                ingridiantChip.setChecked(false);
                areaChip.setChecked(true);

                searchRecyclerView.setAdapter(filterAreaAdapter);
                searchPresenter.getAllAreas();
                searchEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        // Not needed
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String country = s.toString();
                        if (!TextUtils.isEmpty(country)) {
                            searchPresenter.getFetchedCountries(country);// set view in presenter

                        }
                    }
                });
            }


        });
        ingridiantChip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                categoryChip.setChecked(false);
                ingridiantChip.setChecked(true);
                areaChip.setChecked(false);

                searchRecyclerView.setAdapter(filterIngrediantAdapter);
                searchPresenter.getAllIngrediants();

                searchEditText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        String ingrediant = s.toString();
                        if (!TextUtils.isEmpty(ingrediant)) {
                            searchPresenter.getFetchedIngrediants(ingrediant);

                        }
                    }
                });
            }


        });
        if (!categoryChip.isChecked()&& !areaChip.isChecked()&& !ingridiantChip.isChecked()) {
            searchEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    // Not needed
                }

                @Override
                public void afterTextChanged(Editable s) {
                    String mealChar = s.toString();
                    Log.d("TextChan", "Text changed: " + mealChar);
                    if (!TextUtils.isEmpty(mealChar) && !(categoryChip.isChecked())) {
                        searchPresenter.getFetchedMealsByFirstLetter(mealChar);


                    }
                }
            });
        }




    }



    @Override
    public void showCategories(List<Category> results) {
        filterCategoryAdapter.setCategories(results);
        filterCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void showFetchedCategoris(List<Category> fetchedCategories) {
        filterCategoryAdapter.setCategories(fetchedCategories);
        filterCategoryAdapter.notifyDataSetChanged();

    }

    @Override
    public void showFetchedAreas(List<Area> fetchedAreas) {
        filterAreaAdapter.setAreas(fetchedAreas);
        filterAreaAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMealsOfCategoryName(List<Meal> meals) {
        searchRecyclerView.setAdapter(allMealsAdapter);
        allMealsAdapter.setMeals(meals);
        allMealsAdapter.notifyDataSetChanged();

    }

    @Override
    public void showMealsOfAreaName(List<Meal> meals) {
        searchRecyclerView.setAdapter(allMealsAdapter);
        allMealsAdapter.setMeals(meals);
        allMealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMealsOfIngrediant(List<Meal> meals) {
        searchRecyclerView.setAdapter(allMealsAdapter);
        allMealsAdapter.setMeals(meals);
        allMealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showAllAreas(List<Area> areas) {

        filterAreaAdapter.setAreas(areas);
        filterAreaAdapter.notifyDataSetChanged();

    }

    @Override
    public void showAllIngrediants(List<Ingrediant> ingrediants) {
        filterIngrediantAdapter.setIngrediants(ingrediants);
        filterIngrediantAdapter.notifyDataSetChanged();
    }

    @Override
    public void showFetchedIngrediants(List<Ingrediant> ingrediants) {
        filterIngrediantAdapter.setIngrediants(ingrediants);
        filterIngrediantAdapter.notifyDataSetChanged();
    }

    @Override
    public void showMealsByFirstLetter(List<Meal> meals) {
        searchRecyclerView.setAdapter(allMealsAdapter);
        allMealsAdapter.setMeals(meals);
        allMealsAdapter.notifyDataSetChanged();
    }

    @Override
    public void showErrMsg(String error) {

    }

    @Override
    public void getAllMealsOfCategory(String category) {
        searchPresenter.filterByCategory(category);
    }

    @Override
    public void getAllMealsOfArea(String area) {
        searchPresenter.filterByArea(area);
    }

    @Override
    public void getAllMealsOfIngrediant(String ingrediant) {
        searchPresenter.filterByMainIngrediant(ingrediant);
    }

    @Override
    public void OnClickMeal(String id) {
        SearchFeatureFragmentDirections.ActionSearchFeatureFragmentToMealDetailFragment action=
                SearchFeatureFragmentDirections.actionSearchFeatureFragmentToMealDetailFragment(id);
        Navigation.findNavController(getView()).navigate(action);
    }

//    @Override
//    public void onClickArea(String id) {
//        SearchFeatureFragmentDirections.ActionSearchFeatureFragmentToMealDetailFragment action=
//                SearchFeatureFragmentDirections.actionSearchFeatureFragmentToMealDetailFragment(id);
//        Navigation.findNavController(getView()).navigate(action);
//    }
}