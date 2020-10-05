package com.example.pchomeapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;

import com.example.pchomeapp.fragment.BuyHistory;
import com.example.pchomeapp.fragment.Homepage;
import com.example.pchomeapp.fragment.Mine;
import com.example.pchomeapp.fragment.ShoppingCart;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    private Homepage fragment_homepage;
    private BuyHistory fragment_buyHistory;
    private Mine fragment_mine;
    private ShoppingCart fragment_shoppingCart;
    private Fragment[] fragment_list;
    private int lastFragment;//用于记录上个选择的Fragment

    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFragment();
    }


    private void initFragment() {
        fragment_homepage = new Homepage();
        fragment_buyHistory = new BuyHistory();
        fragment_mine = new Mine();
        fragment_shoppingCart = new ShoppingCart();
        fragment_list = new Fragment[]{fragment_homepage, fragment_buyHistory, fragment_mine, fragment_shoppingCart};
        lastFragment = 0;
        getSupportFragmentManager().beginTransaction().replace(R.id.main_fragment, fragment_homepage).show(fragment_homepage).commit();
        bottomNavigationView = findViewById(R.id.nav_view);
        bottomNavigationView.setOnNavigationItemSelectedListener(changeFragment);

    }

    private BottomNavigationView.OnNavigationItemSelectedListener changeFragment = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            switch (menuItem.getItemId()) {

                case R.id.navigation_homepage: {
                    if (lastFragment != 0) {
                        switchFragment(lastFragment, 0);
                        lastFragment = 0;
                    }
                    return true;
                }

                case R.id.navigation_buyHistory: {
                    if (lastFragment != 1) {
                        switchFragment(lastFragment, 1);
                        lastFragment = 1;
                    }
                    return true;
                }

                case R.id.navigation_mine: {
                    if (lastFragment != 2) {
                        switchFragment(lastFragment, 2);
                        lastFragment = 2;
                    }
                    return true;
                }

                case R.id.navigation_shoppingCart: {
                    if (lastFragment != 3) {
                        switchFragment(lastFragment, 3);
                        lastFragment = 3;
                    }
                    return true;
                }

            } //switch

            return false;
        }
    };

    //切换fragment
    private void switchFragment(int lastFragment, int index) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.hide(fragment_list[lastFragment]); //隐藏上一个fragment
        if (fragment_list[index].isAdded() == false) {
            transaction.add(R.id.main_fragment, fragment_list[index]);
        }
        transaction.show(fragment_list[index]).commitAllowingStateLoss();
    }

}