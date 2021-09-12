package com.hngy.zp.notepad.dao.Impl;

import android.util.Log;

import com.hngy.zp.notepad.dao.UserDao;
import com.hngy.zp.notepad.entity.User;
import com.hngy.zp.notepad.util.DBUtil_NotePad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    Thread thread = new Thread();
    Connection connection = null;
    String sql = "";
    int i = 0;

    private void getConnection() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!thread.isInterrupted()) {
                    connection = DBUtil_NotePad.getConnection();
                    if (connection != null) {
                        Log.d("UserDaoImpl",""+connection);
                        thread.interrupt();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public User addUser(User user) {
        getConnection();
        String userName_zp = user.getUserName_zp();
        String userPassword_zp = user.getUserPassword_zp();
        String question_zp = user.getQuestion_zp();
        String ans_zp = user.getAns_zp();
        sql = "insert into T_user_zp values (null,?,?,?,?)";
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!thread.isInterrupted()) {
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, userName_zp);
                        preparedStatement.setString(2, userPassword_zp);
                        preparedStatement.setString(3, question_zp);
                        preparedStatement.setString(4, ans_zp);
                        int i = preparedStatement.executeUpdate();
                        if (i > 0) {
                            DBUtil_NotePad.ClossAll(connection,preparedStatement,null);
                            thread.interrupt();
                        }
                    } catch (Exception throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        });
        thread.start();
        return null;
    }

    @Override
    public User select(User user) {
        return null;
    }

    @Override
    public User forget(User user) {
        return null;
    }

    @Override
    public User passwordNew(User user) {
        return null;
    }

    @Override
    public User oldPwdChangeNewPwd(User user) {
        return null;
    }

    @Override
    public int addQuestion(User user) {
        return 0;
    }

    @Override
    public int selectUserName(String userName_zp) {
        return 0;
    }

    @Override
    public int selectUserId(String userName_zp) {
        getConnection();
        sql = "select * from T_user_zp where userName_zp=?";
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!thread.isInterrupted()) {
                    try {
                        Log.d("UserDaoImpl",""+connection);
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, userName_zp);
                        ResultSet resultSet = preparedStatement.executeQuery();
                       if (resultSet.next()) {
                            i = resultSet.getInt("id_zp");
                            Log.d("UserDaoImpl",""+i);
                        }
                        DBUtil_NotePad.ClossAll(connection,preparedStatement,resultSet);
                        thread.interrupt();
                    } catch (Exception throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        });
        thread.start();
        return i;
    }
}
