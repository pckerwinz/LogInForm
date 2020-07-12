package com.example.loginform.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import com.example.loginform.R;
import com.example.loginform.adapter.AdapterShowResult;
import com.example.loginform.model.SampleModel;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {
    RecyclerView id_recycle;
    ArrayList <SampleModel> mSampleModel;
    AdapterShowResult adapterShowResult;
    SQLiteDatabase mydatabase;
    private static final String TAG = "HomePage";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        id_recycle = findViewById(R.id.id_recycle);
        id_recycle.setHasFixedSize(true);
        id_recycle.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mSampleModel = new ArrayList<>();
        mydatabase = openOrCreateDatabase("DbInfo",MODE_PRIVATE,null);
        getUser();
    }


    public void getUser() {
        Cursor resultSet = mydatabase.rawQuery("Select * from tblInfo", null);

        SampleModel model = new SampleModel();
        if (resultSet.moveToNext()) {
            while (!resultSet.isAfterLast()) {
                String name = resultSet.getString(resultSet.getColumnIndex("Username"));
                String pass = resultSet.getString(resultSet.getColumnIndex("Password"));
                String nickName = resultSet.getString(resultSet.getColumnIndex("Nickname"));
                model.setUsername(name);
                model.setNickname(nickName);
                model.setPassword(pass);
                mSampleModel.add(model);
                resultSet.moveToNext();
            }
        }

        adapterShowResult = new AdapterShowResult(getApplicationContext(),mSampleModel);

        id_recycle.setAdapter(adapterShowResult);


    }

}