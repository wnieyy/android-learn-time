package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class OrderHistoryDetail extends AppCompatActivity {

    TextView orderNo, orderDate, orderPrice, orderStatus;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_history_detail);

        toolbar = findViewById(R.id.toolbar_orderHistory);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Butiran Sejarah Pesanan");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //getSupportActionBar().setDisplayShowHomeEnabled(true);

        orderNo = findViewById(R.id.orderNoDetail);
        orderDate = findViewById(R.id.datesDetail);
        orderPrice = findViewById(R.id.pricesDetail);
        orderStatus = findViewById(R.id.statusDetail);

        Intent intent = getIntent();

        int orderNoHistory = intent.getIntExtra("id", 0);
        String dateHistory = intent.getStringExtra("date");
        Double priceHistory = intent.getDoubleExtra("price", 0.00);
        Boolean statusHistory = intent.getBooleanExtra("status", true);

        orderNo.setText(String.valueOf(orderNoHistory));
        orderDate.setText(dateHistory);
        orderPrice.setText(String.valueOf(priceHistory));
        orderStatus.setText(String.valueOf(statusHistory));

    }

//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        // function to the back button
//        if (id == android.R.id.home) {
//            //wont exit the app
//            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//            startActivity(intent);
//            finish();
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}