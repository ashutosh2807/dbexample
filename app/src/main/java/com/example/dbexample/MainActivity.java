package com.example.dbexample;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.dbexample.Fragements.DeleteFragment;
import com.example.dbexample.Fragements.EditFragment;
import com.example.dbexample.Fragements.HomeFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigation = findViewById(R.id.bottomNavigation);
        replaceFragment(new HomeFragment());
        bottomNavigation.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                if(item.getItemId() == R.id.item1){
                    replaceFragment(new HomeFragment());
                    return  true;
                }
                else if (item.getItemId() == R.id.item2) {
                    replaceFragment(new EditFragment());
                    return  true;
                }
                else{
                    replaceFragment(new DeleteFragment());
                    return  true;
                }
            }
        });
}
    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        // Detach the current fragment if it exists
        Fragment currentFragment = fragmentManager.findFragmentById(R.id.frame);
        if (currentFragment != null) {
            fragmentTransaction.detach(currentFragment);
        }
        // Replace the fragment
        fragmentTransaction.replace(R.id.frame, fragment);
        fragmentTransaction.commit();
    }

}