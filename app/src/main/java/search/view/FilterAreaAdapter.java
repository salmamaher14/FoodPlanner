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

import java.util.ArrayList;
import java.util.List;

import model.Area;

public class FilterAreaAdapter extends RecyclerView.Adapter<FilterAreaAdapter.AreaViewHolder> {
    Context context;
    List<Area> areas;
    List<Integer> imageResourceIds;
    SearchClickListener listener;
    private static final String TAG = "FilterAreaAdapter";

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<Area> getAreas() {
        return areas;
    }

    public void setAreas(List<Area> areas) {
        this.areas = areas;
    }

    public FilterAreaAdapter(Context context, List<Area> areas,SearchClickListener listener) {
        this.context = context;
        this.areas = areas;
        this.listener=listener;
        setAreaImages();
    }


    @NonNull
    @Override
    public AreaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view =inflater.inflate(R.layout.area_item,parent,false);
        AreaViewHolder areaViewHolder=new AreaViewHolder(view); // call viewHolder constractor.
        Log.i(TAG, "onCreateViewHolder: ");
        return areaViewHolder;

    }

    @Override
    public void onBindViewHolder(@NonNull AreaViewHolder holder, int position) {
        Area currentArea= areas.get(position);
        int imageResourceId = imageResourceIds.get(position);

        holder.areaName.setText(currentArea.getStrArea());
        Glide.with(context)
                .load(imageResourceId)
                .apply(new RequestOptions().override(200, 200))
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.areaImageView);

        holder.areaCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.getAllMealsOfArea(currentArea.getStrArea());
            }
        });


    }
    @Override
    public int getItemCount() {
        return areas.size();
    }

    public class AreaViewHolder extends RecyclerView.ViewHolder{

        ImageView areaImageView;
        TextView areaName;
        CardView areaCard;

        public AreaViewHolder(@NonNull View itemView) {
            super(itemView);
            areaImageView=itemView.findViewById(R.id.areaImageView);
            areaName=itemView.findViewById(R.id.areaTextView);
            areaCard=itemView.findViewById(R.id.areaCardView);

        }
    }
    void setAreaImages(){
        imageResourceIds = new ArrayList<>();
        imageResourceIds.add(R.drawable.united);
        imageResourceIds.add(R.drawable.unitedkingdom);
        imageResourceIds.add(R.drawable.canada);
        imageResourceIds.add(R.drawable.china);
        imageResourceIds.add(R.drawable.croatia);
        imageResourceIds.add(R.drawable.netherlands);
        imageResourceIds.add(R.drawable.egypt);
        imageResourceIds.add(R.drawable.philippines);
        imageResourceIds.add(R.drawable.greece);
        imageResourceIds.add(R.drawable.india);
        imageResourceIds.add(R.drawable.ireland);
        imageResourceIds.add(R.drawable.italy);
        imageResourceIds.add(R.drawable.jamaica);
        imageResourceIds.add(R.drawable.japan);
        imageResourceIds.add(R.drawable.kenya);
        imageResourceIds.add(R.drawable.malaysia);
        imageResourceIds.add(R.drawable.mexico);
        imageResourceIds.add(R.drawable.morocco);
        imageResourceIds.add(R.drawable.poland);
        imageResourceIds.add(R.drawable.portugal);
        imageResourceIds.add(R.drawable.russia);
        imageResourceIds.add(R.drawable.spain);
        imageResourceIds.add(R.drawable.thailand);
        imageResourceIds.add(R.drawable.tunisia);
        imageResourceIds.add(R.drawable.turkey);
        imageResourceIds.add(R.drawable.turkey);


    }

}
