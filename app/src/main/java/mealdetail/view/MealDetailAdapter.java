package mealdetail.view;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;
import java.util.List;
import model.MealDetail;

public class MealDetailAdapter extends RecyclerView.Adapter<MealDetailAdapter.MealViewHolder> {

    private Context context;
    private List<MealDetail> mealList;
    private SimpleExoPlayer exoPlayer;



    public MealDetailAdapter(Context context, List<MealDetail> mealList) {
        this.context = context;
        this.mealList = mealList;
    }

    public List<MealDetail> getMealList() {
        return mealList;
    }

    public void setMealList(List<MealDetail> mealList) {
        this.mealList = mealList;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.meal_detail_item, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealDetail meal = mealList.get(position);

        // Set meal name
        holder.textMealName.setText(meal.getStrMeal());

        // Load meal image
        Glide.with(context)
                .load(meal.getStrMealThumb())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.imageMeal);

        // Set origin country
        holder.textOriginCountry.setText(meal.getStrArea());
        // ingridiants
        StringBuilder ingredientsBuilder = new StringBuilder();
        for (int i = 1; i <= 20; i++) {
            String ingredient = getIngredient(meal, i);
            String measure = getMeasure(meal, i);
            appendIfNotEmpty(ingredientsBuilder, ingredient, measure);
        }
        holder.textIngredients.setText(ingredientsBuilder.toString());


        // Set ingredients text
        holder.textIngredients.setText(ingredientsBuilder.toString());


        // Set steps
        holder.textSteps.setText(meal.getStrInstructions());

        // Initialize video player

        Uri videoUri = Uri.parse(meal.getStrYoutube());
        exoPlayer = new SimpleExoPlayer.Builder(context).build();
        MediaItem mediaItem = MediaItem.fromUri(videoUri);
        exoPlayer.setMediaItem(mediaItem);
        exoPlayer.setPlayWhenReady(true);
        holder.playerView.setPlayer(exoPlayer);




    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    private void appendIfNotEmpty(StringBuilder builder, String ingredient, String measure) {
        if (ingredient != null && !ingredient.isEmpty()) {
            builder.append("- ").append(ingredient);
            if (measure != null && !measure.isEmpty()) {
                builder.append(" (").append(measure).append(")");
            }
            builder.append("\n");
        }
    }


    private String getIngredient(MealDetail meal, int index) {
        switch (index) {
            case 1: return meal.getStrIngredient1();
            case 2: return meal.getStrIngredient2();
            case 3: return meal.getStrIngredient3();
            case 4: return meal.getStrIngredient4();
            case 5: return meal.getStrIngredient5();
            case 6: return meal.getStrIngredient6();
            case 7: return meal.getStrIngredient7();
            case 8: return meal.getStrIngredient8();
            case 9: return meal.getStrIngredient9();
            case 10: return meal.getStrIngredient10();
            case 11: return meal.getStrIngredient11();
            case 12: return meal.getStrIngredient12();
            case 13: return meal.getStrIngredient13();
            case 14: return meal.getStrIngredient14();
            case 15: return meal.getStrIngredient15();
            case 16: return (String) meal.getStrIngredient16();
            case 17: return (String) meal.getStrIngredient17();
            case 18: return (String) meal.getStrIngredient18();
            case 19:return (String) meal.getStrIngredient19();
            case 20:return (String) meal.getStrIngredient20();

            default: return "null";
        }
    }


    private String getMeasure(MealDetail meal, int index) {
        switch (index) {
            case 1: return meal.getStrMeasure1();
            case 2: return meal.getStrMeasure2();
            case 3: return meal.getStrMeasure3();
            case 4: return meal.getStrMeasure4();
            case 5: return meal.getStrMeasure5();
            case 6: return meal.getStrMeasure6();
            case 7: return meal.getStrMeasure7();
            case 8: return meal.getStrMeasure8();
            case 9: return meal.getStrMeasure9();
            case 10: return meal.getStrMeasure10();
            case 11: return meal.getStrMeasure11();
            case 12: return meal.getStrMeasure12();
            case 13: return meal.getStrMeasure13();
            case 14: return meal.getStrMeasure14();
            case 15: return meal.getStrMeasure15();
            case 16: return (String) meal.getStrMeasure16();
            case 17: return (String) meal.getStrMeasure17();
            case 18: return (String) meal.getStrMeasure18();
            case 19:return (String) meal.getStrMeasure19();
            case 20:return (String) meal.getStrMeasure20();

            default: return "null";
        }
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder {
        PlayerView playerView;
        ImageView imageMeal;
        TextView textMealName;
        TextView textOriginCountry;
        TextView textIngredients;
        TextView textSteps;
        Button buttonAddToFavorites;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            playerView = itemView.findViewById(R.id.playerView);
            imageMeal = itemView.findViewById(R.id.imageViewMeal);
            textMealName = itemView.findViewById(R.id.textViewMealName);
            textOriginCountry = itemView.findViewById(R.id.textViewOriginCountry);
            textIngredients = itemView.findViewById(R.id.textViewIngredientid);
            textSteps = itemView.findViewById(R.id.textViewSteps);
            buttonAddToFavorites = itemView.findViewById(R.id.buttonAddToFavorites);
        }
    }
}

