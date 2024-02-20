package planned_days.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;

import java.util.List;

public class PlanningDaysAdapter  extends RecyclerView.Adapter<PlanningDaysAdapter.PlanningDayViewHolder> {
    Context context;
    List<String > planningDays;
    OnViewPlannedMealListener listener;
    private static final String TAG = "PlanningMealAdapter";
    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<String> getPlanningDays() {
        return planningDays;
    }

    public void setPlanningDays(List<String> planningDays) {
        this.planningDays = planningDays;
    }


    public PlanningDaysAdapter(Context context, List<String> planningDays, OnViewPlannedMealListener listener) {
        this.context = context;
        this.planningDays = planningDays;
        this.listener = listener;
    }

    @NonNull
    @Override
    public PlanningDayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.planning_day_item,parent,false);
        PlanningDayViewHolder mealViewHolder=new PlanningDayViewHolder(view); // call viewHolder constractor.
        Log.i(TAG, "onCreateViewHolder: ");
        return mealViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull PlanningDayViewHolder holder, int position) {
        String currentDay= planningDays.get(position);
        holder.day.setText(currentDay);
        holder.viewMeal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.viewPlanningMeals(currentDay);
            }
        });
    }

    @Override
    public int getItemCount() {
        return planningDays.size();
    }

    public class PlanningDayViewHolder extends RecyclerView.ViewHolder{
        CardView dayCard;
        TextView day;
        Button viewMeal;


        public PlanningDayViewHolder(@NonNull View itemView) {
            super(itemView);

            day=itemView.findViewById(R.id.dateTextView);
            viewMeal=itemView.findViewById(R.id.viewMealsButton);

        }
    }
}

/*
public class FavMealAdapter extends RecyclerView.Adapter<FavMealAdapter.FavMealViewHolder> {
    Context context;
    List<Meal>meals;
    private static final String TAG = "FavMealAdapter";
    OnRemoveMealClickListener listener;


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

    public FavMealAdapter(Context context, ArrayList<Meal> meals, OnRemoveMealClickListener listener) {
        this.context = context;
        this.meals = meals;
        this.listener=listener;
    }

    @NonNull
    @Override
    public FavMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.fav_meal,parent,false);
        FavMealViewHolder mealViewHolder=new FavMealViewHolder(view); // call viewHolder constractor.
        Log.i(TAG, "onCreateViewHolder: ");
        return mealViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull FavMealViewHolder holder, int position) {
        Meal currentMeal= meals.get(position);

        holder.favMealName.setText(currentMeal.getStrMeal());
        Glide.with(context)
                .load(meals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.favMealImageView);

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.removeMealSelected(currentMeal);   //remove
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class FavMealViewHolder extends RecyclerView.ViewHolder{

        ImageView favMealImageView;
        TextView favMealName;
        CardView mealCard;
        Button btnRemove;


        public FavMealViewHolder(@NonNull View itemView) {   // render each meal
            super(itemView);
            favMealImageView=itemView.findViewById(R.id.favImageView);
            favMealName=itemView.findViewById(R.id.favTitle);
            mealCard=itemView.findViewById(R.id.favCardView);
            btnRemove=itemView.findViewById(R.id.btnRemove);

        }
    }
}



 */
