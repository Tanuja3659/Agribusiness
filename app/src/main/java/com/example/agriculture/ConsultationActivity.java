package com.example.agriculture;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ConsultationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultation);

        TextView linkTextView1 = findViewById(R.id.L1);
        TextView linkTextView2 = findViewById(R.id.L2);
        TextView linkTextView3 = findViewById(R.id.L3);
        TextView linkTextView4 = findViewById(R.id.L4);

        linkTextView1.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView1.setLinkTextColor(Color.BLUE);

        linkTextView2.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView2.setLinkTextColor(Color.BLUE);

        linkTextView3.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView3.setLinkTextColor(Color.BLUE);

        linkTextView4.setMovementMethod(LinkMovementMethod.getInstance());
        linkTextView4.setLinkTextColor(Color.BLUE);

        Button b = findViewById(R.id.gov_sch);

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), GovSchemesActivity.class);
                startActivity(intent);
            }
        });


    }
}