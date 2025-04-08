package eu.ase.ro.damapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import eu.ase.ro.damapp.fragments.AboutFragment;
import eu.ase.ro.damapp.fragments.Expense;
import eu.ase.ro.damapp.fragments.HomeFragment;
import eu.ase.ro.damapp.fragments.ProfileFragment;

public class MainActivity extends AppCompatActivity{

    private FloatingActionButton fabAdd;


    private List<Expense> expenses = new ArrayList<>();

    private DrawerLayout drawerLayout;
    private NavigationView navView;

    private Fragment currentFragment;

    private ActivityResultLauncher<Intent> laucher;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configNavigation();

        navView = findViewById(R.id.calea_gabriela_main_nav_view);
        navView.setNavigationItemSelectedListener(
                getItemSelected());

        fabAdd = findViewById(R.id.calea_gabriela_main_fab_add);
        fabAdd.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), AddExpenseActivity.class);
            //startActivity(intent);
            laucher.launch(intent);
        });


        laucher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(), getAddExpenseCallback());
    }

    private ActivityResultCallback<ActivityResult> getAddExpenseCallback() {
        return result -> {
            if(result.getResultCode() == RESULT_OK && result.getData() != null){
                Expense expense = (Expense) result.getData()
                        .getSerializableExtra(AddExpenseActivity.EXPENSE_KEY);
                if (expense != null) {
                    expenses.add(expense);
                }
            }
        };
    }

    private NavigationView.OnNavigationItemSelectedListener
    getItemSelected() {
        return item -> {
            if (item.getItemId() == R.id.nav_home){
                Toast.makeText(getApplicationContext(),
                        R.string.main_home_clicked,
                        Toast.LENGTH_SHORT).show();
                currentFragment = new HomeFragment();
            } else if (item.getItemId() == R.id.nav_profile){
                Toast.makeText(getApplicationContext(),
                        R.string.main_profile_clicked,
                        Toast.LENGTH_SHORT).show();
                currentFragment = new ProfileFragment();
            } else {
                Toast.makeText(getApplicationContext(),
                        R.string.main_about_clicked,
                        Toast.LENGTH_SHORT).show();
                currentFragment = new AboutFragment();
            }
            drawerLayout.closeDrawer(GravityCompat.START);
            openFragment();
            return true;
        };
    }

    private void openFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.calea_gabriela_main_fl_container, currentFragment)
                .commit();
    }

    private void configNavigation() {
        Toolbar toolbar = findViewById(R.id.calea_gabriela_main_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.calea_gabriela_main_drawer);
        ActionBarDrawerToggle toggle = new
                ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        toggle.syncState();
    }
}