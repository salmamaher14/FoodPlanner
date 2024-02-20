package search.view;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;

import java.util.List;
import model.Category;


public class FilterCategoryAdapter extends RecyclerView.Adapter<FilterCategoryAdapter.CategoryViewHolder> {
    Context context;
    List<Category> categories;
    SearchClickListener listener;
    private static final String TAG = "RecyclerView";

    public FilterCategoryAdapter(Context context, List<Category> categories, SearchClickListener listener) {
        this.context = context;
        this.categories = categories;
        this.listener = listener;
    }

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




    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.filter_category_item,parent,false);
         CategoryViewHolder categoryViewHolder=new CategoryViewHolder(view); // call viewHolder constractor.
        Log.i(TAG, "onCreateViewHolder: ");
        return categoryViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull FilterCategoryAdapter.CategoryViewHolder holder, int position) {
        Category currentCategory = categories.get(position);
        holder.categoryName.setText(currentCategory.getStrCategory());

        Glide.with(context)
                .load(categories.get(position).getStrCategoryThumb())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.imageViewCategory);
        holder.categoryCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getAllMealsOfCategory(currentCategory.getStrCategory());
            }
        });

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder{

        ImageView imageViewCategory;
        TextView categoryName;
        CardView categoryCardView;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            imageViewCategory=itemView.findViewById(R.id.areaImageView);
            categoryName=itemView.findViewById(R.id.areaTextView);
            categoryCardView=itemView.findViewById(R.id.categoryCardView);


        }
    }
}


