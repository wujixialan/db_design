package com.db_design.ui;

import com.db_design.listener.MenuListener;
import com.db_design.model.User;
import com.db_design.service.UserService;
import com.db_design.service.impl.UserServiceImpl;
import com.db_design.utils.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 17-1-7.
 */
public class ModifyPwd implements ActionListener {
    private JLabel oldPwdLabel, newPwdLabel, newTwoPwdLabel;
    private JPasswordField oldPwd, newPwd, newTwoPwd;
    private JButton cancelButton, okButton;
    private UserService userService = new UserServiceImpl();
    private JPanel panel, outPanel;
    private Box b1, b2, b3, b4, b5;
    private Main main;


    public ModifyPwd() {
    }

    public ModifyPwd(Main main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        outPanel = (JPanel) main.getContentPane();
        panel = new ImgPanel(MenuListener.path + "/a1.jpg");
        outPanel.add(panel);

        oldPwdLabel = new JLabel("   旧  密  码   ");
        newPwdLabel = new JLabel("   新  密  码   ");
        newTwoPwdLabel = new JLabel("重新输入密码");


        oldPwd = new JPasswordField(20);
        oldPwd.setPreferredSize(new Dimension(20, 25));
        newPwd = new JPasswordField(20);
        newPwd.setPreferredSize(new Dimension(20, 25));
        newTwoPwd = new JPasswordField(20);
        newTwoPwd.setPreferredSize(new Dimension(20, 25));

        cancelButton = new JButton("取消");
        okButton = new JButton("修改");

        b1 = Box.createHorizontalBox();
        b1.add(oldPwdLabel);
        b1.add(oldPwd);
        b2 = Box.createHorizontalBox();
        b2.add(newPwdLabel);
        b2.add(newPwd);
        b3 = Box.createHorizontalBox();
        b3.add(cancelButton);
        b3.add(Box.createHorizontalStrut(20));
        b3.add(okButton);
        b5 = Box.createHorizontalBox();
        b5.add(newTwoPwdLabel);
        b5.add(newTwoPwd);

        b4 = Box.createVerticalBox();
        b4.add(Box.createVerticalStrut(130));
        b4.add(b1);
        b4.add(Box.createVerticalStrut(20));
        b4.add(b2);
        b4.add(Box.createVerticalStrut(20));
        b4.add(b5);
        b4.add(Box.createVerticalStrut(20));
        b4.add(b3);

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MenuListener.listener.remove(panel);
                MenuListener.listener.updateUI(panel);
            }
        });

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String error = "";
                if(oldPwd.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(panel, "原密码不能为空！");
                    return;
                }
                if(newPwd.getPassword().length == 0 || newTwoPwd.getPassword().length == 0) {
                    JOptionPane.showMessageDialog(panel, "新密码不能为空！");
                    return;
                }
                if(String.valueOf(oldPwd.getPassword()).equals(MenuListener.user.getPassword())) {
                    if(!(String.valueOf(newPwd.getPassword()).equals(String.valueOf(newTwoPwd.getPassword())))) {
                        error = "两次输入的密码不一致, 请重新输入";
                    }
                    if(String.valueOf(oldPwd.getPassword()).equals(String.valueOf(newPwd.getPassword()))) {
                        error = "原密码与新密码相同，不能修改";
                    }
                } else if(!(String.valueOf(oldPwd.getPassword()).equals(MenuListener.user.getPassword()))) {
                    error = "原密码输入错误";
                }

                if(!(error.equals(""))) {
                    JOptionPane.showMessageDialog(null, error);
                    return;
                }

                boolean flag = userService.modifyPwd(MenuListener.user.getVipNo(), String.valueOf(newPwd.getPassword()));
                if (flag) {
                    JOptionPane.showMessageDialog(null, "修改密码成功");
                    MenuListener.listener.remove(panel);
                    MenuListener.listener.updateUI(panel);
                    return;
                } else {
                    JOptionPane.showMessageDialog(null, "修改密码失败");
                    return;
                }
            }
        });

        MenuListener.listener.remove(outPanel);
        panel.add(b4);
        outPanel.add(panel);
        MenuListener.listener.updateUI(outPanel);
    }
}
