package com.example.gaming;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class Home_Bottom extends AppCompatActivity {
private boolean Loadfragment(Fragment fragment)
{
    if(fragment!=null)
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.frame,fragment).commit();
    return true;
    }
    return false;
}
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        Fragment fragment=null;
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {

                    case R.id.live:
                        fragment = new Live_frag();
                        break;
                    case R.id.upcoming:
                        fragment = new upcoming_frag();

                        break;
                    case R.id.completed:
                        fragment = new Completed_frag();
                        break;
                    case R.id.profile:
                        fragment = new Profile_frag();
                        break;

                }


        FragmentTransaction fragmentTransaction=getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.frame,new Live_frag());
        fragmentTransaction.commit();
        return Loadfragment(fragment);
    }
};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__bottom);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
Loadfragment(new Live_frag());
    }

}
