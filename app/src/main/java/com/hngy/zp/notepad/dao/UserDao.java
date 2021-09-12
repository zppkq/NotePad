package com.hngy.zp.notepad.dao;

import com.hngy.zp.notepad.entity.User;

public interface UserDao {
    public User addUser(User user);

    public User select(User user);

    public User forget(User user);

    public User passwordNew(User user);

    public User oldPwdChangeNewPwd(User user);

    public int addQuestion(User user);

    public int selectUserName(String userName_zp);

    public int selectUserId(String userName_zp);

}
