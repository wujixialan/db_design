package com.db_design.service.impl;

import com.db_design.dao.UserDao;
import com.db_design.dao.impl.UserDaoImpl;
import com.db_design.model.User;
import com.db_design.service.UserService;

import java.util.List;

/**
 * Created by root on 17-1-20.
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();

    @Override
    public boolean findByVipNo(String vipNo, String password, String authority) {
        return userDao.findByVipNo(vipNo, password, authority);
    }

    @Override
    public boolean registerUser(User user) {
        return userDao.registerUser(user);
    }

    @Override
    public boolean modifyPwd(String vipNo, String newPwd) {
        return userDao.modifyPwd(vipNo, newPwd);
    }

    @Override
    public User getByName(User user) {
        return userDao.getByName(user);
    }

    @Override
    public void modify(User user) {
        userDao.modify(user);
    }

    @Override
    public boolean getVip(String vipNo) {
        return userDao.getVip(vipNo);
    }

    @Override
    public List<User> findVipUser() {
        return userDao.findVipUser();
    }

    @Override
    public List<User> findByVip(String vipNo) {
        return userDao.findByVip(vipNo);
    }

    @Override
    public boolean updateVipUser(User user) {
        return userDao.updateVipUser(user);
    }
}
