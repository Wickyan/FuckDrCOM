package com.example.fuckdrcom;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private Button login_btn, logout_btn;
    private TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button login_btn = (Button) findViewById(R.id.button1);
        Button logout_btn = (Button) findViewById(R.id.button2);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,ConFig.class));

            }
        });
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Uri uri = Uri.parse("http://202.119.207.100:8080/Self");    //设置跳转的网站
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            Toast.makeText(MainActivity.this,"nav_gallery",Toast.LENGTH_SHORT).show();
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            Toast.makeText(MainActivity.this,"nav_gallery",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(MainActivity.this,"nav_slideshow",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_manage) {
            Toast.makeText(MainActivity.this,"nav_manage",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_share) {
            Toast.makeText(MainActivity.this,"nav_share",Toast.LENGTH_SHORT).show();
        } else if (id == R.id.nav_privacy) {
            Toast.makeText(MainActivity.this,"nav_privacy",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this,PrivacyStatement.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void login(View v){
        SharedPreferences sPreferences=getSharedPreferences("config", MODE_PRIVATE);
        String username=sPreferences.getString("username", "");
        String password =sPreferences.getString("password", "");
        String net =sPreferences.getString("net", "");
        String ip =sPreferences.getString("ip", "");


        Toast.makeText(MainActivity.this,ip + username + password +  net,Toast.LENGTH_SHORT).show();

        String ss = "http://10.2.5.251:801/eportal/?c=Portal&a=login&callback=dr1556283953377&login_method=1&user_account="
                + username +"%40" + net +"&user_password=" + password +"&wlan_user_ip=" + ip
                + "&wlan_user_mac=AE1D0D5D2AC8&wlan_ac_ip=&wlan_ac_name=BRAS&jsVersion=3.0&_=1556283905606\n";
        Uri uri = Uri.parse(ss);    //设置跳转的网站

        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
    }
    public void logout(View v){
        //      Toast.makeText(MainActivity.this,"asd",Toast.LENGTH_SHORT).show();

        SharedPreferences sPreferences=getSharedPreferences("config", MODE_PRIVATE);
        String ip =sPreferences.getString("ip", "");

        String ss = "http://10.2.5.251:801/eportal/?c=Portal&a=logout&callback=dr1556284127322&login_method=1&user_account=drcom&user_password=123&ac_logout=0&wlan_user_ip="
                + ip +
                "&wlan_user_ipv6=&wlan_vlan_id=0&wlan_user_mac=F48E38EF321F&wlan_ac_ip=&wlan_ac_name=BRAS&jsVersion=3.0&_=1556284124928";
        Uri uri = Uri.parse(ss);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);

    }
}
