package getplannedmeals.view;
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
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.foodplanner.R;
import java.util.List;

import allmeals.view.AllMealsFragmentDirections;
import model.PlannedMeal;

public class PlannedMealsAdapter extends RecyclerView.Adapter<PlannedMealsAdapter.PlannedMealViewHolder>{
    Context context;
    List<PlannedMeal> plannedMeals;
    private static final String TAG = "PlannedMealsAdapter";
    OnRemovePlannedMealClickListener listener;
    NavController navController;


    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<PlannedMeal> getMeals() {
        return plannedMeals;
    }

    public void setMeals(List<PlannedMeal> meals) {
        this.plannedMeals = meals;
    }

    public PlannedMealsAdapter(Context context, List<PlannedMeal> plannedMeals, OnRemovePlannedMealClickListener listener, NavController navController) {
        this.context = context;
        this.plannedMeals = plannedMeals;
        this.listener = listener;
        this.navController=navController;
    }

    @NonNull
    @Override
    public PlannedMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.fav_meal,parent,false);
        PlannedMealViewHolder mealViewHolder=new PlannedMealViewHolder(view); // call viewHolder constractor.
        Log.i(TAG, "onCreateViewHolder: ");
        return mealViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull PlannedMealViewHolder holder, int position) {
        PlannedMeal currentMeal= plannedMeals.get(position);

        holder.plannedMealName.setText(currentMeal.getStrMeal());
        Glide.with(context)
                .load(plannedMeals.get(position).getStrMealThumb())
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.plannedMealImageView);

        holder.btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                listener.removeMealSelected(currentMeal);   //remove
            }
        });
//        holder.plannedMealCard.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                PlannedMealsFragmentDirections.ActionPlannedMealsFragmentToMealDetailFragment action=
//                        PlannedMealsFragmentDirections.actionPlannedMealsFragmentToMealDetailFragment(currentMeal.getIdPlannedMeal());
//                navController.navigate(action);
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return plannedMeals.size();
    }

    public class PlannedMealViewHolder extends RecyclerView.ViewHolder{

        ImageView plannedMealImageView;
        TextView plannedMealName;
        CardView plannedMealCard;

        Button btnRemove;


        public PlannedMealViewHolder(@NonNull View itemView) {   // render each meal
            super(itemView);
            plannedMealImageView=itemView.findViewById(R.id.favImageView);
            plannedMealName=itemView.findViewById(R.id.favTitle);
            plannedMealCard=itemView.findViewById(R.id.favCardView);
            btnRemove=itemView.findViewById(R.id.btnRemove);
            btnRemove.setText("Remove from planning");

        }
    }
}

