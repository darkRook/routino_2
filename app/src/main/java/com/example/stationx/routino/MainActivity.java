package com.example.stationx.routino;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.stationx.routino.views.Console;
import com.example.stationx.routino.views.CreateAccount;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button createaccount, signupButton;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{

            this.init();

            this.createaccount.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, CreateAccount.class));
                    finish();
                }
            });



        }
        catch (Exception e){
            Log.i("MainActivity" ,e.toString() );
            Toast.makeText(this,e.toString(),Toast.LENGTH_LONG).show();
        }

    }

    public void init(){
        this.createaccount = (Button) findViewById(R.id.create_account_button);
        this.mAuth = FirebaseAuth.getInstance();

    }

    @Override
    protected void onStart() {
        super.onStart();
        updateUI(mAuth.getCurrentUser());
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null){
            startActivity(new Intent(MainActivity.this, Console.class));
            finish();
        }
    }
}
