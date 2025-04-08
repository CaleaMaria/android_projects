package com.example.tema_quiz_19;

import android.os.Bundle;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;
//https://drive.google.com/file/d/1k6CKdplfWYjhn2K37b8Z4kBGsFTp4zlZ/view?usp=sharing
public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;


    public static User currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar toolbar = findViewById(R.id.Calea_Gabriela_main_toolbar);
        setSupportActionBar(toolbar);


        drawerLayout = findViewById(R.id.Calea_Gabriela_main_drawer);
        navigationView = findViewById(R.id.Calea_Gabriela_nav_view);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();


        if (savedInstanceState == null) {
            goToStartQuizFragment();
        }


        navigationView.setNavigationItemSelectedListener(item -> {
            Fragment selectedFragment = null;


            if (item.getItemId() == R.id.Calea_Gabriela_intrenari_frecvente_drawer) {
                selectedFragment = new FAQFragment();
            } else if (item.getItemId() == R.id.Calea_Gabriela_user_navigation_drawer) {
                selectedFragment = new UserFragment();
            }


            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Calea_Gabriela_fragment_container, selectedFragment)
                        .addToBackStack(null)
                        .commit();
            }


            item.setChecked(true);


            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        });
    }


    public void goToStartQuizFragment() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.Calea_Gabriela_fragment_container, new StartQuizFragment())
                .commit();


        if (navigationView != null) {
            navigationView.getMenu().clear();
            navigationView.inflateMenu(R.menu.activity_main_drawer);
        }
    }

    @Override
    public void onBackPressed() {

        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {

            if (getSupportFragmentManager().getBackStackEntryCount() > 0) {
                getSupportFragmentManager().popBackStack();
            } else {
                super.onBackPressed();
            }
        }
    }
}
