 package allcategories.presenter;;

 import android.content.Context;
 import android.content.Intent;
 import android.util.Log;

 import java.util.List;

 import allcategories.view.AllCategoriesView;
 import allmeals.view.AllMealsActivity;
 import model.FoodRepository;
 import model.Category;
 import network.NetworkDeligate;

public class AllCategoriesPresenterImpl implements AllCategoriesPresenter, NetworkDeligate {
    private AllCategoriesView _view;
    private FoodRepository _repo;
    private Context context;
    private static final String TAG ="allmeals";

    public AllCategoriesPresenterImpl (AllCategoriesView _view , FoodRepository _repo) {
        this._view = _view;
        this._repo = _repo;
    }

    @Override
    public void onSuccessResult(List Category ) {
        _view.showData(Category);

    }

    @Override
    public void onFailureResult(String errorMsg) {
        _view.showErrMsg(errorMsg);

    }



//    @Override
//    public void addToFav(Product product) {
//        _repo.insertProduct(product);
//
//    }

    @Override
    public void getAllCategories() {
        Log.e(TAG, "getAllCategories: " );
        _repo.getAllCategories(this);

    }




}
