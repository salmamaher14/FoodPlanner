package allcategories.view;

import java.util.List;

import model.Category;

public interface AllCategoriesView {
    public void showData(List<Category> categories);
    public void showErrMsg(String error);
}
