package libo.com.social.ui.Main;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.HashMap;

import libo.com.social.R;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    public static HashMap<String, Fragment> fragmentHashMap = new HashMap<>();
    public String[] tags = {"home", "online", "mall", "my"};
    private int currentPosition = 0;


    private ErrorFragment errFragment;
    public DataBindFragment dataBindFragment;
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
//                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    private RadioGroup radioGroup;
    private TextView tv_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv_title = findViewById(R.id.tv_title);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");

        setSupportActionBar(toolbar);

        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.navigation_view);
        navigationView.setNavigationItemSelectedListener(this);

        setupFragment();
        initView();
        getSupportFragmentManager().beginTransaction().add(R.id.fl_munu_container, fragmentHashMap.get(tags[0]), tags[0]).commit();

    }

    @Override
    protected void onResume() {
        super.onResume();



    }

    public void goToError()
    {
        if (errFragment == null)
        {
            errFragment = new ErrorFragment();
        }
        Fragment from = fragmentHashMap.get(tags[0]);
        fragmentHashMap.put(tags[0],errFragment);
//        if (currentPosition ==0 )
//        {
//            switchContent(from,errFragment,tags[0]);
//        }

    }




    private void setupFragment() {
        HomeFragment homeFragment = new HomeFragment();
        InternetFragment internetFragment = new InternetFragment();
        MallFragment mallFragment = new MallFragment();
        MyFragment myFragment = new MyFragment();

        fragmentHashMap.put(tags[0], homeFragment);
        fragmentHashMap.put(tags[1], internetFragment);
        fragmentHashMap.put(tags[2], mallFragment);
        fragmentHashMap.put(tags[3], myFragment);
    }

    private void initView() {
        radioGroup = findViewById(R.id.rg);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                Fragment from = fragmentHashMap.get(tags[currentPosition]);
                switch (checkedId) {
                    case R.id.rb_1:
                        currentPosition = 0;
                        break;
                    case R.id.rb_2:
                        currentPosition = 1;
                        break;
                    case R.id.rb_3:
                        currentPosition = 2;
                        break;
                    case R.id.rb_4:
                        currentPosition = 3;
                        break;
                }
                Fragment targetFragment = fragmentHashMap.get(tags[currentPosition]);
                switchContent(from, targetFragment, tags[currentPosition]);
                tv_title.setText(tags[currentPosition]);
            }
        });
        setRadioChekedByPosition(0);
    }


    private void switchContent(Fragment from, Fragment targetFragment, String tag) {
        if (from == targetFragment) {
            return;
        }
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        if (from != null && targetFragment != null) {
            fragmentTransaction.hide(from);
            if (!targetFragment.isAdded()) {
                fragmentTransaction.add(R.id.fl_munu_container, targetFragment, tag);
            } else {
                    fragmentTransaction.show(targetFragment);
            }
        }
        fragmentTransaction.commitAllowingStateLoss();


    }
    public void setRadioChekedByPosition(int position) {
        if (position == 0) {
            ((RadioButton) radioGroup.getChildAt(0)).setChecked(true);
        } else if (position == 1) {
            ((RadioButton) radioGroup.getChildAt(1)).setChecked(true);
        } else if (position == 2) {
            ((RadioButton) radioGroup.getChildAt(2)).setChecked(true);
        } else if (position == 3) {
            ((RadioButton) radioGroup.getChildAt(3)).setChecked(true);
        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id==R.id.navigation_home){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
