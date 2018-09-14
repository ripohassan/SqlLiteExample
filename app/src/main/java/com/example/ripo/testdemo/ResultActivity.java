package com.example.ripo.testdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ResultActivity extends AppCompatActivity {

    String NameHolder ;
    TextView tvName;
    Button LogOUT ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvName = (TextView)findViewById(R.id.tv_name);
        LogOUT = (Button)findViewById(R.id.button1);

        Intent intent = getIntent();

        // Receiving User Name Send By MainActivity.
        NameHolder = intent.getStringExtra(MainActivity.UserName);

        // Setting up received Name to TextView.
        tvName.setText(tvName.getText().toString()+NameHolder);

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current ResultActivity on button click.
                finish();

                Toast.makeText(ResultActivity.this,"Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });

    }
}