package model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllMealsDetails {

        @SerializedName("meals")
        @Expose
        private List<MealDetail> mealsDetails;

        public List<MealDetail> getMealsDetails() {
            return mealsDetails;
        }

        public void setMealsDetails(List<MealDetail> mealsDetails) {
            this.mealsDetails = mealsDetails;
        }

    }

