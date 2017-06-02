package com.db_design.dao;

import com.db_design.model.User;

import java.util.List;

/**
 * Created by root on 17-1-20.
 */
public interface UserDao {
    boolean findByVipNo(String vipNo, String password, String authority);

    boolean registerUser(User user);

    boolean modifyPwd(String vipNo, String newPwd);

    User getByName(User user);

    void modify(User user);

    boolean getVip(String vipNo);

    List<User> findVipUser();

    List<User> findByVip(String vipNo);

    boolean updateVipUser(User user);
}
