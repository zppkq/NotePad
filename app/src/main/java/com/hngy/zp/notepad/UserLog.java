package com.hngy.zp.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.FocusFinder;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hngy.zp.notepad.tabel.User;

import org.litepal.LitePal;

import java.util.List;

public class UserLog extends AppCompatActivity {
    EditText userPassword, userAccount;
    CheckBox checked;
    boolean isChecked;
    String account, password;
    TextView cancel;
    int bl = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_log);
        init();
        Intent intent = getIntent();
        String account = intent.getStringExtra("account");
        String password = intent.getStringExtra("password");
        userAccount.setText(account);
        userPassword.setText(password);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
            }
        });
        List<User> users = LitePal.where("isCheck_zp=?", String.valueOf(3)).find(User.class);
        if (users.size() > 0) {
            User user = users.get(0);
            userAccount.setText(user.getUserName_zp());
            userPassword.setText(user.getUserPassword_zp());
            checked.setChecked(true);
        }
        checked.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ContentValues contentValues = new ContentValues();
                isChecked = checked.isChecked();
                if (isChecked) {
                    bl = 3;
                } else {
                    bl = 2;
                }
                String account = userAccount.getText().toString();
                String password = userPassword.getText().toString();
                Log.d("UserLog", "????????????" + isChecked + "   " + bl);
                contentValues.put("isCheck_zp", bl);
                int i = LitePal.updateAll(User.class, contentValues, "userName_zp=? and userPassword_zp=?", account, password);
                Log.d("UserLog", "????????????" + isChecked + "   " + i);
            }
        });
    }

    private void init() {
        userAccount = findViewById(R.id.et_account);
        userPassword = findViewById(R.id.et_password);
        checked = findViewById(R.id.cb_check);
        isChecked = checked.isChecked();
        cancel= findViewById(R.id.tv_cancel);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login:
                account = userAccount.getText().toString();
                password = userPassword.getText().toString();
                if ("".equals(account)) {
                    Toast.makeText(getApplication(), "????????????????????????????????????", Toast.LENGTH_SHORT).show();
                    break;
                }
                if ("".equals(password)) {
                    Toast.makeText(getApplicationContext(), "????????????????????????????????????", Toast.LENGTH_SHORT).show();

                    break;
                }
                List<User> users1 = LitePal.where("userName_zp=?", account).find(User.class);
                List<User> users2 = LitePal.where("userName_zp=? and userPassword_zp=?", account, password).find(User.class);
                if (users2.size() == 0) {
                    if (users1.size() > 0) {
                        Toast.makeText(getApplicationContext(), "?????????????????????????????????", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "????????????????????????????????????????????????", Toast.LENGTH_SHORT).show();
                    }
                }
                if (users2.size() > 0) {
                    Toast.makeText(getApplicationContext(), "????????????", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(UserLog.this, MainActivity.class);
                    intent.putExtra("account", account);
                    intent.putExtra("password", password);
                    UserName.userName = account;
                    UserName.setUserName(account);
                    startActivity(intent);
                    finish();
                }
                isChecked = checked.isChecked();
                break;
            case R.id.tv_reg:
                startActivity(new Intent(UserLog.this, UserReg.class));
                finish();
                break;
            case R.id.tv_forget_password:
                startActivity(new Intent(UserLog.this, ForgetPassword.class));
                finish();
        }
    }
}