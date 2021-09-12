package com.hngy.zp.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.hngy.zp.notepad.tabel.MyLog;

import org.litepal.LitePal;

import java.util.List;

public class MyOperation extends AppCompatActivity {
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_operation);
        recyclerView = findViewById(R.id.rectangles);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<MyLog> all = LitePal.where("account_zp=?", UserName.userName).find(MyLog.class);
        MyAdapter1 myAdapter1 = new MyAdapter1(all);
        recyclerView.setAdapter(myAdapter1);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(MyOperation.this,MainActivity.class));
    }
}