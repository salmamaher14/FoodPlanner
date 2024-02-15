package com.example.foodplanner;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import android.os.Bundle;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {
    NavController navController;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        bottomNavigationView=findViewById(R.id.bottomNavigationId);
        navController= Navigation.findNavController(this,R.id.nav_host_fragment);
//        NavigationUI.setupWithNavController(bottomNavigationView,navController);
        NavigationUI.setupActionBarWithNavController(this,navController);













   /*     Button btn=findViewById(R.id.allCategories);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AllCategoriesActivity.class);
                startActivity(intent);

            }
        });

    */


    }
    @Override
    public boolean onSupportNavigateUp() {
        return navController.navigateUp()|| super.onSupportNavigateUp();

    }
}