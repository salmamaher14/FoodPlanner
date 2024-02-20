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
import model.Ingrediant;
import model.Meal;


public class FilterIngrediantAdapter extends RecyclerView.Adapter<FilterIngrediantAdapter.IngrediantViewHolder> {
    Context context;
    List<Ingrediant>ingrediants;
    SearchClickListener listener;
    private static final String TAG = "FilterIngrediantAdapter";



    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Ingrediant> getIngrediants() {
        return ingrediants;
    }

    public void setIngrediants(List<Ingrediant> ingrediants) {
        this.ingrediants = ingrediants;
    }

    public FilterIngrediantAdapter(Context context, ArrayList<Ingrediant> ingrediants,SearchClickListener listener) {
        this.context = context;
        this.ingrediants=ingrediants;
        this.listener=listener;

    }

    @NonNull
    @Override
    public IngrediantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.ingrediant_item,parent,false);
        IngrediantViewHolder ingrediantViewHolder=new IngrediantViewHolder(view);
        Log.i(TAG, "onCreateViewHolder: ");
        return ingrediantViewHolder;

    }


    @Override
    public void onBindViewHolder(@NonNull IngrediantViewHolder holder, int position) {
        Ingrediant currentIngrediant= ingrediants.get(position);
        holder.ingrediantName.setText(currentIngrediant.getStrIngredient());
        holder.ingrediantName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getAllMealsOfIngrediant(currentIngrediant.getStrIngredient());
            }
        });


//        ingrediantName.btnRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                listener.removeMealSelected(currentMeal);   //remove
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return ingrediants.size();
    }

    public class IngrediantViewHolder extends RecyclerView.ViewHolder{

        TextView ingrediantName;

        public IngrediantViewHolder(@NonNull View itemView) {   // render each meal
            super(itemView);
            ingrediantName=itemView.findViewById(R.id.ingrediantName);

        }
    }
}



