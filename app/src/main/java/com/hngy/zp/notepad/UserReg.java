package com.hngy.zp.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hngy.zp.notepad.dao.Impl.UserDaoImpl;
import com.hngy.zp.notepad.tabel.User;

import org.litepal.LitePal;

import java.util.List;

public class UserReg extends AppCompatActivity {
    EditText account, password, passwordAgain, question, ans;
    TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_user_reg);
        init();
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(UserReg.this, UserLog.class));
                finish();
            }
        });

    }

    private void init() {
        account = findViewById(R.id.et_account);
        password = findViewById(R.id.et_password);
        passwordAgain = findViewById(R.id.et_password_again);
        question = findViewById(R.id.et_password_question);
        ans = findViewById(R.id.et_password_ans);
        cancel = findViewById(R.id.tv_cancel);
    }

    public void onClick(View view) {
        String account1 = account.getText().toString().trim();
        String password1 = password.getText().toString().trim();
        String passwordAgain1 = passwordAgain.getText().toString().trim();
        String question1 = question.getText().toString().trim();
        String ans1 = ans.getText().toString().trim();

        if ("".equals(account1)) {
            Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(password1)) {
            Toast.makeText(this, "密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(passwordAgain1)) {
            Toast.makeText(this, "确认密码不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(question1)) {
            Toast.makeText(this, "找回密码问题不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if ("".equals(ans1)) {
            Toast.makeText(this, "找回密码答案不能为空", Toast.LENGTH_SHORT).show();
            return;
        }
        if (!password1.equals(passwordAgain1)) {
            Toast.makeText(this, "密码和确认密码不一致", Toast.LENGTH_SHORT).show();
            return;
        }
        List<User> users = LitePal.where("userName_zp=?", account1).find(User.class);
        if (users.size() > 0) {
            Toast.makeText(this, "当前用户名已经存在", Toast.LENGTH_SHORT).show();
            return;
        }
        User user = new User();
        user.setUserName_zp(account1);
        user.setUserPassword_zp(password1);
        user.setQuestion_zp(question1);
        user.setAns_zp(ans1);
        user.setIsCheck_zp(2);
        user.save();
        com.hngy.zp.notepad.entity.User user1 = new com.hngy.zp.notepad.entity.User();
        user1.setUserName_zp(account1);
        user1.setUserPassword_zp(password1);
        user1.setQuestion_zp(question1);
        user1.setAns_zp(ans1);
        new UserDaoImpl().addUser(user1);
        Toast.makeText(this, "注册成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, UserLog.class);
        intent.putExtra("account", account1);
        intent.putExtra("password", password1);
        startActivity(intent);
        finish();
    }
}