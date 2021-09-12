package com.hngy.zp.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hngy.zp.notepad.dao.Impl.NotePadDaoImpl;
import com.hngy.zp.notepad.dao.Impl.UserDaoImpl;
import com.hngy.zp.notepad.tabel.MyLog;
import com.hngy.zp.notepad.tabel.NotePad;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;

public class AddNotePad extends AppCompatActivity {
    TextView tvDate;
    EditText etTittle, etContent;
    String content1 = "";
    String userName="";
    String TAG="AddNotePad";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_pad);
        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        userName=account;
//        this.requestWindowFeature(Window.FEATURE_NO_TITLE);//隐藏标题栏
//        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN); //隐藏状态栏
        setEtContent();
        tvDate.setText(new SimpleDateFormat().format(new Date(System.currentTimeMillis())));
    }

    public void setEtContent() {
        tvDate = findViewById(R.id.tv_date);
        etContent = findViewById(R.id.et_content);
        etTittle = findViewById(R.id.et_tittle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                if (etTittle.getText().toString().trim().equals("")) {
                    Toast.makeText(AddNotePad.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                String date = tvDate.getText().toString();
                String tittle = etTittle.getText().toString();
                String content = etContent.getText().toString();
                NotePad notePad = new NotePad();
                notePad.setContent_zp(content);
                notePad.setDate_zp(date);
                notePad.setTittle_zp(tittle);
                notePad.setIsSelected(2);
                notePad.setAccount_zp(userName);
                notePad.save();
                MyLog myLog = new MyLog();
                myLog.setTittle_zp(tittle);
                myLog.setDate_zp(date);
                myLog.setOperation("添加");
                myLog.setAccount_zp(userName);
                myLog.setOperationTime(new SimpleDateFormat().format(new Date(System.currentTimeMillis())));
                myLog.save();
                com.hngy.zp.notepad.entity.NotePad notePad1 = new com.hngy.zp.notepad.entity.NotePad();
                notePad1.setDate_zp(date);
                notePad1.setTittle_zp(tittle);
                notePad1.setIsSelected(2);
                notePad1.setContent_zp(content);
                Log.d(TAG,""+UserName.getUserName());
                int userId = new UserDaoImpl().selectUserId(UserName.getUserName());
                Log.d(TAG,""+userId);
                notePad1.setUser_id_zp(userId);
//                new NotePadDaoImpl().addNotePad(notePad1);
                Toast.makeText(AddNotePad.this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_return:
                startActivity(new Intent(AddNotePad.this, MainActivity.class));
                finish();
                break;
            case R.id.btn_cx:
                content1 = etContent.getText().toString().trim();
                Log.d("AddNotePad", content1);
                etContent.setText("");
                break;
            case R.id.btn_hf:
                Log.d("AddNotePad", content1);
                etContent.setText(content1);
                break;
        }
    }
}