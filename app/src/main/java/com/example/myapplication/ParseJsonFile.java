package com.example.myapplication;

import static java.security.AccessController.getContext;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import adapter.CustomAdapter;

public class ParseJsonFile extends AppCompatActivity {

    // ArrayList for person names, email Id's and mobile numbers
    ArrayList<Integer> orderNos = new ArrayList<>();
    ArrayList<String> dates = new ArrayList<>();
    ArrayList<Double> prices = new ArrayList<>();
    ArrayList<Boolean> orderStatus = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parse_json_file);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_parseJSON);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Sejarah Pesanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // get the reference of RecyclerView
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        // set a LinearLayoutManager with default vertical orientation
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        //LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(),  RecyclerView.HORIZONTAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(loadJSONFromAsset());
            // fetch JSONArray named users
            JSONArray userArray = obj.getJSONArray("users");
            // implement for loop for getting users list data
            for (int i = 0; i < userArray.length(); i++) {
                // create a JSONObject for fetching single user data
                JSONObject userDetail = userArray.getJSONObject(i);
                // create a object for getting contact data from JSONObject
                JSONObject order = userDetail.getJSONObject("order");
                // fetch and store in arraylist
                orderNos.add(order.getInt("no"));
                dates.add(order.getString("date"));
                prices.add(order.getDouble("price"));
                orderStatus.add(order.getBoolean("completed"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //  call the constructor of CustomAdapter to send the reference and data to Adapter
        CustomAdapter customAdapter = new CustomAdapter(ParseJsonFile.this, orderNos, dates, prices, orderStatus);
        recyclerView.setAdapter(customAdapter); // set the Adapter to RecyclerView
    }

    public String loadJSONFromAsset() {
        String json = null;
        try {
            InputStream is = getAssets().open("users_list.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }

    public void onBackPressed() {

        Toast.makeText(ParseJsonFile.this,"back button pressed", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(ParseJsonFile.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        // function to the back button
        if (id == android.R.id.home) {
            //wont exit the app
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}