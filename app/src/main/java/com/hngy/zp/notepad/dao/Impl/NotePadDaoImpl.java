package com.hngy.zp.notepad.dao.Impl;

import com.hngy.zp.notepad.dao.NotePadDao;
import com.hngy.zp.notepad.entity.NotePad;
import com.hngy.zp.notepad.util.DBUtil_NotePad;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NotePadDaoImpl implements NotePadDao {
    Thread thread = new Thread();
    Connection connection=null;
    String sql = "";
    int i = 0;

    private void getConnection() {
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!thread.isInterrupted()) {
                    connection = DBUtil_NotePad.getConnection();
                    if (connection != null) {
                        i = 0;
                        thread.interrupt();
                    }
                }
            }
        });
        thread.start();
    }

    @Override
    public int addNotePad(NotePad notePad) {
        getConnection();
        String date_zp = notePad.getDate_zp();
        String tittle_zp = notePad.getTittle_zp();
        String content_zp = notePad.getContent_zp();
        int isSelected = notePad.getIsSelected();
        int user_id_zp = notePad.getUser_id_zp();
        sql = "insert into T_notepad_zp values(null,?,?,?,?,?,null)";
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!thread.isInterrupted()) {
                    try {
                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        preparedStatement.setString(1, date_zp);
                        preparedStatement.setString(2, tittle_zp);
                        preparedStatement.setString(3, content_zp);
                        preparedStatement.setString(4, String.valueOf(isSelected));
                        preparedStatement.setString(5, String.valueOf(user_id_zp));
                        i = preparedStatement.executeUpdate();
                        if (i > 0) {
                            DBUtil_NotePad.ClossAll(connection,preparedStatement,null);
                            thread.interrupt();
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }

                }
            }
        });
        thread.start();
        return i;
    }

}
