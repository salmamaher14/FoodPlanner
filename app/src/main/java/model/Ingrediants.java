package model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Ingrediants {

    @SerializedName("meals")
    @Expose
    private List<Ingrediant> ingrediants;

    public List<Ingrediant> getIngrediants() {
        return ingrediants;
    }

    public void setIngrediants(List<Ingrediant> ingrediants) {
        this.ingrediants = ingrediants;
    }
}
