package db;

import androidx.room.TypeConverter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import model.MealDetail;

import androidx.room.TypeConverter;

public class Converters {

    @TypeConverter
    public static String objectToString(Object object) {
        return object != null ? object.toString() : null;
    }
}
