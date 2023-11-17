package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import adapter.MenuRecyclerViewAdapter;

public class MainActivity extends AppCompatActivity {
    Button btn_logout;
    FirebaseAuth auth;
    FirebaseUser user;
    TextView textview;
    LinearLayoutManager linearLayoutManager;
    GridLayoutManager gridLayoutManager;
    RecyclerView recyclerView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = findViewById(R.id.toolbar_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Menu");

        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();
        textview = findViewById(R.id.tv_signUpTitle);
        recyclerView = findViewById(R.id.recycler_view);
        //linearLayoutManager = new LinearLayoutManager(MainActivity.this, RecyclerView.HORIZONTAL, false);
        linearLayoutManager = new LinearLayoutManager(MainActivity.this);
        recyclerView.setLayoutManager(linearLayoutManager);

        if (user == null) {
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();

        }

        List<Menu> allMenuInfo = getAllMenuInfo();
        MenuRecyclerViewAdapter menuRecyclerViewAdapter = new MenuRecyclerViewAdapter(MainActivity.this, allMenuInfo);
        recyclerView.setAdapter(menuRecyclerViewAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {
            Toast.makeText(this, "Profile was clicked!", Toast.LENGTH_SHORT).show();
            replaceFragment(new ProfileFragment()); //fragment
            Intent intent = new Intent(getApplicationContext(),TabLayoutFragment.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.order) {
            Toast.makeText(this, "Order was clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(getApplicationContext(),ParseJsonFile.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.logout) {

            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getApplicationContext(), Login.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.cart) {
            Toast.makeText(this, "Cart was clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PushNotification.class);
            startActivity(intent);
            finish();

        } else if (id == R.id.notification) {
            Toast.makeText(this, "Notification was clicked!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, OkHttpExample.class);
            startActivity(intent);
            finish();

        }

        return super.onOptionsItemSelected(item);
    }

    private void replaceFragment(ProfileFragment orderFragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.framelayout, orderFragment);
        fragmentTransaction.commit();
        textview.setText(user.getEmail());
    }

    private List<Menu> getAllMenuInfo() {

        List<Menu> allMenu = new ArrayList<Menu>();

        allMenu.add(new Menu("Kopi Kampung", R.drawable.kopi));
        allMenu.add(new Menu("Teh Tarik", R.drawable.tehtarik));
        allMenu.add(new Menu("Roti Bakar", R.drawable.rotibakar));
        allMenu.add(new Menu("Nasi Lemak", R.drawable.sikmak));
        allMenu.add(new Menu("Telur Separuh Masak", R.drawable.telurseparuhmasak));
        allMenu.add(new Menu("Pisang Goreng", R.drawable.wengpisang));
        allMenu.add(new Menu("Sate Ayam", R.drawable.sate));

        return allMenu;

    }
}