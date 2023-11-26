package com.example.agriculture;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    private EditText usernameEt,passwordEt;
    private TextView forgotTv,noAcoountTv;
    private Button loginBtn;
    FirebaseDatabase database=FirebaseDatabase.getInstance();
    DatabaseReference ref=database.getReferenceFromUrl("https://agriculture-262cc-default-rtdb.firebaseio.com/");
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEt=findViewById(R.id.usernameEt);
        passwordEt=findViewById(R.id.passwordEt);
       // forgotTv=findViewById(R.id.forgotTv);
        noAcoountTv=findViewById(R.id.noAcoountTv);
        loginBtn=findViewById(R.id.loginBtn);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);


        noAcoountTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this, Register.class));

            }
        });
        /*forgotTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Login.this,ForgotPasswordActivity.class));


            }
        });*/
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String password=passwordEt.getText().toString().trim();
                String username=usernameEt.getText().toString().trim();
                //String userType=usernameEt.getText().toString().trim();


                if(password.isEmpty()||username.isEmpty()){
                    Toast.makeText(Login.this,"Please enter Username or Password...",Toast.LENGTH_SHORT).show();
                }
                else {
                    progressDialog.setMessage("Logging In...");
                    progressDialog.show();
                    ref.child("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if(snapshot.hasChild(username))
                            {
                                final String  getPassword=snapshot.child(username).child("password").getValue(String.class);

                                if(getPassword.equals(password)) {
                                    progressDialog.dismiss();
                                    if(snapshot.child(username).child("userType").getValue(String.class).equals("Farmer")){
                                        Intent intent = new Intent(Login.this, NavFarm.class);
                                        intent.putExtra("username", username);
                                        startActivity(intent);
                                        finish();
                                        //startActivity(new Intent(Login.this,NavFarm.class));
                                    }
                                    else {
                                        startActivity(new Intent(Login.this, NavCustomer.class));
                                    }

                                    Toast.makeText(Login.this,"Successfully Logged in....",Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else {
                                    progressDialog.dismiss();
                                    Toast.makeText(Login.this,"Wrong Password",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else {
                                progressDialog.dismiss();
                                Toast.makeText(Login.this,"Invalid login",Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }

            }
        });
    }
}