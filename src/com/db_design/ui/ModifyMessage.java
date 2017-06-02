package com.db_design.ui;

import com.db_design.listener.Listener;
import com.db_design.listener.MenuListener;
import com.db_design.model.User;
import com.db_design.service.UserService;
import com.db_design.service.impl.UserServiceImpl;
import com.db_design.utils.ImgPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.jar.JarFile;

/**
 * Created by root on 17-1-7.
 */
public class ModifyMessage implements ActionListener {
    private JLabel phoneNumberLabel;
    private JTextField phoneNumberTF;
    private JButton cancelButton, okButton;
    private UserService userService = new UserServiceImpl();
    private JPanel panel, outPanel;
    private JFrame frame;
    private Main main;
    private Box b2, b3, b4;
    private Listener listener = new Listener();

    public ModifyMessage() {
    }


    public ModifyMessage(Main main) {
        this.main = main;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        outPanel = (JPanel) main.getContentPane();
        panel = new ImgPanel(MenuListener.path + "/a1.jpg");
        outPanel.add(panel);

        User user = userService.getByName(MenuListener.user);
        phoneNumberLabel = new JLabel("手机号");
        phoneNumberTF = new JTextField(20);
        phoneNumberTF.setPreferredSize(new Dimension(20, 25));
        cancelButton = new JButton("取消");
        okButton = new JButton("修改");

        b2 = Box.createHorizontalBox();
        b2.add(phoneNumberLabel);
        b2.add(phoneNumberTF);
        b3 = Box.createHorizontalBox();
        b3.add(cancelButton);
        b3.add(Box.createHorizontalStrut(20));
        b3.add(okButton);


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
                if ("".trim().equals(phoneNumberTF.getText())) {
                    error = "手机号不能为空！";
                }
                if(phoneNumberTF.getText().length() != 11) {
                    error = "手机号必须为 11 位！";
                }

                if (!(error.trim().equals(""))) {
                    JOptionPane.showMessageDialog(null, error);
                    return;
                }
                user.setPhoneNumber(phoneNumberTF.getText());
                userService.modify(user);
                JOptionPane.showMessageDialog(panel, "修改信息成功！");
                MenuListener.listener.remove(panel);
                MenuListener.listener.updateUI(panel);
                return;
            }
        });

        b4 = Box.createVerticalBox();
        b4.add(Box.createVerticalStrut(130));
        b4.add(b2);
        b4.add(Box.createVerticalStrut(20));
        b4.add(b3);
        MenuListener.listener.remove(outPanel);
        panel.add(b4);
        outPanel.add(panel);
        MenuListener.listener.updateUI(outPanel);
    }
}
