package model;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Areas {
        @SerializedName("meals")
        @Expose
        private List<Area> areas;

        public List<Area> getAreas() {
            return areas;
        }

        public void setAreas(List<Area> areas) {
            this.areas = areas;
        }

    }

