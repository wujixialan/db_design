package com.db_design.model;

import java.sql.Date;

/**
 * Created by root on 17-1-6.
 */
public class User {
//    用户 id
    private String vipNo;
//    手机号
    private String phoneNumber;
//    用户密码
    private String password;
//    注册时间
    private Date registerDate;
//    权限控制, 依赖于权限表
    private String authority;

    public User(String name, String password) {
        this.password = password;
    }

    public String getVipNo() {
        return vipNo;
    }

    public void setVipNo(String vipNo) {
        this.vipNo = vipNo;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(Date registerDate) {
        this.registerDate = registerDate;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public User(String vipNo, String phoneNumber, String password, Date registerDate, String authority) {
        this.vipNo = vipNo;
        this.phoneNumber = phoneNumber;
        this.password = password;
        this.registerDate = registerDate;
        this.authority = authority;
    }

    public User() {

    }

    @Override
    public String toString() {
        return "UserService{" +
                "vipNo=" + vipNo +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", password='" + password + '\'' +
                ", registerDate=" + registerDate +
                ", authority=" + authority +
                '}';
    }
}
