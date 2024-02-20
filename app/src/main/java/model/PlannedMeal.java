package model;
import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "plannedMeals_table")
public class PlannedMeal {
    private String strMeal;

    private String strMealThumb;

    private String date;

    @PrimaryKey
    @NonNull
    private String idPlannedMeal;

    public PlannedMeal(String strMeal, String strMealThumb, @NonNull String idPlannedMeal,String date) {
        this.strMeal = strMeal;
        this.strMealThumb = strMealThumb;
        this.idPlannedMeal = idPlannedMeal;
        this.date=date;

    }


    public PlannedMeal() {

    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStrMeal() {
        return strMeal;
    }

    public void setStrMeal(String strMeal) {
        this.strMeal = strMeal;
    }

    public String getStrMealThumb() {
        return strMealThumb;
    }

    public void setStrMealThumb(String strMealThumb) {
        this.strMealThumb = strMealThumb;
    }

    @NonNull
    public String getIdPlannedMeal() {
        return idPlannedMeal;
    }

    public void setIdPlannedMeal(@NonNull String idPlannedMeal) {
        this.idPlannedMeal = idPlannedMeal;
    }
}
