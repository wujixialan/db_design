package com.db_design.ui;

import com.db_design.listener.MenuListener;
import com.db_design.model.User;
import com.db_design.utils.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;

/**
 * Created by root on 17-1-24.
 */
public class RegisterVip implements ActionListener {
    private Main main;
    private JLabel nameLabel, phoneNumberLabel, passwordLabel, authorityLabel;
    private JTextField nameTF, phoneNumberTF;
    private JPasswordField passwordPF;
    private JPanel panel = null, outPanel = null;
    private JComboBox<String> authority;
    private JButton cancelButton, registerButton;
    private Box b1, b2, b3, b4, b5, b6;

    public RegisterVip() {
    }

    public RegisterVip(Main main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        outPanel = (JPanel) main.getContentPane();
        panel = new ImgPanel(MenuListener.path + "/a1.jpg");
        nameLabel = new JLabel("用户名");
        nameTF = new JTextField(20);
        nameTF.setPreferredSize(new Dimension(20, 25));
        phoneNumberLabel = new JLabel("手机号");
        phoneNumberTF = new JTextField(20);
        phoneNumberTF.setPreferredSize(new Dimension(20, 25));
        passwordLabel = new JLabel(" 密 码 ");
        passwordPF = new JPasswordField(20);
        passwordPF.setPreferredSize(new Dimension(20, 25));
        authorityLabel = new JLabel(" 类 型 ");
        authority = new JComboBox<>();
        authority.addItem("请选择");
        authority.addItem("会员");
        authority.addItem("管理员");

        cancelButton = new JButton("取消");
        registerButton = new JButton("注册");

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuListener.listener.remove(panel);
                MenuListener.listener.updateUI(panel);
            }
        });

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String authorit = (String) authority.getSelectedItem();
                if (nameTF.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "工号不能空！");
                    return;
                }

                if (passwordPF.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(null, "密码不能空！");
                    return;
                }

                if (phoneNumberTF.getText().trim().length() == 0) {
                    JOptionPane.showMessageDialog(null, "手机号不能空！");
                    return;
                }

                if (phoneNumberTF.getText().trim().length() != 11) {
                    JOptionPane.showMessageDialog(null, "手机号必须是 11 位！");
                    return;
                }

                if (authorit.equals("请选择")) {
                    JOptionPane.showMessageDialog(null, "用户类型不能不选！");
                    return;
                }
                User user = new User();
                user.setVipNo(nameTF.getText().trim());
                user.setPassword(String.valueOf(passwordPF.getPassword()));
                user.setPhoneNumber(phoneNumberTF.getText().trim());
                user.setAuthority(authorit);
                user.setRegisterDate(new Date(new java.util.Date().getTime()));
                boolean flag = MenuListener.userService.registerUser(user);
                if (flag) {
                    JOptionPane.showMessageDialog(panel, "注册成功！");
                    MenuListener.listener.remove(outPanel);
                    panel = new ImgPanel("D:\\Document\\Java\\db_design\\src\\icon/a1.jpg");
                    outPanel.add(panel);
                    MenuListener.listener.updateUI(outPanel);
                    return;
                }
            }
        });

        b1 = Box.createHorizontalBox();
        b1.add(nameLabel);
        b1.add(Box.createHorizontalStrut(15));
        b1.add(nameTF);

        b2 = Box.createHorizontalBox();
        b2.add(passwordLabel);
        b2.add(Box.createHorizontalStrut(15));
        b2.add(passwordPF);

        b3 = Box.createHorizontalBox();
        b3.add(phoneNumberLabel);
        b3.add(Box.createHorizontalStrut(15));
        b3.add(phoneNumberTF);

        b4 = Box.createHorizontalBox();
        b4.add(authorityLabel);
        b4.add(Box.createHorizontalStrut(15));
        b4.add(authority);

        b5 = Box.createHorizontalBox();
        b5.add(cancelButton);
        b5.add(Box.createHorizontalStrut(35));
        b5.add(registerButton);

        b6 = Box.createVerticalBox();
        b6.add(Box.createVerticalStrut(130));
        b6.add(b1);
        b6.add(Box.createVerticalStrut(15));
        b6.add(b2);
        b6.add(Box.createVerticalStrut(15));
        b6.add(b3);
        b6.add(Box.createVerticalStrut(15));
        b6.add(b4);
        b6.add(Box.createVerticalStrut(15));
        b6.add(b5);
        MenuListener.listener.remove(outPanel);
        panel.add(b6);
        outPanel.add(panel);
        MenuListener.listener.updateUI(outPanel);
    }
}
