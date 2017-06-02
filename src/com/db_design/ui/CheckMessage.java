package com.db_design.ui;

import com.db_design.listener.MenuListener;
import com.db_design.utils.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by root on 17-1-7.
 */
public class CheckMessage implements ActionListener {
    private Main main;
    private JLabel nameLabel, phoneNumberLabel, nameLabel1, phoneNumberLabel1,
            registerL, registerL1, authorityL, authoityL1;
    private JPanel panel, outPanel;
    private Box b1, b2, b3, b4, b5;

    public CheckMessage() {
    }

    public CheckMessage(Main main) {
        this.main = main;
    }



    @Override
    public void actionPerformed(ActionEvent e) {
        panel = (JPanel) main.getContentPane();
        outPanel = new ImgPanel(MenuListener.path + "a1.jpg");
        panel.add(outPanel);


        nameLabel = new JLabel("用户名");
        nameLabel1 = new JLabel(MenuListener.user.getVipNo().toString());
        phoneNumberLabel = new JLabel("手机号");
        phoneNumberLabel1 = new JLabel(String.valueOf(MenuListener.user.getPhoneNumber()));
        registerL = new JLabel("注册时间");
        registerL1 = new JLabel(String.valueOf(MenuListener.user.getRegisterDate()));
        authorityL = new JLabel("用户类型");
        authoityL1 = new JLabel("" + MenuListener.user.getAuthority());

        b1 = Box.createHorizontalBox();
        b1.add(nameLabel);
        b1.add(Box.createHorizontalStrut(20));
        b1.add(nameLabel1);

        b2 = Box.createHorizontalBox();
        b2.add(phoneNumberLabel);
        b2.add(Box.createHorizontalStrut(20));
        b2.add(phoneNumberLabel1);

        b4 = Box.createHorizontalBox();
        b4.add(registerL);
        b4.add(Box.createHorizontalStrut(20));
        b4.add(registerL1);

        b5 = Box.createHorizontalBox();
        b5.add(authorityL);
        b5.add(Box.createHorizontalStrut(20));
        b5.add(authoityL1);

        b3 = Box.createVerticalBox();
        b3.add(Box.createVerticalStrut(130));
        b3.add(b1);
        b3.add(Box.createVerticalStrut(20));
        b3.add(b2);
        b3.add(Box.createVerticalStrut(20));
        b3.add(b4);
        b3.add(Box.createVerticalStrut(20));
        b3.add(b5);

        MenuListener.listener.remove(panel);
        outPanel.add(b3);
        panel.add(outPanel);
        MenuListener.listener.updateUI(panel);
    }
}
