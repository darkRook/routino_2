package com.example.stationx.routino.views;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stationx.routino.MainActivity;
import com.example.stationx.routino.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CreateAccount extends AppCompatActivity {

    private static final String TAG = "CreateAccount";
    TextView emailAddress, PassWord, CpassWord;
    Button signup;
    String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private FirebaseAuth mAuth;
    private View x ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        try{
            this.init();

            this.signup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = emailAddress.getText().toString();
                    String password = PassWord.getText().toString();
                    String cPassword = CpassWord.getText().toString();

                    if(!email.matches(emailPattern) || email.length() <=0){
                        Toast.makeText(getApplication(),"Incorrect Email Address !",Toast.LENGTH_LONG).show();
                    }else if(!password.equals(cPassword) || password.length() <=0){
                        Toast.makeText(getApplication(),"Incorrect Password !",Toast.LENGTH_LONG).show();
                    }else{
                        mAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(CreateAccount.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        Toast.makeText(CreateAccount.this, "createUserWithEmail:onComplete:" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                                        //progressBar.setVisibility(View.GONE);
                                        // If sign in fails, display a message to the user. If sign in succeeds
                                        // the auth state listener will be notified and logic to handle the
                                        // signed in user can be handled in the listener.
                                        if (!task.isSuccessful()) {
                                            Toast.makeText(CreateAccount.this, "Authentication failed." + task.getException(),
                                                    Toast.LENGTH_SHORT).show();
                                        } else {
                                            updateUI(mAuth.getCurrentUser());
                                        }
                                    }
                                });
                    }

                }
            });


        }catch (Exception e){

        }

    }

    public void init(){
        this.emailAddress = (TextView) findViewById(R.id.email_edit_text);
        this.PassWord = (TextView) findViewById(R.id.password_edit_text);
        this.CpassWord = (TextView) findViewById(R.id.c_password_edit_text);
        mAuth = FirebaseAuth.getInstance();
        this.signup = (Button) findViewById(R.id.sign_up_button);

    }

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        updateUI(currentUser);
    }

    private void updateUI(FirebaseUser currentUser) {
        if(currentUser != null){
            startActivity(new Intent(CreateAccount.this, Console.class));
            finish();
        }
    }



}
