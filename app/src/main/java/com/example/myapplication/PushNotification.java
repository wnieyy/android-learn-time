package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

//sent text message in background
public class PushNotification extends AppCompatActivity {

    EditText etToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_push_notification);

        Toolbar toolbarPushNoti = (Toolbar) findViewById(R.id.toolbar_pushnoti);
        setSupportActionBar(toolbarPushNoti);
        getSupportActionBar().setTitle("Push Notification Example");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        etToken = findViewById(R.id.et_token);

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            System.out.println("Fetching FCM registration token failed");
                            return;
                        }

                        // Get new FCM registration token
                        String token = task.getResult();

                        // Log and toast
                        System.out.println(token);
                        Toast.makeText(PushNotification.this, "Your device registration token is " + token, Toast.LENGTH_SHORT).show();
                        Log.i("PushNotification", "token: " + token);
                        etToken.setText(token);
                    }
                });
    }

    public void onBackPressed() {

        Toast.makeText(this,"back button pressed", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        if(item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}