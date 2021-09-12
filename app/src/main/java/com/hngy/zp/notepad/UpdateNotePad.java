package com.hngy.zp.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hngy.zp.notepad.tabel.MyLog;
import com.hngy.zp.notepad.tabel.NotePad;

import org.litepal.LitePal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class UpdateNotePad extends AppCompatActivity {
    TextView tvDate;
    EditText etTittle, etContent;
    String content1 = "";
    String aid = "";
    String TAG = "UpdateNotePad";
    String tittle;
    String date;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.log:
                startActivity(new Intent(UpdateNotePad.this, MyOperation.class));
                finish();
                break;
        }
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_note_pad);
        setEtContent();
        Intent intent = getIntent();
        tittle = intent.getStringExtra("tittle");
        date = intent.getStringExtra("date");
        aid = intent.getStringExtra("id");
        Log.d("UpdateNotePad", aid);
        tvDate.setText(date);
        etTittle.setText(tittle);
        List<NotePad> notePads = LitePal.where("id=?", aid).find(NotePad.class);
        etContent.setText(notePads.get(0).getContent_zp());
    }

    public void setEtContent() {
        tvDate = findViewById(R.id.tv_date);
        etContent = findViewById(R.id.et_content);
        etTittle = findViewById(R.id.et_tittle);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                if (etTittle.getText().toString().trim().equals("")) {
                    Toast.makeText(UpdateNotePad.this, "标题不能为空", Toast.LENGTH_SHORT).show();
                    break;
                }
                String date = tvDate.getText().toString();
                String tittle = etTittle.getText().toString();
                String content = etContent.getText().toString();
                ContentValues contentValues = new ContentValues();
                contentValues.put("tittle_zp", tittle);
                contentValues.put("content_zp", content);
                Log.d("UpdateNotePad", aid + "a");
                LitePal.updateAll(NotePad.class, contentValues, "id=?", aid);
                MyLog myLog = new MyLog();
                myLog.setTittle_zp(tittle);
                myLog.setDate_zp(date);
                myLog.setOperationTime(new SimpleDateFormat().format(new Date(System.currentTimeMillis())));
                myLog.setOperation("修改");
                myLog.setAccount_zp(UserName.userName);
                myLog.save();
                Toast.makeText(UpdateNotePad.this, "保存成功", Toast.LENGTH_SHORT).show();
                break;
            case R.id.btn_return:
                startActivity(new Intent(UpdateNotePad.this, MainActivity.class));
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
            case R.id.tv_share:
                allShare();
                break;
            case R.id.btn_share:
                allShare();
                break;
            case R.id.tv_delete:
                Log.d(TAG, "1");
                delete();
                break;
            case R.id.btn_delete:
                delete();
                break;
        }
    }

    private void delete() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("此笔记将从本地、云空间、及所有同步设备删除。是否删除");
        dialog.setCancelable(true);
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyLog myLog = new MyLog();
                myLog.setTittle_zp(tittle);
                myLog.setDate_zp(date);
                myLog.setOperationTime(new SimpleDateFormat().format(new Date(System.currentTimeMillis())));
                myLog.setOperation("删除");
                myLog.setAccount_zp(UserName.userName);
                myLog.save();
                int deleteRow = LitePal.delete(NotePad.class, Long.parseLong(aid));
                if (deleteRow > 0) {
                    Toast.makeText(UpdateNotePad.this, "删除成功", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(UpdateNotePad.this, MainActivity.class));
                    finish();
                }
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        //记得让对话框显示
        dialog.show();
        Handler handler=new Handler(){};
        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        
                    }
                });
            }
        });
    }

    public void allShare(){
        Intent share_intent = new Intent();
        share_intent.setAction(Intent.ACTION_SEND);//设置分享行为
        share_intent.setType("text/plain");//设置分享内容的类型
        share_intent.putExtra(Intent.EXTRA_SUBJECT, etTittle.getText().toString().trim());//添加分享内容标题
        share_intent.putExtra(Intent.EXTRA_TEXT, etContent.getText().toString().trim());//添加分享内容
        //创建分享的Dialo
        share_intent = Intent.createChooser(share_intent, "日记");
        startActivity(share_intent);
    }
}