package com.example.agriculture;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase mDatabase;
    DatabaseReference reference;
    AlertDialog.Builder builder;
    DatabaseReference mRef;
    FirebaseStorage mStorage;
    // ImageButton imageButton;
    ImageView prod_image;
    EditText upiid, pPrice;
    Button btnInsert;

    private ImageButton backbtn;

    private EditText vegetableName, descriptionEt;
    private TextView category, quantity;


    private static final int Gallery_code = 1;
    private static final int CAMERA_REQUEST_CODE = 200;
    private static final int IMAGE_PICK_CAMERA_CODE = 500;
    private String[] cameraPermission;

    Uri imageUrl = null;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FirebaseApp.initializeApp(this);


        ActionBar actionBar = getSupportActionBar();

        // providing title for the ActionBar
        //actionBar.setTitle("Agribusiness");


        setContentView(R.layout.activity_main);


        prod_image = findViewById(R.id.prod_image);
        backbtn = findViewById(R.id.backbtn);
        upiid = findViewById(R.id.upiid);
        pPrice = findViewById(R.id.vegetablePrice);
        vegetableName = findViewById(R.id.vegetableName);
        descriptionEt = findViewById(R.id.descriptionEt);
        quantity = findViewById(R.id.quantity);
        //pupi=findViewById(R.id.upi);
        btnInsert = findViewById(R.id.AddVegetable);



        builder = new AlertDialog.Builder(this);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = FirebaseDatabase.getInstance().getReference().child("User");
        mStorage = FirebaseStorage.getInstance();

        progressDialog = new ProgressDialog(this);


        prod_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent, Gallery_code);
            }
        });

        backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,NavFarm.class));
            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == Gallery_code && resultCode == RESULT_OK) {
            imageUrl = data.getData();
            prod_image.setImageURI(imageUrl);

        }

        btnInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pu = upiid.getText().toString();
                String pp = pPrice.getText().toString();
                String pvn = vegetableName.getText().toString();
                String pd = descriptionEt.getText().toString();
                String pq = quantity.getText().toString();


                //String pu=pupi.getText().toString();
                if(imageUrl==null)
                {
                    Toast.makeText(MainActivity.this, "Image is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pvn.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Title is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pq)) {
                    Toast.makeText(MainActivity.this, "Quantity is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pd)) {
                    Toast.makeText(MainActivity.this, "Description is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pp)) {
                    Toast.makeText(MainActivity.this, "Price is required", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pu)) {
                    Toast.makeText(MainActivity.this, "UPI ID is required", Toast.LENGTH_SHORT).show();
                    return;
                }



                if (!pu.isEmpty() && !pp.isEmpty() && imageUrl != null && !pvn.isEmpty() && !pd.isEmpty() && !pq.isEmpty()) {
                    progressDialog.setTitle("uploading.......");
                    progressDialog.show();


                    StorageReference filePath = mStorage.getReference().child("imagePost").child(Objects.requireNonNull(imageUrl.getLastPathSegment()));
                    filePath.putFile(imageUrl).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                            Task<Uri> downloadUrl = taskSnapshot.getStorage().getDownloadUrl().addOnCompleteListener(new OnCompleteListener<Uri>() {
                                @Override
                                public void onComplete(@NonNull Task<Uri> task) {

                                    String t = task.getResult().toString();



                                    DatabaseReference newPost = mRef.push();

                                    newPost.child("UPIID").setValue(pu);
                                    newPost.child("Price").setValue(pp);
                                    newPost.child("Name").setValue(pvn);
                                    newPost.child("Quantity").setValue(pq);
                                    newPost.child("Description ").setValue(pd);
                                    //newPost.child("Category ").setValue(pc);

                                    //newPost.child("Upi").setValue(pu);
                                    newPost.child("image").setValue(task.getResult().toString());


                                    builder.setMessage("Do you want to go back ?")
                                            .setCancelable(false)
                                            .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    finish();
                                                    Toast.makeText(getApplicationContext(), "product added successfully",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            })
                                            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    //  Action for 'NO' Button
                                                    dialog.cancel();
                                                    Toast.makeText(getApplicationContext(), "",
                                                            Toast.LENGTH_SHORT).show();
                                                }
                                            });
                                    //Creating dialog box
                                    AlertDialog alert = builder.create();
                                    //Setting the title manually
                                    alert.show();


                                    progressDialog.dismiss();


                                    //Intent intent=new Intent(AddVegetables.this,RetriveDataInRecyclerView.class);
                                    //startActivity(intent);

                                    if (getSupportActionBar() != null) {
                                        getSupportActionBar().hide();
                                    }


                                }
                            });
                        }
                    });


                }


            }
        });


    }
}