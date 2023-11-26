package com.example.agriculture;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends AppCompatActivity {

    private ImageButton backBtn;
    private ImageView profileIv;
    private EditText nameEt,emailEt,phonedEt,countryEt,stateEt,cityEt,addressEt,passwordEt,cpasswordEt,usernameEt,userTypeEt;
    private Button registerBtn;

    private FirebaseAuth firebaseAuth;

    private FirebaseDatabase database;
    DatabaseReference ref;
    //FirebaseStorage mStorage;
   // private static final int Gallery_code=1;
   // Uri imageUrl = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backBtn=findViewById(R.id.backBtn);
        //profileIv=findViewById(R.id.profileIv);
        nameEt=findViewById(R.id.nameEt);
        phonedEt=findViewById(R.id.phonedEt);
        countryEt=findViewById(R.id.countryEt);
        stateEt=findViewById(R.id.stateEt);
        cityEt=findViewById(R.id.cityEt);
        addressEt=findViewById(R.id.addressEt);
        usernameEt=findViewById(R.id.usernameEt);
        passwordEt=findViewById(R.id.passwordEt);
        cpasswordEt=findViewById(R.id.cpasswordEt);
        registerBtn=findViewById(R.id.registerBtn);
        emailEt = findViewById(R.id.emailEt);
        userTypeEt=findViewById(R.id.userTypeEt);


        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Please Wait");
        progressDialog.setCanceledOnTouchOutside(false);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
       // mStorage=FirebaseStorage.getInstance();

       /* profileIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,Gallery_code);
            }
        });*/


        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database=FirebaseDatabase.getInstance();
                ref=database.getReferenceFromUrl("https://agriculture-262cc-default-rtdb.firebaseio.com/");
                String fullName,email,phoneNumber,city,state,county,address,password,cpassword,username,userType;


                fullName=nameEt.getText().toString().trim();
                phoneNumber=phonedEt.getText().toString().trim();
                city=cityEt.getText().toString().trim();
                state=stateEt.getText().toString().trim();
                county=countryEt.getText().toString().trim();
                address=addressEt.getText().toString().trim();
                password=passwordEt.getText().toString().trim();
                cpassword=cpasswordEt.getText().toString().trim();
                username=usernameEt.getText().toString().trim();
                email=emailEt.getText().toString().trim();
               userType=userTypeEt.getText().toString().trim();

                if(TextUtils.isEmpty(fullName)){
                    Toast.makeText(Register.this,"Enter Name...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(city)){
                    Toast.makeText(Register.this,"Enter City...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(state)){
                    Toast.makeText(Register.this,"Enter State...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(county)){
                    Toast.makeText(Register.this,"Enter Country...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(Register.this,"Enter Username...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(phoneNumber)){
                    Toast.makeText(Register.this,"Enter Phone number...",Toast.LENGTH_SHORT).show();
                }
                if(password.length()<6){
                    Toast.makeText(Register.this,"Password must be at least 6 character long...",Toast.LENGTH_SHORT).show();
                }
                if(!password.equals(cpassword)){
                    Toast.makeText(Register.this,"Password doesn't match...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(address)){
                    Toast.makeText(Register.this,"Enter Address...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(userType)){
                    Toast.makeText(Register.this,"Enter User type...",Toast.LENGTH_SHORT).show();
                }


                progressDialog.setMessage("Saving Information...");
                progressDialog.show();

                ref.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {

                        if(snapshot.hasChild(username))
                        {
                            progressDialog.dismiss();
                            Toast.makeText(Register.this,"Username is already registered",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            ref.child("Users").child(username).child("FullName").setValue(fullName);
                            ref.child("Users").child(username).child("username").setValue(username);
                            ref.child("Users").child(username).child("phoneNumber").setValue(phoneNumber);
                            ref.child("Users").child(username).child("country").setValue(county);
                            ref.child("Users").child(username).child("state").setValue(state);
                            ref.child("Users").child(username).child("city").setValue(city);
                            ref.child("Users").child(username).child("address").setValue(address);
                            ref.child("Users").child(username).child("userType").setValue(userType);
                            ref.child("Users").child(username).child("password").setValue(password);
                            progressDialog.dismiss();
                            Toast.makeText(Register.this,"User registered successfully",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Register.this,OtpActivity.class));
                            finish();


                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });


                //ref.child("User").child(username).child("timestamp").setValue(timestamp);


            }
        });



    }

   /*  @Override
   protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==Gallery_code && resultCode==RESULT_OK)
        {
            imageUrl=data.getData();
            profileIv.setImageURI(imageUrl);

        }
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                database=FirebaseDatabase.getInstance();
                ref=database.getReferenceFromUrl("https://agri-4aa9c-default-rtdb.firebaseio.com/");
                String fullName,email,phoneNumber,city,state,county,address,password,cpassword,username;

                fullName=nameEt.getText().toString().trim();
                phoneNumber=phonedEt.getText().toString().trim();
                city=cityEt.getText().toString().trim();
                state=stateEt.getText().toString().trim();
                county=countryEt.getText().toString().trim();
                address=addressEt.getText().toString().trim();
                password=passwordEt.getText().toString().trim();
                cpassword=cpasswordEt.getText().toString().trim();
                username=usernameEt.getText().toString().trim();
                email=emailEt.getText().toString().trim();

                if(TextUtils.isEmpty(fullName)){
                    Toast.makeText(Register.this,"Enter Name...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(city)){
                    Toast.makeText(Register.this,"Enter City...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(state)){
                    Toast.makeText(Register.this,"Enter State...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(county)){
                    Toast.makeText(Register.this,"Enter Country...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(Register.this,"Enter Username...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(phoneNumber)){
                    Toast.makeText(Register.this,"Enter Phone number...",Toast.LENGTH_SHORT).show();
                }
                if(password.length()<6){
                    Toast.makeText(Register.this,"Password must be at least 6 character long...",Toast.LENGTH_SHORT).show();
                }
                if(!password.equals(cpassword)){
                    Toast.makeText(Register.this,"Password doesn't match...",Toast.LENGTH_SHORT).show();
                }
                if(TextUtils.isEmpty(address)){
                    Toast.makeText(Register.this,"Enter Address...",Toast.LENGTH_SHORT).show();
                }

                if(!fullName.isEmpty()&&!city.isEmpty()&&!state.isEmpty()&&!county.isEmpty()&&!username.isEmpty()&&!phoneNumber.isEmpty()&&!password.isEmpty()&&imageUrl!=null){
                    StorageReference filepath = mStorage.getReference().child("imagePost").child(imageUrl.getLastPathSegment());
                    filepath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                                                        @Override
                                                                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                                                            Task<Uri> downloadurl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                                                                @Override
                                                                                public void onComplete(@NonNull Task<Uri> task) {
                                                                                    String t = task.getResult().toString();
                                                                                    ref.child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                                                                                        @Override
                                                                                        public void onDataChange(@NonNull DataSnapshot snapshot) {

                                                                                            if (snapshot.hasChild(username)) {
                                                                                                Toast.makeText(Register.this, "Username is already registered", Toast.LENGTH_SHORT).show();
                                                                                            } else {
                                                                                                progressDialog.setMessage("Saving Account Information...");
                                                                                                ref.child("User").child(username).child("FullName").setValue(fullName);
                                                                                                ref.child("User").child(username).child("username").setValue(username);
                                                                                                ref.child("User").child(username).child("phoneNumber").setValue(phoneNumber);
                                                                                                ref.child("User").child(username).child("country").setValue(county);
                                                                                                ref.child("User").child(username).child("state").setValue(state);
                                                                                                ref.child("User").child(username).child("city").setValue(city);
                                                                                                ref.child("User").child(username).child("address").setValue(address);
                                                                                                ref.child("User").child(username).child("password").setValue(password);
                                                                                                ref.child("User").child(username).child("image")
                                                                                                progressDialog.dismiss();
                                                                                                Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                                                                                                startActivity(new Intent(Register.this, Login.class));
                                                                                                finish();


                                                                                            }
                                                                                        }

                                                                                        @Override
                                                                                        public void onCancelled(@NonNull DatabaseError error) {

                                                                                        }

                                                                                    });



                                                                                }
                                                                            });


                                                                        }
                                                                    });




                //ref.child("User").child(username).child("timestamp").setValue(timestamp);

                }
            }
        });


    }*/
}

