package com.example.agriculture;

import static com.example.agriculture.R.id.increaseBtn;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import androidx.appcompat.app.AppCompatActivity;

public class PayActivity extends AppCompatActivity {

    EditText nameEt, upiEt, amountEt, quantityEt;
    Button send, increaseBtn, decreaseBtn;

    final int UPI_PAYMENT = 0;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);

        send = findViewById(R.id.send);
        nameEt = findViewById(R.id.p_name);
        quantityEt = findViewById(R.id.quantity);
        amountEt = findViewById(R.id.amount_et);
        upiEt = findViewById(R.id.upi_id);
        increaseBtn = findViewById(R.id.increaseBtn);
        decreaseBtn = findViewById(R.id.decreaseBtn);

        // Retrieve data from intent
        String data1 = getIntent().getStringExtra("upi");
        String data2 = getIntent().getStringExtra("price");
        String data3 = getIntent().getStringExtra("product_name");
        String data4 = getIntent().getStringExtra("quantity");

        // Set values to EditTexts
        upiEt.setText(data1);
        nameEt.setText(data3);

        // Initialize quantityEt with the value from the intent
        quantityEt.setText(data4);

        // Calculate initial price
        updatePrice();

        // Add TextWatcher to quantityEt to dynamically update price
        quantityEt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed in this case
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // Not needed in this case
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Update price after the quantity changes
                updatePrice();
            }
        });

        increaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Increase quantity by 1
                incrementQuantity(1);
            }
        });

        decreaseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Decrease quantity by 1, but not below 1
                decrementQuantity(1);
            }
        });

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get amount and upiId after updating the price
                String amount = amountEt.getText().toString();
                String upiId = upiEt.getText().toString();
                payUsingUpi(amount, upiId);
            }
        });
    }

    private void updatePrice() {
        // Get quantity and price
        String quantityStr = quantityEt.getText().toString();
        double quantity = Double.parseDouble(quantityStr);
        double price = Double.parseDouble(getIntent().getStringExtra("price"));

        // Calculate new amount and update amountEt
        double newAmount = quantity * price;
        amountEt.setText(String.valueOf(newAmount));
    }

    private void incrementQuantity(int incrementBy) {
        String quantityStr = quantityEt.getText().toString();
        int quantity = Integer.parseInt(quantityStr);
        quantity += incrementBy;
        quantityEt.setText(String.valueOf(quantity));
        updatePrice();
    }

    private void decrementQuantity(int decrementBy) {
        String quantityStr = quantityEt.getText().toString();
        int quantity = Integer.parseInt(quantityStr);
        if (quantity > decrementBy) {
            quantity -= decrementBy;
            quantityEt.setText(String.valueOf(quantity));
            updatePrice();
        } else {
            Toast.makeText(this, "Quantity cannot be less than 1", Toast.LENGTH_SHORT).show();
        }
    }

    private void payUsingUpi(String amount, String upiId) {
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", upiId)
                .appendQueryParameter("am", amount)
                .appendQueryParameter("cu", "INR")
                .build();

        Intent upiPayIntent = new Intent(Intent.ACTION_VIEW);
        upiPayIntent.setData(uri);

        Intent chooser = Intent.createChooser(upiPayIntent, "Pay With");

        if (null != chooser.resolveActivity(getPackageManager())) {
            startActivityForResult(chooser, UPI_PAYMENT);
        } else {
            Toast.makeText(PayActivity.this, "No UPI app found, please install to continue", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Handle UPI payment result
    }

    private void upiPaymentDataOperation(ArrayList<String> data) {
        // Handle UPI payment data operation
    }

    public static boolean isConnectionAvailable(Context context) {
        // Check if internet connection is available
        return false;
    }
}
