package com.example.loginform.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginform.R;
import com.example.loginform.model.SampleModel;

public class SignUp extends AppCompatActivity {
    Button btn_register;
    SQLiteDatabase mydatabase;
    EditText id_username,id_password,id_con,id_nick;
    TextView tv_check;
    SampleModel sample;
    private static final String TAG = "SignUp";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        mydatabase = openOrCreateDatabase("DbInfo",MODE_PRIVATE,null);
        btn_register = findViewById(R.id.button2);
        id_username = findViewById(R.id.editTextTextPersonName);
        id_password = findViewById(R.id.editTextTextPersonName2);
        id_con = findViewById(R.id.editTextTextPersonName3);
        id_nick = findViewById(R.id.editTextTextPersonName4);
        tv_check = findViewById(R.id.tv_check);
        sample = new SampleModel();



        id_con.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!id_con.getText().toString().equals(id_password.getText().toString()))
                {
                    tv_check.setText("Passwords do not match");
                    tv_check.setVisibility(View.VISIBLE);
                }else{
                    tv_check.setVisibility(View.GONE);
                }
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!id_con.getText().toString().equals(id_password.getText().toString()))
                {
                    tv_check.setText("Passwords do not match");
                    tv_check.setVisibility(View.VISIBLE);
                }else{
                    tv_check.setVisibility(View.GONE);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!id_con.getText().toString().equals(id_password.getText().toString()))
                {
                    tv_check.setText("Passwords do not match");
                    tv_check.setVisibility(View.VISIBLE);
                }else{
                    tv_check.setVisibility(View.GONE);
                }
            }
        });

        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = id_username.getText().toString();
                String pass = id_password.getText().toString();
                String nickname = id_nick.getText().toString();

                sample.setUsername(user);
                sample.setPassword(pass);
                sample.setNickname(nickname);

            mydatabase.execSQL("INSERT INTO tblInfo VALUES('"+sample.getUsername()+"','"+sample.getPassword()+"','"+sample.getNickname()+"')");
                Cursor resultSet = mydatabase.rawQuery("Select * from tblInfo",null);
                resultSet.moveToLast();
                if(resultSet.getCount() != 0){
                    Toast.makeText(getApplicationContext(),"Data Registered",Toast.LENGTH_LONG).show();
                    startActivity(new Intent(SignUp.this, MainActivity.class));
                }


            }
        });


    }
}