package allcategories.view;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import java.util.ArrayList;
import java.util.List;
import model.Category;


public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.CategoryViewHolder> {
    Context context;
    List<Category> categories;
    private static final String TAG = "RecyclerView";
    OnCategoryClickListener listener;



    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }


    public AllCategoriesAdapter(Context context, ArrayList<Category> categories,OnCategoryClickListener listener) {
        this.context = context;
        this.categories = categories;
        this.listener=listener;
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.category_item,parent,false);
        CategoryViewHolder CategoryViewHolder=new CategoryViewHolder(view); // call viewHolder constractor.
        Log.i(TAG, "onCreateViewHolder: ");
        return CategoryViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category currentCategory = categories.get(position);

        holder.categoryName.setText("Category: " + currentCategory.getStrCategory());
        holder.categoryDesc.setText("Description: " + currentCategory.getStrCategoryDescription());

        Glide.with(context)
                .load(categories.get(position).getStrCategoryThumb())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageViewCategory);
        holder.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onCategorySelected(currentCategory);
            }
        });

//        holder.btnAddToFav.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                listener.onCategoryListener(currentCategory);
//            }
//        });
    }
    
    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewCategory;
        TextView categoryName;
        TextView categoryDesc;
        CardView categoryCardView;

        public CategoryViewHolder(@NonNull View itemView) {   // render each category
            super(itemView);
            imageViewCategory=itemView.findViewById(R.id.categoryImage);
            categoryName=itemView.findViewById(R.id.categoryName);
            categoryDesc=itemView.findViewById(R.id.categoryDesc);
            categoryCardView=itemView.findViewById(R.id.categoryCardViewId);




        }
    }
}

