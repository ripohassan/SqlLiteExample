package com.example.ripo.testdemo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.LoginFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final String UserName = "";
    public static final String UserEmail = "";
    Button LogInButton;
    TextInputEditText etEmail,etName,etNumber ;
    String NameHolder, EmailHolder, NumberHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    DBHelper sqLiteHelper;
    Cursor cursor;
    Boolean EditTextEmptyHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LogInButton = (Button)findViewById(R.id.buttonLogin);
        etName = (TextInputEditText)findViewById(R.id.editTextName);
        etEmail = (TextInputEditText) findViewById(R.id.editTextEmail);
        etNumber = (TextInputEditText) findViewById(R.id.editTextNumber);

        sqLiteHelper = new DBHelper(this);

        //Adding click listener to log in button.
        LogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Calling EditText is empty or no method.
                CheckEditTextStatus();

                // Calling login method.
                LoginFunction();


            }
        });



    }

   // Login function starts from here.
    public void LoginFunction(){

        if(EditTextEmptyHolder) {

            // Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            // Adding search name query to cursor.
           cursor = sqLiteDatabaseObj.query(DBHelper.TABLE_NAME, null, " " + DBHelper.Table_Column_1_Name + "=?", new String[]{NameHolder}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {
                    cursor.moveToFirst();
                    // Closing cursor.
                    cursor.close();
                }
            }

            // Calling method to check final result ..
            CheckFinalResult();

        }
        else {

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(MainActivity.this,"Please Enter UserName or Email and Number.",Toast.LENGTH_LONG).show();

        }

    }

    // Checking EditText is empty or not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        NameHolder = etName.getText().toString();
        EmailHolder = etEmail.getText().toString();
        NumberHolder = etNumber.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(NameHolder)||TextUtils.isEmpty(EmailHolder) || TextUtils.isEmpty(NumberHolder)){

            EditTextEmptyHolder = false ;

        }else {

            EditTextEmptyHolder = true ;
        }
    }


    public void CheckFinalResult(){

            Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

            // Going to Result activity after login success message.
            Intent intent = new Intent(MainActivity.this, ResultActivity.class);
            intent .putExtra(UserName,NameHolder);
            startActivity(intent);
        }
}