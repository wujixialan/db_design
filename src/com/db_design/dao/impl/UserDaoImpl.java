package com.db_design.dao.impl;

import com.db_design.dao.UserDao;
import com.db_design.listener.MenuListener;
import com.db_design.model.User;
import com.db_design.utils.DBUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17-1-20.
 */
public class UserDaoImpl implements UserDao {
    private Connection connection = null;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    @Override
    public boolean findByVipNo(String vipNo, String password, String authority) {
        try {
            String sql = "select * from user " +
                    "where vip_no = '" + vipNo
                    + "' and password = '" + password
                    + "' and authority = '" + authority + "'";
            System.out.println(sql);
            connection = DBUtils.getConnection();
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                MenuListener.user.setVipNo(rs.getString("vip_no"));
                MenuListener.user.setPassword(rs.getString("password"));
                MenuListener.user.setRegisterDate(rs.getDate("register_date"));
                MenuListener.user.setAuthority(rs.getString("authority"));
                MenuListener.user.setPhoneNumber(rs.getString("phone_number"));
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                DBUtils.close(rs);
            }

            if (ps != null) {
                DBUtils.close(ps);
            }

            if (connection != null) {
                DBUtils.close(connection);
            }
        }
        return false;
    }

    @Override
    public boolean registerUser(User user) {
        try {
            connection = DBUtils.getConnection();
            String sql = "insert into user" +
                    "(vip_no, phone_number, password, register_date, authority) values(" + "'"
                    + user.getVipNo() + "', "
                    + "'" + user.getPhoneNumber() + "', "
                    + "'" + user.getPassword() + "', "
                    + "'" + user.getRegisterDate() + "', "
                    + "'" + user.getAuthority() + "')";
            ps = DBUtils.getPs(connection, sql);
            Integer flag = ps.executeUpdate();
            if (flag != 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                DBUtils.close(rs);
            }

            if (ps != null) {
                DBUtils.close(ps);
            }

            if (connection != null) {
                DBUtils.close(connection);
            }
        }
        return false;
    }

    @Override
    public boolean modifyPwd(String vipNo, String newPwd) {
        try {
            connection = DBUtils.getConnection();
            String sql = "update user set password = '" + newPwd + "' where vip_no = " + vipNo;
            ps = DBUtils.getPs(connection, sql);
            int flag = ps.executeUpdate();
            if (flag == 0) {
                return false;
            } else {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                DBUtils.close(rs);
            }

            if (ps != null) {
                DBUtils.close(ps);
            }

            if (connection != null) {
                DBUtils.close(connection);
            }
        }
        return false;
    }

    @Override
    public User getByName(User user) {
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from user where vip_no = '" + user.getVipNo() + "' and password = '" + user.getPassword() +"'";
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                user.setVipNo(rs.getString("vip_no"));
                user.setPassword(rs.getString("password"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setRegisterDate(rs.getDate("register_date"));
                user.setAuthority(rs.getString("authority"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return user;
    }

    @Override
    public void modify(User user) {
        try {
            connection = DBUtils.getConnection();
            String sql = "update user set phone_number = " + user.getPhoneNumber()
                    + " where vip_no = " + user.getVipNo();
            ps = DBUtils.getPs(connection, sql);
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
    }

    @Override
    public boolean getVip(String vipNo) {
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from user where vip_no = " + vipNo + " and authority = '会员'";
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            if(rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return false;
    }

    @Override
    public List<User> findVipUser() {
        List<User> users = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from user where authority = '会员'";
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setVipNo(rs.getString("vip_no"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setRegisterDate(rs.getDate("register_date"));
                user.setAuthority(rs.getString("authority"));

                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return users;
    }

    @Override
    public List<User> findByVip(String vipNo) {
        List<User> users = new ArrayList<>();
        try {
            connection = DBUtils.getConnection();
            String sql = "select * from user where vip_no = '" + vipNo + "' and authority = '会员'";
            ps = DBUtils.getPs(connection, sql);
            rs = ps.executeQuery();
            while(rs.next()) {
                User user = new User();
                user.setVipNo(rs.getString("vip_no"));
                user.setPhoneNumber(rs.getString("phone_number"));
                user.setRegisterDate(rs.getDate("register_date"));
                user.setAuthority(rs.getString("authority"));

                users.add(user);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return users;
    }

    @Override
    public boolean updateVipUser(User user) {
        boolean f = false;
        try {
            connection = DBUtils.getConnection();
            String sql = "update user set ";

            if(user.getPassword().length() != 0) {
                sql = sql + "password = '" + user.getPassword() + "'";
                f = true;
            }

            if(user.getPhoneNumber().length() != 0) {
                if(f) {
                    sql = sql + ", ";
                }
                sql = sql + " phone_number = '" + user.getPhoneNumber() + "'";
            }

            sql = sql + " where vip_no = '" + user.getVipNo() +  "' and authority = '会员'";
            ps = DBUtils.getPs(connection, sql);
            System.out.println(sql);
            int flag = ps.executeUpdate();
            if(flag != 0) {
                return true;
            } else {
                return false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if(rs != null) {
                DBUtils.close(rs);
            }
            if(ps != null) {
                DBUtils.close(ps);
            }
            if(connection != null) {
                DBUtils.close(connection);
            }
        }
        return false;
    }
}
