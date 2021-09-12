package com.hngy.zp.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.tv.TvContentRating;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hngy.zp.notepad.tabel.MyLog;
import com.hngy.zp.notepad.tabel.NotePad;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    TextView tvSize;
    EditText etName;
    String userName;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                dialog.setTitle("此笔记将从本地、云空间、及所有同步设备删除。是否删除");
                dialog.setCancelable(true);
                dialog.setPositiveButton("删除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        List<NotePad> notePads = LitePal.where("isSelected=? and account_zp=?", "1", UserName.userName).find(NotePad.class);
                        for (NotePad notePad : notePads) {
                            String tittle_zp = notePad.getTittle_zp();
                            String date_zp = notePad.getDate_zp();
                            String operation = "删除";
                            String date = new SimpleDateFormat().format(new Date(System.currentTimeMillis()));
                            MyLog myLog = new MyLog();
                            myLog.setTittle_zp(tittle_zp);
                            myLog.setDate_zp(date_zp);
                            myLog.setOperation(operation);
                            myLog.setOperationTime(date);
                            myLog.setAccount_zp(UserName.userName);
                            myLog.save();
                        }
                        LitePal.deleteAll(NotePad.class, "isSelected=? and account_zp=?", "1", UserName.userName);
                        List<NotePad> all = LitePal.where("account_zp=?", UserName.userName).find(NotePad.class);
                        MyAdapter myAdapter = new MyAdapter(all, getApplicationContext());
                        recyclerView.setAdapter(myAdapter);
                        tvSize.setText(all.size() + "条笔记");
                        Toast.makeText(MainActivity.this, "删除成功", Toast.LENGTH_SHORT).show();
                    }
                });
                dialog.setNeutralButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                dialog.show();
                break;
            case R.id.log:
                startActivity(new Intent(MainActivity.this, MyOperation.class));
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        Log.d("MainActivity", UserName.userName);
        userName = UserName.userName;
        List<NotePad> allNotePad = LitePal.where("account_zp=?", UserName.userName).find(NotePad.class);
        Log.d("MainActivity", "查询到" + allNotePad.size());
        setEtContent();
        tvSize.setText(allNotePad.size() + "条笔记");
        MyAdapter myAdapter = new MyAdapter(allNotePad, getApplicationContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(myAdapter);
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
        etName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                String tittleName = etName.getText().toString().trim();
                tittleName = "%" + tittleName + "%";
                List<NotePad> notePads = LitePal.where("tittle_zp like ? and account_zp=?", tittleName, userName).find(NotePad.class);
                MyAdapter myAdapter1 = new MyAdapter(notePads, getApplicationContext());
                recyclerView.setAdapter(myAdapter1);
                int size = notePads.size();
                tvSize.setText(size + "条笔记");
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    public void setEtContent() {
        recyclerView = findViewById(R.id.rectangles);
        tvSize = findViewById(R.id.tv_size);
        etName = findViewById(R.id.ed_tittle_name);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.to_do:
                startActivity(new Intent(MainActivity.this, To_Do.class));
                finish();
                break;
            case R.id.add_note:
                Intent intent = new Intent(MainActivity.this, AddNotePad.class);
                intent.putExtra("account", userName);
                startActivity(intent);
                finish();
                break;
        }
    }
}