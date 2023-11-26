package com.example.agriculture;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

public class GovSchemesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gov_schemes);

        TextView ltv1 = findViewById(R.id.sch1_tv);
        TextView ltv2 = findViewById(R.id.sch2_tv);
        TextView ltv3 = findViewById(R.id.sch3_tv);
        TextView ltv4 = findViewById(R.id.sch4_tv);
        TextView ltv5 = findViewById(R.id.sch5_tv);
        TextView ltv6 = findViewById(R.id.sch6_tv);
        TextView ltv7 = findViewById(R.id.sch7_tv);
        TextView ltv8 = findViewById(R.id.sch8_tv);
        TextView ltv9 = findViewById(R.id.sch9_tv);

        ltv1.setMovementMethod(LinkMovementMethod.getInstance());
        ltv1.setLinkTextColor(Color.BLUE);

        ltv2.setMovementMethod(LinkMovementMethod.getInstance());
        ltv2.setLinkTextColor(Color.BLUE);

        ltv3.setMovementMethod(LinkMovementMethod.getInstance());
        ltv3.setLinkTextColor(Color.BLUE);

        ltv4.setMovementMethod(LinkMovementMethod.getInstance());
        ltv4.setLinkTextColor(Color.BLUE);

        ltv5.setMovementMethod(LinkMovementMethod.getInstance());
        ltv5.setLinkTextColor(Color.BLUE);

        ltv6.setMovementMethod(LinkMovementMethod.getInstance());
        ltv6.setLinkTextColor(Color.BLUE);

        ltv7.setMovementMethod(LinkMovementMethod.getInstance());
        ltv7.setLinkTextColor(Color.BLUE);

        ltv8.setMovementMethod(LinkMovementMethod.getInstance());
        ltv8.setLinkTextColor(Color.BLUE);

        ltv9.setMovementMethod(LinkMovementMethod.getInstance());
        ltv9.setLinkTextColor(Color.BLUE);
    }
}