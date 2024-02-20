package search.view;

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

import favouritemeals.view.OnRemoveMealClickListener;
import model.Meal;


public class AllMealsAdapter extends RecyclerView.Adapter<AllMealsAdapter.AllMealsViewHolder> {
    Context context;
    List<Meal>meals;
    SearchClickListener listener;
    private static final String TAG = "AllMealsAdapter";


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> meals) {
        this.meals = meals;
    }

    public AllMealsAdapter(Context context, List<Meal> meals, SearchClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener = listener;
    }

    @NonNull
    @Override
    public AllMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.meal_item,parent,false);
        AllMealsViewHolder mealViewHolder=new AllMealsViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return mealViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull AllMealsViewHolder holder, int position) {
        Meal currentMeal= meals.get(position);

        holder.mealName.setText(currentMeal.getStrMeal());
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealImageView);

        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnClickMeal(currentMeal.getIdMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class AllMealsViewHolder extends RecyclerView.ViewHolder{

        ImageView mealImageView;
        TextView mealName;
        CardView mealCard;



        public AllMealsViewHolder(@NonNull View itemView) {   // render each meal
            super(itemView);
            mealImageView=itemView.findViewById(R.id.mealImageView);
            mealName=itemView.findViewById(R.id.mealName);
            mealCard=itemView.findViewById(R.id.mealCard);

        }
    }
}



