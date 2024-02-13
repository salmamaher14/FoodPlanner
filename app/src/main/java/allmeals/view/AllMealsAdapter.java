package allmeals.view;
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
import java.util.ArrayList;
import java.util.List;
import model.Meal;


public class AllMealsAdapter extends RecyclerView.Adapter<AllMealsAdapter.MealViewHolder> {
    Context context;
    List<Meal>meals;
    private static final String TAG = "RecyclerView";
    OnMealClickListener listener;


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

    public AllMealsAdapter(Context context, ArrayList<Meal> meals, OnMealClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener=listener;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.meal_item,parent,false);
        MealViewHolder mealViewHolder=new MealViewHolder(view); // call viewHolder constractor.
        Log.i(TAG, "onCreateViewHolder: ");
        return mealViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        Meal currentMeal= meals.get(position);

        holder.mealName.setText("Meal: " + currentMeal.getStrMeal());
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.mealImageView);

        holder.mealCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.onMealSelected(currentMeal);
            }
        });
    }
    
    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class MealViewHolder extends RecyclerView.ViewHolder{

        ImageView mealImageView;
        TextView mealName;
        CardView mealCard;


        public MealViewHolder(@NonNull View itemView) {   // render each meal
            super(itemView);
            mealImageView=itemView.findViewById(R.id.mealImageView);
            mealName=itemView.findViewById(R.id.mealName);
            mealCard=itemView.findViewById(R.id.mealCard);

        }
    }
}

