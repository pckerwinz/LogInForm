package com.example.loginform.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.loginform.R;

public class MainActivity extends AppCompatActivity {
Button btn_register,btn_login ;

EditText id_username,id_password;
    SQLiteDatabase mydatabase;
    private static final String TAG = "DB";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mydatabase = openOrCreateDatabase("DbInfo",MODE_PRIVATE,null);
        mydatabase.execSQL("CREATE TABLE IF NOT EXISTS tblInfo(Username VARCHAR,Password VARCHAR,Nickname VARCHAR);");

        Cursor resultSet = mydatabase.rawQuery("Select * from tblInfo",null);

        Log.e(TAG, "onCreate:  " + resultSet.getCount() );

        btn_register = findViewById(R.id.btn_register);
        btn_login = findViewById(R.id.btn_login);
        id_username = findViewById(R.id.id_username);
        id_password = findViewById(R.id.id_password);


        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, SignUp.class));
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = id_username.getText().toString();
                String pass = id_password.getText().toString();

                Cursor resultSet = mydatabase.rawQuery("Select * from tblInfo",null);
                resultSet.moveToLast();

                if(resultSet.getCount() != 0){
                    String username = resultSet.getString(0);
                    String password = resultSet.getString(1);
                    String con = resultSet.getString(2);

                    if (username != null){
                        if(user.equals(username) && pass.equals(password) ){
                            Toast.makeText(getApplicationContext(),"connected",Toast.LENGTH_LONG).show();
                            startActivity(new Intent(MainActivity.this, HomePage.class));
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"Not in Data",Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Register First",Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Register First no data",Toast.LENGTH_LONG).show();

                }





            }
        });
    }


}